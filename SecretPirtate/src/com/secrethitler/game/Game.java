package com.secrethitler.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.jack5496.secrethitler.Main;
import com.jack5496.secrethitler.ResourceLoader;
import com.secrethitler.entitys.LocalPlayer;
import com.secrethitler.entitys.LocalPlayerHandler;
import com.secrethitler.menu.ChooseCards;
import com.secrethitler.menu.MenuHandler;
import com.secrethitler.multiplayer.Multiplayer;

public class Game {

	public LocalPlayer hitler;
	List<LocalPlayer> fasists;
	List<LocalPlayer> liberals;

	public List<LocalPlayer> presidentOrder;

	public LocalPlayer cancelor;
	public LocalPlayer president;

	public int minPlayerStartAmount = 5;
	public int maxPlayerStartAmount = 10;

	final int fasictCardsTotal = 7;
	final int liberalCardsTotal = 11;

	List<String> boardcards;
	List<String> drawcards;
	List<String> discard;

	static Random random = new Random();

	public static int votesForCancelor = 0;
	public static int totalVotesCancelor = 0;

	public boolean running;

	public Game() {
		running = false;

		boardcards = new ArrayList<String>();
		discard = new ArrayList<String>();
		drawcards = new ArrayList<String>();
		for (int i = 0; i < fasictCardsTotal; i++) {
			drawcards.add(ResourceLoader.fasictCard);
		}
		for (int i = 0; i < liberalCardsTotal; i++) {
			drawcards.add(ResourceLoader.liberalCard);
		}
	}

	public void startGame() {
		ArrayList<LocalPlayer> toChoose = new ArrayList<LocalPlayer>(Multiplayer.activRoom.players.values());
		if (toChoose.size() >= minPlayerStartAmount) {

			List<LocalPlayer> all = new ArrayList<LocalPlayer>(toChoose);

			int totalAmount = toChoose.size();

			LocalPlayer hitler = toChoose.remove(random.nextInt(toChoose.size()));

			List<LocalPlayer> liberals = new ArrayList<LocalPlayer>();
			for (int i = 0; i < getLiberalsAmount(totalAmount); i++) {
				liberals.add(toChoose.remove(random.nextInt(toChoose.size())));
			}

			List<LocalPlayer> fasists = new ArrayList<LocalPlayer>(toChoose);

			List<LocalPlayer> presidentOrder = new ArrayList<LocalPlayer>();

			int size = all.size();
			for (int i = 0; i < size; i++) {
				LocalPlayer nextPresident = all.remove(random.nextInt(all.size()));
				presidentOrder.add(nextPresident);
			}

			Multiplayer.sendRoles(hitler, liberals, fasists, presidentOrder);
		}
	}

	public void recievePlayerRoles(LocalPlayer hitler, List<LocalPlayer> liberals, List<LocalPlayer> fasists,
			List<LocalPlayer> presidentOrder) {
		running = true;

		Main.log(getClass(), "RecievedAmount : " + presidentOrder.size());
		this.presidentOrder = new ArrayList<LocalPlayer>(presidentOrder);

		this.hitler = hitler;
		this.liberals = new ArrayList<LocalPlayer>(liberals);
		this.fasists = new ArrayList<LocalPlayer>(fasists);
		this.president = this.presidentOrder.get(0);
		Multiplayer.activRoom.rolePresident.label = "President: \n" + this.president.name;

		LocalPlayer me = LocalPlayerHandler.localPlayer;
		if (me.equals(hitler)) {
			Multiplayer.activRoom.role.label = "Hitler";
		}
		if (this.liberals.contains(me)) {
			Multiplayer.activRoom.role.label = "Liberal";
		}
		if (this.fasists.contains(me)) {
			Multiplayer.activRoom.role.label = "Fasist";
		}

		enablePresitent();
	}

	public void killPlayer(LocalPlayer player) {
		presidentOrder.remove(player);
		fasists.remove(player);
		liberals.remove(player);
		if (isPlayerHitler(player)) {
			liberalWon();
		} else {
			setNextPresident();
		}
	}

	public int getLiberalsAmount(int playerAmount) {
		return (playerAmount / 2) + 1;
	}

	public void recieveStart() {
		running = true;
	}

	public boolean isPlayerAlive(LocalPlayer p) {
		return presidentOrder.contains(p);
	}

	public void startAsPresident() {
		MenuHandler.setActivMenu(new ChooseCards(card1, card2, card3));
	}

	String card1;
	String card2;
	String card3;

	public void enableCancelor(String card1, String card2, String discardedCard) {
		this.card1 = card1;
		this.card2 = card2;
		addDiscardCard(discardedCard);
		Multiplayer.activRoom.enableCancelorButton();
	}

	public void addDiscardCard(String discardedCard) {
		discard.add(discardedCard);
	}

	public void enablePresitent() {
		if (Multiplayer.activRoom.activGame.president.equals(LocalPlayerHandler.localPlayer)) {
			presidentChoosesCancelor();
		}
	}

	public void presidentChoosesCancelor() {
		Multiplayer.activRoom.enablePresidentChooseCancelorButton();
	}

	public void startAsCancelor() {
		MenuHandler.setActivMenu(new ChooseCards(card1, card2));
	}

	public void resetDrawCards() {
		card1 = null;
		card2 = null;
		card3 = null;
	}

	public void endRound() {

	}

	public void updateCardsAndDiscards(boolean nextPresident, String cardPlayed) {
		int liberalBoard = 0;
		int fasictBoard = 0;
		int liberalCards = 0;
		int fasictCards = 0;
		int liberalDiscards = 0;
		int fasictDiscards = 0;

		for (String card : boardcards) {
			if (card.equals(ResourceLoader.fasictCard))
				fasictBoard++;
			if (card.equals(ResourceLoader.liberalCard))
				liberalBoard++;
		}
		for (String card : drawcards) {
			if (card.equals(ResourceLoader.fasictCard))
				fasictCards++;
			if (card.equals(ResourceLoader.liberalCard))
				liberalCards++;
		}
		for (String card : discard) {
			if (card.equals(ResourceLoader.fasictCard))
				fasictDiscards++;
			if (card.equals(ResourceLoader.liberalCard))
				liberalDiscards++;
		}

		String played = "none";
		if (cardPlayed != null) {
			played = cardPlayed;
		}

		Multiplayer.updateCards(nextPresident, played, liberalBoard, fasictBoard, liberalCards, fasictCards,
				liberalDiscards, fasictDiscards);
	}

	public void setCardsAndDiscards(int liberalBoard, int fasictBoard, int liberalCards, int fasictCards,
			int liberalDiscards, int fasictDiscards) {
		boardcards = new ArrayList<String>();
		for (int i = 0; i < fasictBoard; i++) {
			boardcards.add(ResourceLoader.fasictCard);
		}
		for (int i = 0; i < liberalBoard; i++) {
			boardcards.add(ResourceLoader.liberalCard);
		}

		discard = new ArrayList<String>();
		for (int i = 0; i < fasictDiscards; i++) {
			discard.add(ResourceLoader.fasictCard);
		}
		for (int i = 0; i < liberalDiscards; i++) {
			discard.add(ResourceLoader.liberalCard);
		}

		drawcards = new ArrayList<String>();
		for (int i = 0; i < fasictCards; i++) {
			drawcards.add(ResourceLoader.fasictCard);
		}
		for (int i = 0; i < liberalCards; i++) {
			drawcards.add(ResourceLoader.liberalCard);
		}

		Multiplayer.activRoom.fasictCards = fasictBoard;
		Multiplayer.activRoom.liberalCards = liberalBoard;
	}

	private void fasictWon() {

	}

	private void liberalWon() {

	}

	public void checkIfGameOver(boolean nextPresident, String playedCard) {
		boolean liberalWon = getLiberalAmountOnBoard() >= 5;
		boolean fasictWon = getFasictAmountOnBoard() >= 5;

		if (liberalWon) {
			liberalWon();
		} else if (fasictWon) {
			fasictWon();
		} else {

			if (nextPresident) {
				if (getFasictAmountOnBoard() == 4
						|| getFasictAmountOnBoard() == 5 && playedCard.equals(ResourceLoader.fasictCard)) {
					if(this.president.equals(LocalPlayerHandler.localPlayer)){
						Multiplayer.activRoom.enablePresidentKillPlayerButton();
					}
				} else {
					setNextPresident();
				}
			}
		}
	}

	private void setNextPresident() {
		LocalPlayer atBack = presidentOrder.remove(0);
		president = presidentOrder.get(0);
		presidentOrder.add(atBack);
		Multiplayer.activRoom.rolePresident.label = president.name;
		enablePresitent();
	}

	public String drawCard() {
		Main.log(getClass(), "Draw: " + getTotalAmountInDrawPile() + " | Discard: " + getTotalAmountInDiscardPile());
		if (getTotalAmountInDrawPile() == 0) {
			drawcards.addAll(discard);
			discard = new ArrayList<String>();
		}
		String card = drawcards.remove(random.nextInt(getTotalAmountInDrawPile()));

		return card;
	}

	public int getTotalAmountCards() {
		return fasictCardsTotal + liberalCardsTotal;
	}

	public int getTotalAmountInDrawPile() {
		return drawcards.size();
	}

	public int getLiberalAmountInDrawPile() {
		int amount = 0;
		for (String card : drawcards) {
			if (card.equals(ResourceLoader.liberalCard))
				amount++;
		}
		return amount;
	}

	public int getFasictAmountInDrawPile() {
		int amount = 0;
		for (String card : drawcards) {
			if (card.equals(ResourceLoader.fasictCard))
				amount++;
		}
		return amount;
	}

	public int getTotalAmountInDiscardPile() {
		return discard.size();
	}

	public int getLiberalAmountInDiscardPile() {
		int amount = 0;
		for (String card : discard) {
			if (card.equals(ResourceLoader.liberalCard))
				amount++;
		}
		return amount;
	}

	public int getFasictAmountInDiscardPile() {
		int amount = 0;
		for (String card : discard) {
			if (card.equals(ResourceLoader.fasictCard))
				amount++;
		}
		return amount;
	}

	public int getTotalAmountOnBoard() {
		return boardcards.size();
	}

	public int getLiberalAmountOnBoard() {
		int amount = 0;
		for (String card : boardcards) {
			if (card.equals(ResourceLoader.liberalCard))
				amount++;
		}
		return amount;
	}

	public int getFasictAmountOnBoard() {
		int amount = 0;
		for (String card : boardcards) {
			if (card.equals(ResourceLoader.fasictCard))
				amount++;
		}
		return amount;
	}

	public void playCard(String card) {
		if (card.equals(ResourceLoader.fasictCard)) {
			boardcards.add(ResourceLoader.fasictCard);
		}
		if (card.equals(ResourceLoader.liberalCard)) {
			boardcards.add(ResourceLoader.liberalCard);
		}
		updateCardsAndDiscards(true, card);
	}

	public boolean isPlayerHitler(LocalPlayer player) {
		return player.equals(hitler);
	}

	public boolean isHitlerCancelor() {
		return isPlayerHitler(cancelor);
	}

	public void checkIfVoteEnded() {
		if (totalVotesCancelor >= presidentOrder.size()) {
			if (votesForCancelor >= totalVotesCancelor / 2.0) {
				if (getFasictAmountOnBoard() >= 3 && isHitlerCancelor()) {
					fasictWon();
				} else {
					if (Multiplayer.activRoom.activGame.president.equals(LocalPlayerHandler.localPlayer)) {
						card1 = drawCard();
						card2 = drawCard();
						card3 = drawCard();
						updateCardsAndDiscards(false, null);
						Multiplayer.activRoom.enablePresidentButton();
					}
				}
			} else {
				Main.log(getClass(), "Wahl gescheitert");
				setNextPresident();
			}
		}
	}

}
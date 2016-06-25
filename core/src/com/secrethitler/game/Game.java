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

	public void recievePlayerRoles(LocalPlayer hitler, List<LocalPlayer> liberals, List<LocalPlayer> fasists,
			List<LocalPlayer> presidentOrder) {
		running = true;

		Main.log(getClass(), "RecievedAmount : "+presidentOrder.size());
		this.presidentOrder = new ArrayList<LocalPlayer>(presidentOrder);

		this.hitler = hitler;
		this.liberals = new ArrayList<LocalPlayer>(liberals);
		this.fasists = new ArrayList<LocalPlayer>(fasists);
		this.president = this.presidentOrder.get(0);
		Multiplayer.activRoom.rolePresident.text = this.president.name;

		LocalPlayer me = LocalPlayerHandler.localPlayer;
		if (me.equals(hitler)) {
			Multiplayer.activRoom.role.text = "Hitler";
		}
		if (this.liberals.contains(me)) {
			Multiplayer.activRoom.role.text = "Liberal";
		}
		if (this.fasists.contains(me)) {
			Multiplayer.activRoom.role.text = "Fasist";
		}

		enablePresitent();
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

	public void enableCancelor(String card1, String card2) {
		this.card1 = card1;
		this.card2 = card2;
		Multiplayer.activRoom.enableCancelorButton();
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

	public void updateCardsAndDiscards(boolean nextPresident) {
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

		Multiplayer.updateCards(nextPresident, liberalBoard, fasictBoard, liberalCards, fasictCards, liberalDiscards, fasictDiscards);
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

	public void setNextPresident() {
		Main.log(getClass(), "Alive Before: "+presidentOrder.size());
		LocalPlayer atBack = presidentOrder.remove(0);
		president = presidentOrder.get(0);
		presidentOrder.add(atBack);
		Main.log(getClass(), "Alive After: "+presidentOrder.size());
		Multiplayer.activRoom.rolePresident.text = president.name;
		enablePresitent();
	}

	public String drawCard() {
		if (drawcards.size() == 0) {
			drawcards.addAll(discard);
			discard = new ArrayList<String>();
		}
		String card = drawcards.remove(random.nextInt(drawcards.size()));

		return card;
	}

	public void playCard(String card) {
		if (card.equals(ResourceLoader.fasictCard)) {
			boardcards.add(ResourceLoader.fasictCard);
		}
		if (card.equals(ResourceLoader.liberalCard)) {
			boardcards.add(ResourceLoader.liberalCard);
		}
		updateCardsAndDiscards(true);
	}

	public void checkIfVoteEnded() {
		if(totalVotesCancelor>=presidentOrder.size()){
			if(votesForCancelor>=totalVotesCancelor/2.0){
				Main.log(getClass(), "Wahl erfolgreich");
				if (Multiplayer.activRoom.activGame.president.equals(LocalPlayerHandler.localPlayer)) {
					card1 = drawCard();
					 card2 = drawCard();
					 card3 = drawCard();
					 updateCardsAndDiscards(false);
					 Multiplayer.activRoom.enablePresidentButton();
				}
			}
			else{
				Main.log(getClass(), "Wahl gescheitert");
				setNextPresident();
			}
		}
	}

}
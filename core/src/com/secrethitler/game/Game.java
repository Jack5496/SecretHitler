package com.secrethitler.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
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
	
	List<LocalPlayer> alive;

	
	public LocalPlayer cancelor = LocalPlayerHandler.localPlayer;
	public LocalPlayer president = LocalPlayerHandler.localPlayer;

	final int fasictCardsTotal = 7;
	final int liberalCardsTotal = 11;


	List<String> boardcards;
	List<String> drawcards;
	List<String> discard;

	static Random random = new Random();

	public boolean running;

	public Game() {
		running = false;
		alive = new LinkedList<LocalPlayer>();

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
		int totalAmount = toChoose.size();
		
		LocalPlayer hitler = toChoose.remove(random.nextInt(toChoose.size()));
		
		List<LocalPlayer> liberals = new ArrayList<LocalPlayer>();
		for (int i = 0; i < getLiberalsAmount(totalAmount); i++) {
			liberals.add(toChoose.remove(random.nextInt(toChoose.size())));
		}
		
		List<LocalPlayer> fasists = new ArrayList<LocalPlayer>(toChoose);
		
		Multiplayer.sendRoles(hitler, liberals, fasists);
	}
	
	public void recievePlayerRoles(LocalPlayer hitler, List<LocalPlayer> liberals, List<LocalPlayer> fasists){
		running = true;
		
		alive = new ArrayList<LocalPlayer>();
		
		alive.add(hitler);
		this.liberals = new ArrayList<LocalPlayer>(liberals);
		this.fasists = new ArrayList<LocalPlayer>(fasists);
		
		LocalPlayer me = LocalPlayerHandler.localPlayer;
		if(me.equals(hitler)) {
			Multiplayer.activRoom.role.text ="Hitler";
		}
		if(this.liberals.contains(me)) {
			Multiplayer.activRoom.role.text ="Liberal";
		}
		if(this.fasists.contains(me)) {
			Multiplayer.activRoom.role.text ="Fasist";
		}
	}

	public int getLiberalsAmount(int playerAmount) {
		return (playerAmount / 2) + 1;
	}

	public void recieveStart() {
		running = true;
	}

	public boolean isPlayerAlive(LocalPlayer p) {
		return alive.contains(p);
	}

	public void addAlivePlayer(LocalPlayer p) {
		alive.add(p);
	}

	public void setPresident(LocalPlayer p) {
		president = p;
	}

	public void setCancelor(LocalPlayer p) {
		cancelor = p;
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
		card1 = drawCard();
		card2 = drawCard();
		card3 = drawCard();
		updateCardsAndDiscards();
		Multiplayer.activRoom.enablePresidentButton();
	}

	public void startAsCancelor() {
		MenuHandler.setActivMenu(new ChooseCards(card1, card2));
	}

	public void resetDrawCards() {
		card1 = null;
		card2 = null;
		card3 = null;
	}

	public void updateCardsAndDiscards() {
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

		Multiplayer.updateCards(liberalBoard, fasictBoard, liberalCards, fasictCards, liberalDiscards, fasictDiscards);
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
		updateCardsAndDiscards();
	}

}
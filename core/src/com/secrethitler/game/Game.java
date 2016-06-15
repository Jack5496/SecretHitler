package com.secrethitler.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.jack5496.secrethitler.Main;
import com.jack5496.secrethitler.ResourceLoader;
import com.secrethitler.entitys.LocalPlayer;
import com.secrethitler.menu.ChooseCards;
import com.secrethitler.menu.MenuHandler;
import com.secrethitler.multiplayer.Multiplayer;

public class Game {

	LocalPlayer hitler;
	LocalPlayer cancelor;
	LocalPlayer president;

	int fasictCards = 0;
	int liberalCards = 0;

	final int fasictCardsTotal = 7;
	final int liberalCardsTotal = 11;
	
	
	List<LocalPlayer> alive;
	List<String> cards;
	
	static Random random = new Random();
	
	public Game() {
		alive = new ArrayList<LocalPlayer>();
		
		cards = new ArrayList<String>();
		for(int i=0; i<fasictCardsTotal;i++){
			cards.add(ResourceLoader.fasictCard);
		}
		for(int i=0; i<liberalCardsTotal;i++){
			cards.add(ResourceLoader.liberalCard);
		}
	}

	public void addAlivePlayer(LocalPlayer p) {
		alive.add(p);
	}

	public static String chooseRegex = " ";
	
	public void startAsPresident(){
		String cardDraw = "";
		for(int i=0;i<3;i++){
			cardDraw += drawCard()+chooseRegex;
		}
		cardDraw = cardDraw.substring(0, cardDraw.length()-1);
		
		Main.log(getClass(), "drawCards: "+cardDraw);
		cardsChoosed(cardDraw);
	}
	
	public String drawCard(){
		return cards.remove(random.nextInt(cards.size()));
	}

	public void cardsChoosed(String cards) {
		String[] cardA = cards.split(chooseRegex);
		

		if (cardA != null) {
			Main.log(getClass(), "length: "+cardA.length);
			if (cardA.length >= 3) { // 3 Predisent wählt
				MenuHandler.setActivMenu(new ChooseCards(cardA[0],cardA[1],cardA[2]));
			} else if (cardA.length == 2) { // 2 Karten Cancelor wählt
				Main.log(getClass(), "cards as cancelor: "+cardA[0]+" and "+cardA[1]);
				MenuHandler.setActivMenu(new ChooseCards(cardA[0],cardA[1]));
			} else if (cardA.length == 1) { //message an alle
				playCard(cardA[0]);
			} else {
				Main.log(getClass(), "ah okay?");
			}
		}
		else{
			Main.log(getClass(), "Lol Error");
		}
	}

	public void playCard(String card) {
		Main.log(getClass(), card);
		if (card.equals(ResourceLoader.fasictCard)) {
			fasictCards++;
		} else if (card.equals(ResourceLoader.liberalCard)) {
			liberalCards++;
		} else {
			Main.log(getClass(), "ERROR");
		}

		Multiplayer.activRoom.fasictCards = fasictCards;
		Multiplayer.activRoom.liberalCards = liberalCards;
	}
}
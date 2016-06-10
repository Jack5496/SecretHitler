package com.secrethitler.lobby;

import java.util.ArrayList;
import java.util.List;

import com.jack5496.secrethitler.Main;
import com.secrethitler.entitys.LocalPlayer;

public class Lobby {

	public static final int minPlayers = 5;
	public static final int maxPlayers = 10;

	public boolean running;

	List<LocalPlayer> players;

	public Lobby() {
		initVariables();
	}
	
	public void initVariables(){
		running = false;
		players = new ArrayList<LocalPlayer>();
	}

	public int getPlayerAmount() {
		return players.size();
	}

	public void start() {
		if(!running){
			running = true;
		}
	}
	
	public void end() {
		
	}

	public boolean isPlayerInLobby(LocalPlayer player) {
		return players.contains(player);
	}

	public void join(LocalPlayer player) {
		if (!running) {
			if (getPlayerAmount() < maxPlayers) { // nur wenn du der 10. bist
				if (!isPlayerInLobby(player)) {
					Main.log(getClass(), "Spieler tritt Lobby bei");

					players.add(player);

				} else {
					Main.log(getClass(), "Spieler bereits in Lobby");
				}
			} else {
				Main.log(getClass(), "Hier muss noch ausgabe an joinenden Spieler gehen dass er nicht beitreten darf");
			}
		} else {
			Main.log(getClass(), "Spiel läuft bereits");
		}
	}

}

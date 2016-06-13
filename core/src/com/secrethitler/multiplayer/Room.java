package com.secrethitler.multiplayer;

import java.util.ArrayList;
import java.util.List;

import com.jack5496.secrethitler.Main;
import com.secrethitler.entitys.LocalPlayer;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomData;

public class Room {
	public String id;
	public String name;
	public int maxUser;
	public String ownerName;

	public static final int minPlayers = 5;
	public static final int maxPlayers = 10;

	public boolean running;

	List<LocalPlayer> players;

	public Room(String id) {
		initVariables();
		this.id = id;
	}

	public Room(RoomData data) {
		initVariables();
		roomInformationsFound(data);
	}

	public void initVariables() {
		running = false;
		players = new ArrayList<LocalPlayer>();
	}

	

	public void updateRoomInformations() {

	}

	public void roomInformationsFound(RoomData data) {
		this.id = data.getId();
		this.name = data.getName();
		this.ownerName = data.getRoomOwner();
		this.maxUser = data.getMaxUsers();
		Main.log(getClass(), "ID: " + id + " | Name: " + name + " | owner: " + ownerName + " | maxUser: " + maxUser);
	}

	public int getPlayerAmount() {
		return players.size();
	}

	public void start() {
		if (!running) {

			if (getPlayerAmount() >= minPlayers) {
				running = true;
			}
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
//					Main.log(getClass(), "Spieler tritt Lobby bei");

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
	
	public void leave(LocalPlayer p) {
		if(players.contains(p)){
			players.remove(p);
		}
	}

}

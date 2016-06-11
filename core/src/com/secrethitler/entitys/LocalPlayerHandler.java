package com.secrethitler.entitys;

import java.util.HashMap;

import com.jack5496.secrethitler.Main;

public class LocalPlayerHandler {

	HashMap<String, LocalPlayer> localPlayers;

	public LocalPlayer getPlayer(int id){
		LocalPlayer[] players = getPlayers();
		return players[id];
	}
	
	public LocalPlayer[] getPlayers(){
		LocalPlayer[] players = new LocalPlayer[localPlayers.values().size()];
		localPlayers.values().toArray(players);
		return players;
	}
	
	public int getPlayerNumber(LocalPlayer p){
		LocalPlayer[] players = getPlayers();
		for(int i=0; i<getPlayerAmount();i++){
			if(p.equals(players[i])) return i;
		}
		return -1;
	}
	
	public int getPlayerAmount(){
		return getPlayers().length;
	}
	
	public LocalPlayerHandler() {
		localPlayers = new HashMap<String, LocalPlayer>();
	}

	public LocalPlayer getPlayerByInput(String inputHandlerName) {
		boolean found = localPlayers.containsKey(inputHandlerName);

		if (!found) {
			Main.log(getClass(), "New Player found");
			LocalPlayer p = new LocalPlayer("Bob");
			localPlayers.put(inputHandlerName, p);
		}

		return localPlayers.get(inputHandlerName);
	}


}
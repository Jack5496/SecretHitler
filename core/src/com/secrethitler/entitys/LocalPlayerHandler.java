package com.secrethitler.entitys;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.jack5496.secrethitler.Main;
import com.secrethitler.multiplayer.Multiplayer;
import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;

public class LocalPlayerHandler {

	HashMap<String, LocalPlayer> localPlayers;

	public LocalPlayer getPlayer(int id) {
		LocalPlayer[] players = getPlayers();
		return players[id];
	}

	public LocalPlayer[] getPlayers() {
		LocalPlayer[] players = new LocalPlayer[localPlayers.values().size()];
		localPlayers.values().toArray(players);
		return players;
	}

	public int getPlayerNumber(LocalPlayer p) {
		LocalPlayer[] players = getPlayers();
		for (int i = 0; i < getPlayerAmount(); i++) {
			if (p.equals(players[i]))
				return i;
		}
		return -1;
	}

	public int getPlayerAmount() {
		return getPlayers().length;
	}

	public LocalPlayerHandler() {
		localPlayers = new HashMap<String, LocalPlayer>();
		if (!localPlayerExsist()) {
			openPlayerNameInput();
		}
	}

	public boolean localPlayerExsist() {
		return (Main.localPlayer != null);
	}

	public void openPlayerNameInput(byte result) {
		InputName listener = new InputName();

		String extraMessage = "Your Username";

		if (result == WarpResponseResultCode.AUTH_ERROR) {
			extraMessage = "Already taken";
		}
		if (result == WarpResponseResultCode.UNKNOWN_ERROR) {
			extraMessage = "We need a Username";
		}
		Gdx.input.getTextInput(listener, "Player Name", "", extraMessage);
	}

	public void openPlayerNameInput() {
		openPlayerNameInput(WarpResponseResultCode.SUCCESS);
	}

	public static String userNameWanted = null;

	public class InputName implements TextInputListener {
		@Override
		public void input(String text) {
			userNameWanted = text;
			Multiplayer.goOnline(text);
		}

		@Override
		public void canceled() {
			if (Main.localPlayer == null) {
				openPlayerNameInput(WarpResponseResultCode.UNKNOWN_ERROR);
			}
		}
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

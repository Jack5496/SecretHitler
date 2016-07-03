package com.secrethitler.entitys;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.jack5496.secrethitler.Main;
import com.secrethitler.multiplayer.Multiplayer;
import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;

public class LocalPlayerHandler {
	
	public static LocalPlayer localPlayer;

	public static boolean playerLoggedIn() {
		return (localPlayer != null);
	}

	public static void openPlayerNameInput(byte result) {
		InputName listener = new InputName();

		String extraMessage = "Your Username";

		if (result == WarpResponseResultCode.AUTH_ERROR) {
			extraMessage = "Already taken";
		}
		if (result == WarpResponseResultCode.UNKNOWN_ERROR) {
			extraMessage = "We need a Username";
		}
		
		Gdx.input.getTextInput(listener, extraMessage, "");
	}

	public static void openPlayerNameInput() {
		openPlayerNameInput(WarpResponseResultCode.SUCCESS);
	}

	public static String userNameWanted = null;

	public static class InputName implements TextInputListener {
		
		@Override
		public void input(String text) {
			
			userNameWanted = text;
			Multiplayer.goOnline(text);
		}

		@Override
		public void canceled() {
			Main.log(getClass(), "Canceled");
//			if (!playerLoggedIn()) {
//				openPlayerNameInput(WarpResponseResultCode.UNKNOWN_ERROR);
//			}
		}
	}

}

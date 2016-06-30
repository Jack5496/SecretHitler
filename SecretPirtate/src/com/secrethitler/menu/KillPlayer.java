package com.secrethitler.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jack5496.secrethitler.Main;
import com.secrethitler.entitys.LocalPlayer;
import com.secrethitler.entitys.LocalPlayerHandler;
import com.secrethitler.game.Game;
import com.secrethitler.helper.Message;
import com.secrethitler.multiplayer.Multiplayer;
import com.secrethitler.multiplayer.Notifications;
import com.secrethitler.uiElements.GUIButton;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomData;

public class KillPlayer implements MenuInterface {

	List<GUIButton> buttons;
	GUIButton activButton;

	GUIButton ok = new GUIButton("OK", "test", 90, 25, 25,true).setOnHoverBigger(true);
	GUIButton back = new GUIButton("Back", "test", 90, 75, 25,true).setOnHoverBigger(true);

	static HashMap<GUIButton, LocalPlayer> choosed;
	static List<GUIButton> notChoosed;

	GUIButton no = new GUIButton("", "gesetztVerdeckt", 0, 0, 0,0);

	GUIButton personToKill;

	private void initLists() {
		buttons = new ArrayList<GUIButton>();
		choosed = new HashMap<GUIButton, LocalPlayer>();
		notChoosed = new ArrayList<GUIButton>();
	}

	public KillPlayer() {
		initLists();

		List<LocalPlayer> alivePlayers = new ArrayList<LocalPlayer>(Multiplayer.activRoom.activGame.presidentOrder);

		for (int i = 0; i < alivePlayers.size(); i++) {
			LocalPlayer player = alivePlayers.get(i);
			int xpos = i * 10 + 10;
			int ypos = 75;
			if (i > 5) {
				xpos -= 50;
				ypos -= 50;
			}

			GUIButton playerButton = new GUIButton(player.name, "test", xpos, ypos, 10,true).setOnHoverBigger(true);

			choosed.put(playerButton, player);
			personToKill = playerButton;
		}

		buttons.add(ok);
		buttons.add(back);
	}

	@Override
	public void render(SpriteBatch batch) {
		for (GUIButton button : buttons) {
			button.render(batch);
		}
		// for (GUIButton button : notChoosed) {
		// button.render(batch);
		// }
		for (GUIButton button : choosed.keySet()) {
			if (button != personToKill) {
				no.centerPercentX = button.centerPercentX;
				no.centerPercentY = button.centerPercentY;
				no.percentWidth = button.percentWidth;
				no.percentHeight = button.percentHeight;
				no.render(batch);
			}
			button.render(batch);
		}

	}

	public void enter() {
		if (activButton != null) {
			if (activButton == ok) {
				MenuHandler.setActivMenu(Multiplayer.activRoom);
				Multiplayer.activRoom.disablePresidentKillPlayerButton();
				Multiplayer.killPlayer(choosed.get(personToKill));
			}
			if (activButton == back) {
				MenuHandler.setActivMenu(Multiplayer.activRoom);
			}
			if (choosed.containsKey(activButton)) {
				notChoosed = new ArrayList<GUIButton>();
				for (GUIButton button : choosed.keySet()) {
					if (activButton != button) {
						notChoosed.add(new GUIButton("", "gesetztVerdeckt", button));
					}
				}
				personToKill = activButton;
			}

		}
	}

	@Override
	public void clicked(int x, int y) {
		mouseMoved(x, y);
		enter();
	}

	@Override
	public void mouseMoved(int x, int y) {
		if (activButton != null) {
			activButton.setHovered(false);
		}
		activButton = null;
		for (GUIButton button : buttons) {
			button.pressAt(x, y);
			if (button.isPressed()) {
				activButton = button;
				activButton.setHovered(true);
			}
		}
		for (GUIButton button : choosed.keySet()) {
			button.pressAt(x, y);
			if (button.isPressed()) {
				activButton = button;
				activButton.setHovered(true);
			}
		}

	}

}
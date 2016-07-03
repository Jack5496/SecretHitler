package com.secrethitler.menu;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jack5496.secrethitler.Main;
import com.secrethitler.entitys.LocalPlayerHandler;
import com.secrethitler.uiElements.GUIButton;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomData;

public class MainMenu implements MenuInterface {

	List<GUIButton> buttons;
	GUIButton activButton;

	GUIButton listRooms = new GUIButton("Browse", "browse", 50, 80, 25,false).setOnHoverBigger(true);
	GUIButton create = new GUIButton("Create", "test", 50, 50, 25,false).setOnHoverBigger(true);
	GUIButton options = new GUIButton("Options", "changeName", 50, 20, 25,false).setOnHoverBigger(true);

	public MainMenu() {
		buttons = new ArrayList<GUIButton>();

		buttons.add(listRooms);
		buttons.add(create);
		buttons.add(options);
	}

	@Override
	public void render(SpriteBatch batch) {

		for (GUIButton button : buttons) {
			button.render(batch);
		}

	}

	public GUIButton getActivButton() {
		return activButton;
	}

	public void enter() {
		if (activButton != null) {
			if (activButton == listRooms) {
				if (LocalPlayerHandler.playerLoggedIn()) {
					Main.log(getClass(), "Switching to RoomListning");
					MenuHandler.setActivMenu(new RoomListning());
				} else {
					LocalPlayerHandler.openPlayerNameInput();
				}
			}
			if (activButton == create) {
				RoomData data = new RoomData("id", "owner", "roomName", 10);
				MenuHandler.setActivMenu(new Room(data));
			}
			if (activButton == options) {
				Main.log(getClass(), "Switching to Options");
				MenuHandler.setActivMenu(new OptionMenu());
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
	}

}
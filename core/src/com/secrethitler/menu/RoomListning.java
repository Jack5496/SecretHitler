package com.secrethitler.menu;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jack5496.secrethitler.Main;
import com.secrethitler.Inputs.KeyBoard;
import com.secrethitler.multiplayer.Multiplayer;
import com.secrethitler.multiplayer.ZoneListener;
import com.secrethitler.uiElements.GUIButton;

public class RoomListning implements MenuInterface {

	List<GUIButton> buttons;
	GUIButton activButton;

	int maxRoomsShow = 8;

	GUIButton back = new GUIButton("Back", "test", 25, 80);
	GUIButton loadRooms = new GUIButton("Load Rooms", "test", 25, 20);

	List<GUIButton> roomButtons;

	public static RoomListning instance;

	public RoomListning() {
		instance = this;

		buttons = new ArrayList<GUIButton>();
		roomButtons = new ArrayList<GUIButton>();

		buttons.add(back);
		buttons.add(loadRooms);

		back.setNeighbors(back, back, loadRooms, loadRooms);
		loadRooms.setNeighbors(loadRooms, loadRooms, back, back);

		activButton = back;
		activButton.setHovered(true);
	}

	@Override
	public void render(SpriteBatch batch) {

		for (GUIButton button : buttons) {
			button.render(batch);
		}
		for (GUIButton button : roomButtons) {
			button.render(batch);
		}

	}

	public GUIButton getActivButton() {
		return activButton;
	}

	@Override
	public void enter() {
		if (activButton == back) {
			Main.log(getClass(), "Switching to Main");
			MenuHandler.setActivMenu(new MainMenu());
		}
		if (activButton == loadRooms) {
			Main.log(getClass(), "Load Rooms");
			resetLoadedRooms();
			Multiplayer.getAllRooms();
		}
		if (roomButtons.contains(activButton)) {
			Main.log(getClass(), "Enter Room");
			String roomID = activButton.label;
			Multiplayer.updateRoomInformations(roomID);
		}
	}

	public void resetLoadedRooms() {
		roomButtons = new ArrayList<GUIButton>();
	}

	public void allRoomsLoaded() {
		resetLoadedRooms();

		List<String> roomIDs = new ArrayList<String>();
		roomIDs.addAll(ZoneListener.instance.rooms.keySet());

		updateMenuButtonsToRight();

		int ypos = 90;
		for (String roomID : roomIDs) {
			GUIButton room = new GUIButton(roomID, null, 75, ypos);

			roomButtons.add(room);
			ypos -= 10;
		}

		for (int i = 0; i < roomButtons.size(); i++) {
			GUIButton button = roomButtons.get(i);
			if (i == 0) { // falls erster
				button.setNeighbors(loadRooms, button, button, roomButtons.get(i + 1));
			} else if (i == roomButtons.size() - 1) { // falls letzter
				button.setNeighbors(loadRooms, button, roomButtons.get(i - 1), button);
			} else {
				button.setNeighbors(loadRooms, button, roomButtons.get(i - 1), roomButtons.get(i + 1));
			}
		}

		if (!roomButtons.isEmpty()) {
			for (GUIButton button : buttons) {
				button.right = roomButtons.get(0);
			}
		}
	}

	public void updateMenuButtonsToRight() {
		for (GUIButton button : buttons) {
			button.right = button;
		}
	}

	public void changeActivButton(GUIButton button) {
		activButton.setHovered(false);
		activButton = button;
		activButton.setHovered(true);
	}

	@Override
	public void up() {
		changeActivButton(activButton.abouve);
	}

	@Override
	public void down() {
		changeActivButton(activButton.down);
	}

	@Override
	public void left() {
		changeActivButton(activButton.left);
	}

	@Override
	public void right() {
		changeActivButton(activButton.right);
	}

	@Override
	public void keyTyped(final int keycode) {
		switch (keycode) {
		case KeyBoard.UP:
			up();
			break;
		case KeyBoard.DOWN:
			down();
			break;
		case KeyBoard.LEFT:
			left();
			break;
		case KeyBoard.RIGHT:
			right();
			break;
		case KeyBoard.ENTER:
			enter();
			break;
		}
	}

	@Override
	public void clicked(int x, int y) {

	}

}
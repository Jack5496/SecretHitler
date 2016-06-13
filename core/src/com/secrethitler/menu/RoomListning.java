package com.secrethitler.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.Input.Keys;
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

	GUIButton back = new GUIButton("Back", "test", 25, 80,0.2f);
	GUIButton loadRooms = new GUIButton("Load Rooms", "test", 25, 20,0.2f);

	HashMap<GUIButton,Room> roomsListed;
	GUIButton lastAdded;

	public static RoomListning instance;

	public RoomListning() {
		instance = this;
		buttons = new ArrayList<GUIButton>();
		resetLoadedRooms();

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
		
		for (GUIButton button : roomsListed.keySet()) {
			button.render(batch);
		}

	}

	public GUIButton getActivButton() {
		return activButton;
	}

	@Override
	public void enter() {
		if (activButton != null) {
			if (activButton == back) {
				Main.log(getClass(), "Switching to Main");
				MenuHandler.setActivMenu(new MainMenu());
			}
			if (activButton == loadRooms) {
				Main.log(getClass(), "Load Rooms");
				resetLoadedRooms();
				Multiplayer.getAllRooms();
			}
			if (roomsListed.containsKey(activButton)) {
				Main.log(getClass(), "Enter Room");
				
				Room room = roomsListed.get(activButton);				
				Multiplayer.joinRoom(room);
				MenuHandler.setActivMenu(room);
			}
		}
	}

	public void resetLoadedRooms() {
		roomsListed = new HashMap<GUIButton,Room>();
	}

	public void allRoomsLoaded() {
		resetLoadedRooms();

		List<String> roomIDs = new ArrayList<String>(ZoneListener.instance.rooms);

		updateMenuButtonsToRight();

		Main.log(getClass(), "Updating room Informations of all");
		for(String roomID : roomIDs){
			Multiplayer.updateRoomInformations(roomID);
		}
	}
	
	public void addLoadedRoom(Room room){
		
		String text = room.name+" ["+room.getPlayerAmount()+"/"+room.maxUser+"]";
		int ypos = roomsListed.size()*-10;
		GUIButton roomButton = new GUIButton(text,null,80,80+ypos,0.2f);
		roomsListed.put(roomButton, room);
		
		if(lastAdded==null){
			for (GUIButton button : buttons) {
				button.right = roomButton;
			}
			roomButton.setNeighbors(loadRooms, roomButton, roomButton, roomButton);
			lastAdded = roomButton;
		}
		else{
			roomButton.setNeighbors(loadRooms, roomButton, lastAdded, roomButton);
			lastAdded.setNeighbors(loadRooms, lastAdded, lastAdded.abouve, roomButton);
			lastAdded = roomButton;
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
		if (activButton != null) {
			changeActivButton(activButton.abouve);
		}
	}

	@Override
	public void down() {
		if (activButton != null) {
			changeActivButton(activButton.down);
		}
	}

	@Override
	public void left() {
		if (activButton != null) {
			changeActivButton(activButton.left);
		}
	}

	@Override
	public void right() {
		if (activButton != null) {
			changeActivButton(activButton.right);
		}
	}

	@Override
	public void keyTyped(final int keycode) {
		switch (keycode) {
		case Keys.UP:
			up();
			break;
		case Keys.DOWN:
			down();
			break;
		case Keys.LEFT:
			left();
			break;
		case Keys.RIGHT:
			right();
			break;
		case Keys.ENTER:
			enter();
			break;
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
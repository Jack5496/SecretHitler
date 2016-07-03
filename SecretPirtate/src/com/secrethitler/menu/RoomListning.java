package com.secrethitler.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jack5496.secrethitler.Main;
import com.secrethitler.multiplayer.Multiplayer;
import com.secrethitler.multiplayer.ZoneListener;
import com.secrethitler.uiElements.GUIButton;

public class RoomListning implements MenuInterface {

	List<GUIButton> buttons;
	GUIButton activButton;

	int maxRoomsShow = 8;

	GUIButton back = new GUIButton("Back", "test", 25, 80,25,false);
	GUIButton loadRooms = new GUIButton("Load Rooms", "test", 25, 20,25,false);

	HashMap<GUIButton,Room> roomsListed;

	public static RoomListning instance;

	public RoomListning() {
		instance = this;
		buttons = new ArrayList<GUIButton>();
		resetLoadedRooms();

		buttons.add(back);
		buttons.add(loadRooms);

		activButton = back;
		activButton.setHovered(true);
	}

	@Override
	public void render(SpriteBatch batch) {

		for (GUIButton button : buttons) {
			button.render(batch);
		}
		
		for (GUIButton button : new ArrayList<GUIButton>(roomsListed.keySet())) {
			button.render(batch);
		}

	}

	public GUIButton getActivButton() {
		return activButton;
	}

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

		Main.log(getClass(), "Updating room Informations of all");
		for(String roomID : roomIDs){
			Multiplayer.updateRoomInformations(roomID);
		}
	}
	
	int entryHeight = 20;
	
	public void addLoadedRoom(Room room){
		String text = room.name+" ["+room.getPlayerAmount()+"/"+room.maxUser+"]";
		int ypos = roomsListed.size()*-entryHeight;
		GUIButton roomButton = new GUIButton(text,"test",70,80+ypos,40,entryHeight);
		roomsListed.put(roomButton, room);
	}

	public void changeActivButton(GUIButton button) {
		activButton.setHovered(false);
		activButton = button;
		activButton.setHovered(true);
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
		for (GUIButton button : roomsListed.keySet()) {
			button.pressAt(x, y);
			if (button.isPressed()) {
				activButton = button;
				activButton.setHovered(true);
			}
		}
	}

}
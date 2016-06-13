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
import com.secrethitler.helper.Message;
import com.secrethitler.multiplayer.Multiplayer;
import com.secrethitler.multiplayer.Notifications;
import com.secrethitler.uiElements.GUIButton;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomData;

public class Room implements MenuInterface {

	public String id;
	public String name;
	public int maxUser;
	public String ownerName;

	public static final int minPlayers = 5;
	public static final int maxPlayers = 10;

	public boolean running;

	List<GUIButton> buttons;
	GUIButton activButton;

	GUIButton roomID = new GUIButton("RoomID", null, 10, 80);

	GUIButton chat = new GUIButton("Chat", "test", 50, 80);
	GUIButton back = new GUIButton("Back", "test", 50, 50);

	HashMap<GUIButton, LocalPlayer> players;

	public Room(RoomData data) {
		roomInformationsFound(data);

		buttons = new ArrayList<GUIButton>();
		players = new HashMap<GUIButton, LocalPlayer>();

		buttons.add(chat);
		buttons.add(back);

		chat.setNeighbors(chat, chat, back, back);
		back.setNeighbors(back, back, chat, chat);

		activButton = chat;
		activButton.setHovered(true);
	}

	public int getPlayerAmount() {
		return players.size();
	}

	public void roomInformationsFound(RoomData data) {
		this.id = data.getId();
		this.name = data.getName();
		this.ownerName = data.getRoomOwner();
		this.maxUser = data.getMaxUsers();
		// Main.log(getClass(), "ID: " + id + " | Name: " + name + " | owner: "
		// + ownerName + " | maxUser: " + maxUser);
	}

	public static int maxMessagesToShow = 8;

	@Override
	public void render(SpriteBatch batch) {

		for (GUIButton button : buttons) {
			button.render(batch);
		}

		roomID.render(batch);
		renderMessages(batch);

		int ypos = 0;
		for (GUIButton playerButton : players.keySet()) {
			playerButton.yper = 80 - ypos * 5;
			playerButton.render(batch);
			ypos++;
		}
	}

	public void setJoinedUsers(String[] joinedUsers) {
		for (String userName : joinedUsers) {
			playerJoined(new LocalPlayer(userName));
		}
	}

	GUIButton lastAdded = null;

	public void playerJoined(LocalPlayer p) {
		GUIButton playerButton = new GUIButton(p.name, null, 80, 80);

		if (lastAdded == null) {
			playerButton.setNeighbors(chat, playerButton, playerButton, playerButton);
			for (GUIButton button : buttons) {
				button.setNeighbors(button.left, playerButton, button.abouve, button.down);
			}
		} else {
			playerButton.setNeighbors(chat, playerButton, lastAdded, playerButton);
			lastAdded.setNeighbors(lastAdded.left, lastAdded.right, lastAdded.abouve, playerButton);
		}
		players.put(playerButton, p);

		lastAdded = playerButton;
	}

	public void playerLeft(LocalPlayer p) {
		GUIButton toRemove = null;
		GUIButton previous = null;
		GUIButton next = null;

		Iterator<Entry<GUIButton, LocalPlayer>> it = players.entrySet().iterator();

		while (it.hasNext()) {
			Entry<GUIButton, LocalPlayer> entry = it.next();

			if (toRemove == null) {
				previous = entry.getKey();
				if (entry.getValue().equals(p)) {
					it.remove();

					if (it.hasNext()) {
						next = it.next().getKey();

					} else {
						next = previous;
					}
				}
			}
		}

		if (previous != null) {
			if (next != null) {
				previous.down = next;
				next.abouve = previous;
			}
		}
	}

	public void renderMessages(SpriteBatch batch) {
		List<Message> messages = Notifications.getMessages();

		int countUpTo = maxMessagesToShow;
		if (messages.size() < countUpTo) {
			countUpTo = messages.size();
		}

		int start = messages.size() - countUpTo;
		int end = messages.size();

		int ypos = 0;
		for (int i = start; i < end; i++, ypos++) {
			Message m = messages.get(i);

			String label = m.getTime().getTimeInHoursMinutesFormat() + " | " + m.getSender() + ": " + m.getMessage();

			GUIButton guiMessage = new GUIButton(label, null, 10, 50 - ypos * 5);
			guiMessage.render(batch);
		}
	}

	public GUIButton getActivButton() {
		return activButton;
	}

	@Override
	public void enter() {
		if (activButton != null) {
			if (activButton == chat) {
				Multiplayer.chatInRoom();
			}
			if (activButton == back) {
				Main.log(getClass(), "Switching to Room Listning");
				MenuHandler.setActivMenu(new RoomListning());
				Multiplayer.leaveRoom();
			}
			if (players.keySet().contains(activButton)) {
				Main.log(getClass(), "Send private Message");
				Multiplayer.chatToPlayer(players.get(activButton));
			}
		}
	}

	@Override
	public void up() {
		if (activButton != null) {
			activButton.setHovered(false);
			activButton = activButton.abouve;
			activButton.setHovered(true);
			// Main.log(getClass(), "" + position);
		}
	}

	@Override
	public void down() {
		if (activButton != null) {
			activButton.setHovered(false);
			activButton = activButton.down;
			activButton.setHovered(true);
			// Main.log(getClass(), "" + position);
		}
	}

	@Override
	public void left() {
		if (activButton != null) {
			// TODO Auto-generated method stub
			activButton.setHovered(false);
			activButton = activButton.left;
			activButton.setHovered(true);
		}
	}

	@Override
	public void right() {
		if (activButton != null) {
			// TODO Auto-generated method stub
			activButton.setHovered(false);
			activButton = activButton.right;
			activButton.setHovered(true);
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
		for (GUIButton button : players.keySet()) {
			button.pressAt(x, y);
			if (button.isPressed()) {
				activButton = button;
				activButton.setHovered(true);
			}
		}
	}

}
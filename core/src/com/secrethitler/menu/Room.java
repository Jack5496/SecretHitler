package com.secrethitler.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jack5496.secrethitler.Main;
import com.jack5496.secrethitler.ResourceLoader;
import com.secrethitler.entitys.LocalPlayer;
import com.secrethitler.entitys.LocalPlayerHandler;
import com.secrethitler.game.Game;
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

	public boolean spectate = false;

	List<GUIButton> buttons;
	GUIButton activButton;

	public GUIButton role = new GUIButton("ROLE", "test", 10, 80, 0.2f);
	public GUIButton rolePresident = new GUIButton("President", "test", 50, 80, 0.2f);
//	public GUIButton roleCancelor = new GUIButton("Cancelor", "test", 10, 80, 0.2f);
	

	static GUIButton faschistBoard = new GUIButton("", "spielfeldFaschist5-6", 50, 80, 0.5f);
	static GUIButton liberalBoard = new GUIButton("", "spielfeldLiberal", 50, 50, 0.5f);

	GUIButton start = new GUIButton("START", "test", 50, 50, 0.4f).setOnHoverBigger(true);

	GUIButton chat = new GUIButton("Chat", "test", 70, 15, 0.2f).setOnHoverBigger(true);
	GUIButton presidentChooseCancelor = new GUIButton("Select Cancelor", "test", 50, (faschistBoard.yper + liberalBoard.yper) / 2, 0.4f)
			.setOnHoverBigger(true);
	GUIButton presidentKillPlayer = new GUIButton("Kill Player", "test", 50, (faschistBoard.yper + liberalBoard.yper) / 2, 0.4f)
			.setOnHoverBigger(true);
	GUIButton voteForCancelor = new GUIButton("Vote for Cancelor", "test", 50, (faschistBoard.yper + liberalBoard.yper) / 2, 0.4f)
			.setOnHoverBigger(true);
	GUIButton presidentStart = new GUIButton("Choose", "test", 50, (faschistBoard.yper + liberalBoard.yper) / 2, 0.4f)
			.setOnHoverBigger(true);
	GUIButton cancelorStart = new GUIButton("Choose", "test", 50, (faschistBoard.yper + liberalBoard.yper) / 2, 0.4f)
			.setOnHoverBigger(true);
	GUIButton back = new GUIButton("Back", "test", 30, 15, 0.2f).setOnHoverBigger(true);

	static float fasPlatz = 6.9f;
	static int fasStart = 33;

	public int fasictCards = 0;
	List<GUIButton> fasictCardsList;

	static GUIButton fasictGesetzt1 = new GUIButton("", "gesetztFaschist", fasStart, 80, 0.06f);
	static GUIButton fasictGesetzt2 = new GUIButton("", "gesetztFaschist", fasStart + 1 * fasPlatz, 80, 0.06f);
	static GUIButton fasictGesetzt3 = new GUIButton("", "gesetztFaschist", fasStart + 2 * fasPlatz, 80, 0.06f);
	static GUIButton fasictGesetzt4 = new GUIButton("", "gesetztFaschist", fasStart + 3 * fasPlatz, 80, 0.06f);
	static GUIButton fasictGesetzt5 = new GUIButton("", "gesetztFaschist", fasStart + 4 * fasPlatz, 80, 0.06f);
	static GUIButton fasictGesetzt6 = new GUIButton("", "gesetztFaschist", fasStart + 5 * fasPlatz, 80, 0.06f);

	static int libPlatz = 7;
	static int libStart = 50;

	public int liberalCards = 0;
	List<GUIButton> liberalCardsList;

	static GUIButton liberalGesetzt1 = new GUIButton("", "gesetztLiberal", libStart - 2 * libPlatz, 50, 0.06f);
	static GUIButton liberalGesetzt2 = new GUIButton("", "gesetztLiberal", libStart - 1 * libPlatz, 50, 0.06f);
	static GUIButton liberalGesetzt3 = new GUIButton("", "gesetztLiberal", libStart, 50, 0.06f);
	static GUIButton liberalGesetzt4 = new GUIButton("", "gesetztLiberal", libStart + 1 * libPlatz, 50, 0.06f);
	static GUIButton liberalGesetzt5 = new GUIButton("", "gesetztLiberal", libStart + 2 * libPlatz, 50, 0.06f);

	static int coinStart = 43;
	static float coinPlatz = 4.5f;
	static int coindHeight = 39;

	static GUIButton coin1 = new GUIButton("", "coin", coinStart, coindHeight, 0.025f);
	static GUIButton coin2 = new GUIButton("", "coin", coinStart + 1 * coinPlatz, coindHeight, 0.025f);
	static GUIButton coin3 = new GUIButton("", "coin", coinStart + 2 * coinPlatz, coindHeight, 0.025f);
	static GUIButton coin4 = new GUIButton("", "coin", coinStart + 3 * coinPlatz, coindHeight, 0.025f);

	public HashMap<GUIButton, LocalPlayer> players;

	public Game activGame;

	public Room(RoomData data) {
		roomInformationsFound(data);

		buttons = new ArrayList<GUIButton>();
		players = new HashMap<GUIButton, LocalPlayer>();

		buttons.add(chat);
		buttons.add(back);

		liberalCardsList = new ArrayList<GUIButton>();
		liberalCardsList.add(liberalGesetzt1);
		liberalCardsList.add(liberalGesetzt2);
		liberalCardsList.add(liberalGesetzt3);
		liberalCardsList.add(liberalGesetzt4);
		liberalCardsList.add(liberalGesetzt5);

		fasictCardsList = new ArrayList<GUIButton>();
		fasictCardsList.add(fasictGesetzt1);
		fasictCardsList.add(fasictGesetzt2);
		fasictCardsList.add(fasictGesetzt3);
		fasictCardsList.add(fasictGesetzt4);
		fasictCardsList.add(fasictGesetzt5);
		fasictCardsList.add(fasictGesetzt6);

		// buttons.add(coin1);
		// buttons.add(coin2);
		// buttons.add(coin3);
		// buttons.add(coin4);

		chat.setNeighbors(back, chat, chat, chat);
		back.setNeighbors(back, chat, back, back);

		activButton = chat;
		activButton.setHovered(true);

		activGame = new Game();
	}
	
	public void enablePresidentChooseCancelorButton(){
		buttons.add(presidentChooseCancelor);
	}
	
	public void disablePresidentChooseCancelorButton(){
		buttons.remove(presidentChooseCancelor);
	}
	
	public void enablePresidentKillPlayerButton(){
		buttons.add(presidentKillPlayer);
	}
	
	public void disablePresidentKillPlayerButton(){
		buttons.remove(presidentKillPlayer);
	}
	
	public void enableVoteForCancelorButton(){
		voteForCancelor.text = activGame.cancelor.name+" Cancelor?";
		buttons.add(voteForCancelor);
	}
	
	public void disableVoteForCancelorButton(){
		buttons.remove(voteForCancelor);
	}

	public void enablePresidentButton() {
		buttons.add(presidentStart);
	}

	public void disablePresidentButton() {
		buttons.remove(presidentStart);
	}

	public void enableCancelorButton() {
		buttons.add(cancelorStart);
	}

	public void disableCancelorButton() {
		buttons.remove(cancelorStart);
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
		renderGameBoard(batch);

		for (GUIButton button : buttons) {
			if (spectate) {
				if (button != chat) {
					button.render(batch);
				}
			} else {
				button.render(batch);
			}
		}

		role.render(batch);
		rolePresident.render(batch);

		renderMessages(batch);

		int ypos = 0;
		for (GUIButton playerButton : players.keySet()) {
			playerButton.yper = 80 - ypos * 5;
			playerButton.render(batch);
			ypos++;
		}
	}

	private void renderGameBoard(SpriteBatch batch) {
		if (activGame.running) {
			faschistBoard.render(batch);
			for (int i = 0; i < fasictCards; i++) {
				fasictCardsList.get(i).render(batch);
			}

			liberalBoard.render(batch);
			for (int i = 0; i < liberalCards; i++) {
				liberalCardsList.get(i).render(batch);
			}
		}
		else{
			start.render(batch);
		}
	}

	public void setJoinedUsers(String[] joinedUsers) {
		for (String userName : joinedUsers) {
			playerJoined(new LocalPlayer(userName));
		}
	}

	GUIButton lastAdded = null;

	public void playerJoined(LocalPlayer p) {
		GUIButton playerButton = new GUIButton(p.name, null, 80, 80, 0.2f);

		if (lastAdded == null) {
			playerButton.setNeighbors(chat, playerButton, playerButton, playerButton);
			chat.setNeighbors(chat.left, playerButton, chat, chat);

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

			GUIButton guiMessage = new GUIButton(label, null, 10, 50 - ypos * 5, 0.2f);
			guiMessage.render(batch);
		}
	}

	public GUIButton getActivButton() {
		return activButton;
	}

	@Override
	public void enter() {
		if (activButton != null) {
			if (spectate == false) {
				if (activButton == chat) {
					Multiplayer.chatInRoom();
				}
				if(!activGame.running){
					if(activButton==start){
						Main.log(getClass(), "Starte Spiel");
						activGame.startGame();
					}
				}
			}
			if (activButton == back) {
				Main.log(getClass(), "Switching to Room Listning");
				MenuHandler.setActivMenu(new RoomListning());
				Multiplayer.leaveRoom();
			}
			if (activButton == presidentChooseCancelor) {
				MenuHandler.setActivMenu(new ChooseCancelor());
			}
			if (activButton == voteForCancelor) {
				MenuHandler.setActivMenu(new ChooseCancelorYESNO(activGame.cancelor));
			}
			if (activButton == presidentStart) {
				activGame.startAsPresident();
			}
			if (activButton == cancelorStart) {
				activGame.startAsCancelor();
			}
			if (activButton == presidentKillPlayer) {
				MenuHandler.setActivMenu(new KillPlayer());
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
		if(!activGame.running){
			start.pressAt(x, y);
			if (start.isPressed()) {
				activButton = start;
				activButton.setHovered(true);
			}
		}
	}

}
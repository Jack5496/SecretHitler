package com.secrethitler.menu;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jack5496.secrethitler.Main;
import com.secrethitler.Inputs.KeyBoard;
import com.secrethitler.entitys.LocalPlayerHandler;
import com.secrethitler.helper.Message;
import com.secrethitler.multiplayer.Multiplayer;
import com.secrethitler.multiplayer.Notifications;
import com.secrethitler.uiElements.GUIButton;

public class RoomMenu implements MenuInterface {

	List<GUIButton> buttons;
	GUIButton activButton;

	GUIButton roomID = new GUIButton("RoomID", null, 10, 80);

	GUIButton chat = new GUIButton("Chat", "test", 50, 80);
	GUIButton back = new GUIButton("Back", "test", 50, 50);

	public RoomMenu() {
		buttons = new ArrayList<GUIButton>();

		buttons.add(chat);
		buttons.add(back);

		chat.setNeighbors(chat, chat, back, back);
		back.setNeighbors(back, back, chat, chat);

		activButton = chat;
		activButton.setHovered(true);
	}
	
	public static int maxMessagesToShow = 8;

	@Override
	public void render(SpriteBatch batch) {

		for (GUIButton button : buttons) {
			button.render(batch);
		}

		roomID.render(batch);

		List<Message> messages = Notifications.getMessages();
		
		int countUpTo = maxMessagesToShow;
		if(messages.size()<countUpTo){
			countUpTo = messages.size();
		}
		
		int start = messages.size()-countUpTo;
		int end = messages.size();
		
		int ypos = 0;
		for(int i =start; i<end; i++, ypos++){
			Message m = messages.get(i);
			
			String label = m.getTime().getTimeInHoursMinutesFormat()+" | "+m.getSender()+": "+m.getMessage();
			
			GUIButton guiMessage = new GUIButton(label, null, 10, 50-ypos*5);
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
				Multiplayer.chat();
			}
			if (activButton == back) {
				Main.log(getClass(), "Switching to Room Listning");
				MenuHandler.setActivMenu(new RoomListning());
				Multiplayer.leaveRoom();
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
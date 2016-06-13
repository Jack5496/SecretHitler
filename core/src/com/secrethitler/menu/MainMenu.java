package com.secrethitler.menu;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jack5496.secrethitler.Main;
import com.secrethitler.entitys.LocalPlayerHandler;
import com.secrethitler.uiElements.GUIButton;

public class MainMenu implements MenuInterface {

	List<GUIButton> buttons;
	GUIButton activButton;

	
	GUIButton listRooms = new GUIButton("Browse", "test", 50, 80,0.2f);
	GUIButton create = new GUIButton("Create", "test", 50, 50,0.2f);
	GUIButton options = new GUIButton("Options", "test", 50, 20,0.2f);

	public MainMenu() {
		buttons = new ArrayList<GUIButton>();

		buttons.add(listRooms);
		buttons.add(create);
		buttons.add(options);

		listRooms.setNeighbors(listRooms, listRooms, options, create);
		create.setNeighbors(create, create, listRooms, options);
		options.setNeighbors(options, options, create, listRooms);

		activButton = listRooms;
		activButton.setHovered(true);
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

	@Override
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
				MenuHandler.setActivMenu(new ChooseCards("gesetztFaschist","gesetztFaschist","gesetztLiberal"));
			}
			if (activButton == options) {
				Main.log(getClass(), "Switching to Options");
				MenuHandler.setActivMenu(new OptionMenu());
			}
		}
	}

	@Override
	public void up() {
		if (activButton != null) {
			activButton.setHovered(false);
			activButton = activButton.abouve;
			activButton.setHovered(true);
		}
		// Main.log(getClass(), "" + position);
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
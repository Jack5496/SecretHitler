package com.secrethitler.menu;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jack5496.secrethitler.Main;
import com.secrethitler.Inputs.KeyBoard;
import com.secrethitler.uiElements.GUIButton;

public class MainMenu implements MenuInterface {

	List<GUIButton> buttons;
	GUIButton activButton;
	
	GUIButton listRooms = new GUIButton("List all Rooms", "test", 50, 80);
	GUIButton test = new GUIButton("Test", "test", 50, 50);
	GUIButton options = new GUIButton("Options", "test", 50, 20);

	public MainMenu() {
		buttons = new ArrayList<GUIButton>();
		
		buttons.add(listRooms);
		buttons.add(test);
		buttons.add(options);

		listRooms.setNeighbors(listRooms, listRooms, options, test);
		test.setNeighbors(test, test, listRooms, options);
		options.setNeighbors(options, options, test, listRooms);
		
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
		if(activButton==listRooms){
			Main.log(getClass(), "Switching to RoomListning");
			MenuHandler.setActivMenu(new RoomListning());
		}
	}

	@Override
	public void up() {
		activButton.setHovered(false);
		activButton = activButton.abouve;
		activButton.setHovered(true);
		// Main.log(getClass(), "" + position);
	}

	@Override
	public void down() {
		activButton.setHovered(false);
		activButton = activButton.down;
		activButton.setHovered(true);
		// Main.log(getClass(), "" + position);
	}

	@Override
	public void left() {
		// TODO Auto-generated method stub
		activButton.setHovered(false);
		activButton = activButton.left;
		activButton.setHovered(true);
	}

	@Override
	public void right() {
		// TODO Auto-generated method stub
		activButton.setHovered(false);
		activButton = activButton.right;
		activButton.setHovered(true);
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
		case KeyBoard.ENTER:
			enter();
			break;
		}
	}

	@Override
	public void clicked(int x, int y) {

	}

}
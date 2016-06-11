package com.secrethitler.menu;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jack5496.secrethitler.Main;
import com.secrethitler.Inputs.KeyBoard;
import com.secrethitler.uiElements.GUIButton;

public class MainMenu implements MenuInterface {

	public int position;

	public final int roomListning = 0;

	List<GUIButton> buttons;

	public MainMenu() {
		position = 0;
		buttons = new ArrayList<GUIButton>();
		buttons.add(new GUIButton("List all Rooms", 50, 100));
		buttons.add(new GUIButton("Test", 50, 50));
		buttons.add(new GUIButton("Options", 50, 20));
	}

	@Override
	public void render(SpriteBatch batch) {

		for (GUIButton button : buttons) {
			button.render(batch);
		}

	}

	@Override
	public void enter() {
		switch (position) {
		case roomListning:
			Main.log(getClass(), "Switching to RoomListning");
			MenuHandler.setActivMenu(new RoomListning());
			break;
		}
	}

	@Override
	public void up() {
		if (position > 0) {
			position--;
		}
		Main.log(getClass(), "" + position);
	}

	@Override
	public void down() {
		if (buttons.size() > position + 1) {
			position++;
		}
		Main.log(getClass(), "" + position);
	}

	@Override
	public void left() {
		// TODO Auto-generated method stub

	}

	@Override
	public void right() {
		// TODO Auto-generated method stub

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

}
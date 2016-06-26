package com.secrethitler.menu;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jack5496.secrethitler.Main;
import com.secrethitler.Inputs.KeyBoard;
import com.secrethitler.entitys.LocalPlayerHandler;
import com.secrethitler.multiplayer.Multiplayer;
import com.secrethitler.uiElements.GUIButton;

public class OptionMenu implements MenuInterface {

	List<GUIButton> buttons;
	GUIButton activButton;

	GUIButton changeName = new GUIButton("ChangeName", "test", 50, 80,0.2f).setOnHoverBigger(true);
	GUIButton back = new GUIButton("Back", "test", 50, 50,0.2f).setOnHoverBigger(true);

	public OptionMenu() {
		buttons = new ArrayList<GUIButton>();

		buttons.add(changeName);
		buttons.add(back);

		activButton = changeName;
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

	public void enter() {
		if (activButton != null) {
			if (activButton == changeName) {
				Main.log(getClass(), "Switching name soon");
				Multiplayer.goOffline();
				LocalPlayerHandler.openPlayerNameInput();
			}
			if (activButton == back) {
				Main.log(getClass(), "Switching to Main");
				MenuHandler.setActivMenu(new MainMenu());
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
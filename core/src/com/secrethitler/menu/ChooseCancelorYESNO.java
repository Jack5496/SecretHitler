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
import com.secrethitler.entitys.LocalPlayerHandler;
import com.secrethitler.game.Game;
import com.secrethitler.helper.Message;
import com.secrethitler.multiplayer.Multiplayer;
import com.secrethitler.multiplayer.Notifications;
import com.secrethitler.uiElements.GUIButton;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomData;

public class ChooseCancelorYESNO implements MenuInterface {

	List<GUIButton> buttons;
	GUIButton activButton;

	GUIButton ok = new GUIButton("OK", "test", 90, 25, 0.2f).setOnHoverBigger(true);
	GUIButton back = new GUIButton("Back", "test", 90, 75, 0.2f).setOnHoverBigger(true);

	static GUIButton yes;
	static GUIButton no;
	
	static boolean wants;
	

	private void initLists() {
		buttons = new ArrayList<GUIButton>();
	}

	public ChooseCancelorYESNO(LocalPlayer p) {

		initLists();

		buttons.add(ok);
		buttons.add(back);

		yes = new GUIButton("YES", "test", 25, 50, 0.2f).setOnHoverBigger(true);
		no = new GUIButton("NO", "test", 75, 50, 0.2f).setOnHoverBigger(true);
		wants = true;

		ok.setNeighbors(back, ok, ok, ok);
		back.setNeighbors(back, ok, back, back);

		activButton = ok;
		activButton.setHovered(true);
	}

	@Override
	public void render(SpriteBatch batch) {

		for (GUIButton button : buttons) {
			button.render(batch);
		}
		yes.render(batch);
		no.render(batch);
	}

	@Override
	public void enter() {
		if (activButton != null) {
			if (activButton == ok) {
				Multiplayer.activRoom.disableVoteForCancelorButton();
				MenuHandler.setActivMenu(Multiplayer.activRoom);
				Multiplayer.wantsCancelor(wants);
			}
			if (activButton == back) {
				MenuHandler.setActivMenu(Multiplayer.activRoom);
			}
			if (activButton == yes) {
				wants = true;
			}
			if (activButton == no) {
				wants = false;
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
	}

	@Override
	public void down() {
		if (activButton != null) {
			activButton.setHovered(false);
			activButton = activButton.down;
			activButton.setHovered(true);
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
		yes.pressAt(x, y);
		if (yes.isPressed()) {
			activButton = yes;
			activButton.setHovered(true);
		}
		
		no.pressAt(x, y);
		if (no.isPressed()) {
			activButton = no;
			activButton.setHovered(true);
		}
		

	}

}
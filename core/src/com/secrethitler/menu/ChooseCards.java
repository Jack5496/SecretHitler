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

public class ChooseCards implements MenuInterface {

	List<GUIButton> buttons;
	GUIButton activButton;

	GUIButton ok = new GUIButton("OK", "test", 90, 25, 0.2f);
	GUIButton back = new GUIButton("Exit", "test", 90, 75, 0.2f);

	static float fasPlatz = 20;
	static int cardHeight = 50;
	static int fasStart = 10;
	static float cardSize = 0.18f;
	
	static List<GUIButton> cards;
	static List<GUIButton> choosed;
	static List<GUIButton> discard;

	static GUIButton fasictGesetzt1;
	static GUIButton fasictGesetzt2;
	static GUIButton fasictGesetzt3;
	
	public ChooseCards(String first, String second) {

		initLists();

		
		buttons.add(ok);
		buttons.add(back);

		fasictGesetzt1 = new GUIButton(first, first, fasStart, cardHeight, cardSize);
		fasictGesetzt2 = new GUIButton(second, second, fasStart + 1 * fasPlatz, cardHeight, cardSize);
		
		cards.add(fasictGesetzt1);
		cards.add(fasictGesetzt2);
		
		choosed.add(fasictGesetzt1);
		choosed.add(fasictGesetzt2);

		ok.setNeighbors(back, ok, ok, ok);
		back.setNeighbors(back, ok, back, back);

		activButton = ok;
		activButton.setHovered(true);
	}
	
	private void initLists(){
		buttons = new ArrayList<GUIButton>();
		cards = new ArrayList<GUIButton>();
		choosed = new ArrayList<GUIButton>();
		discard = new ArrayList<GUIButton>();
	}

	public ChooseCards(String first, String second, String third) {

		initLists();

		
		buttons.add(ok);
		buttons.add(back);

		fasictGesetzt1 = new GUIButton(first, first, fasStart, cardHeight, cardSize);
		fasictGesetzt2 = new GUIButton(second, second, fasStart + 1 * fasPlatz, cardHeight, cardSize);
		fasictGesetzt3 = new GUIButton(third, third, fasStart + 2 * fasPlatz, cardHeight, cardSize);
		
		cards.add(fasictGesetzt1);
		cards.add(fasictGesetzt2);
		cards.add(fasictGesetzt3);
		
		choosed.add(fasictGesetzt1);
		choosed.add(fasictGesetzt2);
		choosed.add(fasictGesetzt3);

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
		for (GUIButton button : cards) {
			button.render(batch);
		}
		for (GUIButton button : discard) {
			button.render(batch);
		}
	}

	@Override
	public void enter() {
		if (activButton != null) {
			if (activButton == ok) {
				if(choosed.size()==cards.size()-1){
				MenuHandler.setActivMenu(Multiplayer.activRoom);
				Multiplayer.sendMessage("");
				}else{
					Main.log(getClass(), "Choose "+(cards.size()-1)+" Cards");
				}
			}
			if (activButton == back) {
				Main.log(getClass(), "Switching to Room Listning");
				MenuHandler.setActivMenu(new RoomListning());
				Multiplayer.leaveRoom();
			}
			if(cards.contains(activButton)){
				if(choosed.contains(activButton)){
					choosed.remove(activButton);
					discard.add(new GUIButton("", "gesetztVerdeckt", activButton.xper, activButton.yper, cardSize));
				}
				else{
					choosed.add(activButton);
					discard.remove(0);
				}
				Main.log(getClass(), "Choosed: "+choosed.size()+" Cards");
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
		for (GUIButton button : cards) {
			button.pressAt(x, y);
			if (button.isPressed()) {
				activButton = button;
				activButton.setHovered(true);
			}
		}

	}

}
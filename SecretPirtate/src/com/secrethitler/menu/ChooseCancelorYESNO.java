package com.secrethitler.menu;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.secrethitler.entitys.LocalPlayer;
import com.secrethitler.multiplayer.Multiplayer;
import com.secrethitler.uiElements.GUIButton;

public class ChooseCancelorYESNO implements MenuInterface {

	List<GUIButton> buttons;
	GUIButton activButton;

	GUIButton ok = new GUIButton("OK", "test", 90, 25, 25,true).setOnHoverBigger(true);
	GUIButton back = new GUIButton("Back", "test", 90, 75, 25,true).setOnHoverBigger(true);
	
	GUIButton yes = new GUIButton("YES", "test", 25, 50, 25,true).setOnHoverBigger(true);
	GUIButton no = new GUIButton("NO", "test", 75, 50, 25,true).setOnHoverBigger(true);
	GUIButton notWant = new GUIButton("", "gesetztVerdeckt", no);
	GUIButton wants = yes;
	

	private void initLists() {
		buttons = new ArrayList<GUIButton>();
	}

	public ChooseCancelorYESNO(LocalPlayer p) {
		initLists();

		buttons.add(ok);
		buttons.add(back);
	}

	@Override
	public void render(SpriteBatch batch) {

		for (GUIButton button : buttons) {
			button.render(batch);
		}
		notWant.render(batch);
		yes.render(batch);
		no.render(batch);
	}

	public void enter() {
		if (activButton != null) {
			if (activButton == ok) {
				Multiplayer.activRoom.disableVoteForCancelorButton();
				MenuHandler.setActivMenu(Multiplayer.activRoom);
				Multiplayer.wantsCancelor(wants==yes);
			}
			if (activButton == back) {
				MenuHandler.setActivMenu(Multiplayer.activRoom);
			}
			if (activButton == yes) {
				wants = yes;
				notWant = new GUIButton("", "gesetztVerdeckt", no);
			}
			if (activButton == no) {
				wants = no;
				notWant = new GUIButton("", "gesetztVerdeckt", yes);
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
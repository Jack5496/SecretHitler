package com.secrethitler.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.secrethitler.entitys.LocalPlayer;
import com.secrethitler.entitys.LocalPlayerHandler;
import com.secrethitler.multiplayer.Multiplayer;
import com.secrethitler.uiElements.GUIButton;

public class KillPlayer implements MenuInterface {

	List<GUIButton> buttons;
	GUIButton activButton;

	GUIButton ok = new GUIButton("OK", "test", 90, 25, 25,true).setOnHoverBigger(true);
	GUIButton back = new GUIButton("Back", "test", 90, 75, 25,true).setOnHoverBigger(true);

	static HashMap<GUIButton, LocalPlayer> choosed;

	GUIButton kill;

	GUIButton personToKill;

	private void initLists() {
		buttons = new ArrayList<GUIButton>();
		choosed = new HashMap<GUIButton, LocalPlayer>();
	}

	public KillPlayer() {
		initLists();

		List<LocalPlayer> alivePlayers = new ArrayList<LocalPlayer>(Multiplayer.activRoom.activGame.presidentOrder);
//		List<LocalPlayer> alivePlayers = new ArrayList<LocalPlayer>();
//		
//		alivePlayers.add(new LocalPlayer("Tim"));
//		alivePlayers.add(new LocalPlayer("Karl"));
//		alivePlayers.add(new LocalPlayer("Huso"));
//		alivePlayers.add(new LocalPlayer("Test"));
//		alivePlayers.add(new LocalPlayer("Jack"));
//		alivePlayers.add(new LocalPlayer("James"));
//		alivePlayers.add(new LocalPlayer("Joe"));
//		alivePlayers.add(new LocalPlayer("Indiana"));
//		alivePlayers.add(new LocalPlayer("Nils"));

		
		alivePlayers.remove(LocalPlayerHandler.localPlayer);
		
		float höhenAbstand = 30;
		float breitenAbstand = 20;
		float platz = (int) GUIButton.hoverSize;
		
		for (int i = 0; i < alivePlayers.size(); i++) {
			LocalPlayer player = alivePlayers.get(i);
			float xpos = i/3 * breitenAbstand+10;
			float ypos = 80-i%3*höhenAbstand;
			

			GUIButton playerButton = new GUIButton(player.name, "test", xpos+platz/2, ypos+platz/2,breitenAbstand-platz/2,höhenAbstand-platz/2).setOnHoverBigger(true);

			choosed.put(playerButton, player);
			personToKill = playerButton;
			updateKillButton();
		}

		buttons.add(ok);
		buttons.add(back);
	}
	
	private void updateKillButton(){
//		kill = new GUIButton("","dead",personToKill.centerPercentX,personToKill.centerPercentY,10/2,40/2);
		kill = new GUIButton("","dead",personToKill.centerPercentX,personToKill.centerPercentY,10,40/2);
	}

	@Override
	public void render(SpriteBatch batch) {
		for (GUIButton button : buttons) {
			button.render(batch);
		}
		
		kill.render(batch);
		for (GUIButton button : choosed.keySet()) {
			button.render(batch);
		}
		
	}

	public void enter() {
		if (activButton != null) {
			if (activButton == ok) {
				MenuHandler.setActivMenu(Multiplayer.activRoom);
				Multiplayer.activRoom.disablePresidentKillPlayerButton();
				Multiplayer.killPlayer(choosed.get(personToKill));
			}
			if (activButton == back) {
				MenuHandler.setActivMenu(Multiplayer.activRoom);
			}
			if (choosed.containsKey(activButton)) {
				personToKill = activButton;
				updateKillButton();
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
		for (GUIButton button : choosed.keySet()) {
			button.pressAt(x, y);
			if (button.isPressed()) {
				activButton = button;
				activButton.setHovered(true);
			}
		}

	}

}
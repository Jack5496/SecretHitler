package com.secrethitler.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jack5496.secrethitler.Main;
import com.secrethitler.entitys.LocalPlayerHandler;
import com.secrethitler.uiElements.GUIButton;

public class MenuHandler {

	private static MenuInterface activMenu;

	public MenuHandler() {
		activMenu = new MainMenu();
	}

	public static void setActivMenu(MenuInterface menu) {
		activMenu = menu;
	}

	static GUIButton fps = new GUIButton("", null, 90, 90, 10, false);
	static GUIButton user = new GUIButton("", null, 10, 90, 10, false);

	public static void render(SpriteBatch batch) {
		activMenu.render(batch);

		fps.label = Gdx.graphics.getFramesPerSecond() + "";
		fps.render(batch);

		if (LocalPlayerHandler.playerLoggedIn()) {
			user.label = LocalPlayerHandler.localPlayer.name;
		} else {
			user.label = "Offline";
		}

		user.render(batch);
	}

	// public static void enter() {
	// activMenu.enter();
	// }

	public static void mouseMoved(int x, int y) {
		int xpos = (int) (((float) x / (float)Main.getInstance().getWidth())*100);
		int ypos = (int) (((float) y / (float)Main.getInstance().getHeight())*100);

		activMenu.mouseMoved(xpos, ypos);
	}

	public static void clicked(int x, int y) {
		int xpos = (int) (((float) x / (float)Main.getInstance().getWidth())*100);
		int ypos = (int) (((float) y / (float)Main.getInstance().getHeight())*100);

		activMenu.clicked(xpos, ypos);
	}

}
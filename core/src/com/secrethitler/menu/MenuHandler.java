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

	static GUIButton fps = new GUIButton("", null, 90, 90);

	static GUIButton user = new GUIButton("", null, 10, 90);

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

	public static void enter() {
		if (LocalPlayerHandler.playerLoggedIn()) {
			activMenu.enter();
		}
	}

	public static void up() {
		if (LocalPlayerHandler.playerLoggedIn()) {
			activMenu.up();
		}
	}

	public static void down() {
		if (LocalPlayerHandler.playerLoggedIn()) {
			activMenu.down();
		}
	}

	public static void left() {
		if (LocalPlayerHandler.playerLoggedIn()) {
			activMenu.left();
		}
	}

	public static void right() {
		if (LocalPlayerHandler.playerLoggedIn()) {
			activMenu.right();
		}
	}

	public static void keyTyped(int keycode) {
		if (LocalPlayerHandler.playerLoggedIn()) {
			activMenu.keyTyped(keycode);
		}
	}

	public static void clicked(int x, int y) {
		if (LocalPlayerHandler.playerLoggedIn()) {
			activMenu.clicked(x, y);
		}
	}

}
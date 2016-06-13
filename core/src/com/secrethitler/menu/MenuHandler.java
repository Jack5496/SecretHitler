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

		fps.text = Gdx.graphics.getFramesPerSecond() + "";
		fps.render(batch);

		if (LocalPlayerHandler.playerLoggedIn()) {
			user.text = LocalPlayerHandler.localPlayer.name;
		} else {
			user.text = "Offline";
		}

		user.render(batch);
	}

	public static void enter() {
		activMenu.enter();
	}

	public static void up() {
		activMenu.up();
	}

	public static void down() {
		activMenu.down();
	}

	public static void left() {
		activMenu.left();
	}

	public static void right() {
		activMenu.right();
	}

	public static void keyTyped(int keycode) {
		activMenu.keyTyped(keycode);
	}

	public static void mouseMoved(int x, int y) {
		activMenu.mouseMoved(x, y);
	}

	public static void clicked(int x, int y) {
		activMenu.clicked(x, y);
	}

}
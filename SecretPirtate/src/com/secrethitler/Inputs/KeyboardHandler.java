package com.secrethitler.Inputs;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.jack5496.secrethitler.Main;
import com.secrethitler.entitys.LocalPlayer;
import com.secrethitler.menu.MenuHandler;

public class KeyboardHandler {

	public KeyBoard keyboard;
	public Mouse mouse;

	public static String inputHandlerName;

	public KeyboardHandler(InputHandler inputHandler) {
		inputHandlerName = "Keyboard";
		keyboard = new KeyBoard();
		mouse = new Mouse();
	}

	public void updateInputLogic() {
		updateLeftStick();
	}

	public void updateLeftStick() {
		Vector2 dir = new Vector2(0, 0);
	}

	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		int maxY = Main.getInstance().getHeight();
		screenY = maxY - screenY;		//Maus wird invertiert angegeben -.-
		
		Main.log(getClass(), "TouchUp: "+pointer+" | "+button);
		if (button == Input.Buttons.LEFT) {
			mouse.left.press();
//			Main.log(getClass(), "Mouse Left");
			MenuHandler.clicked(screenX, screenY);
		}
		if (button == Input.Buttons.RIGHT) {
			mouse.right.press();
			Main.log(getClass(), "Mouse Right");
		}
		return true;
	}

	public boolean mouseMoved(int screenX, int screenY) {
		
		int maxY = Main.getInstance().getHeight();
		screenY = maxY - screenY;		//Maus wird invertiert angegeben -.-
		
		mouse.updatePosition(screenX, screenY);
		MenuHandler.mouseMoved(screenX, screenY);

		return true;
	}

	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		int maxY = Main.getInstance().getHeight();
		screenY = maxY - screenY;		//Maus wird invertiert angegeben -.-
		
//		if (button == Input.Buttons.LEFT) {
//			mouse.left.press();
////			Main.log(getClass(), "Mouse Left");
//			MenuHandler.clicked(screenX, screenY);
//		}
//		if (button == Input.Buttons.RIGHT) {
//			mouse.right.press();
//			Main.log(getClass(), "Mouse Right");
//		}

		return true;
	}

	public boolean touchDragged(int screenX, int screenY, int pointer) {
		mouseMoved(screenX,screenY);
		return true;
	}

	public boolean scrolled(int amount) {
		return true;
	}

	public boolean keyTyped(char character) {
		return true;
	}

	public boolean keyUp(int keycode) {
		keyboard.key(keycode).release();

		return false;
	}

	public boolean keyDown(int keycode) {
		keyboard.key(keycode).press();
		return false;
	}

}

package com.secrethitler.Inputs;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.jack5496.secrethitler.Main;
import com.secrethitler.entitys.LocalPlayer;

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
		LocalPlayer p = Main.getInstance().playerHandler.getPlayerByInput(inputHandlerName);
	}

	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (button == Input.Buttons.LEFT) {
			mouse.left.release();
		}
		if (button == Input.Buttons.RIGHT) {
			mouse.right.release();
		}
		return true;
	}

	public boolean mouseMoved(int screenX, int screenY) {
		mouse.updatePosition(screenX, screenY);

		return true;
	}

	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (button == Input.Buttons.LEFT) {
			mouse.left.press();
		}
		if (button == Input.Buttons.RIGHT) {
			mouse.right.press();
		}

		return true;
	}

	public boolean touchDragged(int screenX, int screenY, int pointer) {

		return true;
	}

	public boolean scrolled(int amount) {
		LocalPlayer p = Main.getInstance().playerHandler.getPlayerByInput(inputHandlerName);

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
		Main.log(getClass(), "Keycode: "+keycode);
		Main.getInstance().menuHandler.getActivMenu().keyTyped(keycode);
		return false;
	}

}

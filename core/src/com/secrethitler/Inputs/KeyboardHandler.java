package com.secrethitler.Inputs;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

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
		updateABXY();
		updateMouseInputs();
	}

	public void updateMouseInputs() {
		// Player p =
		// Main.getInstance().playerHandler.getPlayerByInput(inputHandlerName);
		// p.shoot = mouseLeft;
		// p.rightClick = mouseRight;
	}

	public void updateABXY() {
		// Player p =
		// Main.getInstance().playerHandler.getPlayerByInput(inputHandlerName);
		// p.jump = keys[Keys.SPACE];
		//
		// if(keys[Keys.Z]){
		// for(int i=0;i<100; i++){
		// Main.getInstance().aiHandler.createAI("AI."+i);
		// }
		// }
	}

	boolean cloud = true;

	public void updateLeftStick() {
		Vector2 dir = new Vector2(0, 0);
		LocalPlayer p = Main.getInstance().playerHandler.getPlayerByInput(inputHandlerName);

		if (keyboard.isPressed(Keys.A)) {
			dir.add(new Vector2(-1, 0)); // left
		}
		if (keyboard.isPressed(Keys.Q)) {
			dir.add(new Vector2(-1, 1)); // left
		}
		if (keyboard.isPressed(Keys.D)) {
			dir.add(new Vector2(1, 0)); // right
		}
		if (keyboard.isPressed(Keys.W)) {
			dir.add(new Vector2(0, 1)); // up
		}
		if (keyboard.isPressed(Keys.S)) {
			dir.add(new Vector2(0, -1)); // down
		}
		if (keyboard.isPressed(Keys.C)) {
			if(cloud){
				cloud = false;
				Main.getInstance().cloudHandler.spawnCloud();
			}
		}
		if (keyboard.isPressed(Keys.UP)) {
			Main.getInstance().cloudHandler.windBlowDirection=Direction.NORTH;
		}
		if (keyboard.isPressed(Keys.RIGHT)) {
			Main.getInstance().cloudHandler.windBlowDirection=Direction.EAST;
		}
		if (keyboard.isPressed(Keys.DOWN)) {
			Main.getInstance().cloudHandler.windBlowDirection=Direction.SOUTH;
		}
		if (keyboard.isPressed(Keys.LEFT)) {
			Main.getInstance().cloudHandler.windBlowDirection=Direction.WEST;
		}

		p.run(keyboard.isPressed(Keys.SHIFT_LEFT, Keys.SHIFT_RIGHT));
		p.sneak(keyboard.isPressed(Keys.CONTROL_LEFT, Keys.CONTROL_RIGHT));

		dir.nor();
		p.move(dir);
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
		// Vector3 dir = new Vector3(1, 0, 0);
		// Player p =
		// Main.getInstance().playerHandler.getPlayerByInput(inputHandlerName);
		//
		// float yaw = getYawInDegreeOfModelWithMouse(screenX, screenY,
		// p.getObjPos());
		// dir = dir.rotate(yaw, 0, 1, 0);
		//
		// p.stickRight = dir;

		return true;
	}

	public boolean scrolled(int amount) {
		LocalPlayer p = Main.getInstance().playerHandler.getPlayerByInput(inputHandlerName);
		p.cameraController.changeDistance(amount);
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

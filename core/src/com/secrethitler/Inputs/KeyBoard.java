package com.secrethitler.Inputs;

import com.badlogic.gdx.Input.Keys;

public class KeyBoard {

	private Button[] keys;
	
	public static final int UP = 19;
	public static final int DOWN = 20;
	public static final int ENTER = 66;

	public KeyBoard() {
		keys = new Button[256];
		for (int i = 0; i < keys.length; i++) {
			keys[i] = new Button();
		}
	}

	public boolean isPressed(String... key) {
		for(String s : key){
			if(key(s).isPressed()) return true;
		}
		return false;
	}

	public boolean isPressed(int... key) {
		for(int s : key){
			if(key(s).isPressed()) return true;
		}
		return false;
	}

	public Button key(int key) {
		return keys[key];
	}

	public Button key(String key) {
		return keys[Keys.valueOf(key)];
	}

}

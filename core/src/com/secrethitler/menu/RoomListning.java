package com.secrethitler.menu;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jack5496.secrethitler.Main;
import com.secrethitler.Inputs.KeyBoard;
import com.secrethitler.uiElements.GUIButton;

public class RoomListning implements MenuInterface{
	
	public int position;
	
	public final int mainMenu = 0;
	
	List<GUIButton> buttons;
	
	public RoomListning(){
		position = 0;
		buttons = new ArrayList<GUIButton>();
		buttons.add(new GUIButton("Back", 50, 100));
	}
	
	@Override
	public void render(SpriteBatch batch) {
		for (GUIButton button : buttons) {
			button.render(batch);
		}
	}

	@Override
	public void enter() {
		switch(position){
		case mainMenu : Main.log(getClass(), "Switching to MainMenu"); MenuHandler.setActivMenu(new MainMenu()); break;
		}
	}

	@Override
	public void up() {
		position--;
		Main.log(getClass(), ""+position);
	}

	@Override
	public void down() {
		position++;
		Main.log(getClass(), ""+position);
	}

	@Override
	public void left() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void right() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(final int keycode) {
		switch(keycode){
		case KeyBoard.UP : up(); break;
		case KeyBoard.DOWN : down(); break;
		case KeyBoard.ENTER : enter(); break;
		}
	}

	
	
	
	
}
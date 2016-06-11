package com.secrethitler.menu;

import com.badlogic.gdx.Gdx;
import com.jack5496.secrethitler.Main;
import com.secrethitler.Inputs.InputListener;
import com.secrethitler.Inputs.KeyBoard;

public class MainMenu implements MenuInterface{
	
	public int position;
	
	public final int roomListning = 0;
	
	public MainMenu(){
		position = 0;
	}
	
	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enter() {
		switch(position){
		case roomListning : Main.log(getClass(), "Switching to RoomListning"); MenuHandler.setActivMenu(new RoomListning()); break;
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
package com.secrethitler.menu;

import com.jack5496.secrethitler.Main;
import com.secrethitler.Inputs.KeyBoard;

public class MainMenu implements MenuInterface{
	
	public int position;
	
	public final int pollAllRooms = 0;
	
	public MainMenu(){
		position = 0;
	}

	@Override
	public void enter() {
		switch(position){
		case pollAllRooms : Main.getInstance().onlineConnector.printAllRooms(); break;
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
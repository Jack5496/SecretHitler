package com.secrethitler.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuHandler {

	public static MenuInterface activMenu;
	
	public MenuHandler(){
		activMenu = new MainMenu();
	}
	
	public static void setActivMenu(MenuInterface menu){
		activMenu = menu;
	}
	
	public static MenuInterface getActivMenu(){
		return activMenu;
	}
	
	public static void renderActivMenu(SpriteBatch batch){
		activMenu.render(batch);
	}
	
	
	
}
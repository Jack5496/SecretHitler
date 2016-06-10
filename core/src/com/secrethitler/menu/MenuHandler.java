package com.secrethitler.menu;

public class MenuHandler {

	public MenuInterface activMenu;
	
	public MenuHandler(){
		activMenu = new MainMenu();
	}
	
	public MenuInterface getActivMenu(){
		return activMenu;
	}
	
	
	
}
package com.secrethitler.menu;

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
	
	
	
}
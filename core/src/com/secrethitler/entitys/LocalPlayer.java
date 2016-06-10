package com.secrethitler.entitys;


public class LocalPlayer{

	String name;
	String faction;
	boolean alive;
	
	boolean hitler;
	boolean cancelor;
	boolean president;
	
	
	public LocalPlayer(String name) {
		this.name = name;
	}
	
	public void setFaction(String faction){
		this.faction = faction;
	}
	
	public void setHitler(boolean isHitler){
		this.hitler = isHitler;
	}
	
	public void setPresident(boolean isPresident){
		this.president = isPresident;
	}
	
	public void setCancelor(boolean isCancelor){
		this.cancelor = isCancelor;
	}
	
}

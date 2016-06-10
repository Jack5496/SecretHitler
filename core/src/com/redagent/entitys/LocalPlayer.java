package com.redagent.entitys;


public class LocalPlayer{

	String name;
	String faction;
	boolean alive;
	
	
	public LocalPlayer(String name) {
		this(name,Factions.NONE);
	}
	
	public LocalPlayer(String name, String faction) {
		this.name = name;
		this.faction = faction;
		this.alive = true;
	}
	
	public LocalPlayer(String name, boolean facist) {
		this(name);
		
		if(facist){
			this.faction = Factions.FACIST;
		}
		else{
			this.faction = Factions.LIBERAL;
		}
	}



}

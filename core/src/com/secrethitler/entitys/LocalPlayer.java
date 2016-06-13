package com.secrethitler.entitys;


public class LocalPlayer{

	public String name;
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
	
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }
	    if (!LocalPlayer.class.isAssignableFrom(obj.getClass())) {
	        return false;
	    }
	    final LocalPlayer other = (LocalPlayer) obj;
	    if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
	        return false;
	    }
	    return true;
	}

	@Override
	public int hashCode() {
	    int hash = 3;
	    hash = 53 * hash + (this.name != null ? this.name.hashCode() : 0);
	    return hash;
	}
	
}

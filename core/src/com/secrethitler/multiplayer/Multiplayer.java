package com.secrethitler.multiplayer;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;  

public class Multiplayer {
	
	WarpClient warpClient = null;
	public static Multiplayer instance = null;
	
	public Multiplayer(){
		instance = this;
		
		WarpClient.initialize("9a5d2fbd5118c7864d156375334a4df70fc6c3432979e5e220c64f46bbedbd8a","8153173c208b6eb07b795dfed2c55e38eb7d75659763703df0049f29e77bfef0"); 
		
		try {
			warpClient = WarpClient.getInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		warpClient.addConnectionRequestListener(new ConListener());   
		warpClient.addZoneRequestListener(new LobbyFinder());
		
	}
	
	public static Multiplayer getInstance(){
		return instance;
	}
	
	
	public void goOnline(String name){
		warpClient.connectWithUserName(name); 
		
	}
	
	public void printAllRooms(){
		warpClient.getAllRooms();
	}

}

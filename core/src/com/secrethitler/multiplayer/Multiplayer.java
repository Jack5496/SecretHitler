package com.secrethitler.multiplayer;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;  

public class Multiplayer {
	
	static WarpClient warpClient = null;
	
	public Multiplayer(){
		
		WarpClient.initialize("9a5d2fbd5118c7864d156375334a4df70fc6c3432979e5e220c64f46bbedbd8a","8153173c208b6eb07b795dfed2c55e38eb7d75659763703df0049f29e77bfef0"); 
		
		try {
			warpClient = WarpClient.getInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		
		addListeners();
	}
	
	public void addListeners(){
		warpClient.addChatRequestListener(new ChatListener());	
		warpClient.addConnectionRequestListener(new ConListener());
		warpClient.addLobbyRequestListener(new LobbyListener());
		warpClient.addNotificationListener(new Notifications());
		warpClient.addRoomRequestListener(new RoomListener());
		warpClient.addTurnBasedRoomListener(new TurnListener());
		warpClient.addUpdateRequestListener(new UpdateListener());
		warpClient.addZoneRequestListener(new ZoneListener());
	}	
	
	public static void goOnline(String name){
		warpClient.connectWithUserName(name); 
	}
	
	public static void goOffline(){
		warpClient.disconnect();
	}
	
	public void printAllRooms(){
		warpClient.getAllRooms();
	}

}

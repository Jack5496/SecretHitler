package com.secrethitler.multiplayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.jack5496.secrethitler.Main;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;
import com.shephertz.app42.gaming.multiplayer.client.events.AllRoomsEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.AllUsersEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.LiveUserInfoEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.MatchedRoomsEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.ZoneRequestListener;

public class LobbyFinder implements ZoneRequestListener {
	
	List<String> roomIDs;
	HashMap<String, Room> rooms;
	
	public LobbyFinder(){
		roomIDs = new ArrayList<String>();
		rooms = new HashMap<String,Room>();
	}

	@Override
	public void onCreateRoomDone(RoomEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDeleteRoomDone(RoomEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetAllRoomsCountDone(AllRoomsEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetAllRoomsDone(AllRoomsEvent arg0) {
		String[] roomIds = arg0.getRoomIds();
		roomIDs = Arrays.asList(roomIds);
		
		
		
		Main.log(getClass(), "Print all Room IDS: ");
		
		if(roomIds!=null){
		for (String roomId : roomIds) {
			Main.log(getClass(), roomId);
		}
		}
		else{
			Main.log(getClass(), "No Rooms Found");
		}
	}

	@Override
	public void onGetLiveUserInfoDone(LiveUserInfoEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onGetMatchedRoomsDone(MatchedRoomsEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetOnlineUsersCountDone(AllUsersEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetOnlineUsersDone(AllUsersEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetUserStatusDone(LiveUserInfoEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSetCustomUserDataDone(LiveUserInfoEvent arg0) {
		// TODO Auto-generated method stub

	}

}

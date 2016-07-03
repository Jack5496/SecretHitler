package com.secrethitler.multiplayer;

import java.util.ArrayList;
import java.util.List;

import com.jack5496.secrethitler.Main;
import com.secrethitler.menu.RoomListning;
import com.shephertz.app42.gaming.multiplayer.client.events.AllRoomsEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.AllUsersEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.LiveUserInfoEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.MatchedRoomsEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.ZoneRequestListener;

public class ZoneListener implements ZoneRequestListener {
	public List<String> rooms;

	public static ZoneListener instance;

	public ZoneListener() {
		instance = this;
		rooms = new ArrayList<String>();
	}

	@Override
	public void onCreateRoomDone(RoomEvent arg0) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onCreateRoomDone");
	}

	@Override
	public void onDeleteRoomDone(RoomEvent arg0) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onDeleteRoomDone");
	}

	@Override
	public void onGetAllRoomsCountDone(AllRoomsEvent arg0) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onGetAllRoomsCountDone");
	}

	@Override
	public void onGetAllRoomsDone(AllRoomsEvent arg0) {
		Main.log(getClass(), "onGetAllRoomsDone");

		String[] roomIDs = arg0.getRoomIds();

		if (roomIDs != null) {
			Main.log(getClass(), "Rooms Found");
			for (String roomID : roomIDs) {
				if (!rooms.contains(roomID)) {
					rooms.add(roomID);
					// Main.log(getClass(), "-- "+roomID);
				}
			}
		} else {
			Main.log(getClass(), "No Rooms Found");
		}

		RoomListning.instance.allRoomsLoaded();
	}

	@Override
	public void onGetLiveUserInfoDone(LiveUserInfoEvent arg0) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onGetLiveUserInfoDone");
	}

	@Override
	public void onGetMatchedRoomsDone(MatchedRoomsEvent arg0) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onGetMatchedRoomsDone");
	}

	@Override
	public void onGetOnlineUsersCountDone(AllUsersEvent arg0) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onGetOnlineUsersCountDone");
	}

	@Override
	public void onGetOnlineUsersDone(AllUsersEvent arg0) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onGetOnlineUsersDone");
	}

	@Override
	public void onGetUserStatusDone(LiveUserInfoEvent arg0) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onGetUserStatusDone");
	}

	@Override
	public void onSetCustomUserDataDone(LiveUserInfoEvent arg0) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onSetCustomUserDataDone");
	}

}

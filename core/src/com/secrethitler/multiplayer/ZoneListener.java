package com.secrethitler.multiplayer;

import java.util.HashMap;
import com.jack5496.secrethitler.Main;
import com.shephertz.app42.gaming.multiplayer.client.events.AllRoomsEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.AllUsersEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.LiveUserInfoEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.MatchedRoomsEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.ZoneRequestListener;

public class ZoneListener implements ZoneRequestListener {
	HashMap<String, Room> rooms;

	public ZoneListener() {
		rooms = new HashMap<String, Room>();
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
		String[] roomIDs = arg0.getRoomIds();

		if (roomIDs != null) {
			for (String roomId : roomIDs) {
				rooms.put(roomId, null);
			}
		} else {
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

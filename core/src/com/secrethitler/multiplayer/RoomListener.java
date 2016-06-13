package com.secrethitler.multiplayer;

import com.jack5496.secrethitler.Main;
import com.secrethitler.menu.RoomListning;
import com.shephertz.app42.gaming.multiplayer.client.events.LiveRoomInfoEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.RoomRequestListener;

public class RoomListener implements RoomRequestListener {

	@Override
	public void onGetLiveRoomInfoDone(LiveRoomInfoEvent arg0) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onGetLiveRoomInfoDone");

		Room room = null;

		if (room == null) {
			room = new Room(arg0.getData());
			if (arg0.getJoinedUsers() != null) {
				room.setJoinedUsers(arg0.getJoinedUsers());
			}
			RoomListning.instance.addLoadedRoom(room);
		}
		// else{
		// room.roomInformationsFound(arg0.getData());
		// }
	}

	@Override
	public void onJoinAndSubscribeRoomDone(RoomEvent arg0) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onJoinAndSubscribeRoomDone");
	}

	@Override
	public void onJoinRoomDone(RoomEvent arg0) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onJoinRoomDone");

	}

	@Override
	public void onLeaveAndUnsubscribeRoomDone(RoomEvent arg0) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onLeaveAndUnsubscribeRoomDone");
	}

	@Override
	public void onLeaveRoomDone(RoomEvent arg0) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onLeaveRoomDone");
	}

	@Override
	public void onLockPropertiesDone(byte arg0) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onLockPropertiesDone");
	}

	@Override
	public void onSetCustomRoomDataDone(LiveRoomInfoEvent arg0) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onSetCustomRoomDataDone");
	}

	@Override
	public void onSubscribeRoomDone(RoomEvent arg0) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onSubscribeRoomDone");
	}

	@Override
	public void onUnSubscribeRoomDone(RoomEvent arg0) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onUnSubscribeRoomDone");
	}

	@Override
	public void onUnlockPropertiesDone(byte arg0) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onUnlockPropertiesDone");
	}

	@Override
	public void onUpdatePropertyDone(LiveRoomInfoEvent arg0) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onUpdatePropertyDone");
	}

}

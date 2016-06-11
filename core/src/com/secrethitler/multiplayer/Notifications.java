package com.secrethitler.multiplayer;

import java.util.HashMap;

import com.jack5496.secrethitler.Main;
import com.shephertz.app42.gaming.multiplayer.client.events.ChatEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.LobbyData;
import com.shephertz.app42.gaming.multiplayer.client.events.MoveEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomData;
import com.shephertz.app42.gaming.multiplayer.client.events.UpdateEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.NotifyListener;

public class Notifications implements NotifyListener {

	@Override
	public void onChatReceived(ChatEvent arg0) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onChatReceived");
	}

	@Override
	public void onGameStarted(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onGameStarted");
	}

	@Override
	public void onGameStopped(String arg0, String arg1) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onGameStopped");
	}

	@Override
	public void onMoveCompleted(MoveEvent arg0) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onMoveCompleted");
	}

	@Override
	public void onNextTurnRequest(String arg0) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onNextTurnRequest");
	}

	@Override
	public void onPrivateChatReceived(String arg0, String arg1) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onPrivateChatReceived");
	}

	@Override
	public void onPrivateUpdateReceived(String arg0, byte[] arg1, boolean arg2) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onPrivateUpdateReceived");
	}

	@Override
	public void onRoomCreated(RoomData arg0) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onRoomCreated");
	}

	@Override
	public void onRoomDestroyed(RoomData arg0) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onRoomDestroyed");
	}

	@Override
	public void onUpdatePeersReceived(UpdateEvent arg0) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onUpdatePeersReceived");
	}

	@Override
	public void onUserChangeRoomProperty(RoomData arg0, String arg1, HashMap<String, Object> arg2,
			HashMap<String, String> arg3) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onUserChangeRoomProperty");
	}

	@Override
	public void onUserJoinedLobby(LobbyData arg0, String arg1) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onUserJoinedLobby");
	}

	@Override
	public void onUserJoinedRoom(RoomData arg0, String arg1) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onUserJoinedRoom");
	}

	@Override
	public void onUserLeftLobby(LobbyData arg0, String arg1) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onUserLeftLobby");
	}

	@Override
	public void onUserLeftRoom(RoomData arg0, String arg1) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onUserLeftRoom");
	}

	@Override
	public void onUserPaused(String arg0, boolean arg1, String arg2) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onUserPaused");
	}

	@Override
	public void onUserResumed(String arg0, boolean arg1, String arg2) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onUserResumed");
	}

	

}

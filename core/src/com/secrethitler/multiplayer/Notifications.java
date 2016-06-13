package com.secrethitler.multiplayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jack5496.secrethitler.Main;
import com.secrethitler.entitys.LocalPlayer;
import com.secrethitler.helper.Message;
import com.secrethitler.menu.Room;
import com.shephertz.app42.gaming.multiplayer.client.events.ChatEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.LobbyData;
import com.shephertz.app42.gaming.multiplayer.client.events.MoveEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomData;
import com.shephertz.app42.gaming.multiplayer.client.events.UpdateEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.NotifyListener;

public class Notifications implements NotifyListener {

	static List<Message> messages;
	int maxMessagesHolding = Room.maxMessagesToShow;

	public Notifications() {
		resetMessages();
	}

	public static void resetMessages() {
		messages = new ArrayList<Message>();
	}

	public static List<Message> getMessages() {
		return messages;
	}
	
	public void addMessage(Message m){
		if (messages.size() >= maxMessagesHolding) {
			messages.remove(0);
		}
		messages.add(m);
	}

	@Override
	public void onChatReceived(ChatEvent arg0) {
		Message m = new Message(arg0);
		addMessage(m);
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
		Message m = new Message(arg0, null, arg1);
		addMessage(m);
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
	public void onUserJoinedRoom(RoomData arg0, String userName) {
		// TODO Auto-generated method stub
//		Main.log(getClass(), "onUserJoinedRoom");

		// sicher selber raum, einfach extra prüfung
		if (Multiplayer.activRoom.id.equals(arg0.getId())) {
			Multiplayer.activRoom.playerJoined(new LocalPlayer(userName));
			
			Message m = new Message("System",arg0.getId(),userName+" joined");
			addMessage(m);
		}
	}

	@Override
	public void onUserLeftLobby(LobbyData arg0, String arg1) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onUserLeftLobby");
	}

	@Override
	public void onUserLeftRoom(RoomData arg0, String userName) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onUserLeftRoom");
		if (Multiplayer.activRoom.id.equals(arg0.getId())) {
			Multiplayer.activRoom.playerLeft(new LocalPlayer(userName));
			Message m = new Message("System",arg0.getId(),userName+" left");
			addMessage(m);
		}
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

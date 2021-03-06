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

	public static String SYSTEM = "GAME";
	public static String DISCARD = "DISCARD";
	public static String UPDATECARDS = "UPDATECARDS";
	public static String PRESIDENT = "PRESIDENT";
	public static String ROLES = "ROLES";
	public static String CANCELOR = "CANCELOR";
	public static String NEXTCANCELOR = "NEXTCANCELOR";
	public static String WANTSCANCELOR = "WANTSCANCELOR";
	public static String KILLPLAYER = "KILLPLAYER";
	public static String REGEX = " ";

	public Notifications() {
		resetMessages();
	}

	public static void resetMessages() {
		messages = new ArrayList<Message>();
	}

	public static List<Message> getMessages() {
		return messages;
	}

	public void addMessage(Message m) {
		if (messages.size() >= maxMessagesHolding) {
			messages.remove(0);
		}
		messages.add(m);
	}

	public void systemMessage(String message) {
		String[] functions = message.split(REGEX);

		String what = functions[1];
		if (what.equals(UPDATECARDS)) {
			int pos = 2;

			boolean nextPresident = functions[pos++].equals("" + true);
			String playedCard = functions[pos++];
			int liberalBoard = Integer.parseInt(functions[pos++]);
			int fasictBoard = Integer.parseInt(functions[pos++]);
			int liberalCards = Integer.parseInt(functions[pos++]);
			int fasictCards = Integer.parseInt(functions[pos++]);
			int liberalDiscards = Integer.parseInt(functions[pos++]);
			int fasictDiscards = Integer.parseInt(functions[pos++]);
			Multiplayer.activRoom.activGame.setCardsAndDiscards(liberalBoard, fasictBoard, liberalCards, fasictCards,
					liberalDiscards, fasictDiscards);

			Multiplayer.activRoom.activGame.checkIfGameOver(nextPresident,playedCard);
		}
		if (what.equals(ROLES)) {
			LocalPlayer hitler = new LocalPlayer(functions[2]);
			int pos = 3;

			int liberalSize = Integer.parseInt(functions[3]);
			pos++;

			List<LocalPlayer> liberals = new ArrayList<LocalPlayer>();
			for (int i = 0; i < liberalSize; i++) {
				liberals.add(new LocalPlayer(functions[pos]));
				pos++;
			}

			int fasictSize = Integer.parseInt(functions[pos]);
			pos++;

			List<LocalPlayer> fasists = new ArrayList<LocalPlayer>();
			for (int i = 0; i < fasictSize; i++) {
				fasists.add(new LocalPlayer(functions[pos]));
				pos++;
			}

			List<LocalPlayer> presidentOrder = new ArrayList<LocalPlayer>();
			for (int i = 0; i < fasictSize + liberalSize + 1; i++) {
				presidentOrder.add(new LocalPlayer(functions[pos]));
				pos++;
			}

			Multiplayer.activRoom.activGame.recievePlayerRoles(hitler, liberals, fasists, presidentOrder);
		}
		if (what.equals(PRESIDENT)) {
			LocalPlayer president = new LocalPlayer(functions[2]);
			Multiplayer.activRoom.activGame.president = president;
			Multiplayer.activRoom.activGame.enablePresitent();
		}
		if (what.equals(CANCELOR)) {
			String card1 = functions[2];
			String card2 = functions[3];
			String discardCard = functions[4];
			Multiplayer.activRoom.activGame.enableCancelor(card1, card2, discardCard);
		}
		if (what.equals(NEXTCANCELOR)) {
			LocalPlayer candidat = new LocalPlayer(functions[2]);
			Multiplayer.activRoom.activGame.cancelor = candidat;
			Multiplayer.activRoom.activGame.votesForCancelor = 0;
			Multiplayer.activRoom.activGame.totalVotesCancelor = 0;
			Multiplayer.activRoom.enableVoteForCancelorButton();
		}
		if (what.equals(WANTSCANCELOR)) {
			Multiplayer.activRoom.activGame.totalVotesCancelor++;
			if (functions[2].equals("true")) {
				Multiplayer.activRoom.activGame.votesForCancelor++;
			}
			Multiplayer.activRoom.activGame.checkIfVoteEnded();
		}
		if(what.equals(KILLPLAYER)){
			LocalPlayer deadPerson = new LocalPlayer(functions[2]);
			Multiplayer.activRoom.activGame.killPlayer(deadPerson);
		}

	}

	public boolean isSystemMessage(String message) {
		return message.startsWith(SYSTEM);
	}

	@Override
	public void onChatReceived(ChatEvent arg0) {
		Message m = new Message(arg0);
		if (isSystemMessage(m.getMessage())) {
			systemMessage(m.getMessage());
		} else {
			addMessage(m);
		}
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
		if (isSystemMessage(m.getMessage())) {
			systemMessage(m.getMessage());
		} else {
			addMessage(m);
		}
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
		// Main.log(getClass(), "onUserJoinedRoom");

		// sicher selber raum, einfach extra pr�fung
		if (Multiplayer.activRoom.id.equals(arg0.getId())) {
			Multiplayer.activRoom.playerJoined(new LocalPlayer(userName));

			Message m = new Message("System", arg0.getId(), userName + " joined");
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
			Message m = new Message("System", arg0.getId(), userName + " left");
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

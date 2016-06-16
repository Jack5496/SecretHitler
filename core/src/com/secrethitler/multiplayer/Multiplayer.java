package com.secrethitler.multiplayer;

import java.util.List;

import com.jack5496.secrethitler.Main;
import com.secrethitler.entitys.LocalPlayer;
import com.secrethitler.entitys.LocalPlayerHandler;
import com.secrethitler.menu.Room;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;

public class Multiplayer {

	private static WarpClient warpClient = null;

	private static ChatListener chatListener;

	public static Room activRoom;

	public Multiplayer() {

		WarpClient.initialize("9a5d2fbd5118c7864d156375334a4df70fc6c3432979e5e220c64f46bbedbd8a",
				"8153173c208b6eb07b795dfed2c55e38eb7d75659763703df0049f29e77bfef0");

		try {
			warpClient = WarpClient.getInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		addListeners();
	}

	public void addListeners() {
		chatListener = new ChatListener();
		warpClient.addChatRequestListener(chatListener);

		warpClient.addConnectionRequestListener(new ConListener());
		warpClient.addLobbyRequestListener(new LobbyListener());
		warpClient.addNotificationListener(new Notifications());
		warpClient.addRoomRequestListener(new RoomListener());
		warpClient.addTurnBasedRoomListener(new TurnListener());
		warpClient.addUpdateRequestListener(new UpdateListener());
		warpClient.addZoneRequestListener(new ZoneListener());
	}

	public static void goOnline(String name) {
		warpClient.connectWithUserName(name);
	}

	public static void joinRoom(Room room) {
		if (activRoom == null) {
			activRoom = room;
			warpClient.joinAndSubscribeRoom(activRoom.id);
		}
	}

	public static void getAllRooms() {
		warpClient.getAllRooms();
	}

	public static void updateRoomInformations(String roomID) {
		warpClient.getLiveRoomInfo(roomID);
	}

	/**
	 * Nur nutzen in besonderen fällen! Nutze vorher lieber chatInRoom() oder
	 * sendPrivateMessage()
	 * 
	 * @param message
	 */
	public static void sendMessage(String message) {
		warpClient.sendChat(message);
	}

	/**
	 * Nur nutzen in besonderen fällen!
	 * 
	 * @param message
	 */
	public static void sendPrivateMessage(String message, LocalPlayer p) {
		warpClient.sendPrivateChat(p.name, "PRIVAT: " + message);
	}

	public static void updateCards(int liberalBoard, int fasictBoard, int liberalCards, int fasictCards,
			int liberalDiscards, int fasictDiscards) {
		warpClient.sendChat(Notifications.SYSTEM + Notifications.REGEX + Notifications.UPDATECARDS + Notifications.REGEX
				+ liberalBoard + Notifications.REGEX + fasictBoard + Notifications.REGEX + liberalCards
				+ Notifications.REGEX + fasictCards + Notifications.REGEX + liberalDiscards + Notifications.REGEX
				+ fasictDiscards);
	}

	public static void startPresident(LocalPlayer p) {
		warpClient.sendPrivateChat(p.name,
				Notifications.SYSTEM + Notifications.REGEX + Notifications.PRESIDENT + Notifications.REGEX + p.name);
	}

	public static void sendRoles(LocalPlayer hitler, List<LocalPlayer> liberals, List<LocalPlayer> fasicts) {
		String message = "";
		message += Notifications.SYSTEM + Notifications.REGEX;
		message += Notifications.ROLES + Notifications.REGEX;
		message += hitler.name + Notifications.REGEX;
		message += liberals.size() + Notifications.REGEX;
		Main.log(Multiplayer.class,"Hitler ist:"+ hitler.name);
		for (LocalPlayer liberal : liberals) {
			Main.log(Multiplayer.class,"Liberale sind :"+ liberal.name);
			message += liberal.name + Notifications.REGEX;
		}
		message += fasicts.size() + Notifications.REGEX;
		for (LocalPlayer fasict : fasicts) {
			Main.log(Multiplayer.class,"Fasict sind:"+ fasict.name);
			message += fasict.name + Notifications.REGEX;
		}
		message = message.substring(0, message.length()-Notifications.REGEX.length());

		Main.log(Multiplayer.class, message);
		warpClient.sendChat(message);

	}

	public static void startCancelor(LocalPlayer p, String card1, String card2) {
		warpClient.sendPrivateChat(p.name, Notifications.SYSTEM + Notifications.REGEX + Notifications.CANCELOR
				+ Notifications.REGEX + card1 + Notifications.REGEX + card2);
	}

	public static void chatInRoom() {
		chatListener.chat();
	}

	public static void chatToPlayer(LocalPlayer p) {
		chatListener.privateChat(p);
	}

	public static void leaveRoom() {
		warpClient.leaveAndUnsubscribeRoom(activRoom.id);
		activRoom = null;
	}

	public static void goOffline() {
		warpClient.disconnect();
		LocalPlayerHandler.localPlayer = null;
	}

	public void printAllRooms() {
		warpClient.getAllRooms();
	}

}

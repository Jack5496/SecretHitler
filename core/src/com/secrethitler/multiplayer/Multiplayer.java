package com.secrethitler.multiplayer;

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

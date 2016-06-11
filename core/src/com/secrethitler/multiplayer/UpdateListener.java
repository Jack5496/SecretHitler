package com.secrethitler.multiplayer;

import com.jack5496.secrethitler.Main;
import com.shephertz.app42.gaming.multiplayer.client.listener.UpdateRequestListener;

public class UpdateListener implements UpdateRequestListener {

	@Override
	public void onSendPrivateUpdateDone(byte arg0) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onSendPrivateUpdateDone");
	}

	@Override
	public void onSendUpdateDone(byte arg0) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onSendUpdateDone");
	}
	

}

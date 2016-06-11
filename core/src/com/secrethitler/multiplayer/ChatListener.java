package com.secrethitler.multiplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.jack5496.secrethitler.Main;
import com.secrethitler.Inputs.InputListener;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;
import com.shephertz.app42.gaming.multiplayer.client.listener.ChatRequestListener;

public class ChatListener implements ChatRequestListener {
	
	ChatInput listener;
	
	public ChatListener(){
		listener = new ChatInput();
		chat();
	}
	
	public void chat(){
		Gdx.input.getTextInput(listener, "Send Message", "", "Your Message");
	}

	@Override
	public void onSendChatDone(byte arg0) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onSendChatDone");
	}

	@Override
	public void onSendPrivateChatDone(byte arg0) {
		// TODO Auto-generated method stub
		Main.log(getClass(), "onSendPricateChatDone");
	}
	
	public class ChatInput implements TextInputListener {
		   @Override
		   public void input(String text) {
			   Multiplayer.goOnline(text);
Main.log(getClass(), "Joining as: "+text);
			  
		   }

		   @Override
		   public void canceled () {
			   
		   }
		}


}

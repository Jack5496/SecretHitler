package com.secrethitler.multiplayer;

import com.jack5496.secrethitler.Main;
import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.ConnectEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.ConnectionRequestListener;

public class ConListener implements ConnectionRequestListener{  
	
    @Override  
    public void onConnectDone(ConnectEvent event) {  
        if(event.getResult() == WarpResponseResultCode.SUCCESS){    
        	
        	Main.log(getClass(), "yipee I have connected"); 
        }  
    }  
    @Override  
    public void onDisconnectDone(ConnectEvent event) {
    	Main.log(getClass(), "On Disconnected invoked"); 
    }
    
	@Override
	public void onInitUDPDone(byte arg0) {
		// TODO Auto-generated method stub
		
	}      
}   
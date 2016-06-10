package com.secrethitler.multiplayer;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;  

public class OnlineConnector {

	public void OnlineConnector(){
		WarpClient.initialize("9a5d2fbd5118c7864d156375334a4df70fc6c3432979e5e220c64f46bbedbd8a","8153173c208b6eb07b795dfed2c55e38eb7d75659763703df0049f29e77bfef0"); 
		
		
		WarpClient myGame = null;
		try {
			myGame = WarpClient.getInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		myGame.addConnectionRequestListener(new ConListener());       
	}

}

package com.jack5496.secrethitler.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jack5496.secrethitler.Main;

public class DesktopLauncher {

	public static final String iphone5 = "IPhone 5";
	public static final String iphone6 = "IPhone 6";
	public static final String samsung = "Samsung";
	public static final String htc = "HTC";
	public static final String ipad = "IPad";

	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "SecretPeter";
		String model = iphone5;

		if (model.equals(iphone5)) {
			config.width = 1163;
			config.height = 640;
		}
		if (model.equals(iphone6)) {
			config.width = 1334;
			config.height = 750;
		}
		if (model.equals(samsung)) {
			config.width = 2560 / 4;
			config.height = 1440 / 4;
		}
		if (model.equals(htc)) {
			config.width = 1920 / 2;
			config.height = 1080 / 2;
		}
		if (model.equals(ipad)) {
			config.width = 1024;
			config.height = 768;
		}
		new LwjglApplication(new Main(), config);
	}
}

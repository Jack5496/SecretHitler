package com.secrethitler.uiElements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jack5496.secrethitler.Main;
import com.jack5496.secrethitler.ResourceLoader;
import com.secrethitler.Inputs.Button;

public class GUIButton extends Button{
	
	static BitmapFont font = new BitmapFont();
	static{font.setColor(Color.BLACK); }
	
	int xper;
	int yper;
	String label;
	String buttonName;
	
	public GUIButton(String label, String buttonName, int xpos, int ypos){
		this.label = label;
		this.xper = xpos;
		this.yper = ypos;
		this.buttonName = buttonName;
	}
	
	public void render(SpriteBatch batch){
		int xpos = Main.getInstance().getWidth()*xper/100;
		int ypos = Main.getInstance().getHeight()*yper/100;
		batch.draw(ResourceLoader.getInstance().getButton("test"), xpos, ypos);
		font.draw(batch, label, xpos, ypos);
	}
	
}
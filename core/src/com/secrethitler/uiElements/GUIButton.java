package com.secrethitler.uiElements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GUIButton {
	
	static BitmapFont font = new BitmapFont();
	static{font.setColor(Color.BLACK); }
	
	int xpos;
	int ypos;
	String label;
	
	public GUIButton(String label, int xpos, int ypos){
		this.label = label;
		this.xpos = xpos;
		this.ypos = ypos;
	}
	
	public void render(SpriteBatch batch){
		font.draw(batch, label, xpos, ypos);
	}
	
}
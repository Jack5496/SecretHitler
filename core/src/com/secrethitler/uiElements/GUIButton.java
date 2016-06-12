package com.secrethitler.uiElements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jack5496.secrethitler.Main;
import com.jack5496.secrethitler.ResourceLoader;
import com.secrethitler.Inputs.Button;

public class GUIButton extends Button {

	static BitmapFont font = new BitmapFont();
	static {
		font.setColor(Color.BLACK);
	}

	boolean hoverd;
	int xper;
	int yper;
	public String label;
	String buttonName;

	public GUIButton(String label, String buttonName, int xpos, int ypos) {
		this.label = label;
		this.xper = xpos;
		this.yper = ypos;
		this.buttonName = buttonName;
		this.hoverd = false;
	}

	public void setHovered(boolean hoverd) {
		this.hoverd = hoverd;
	}

	public void render(SpriteBatch batch) {
		int xpos = Main.getInstance().getWidth() * xper / 100;
		int ypos = Main.getInstance().getHeight() * yper / 100;
		
		if(buttonName!=null){
		batch.draw(ResourceLoader.getInstance().getButton(buttonName), xpos, ypos);
		}
		
		if (this.hoverd) {
			font.setColor(Color.RED);
		}
		font.draw(batch, label, xpos, ypos);
		font.setColor(Color.BLACK);
	}
	
	public GUIButton left;
	public GUIButton right;
	public GUIButton abouve;
	public GUIButton down;
	
	public void setNeighbors(GUIButton left, GUIButton right, GUIButton abouve, GUIButton down){
		this.left = left;
		this.right = right;
		this.abouve = abouve;
		this.down = down;
	}
	
	

}
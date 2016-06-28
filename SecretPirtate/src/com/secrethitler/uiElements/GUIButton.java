package com.secrethitler.uiElements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
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
	public float xper;
	public float yper;
	public int width;
	public int height;

	private int widthSRC;
	private int heightSRC;

	public String text;
	public String texture;

	public float scale;

	public boolean onHoverBigger = false;

	public GUIButton(String label, String buttonName, float xpos, float ypos, float scale) {
		this.text = label;
		this.xper = xpos;
		this.yper = ypos;
		this.texture = buttonName;
		this.scale = scale;
		if (buttonName != null) {
			Texture button = ResourceLoader.getInstance().getButton(buttonName);
			widthSRC = button.getWidth();
			heightSRC = button.getHeight();
		}

		this.hoverd = false;
	}
	
	public GUIButton setOnHoverBigger(boolean bigger){
		this.onHoverBigger = bigger;
		return this;
	}

	public void pressAt(int x, int y) {
		int xpos = (int) (Main.getInstance().getWidth() * xper / 100);
		int ypos = (int) (Main.getInstance().getHeight() * yper / 100);

		this.release();
		if (xpos - width / 2 < x && x < xpos + width / 2) {
			if (ypos - height / 2 < y && y < ypos + height / 2) {
				this.press();
			}
		}
	}

	public void setHovered(boolean hoverd) {
		this.hoverd = hoverd;
	}

	public void render(SpriteBatch batch) {
		float drawScale = this.scale;

		if (this.hoverd) {
			if (onHoverBigger) {
				drawScale += 0.025f;
			}
			font.setColor(Color.RED);
		}

		int xpos = (int) (Main.getInstance().getWidth() * xper / 100);
		int ypos = (int) (Main.getInstance().getHeight() * yper / 100);

		if (texture != null) {
			Texture button = ResourceLoader.getInstance().getButton(texture);

			width = (int) (Main.getInstance().getWidth() * drawScale);
			float xscale = (float) width / (float) button.getWidth();
			height = (int) (button.getHeight() * xscale);

			batch.draw(button, xpos - (width / 2), ypos - (height / 2), width, height);
		}

	

		float width = 50f;// contains the width of the current set text
		float height = 50f; // contains the height of the current set
										// text

		font.draw(batch, text, xpos - (width / 2), ypos + (height / 2)); // text
																			// wird
																			// nach
																			// unten
																			// rechts
																			// gezeichnet
		font.setColor(Color.BLACK);
	}

}
package com.secrethitler.uiElements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
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
	String buttonName;

	public float scale;

	public GUIButton(String label, String buttonName, float xpos, float ypos, float scale) {
		this.text = label;
		this.xper = xpos;
		this.yper = ypos;
		this.buttonName = buttonName;
		this.scale = scale;
		if (buttonName != null) {
			Texture button = ResourceLoader.getInstance().getButton(buttonName);
			widthSRC = button.getWidth();
			heightSRC = button.getHeight();
		}

		this.hoverd = false;
	}

	public void pressAt(int x, int y) {
		int xpos = (int) (Main.getInstance().getWidth() * xper / 100);
		int ypos = (int) (Main.getInstance().getHeight() * yper / 100);

		this.release();
		if (xpos - width/2 < x && x < xpos + width/2) {
			if (ypos - height/2 < y && y < ypos + height/2) {
				this.press();
			}
		}
	}

	public void setHovered(boolean hoverd) {
		this.hoverd = hoverd;
	}

	public void render(SpriteBatch batch) {
		int xpos = (int) (Main.getInstance().getWidth() * xper / 100);
		int ypos = (int) (Main.getInstance().getHeight() * yper / 100);

		if (buttonName != null) {
			Texture button = ResourceLoader.getInstance().getButton(buttonName);

			width = (int) (Main.getInstance().getWidth() * this.scale);
			float xscale = (float) width / (float) button.getWidth();
			height = (int) (button.getHeight() * xscale);

			batch.draw(button, xpos - (width / 2), ypos - (height / 2), width, height);
		}

		if (this.hoverd) {
			font.setColor(Color.RED);
		}

		GlyphLayout layout = new GlyphLayout(); // dont do this every frame!
												// Store it as member
		layout.setText(font, text);

		float width = layout.width;// contains the width of the current set text
		float height = layout.height; // contains the height of the current set
										// text

		font.draw(batch, text, xpos - (width / 2), ypos + (height / 2)); // text
																			// wird
																			// nach
																			// unten
																			// rechts
																			// gezeichnet
		font.setColor(Color.BLACK);
	}

	public GUIButton left;
	public GUIButton right;
	public GUIButton abouve;
	public GUIButton down;

	public void setNeighbors(GUIButton left, GUIButton right, GUIButton abouve, GUIButton down) {
		this.left = left;
		this.right = right;
		this.abouve = abouve;
		this.down = down;
	}

}
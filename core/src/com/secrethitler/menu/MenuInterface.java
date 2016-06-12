package com.secrethitler.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface MenuInterface {

	public void render(SpriteBatch batch);
	public void enter();
	public void up();
	public void down();
	public void left();
	public void right();
	public void keyTyped(final int keycode);
	public void clicked(int x, int y);
	public void mouseMoved(int x, int y);
}
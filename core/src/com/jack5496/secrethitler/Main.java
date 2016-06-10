package com.jack5496.secrethitler;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		instance = this;
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}
	
	private static Main instance;
	
	public static Main getInstance() {
		return instance;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	public static void log(Class<?> c, String log) {
		System.out.println(c.getSimpleName() + ": " + log);
	}
}

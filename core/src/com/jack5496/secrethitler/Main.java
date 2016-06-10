package com.jack5496.secrethitler;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.secrethitler.Inputs.InputHandler;
import com.secrethitler.entitys.LocalPlayerHandler;
import com.secrethitler.menu.MenuHandler;
import com.secrethitler.multiplayer.OnlineConnector;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	BitmapFont font;
	Texture img;

	private static Main instance;

	public InputHandler inputHandler;
	public OnlineConnector onlineConnector;
	public LocalPlayerHandler playerHandler;
	public MenuHandler menuHandler;

	@Override
	public void create() {
		instance = this;
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		font = new BitmapFont();
        font.setColor(Color.BLACK);

		onlineConnector = new OnlineConnector();
		menuHandler = new MenuHandler();
		initPlayerHandler();
		initInputHandler();
	}

	private void initInputHandler() {
		inputHandler = new InputHandler();
	}

	private void initPlayerHandler() {
		playerHandler = new LocalPlayerHandler();
	}

	public static Main getInstance() {
		return instance;
	}
	
	GlyphLayout layout = new GlyphLayout();

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		String fps = Gdx.graphics.getFramesPerSecond()+"";
		
		int lineHeight = (int) font.getLineHeight();
		int height = Gdx.graphics.getHeight();
		
		layout.setText(font, fps);
		int fpsLength = (int) layout.width;
		
		int width = Gdx.graphics.getWidth();
		
		batch.begin();
//		batch.draw(img, 0, 0);
		font.draw(batch, fps, width-fpsLength, height);
		batch.end();
	}

	public static void log(Class<?> c, String log) {
		System.out.println(c.getSimpleName() + ": " + log);
	}
}

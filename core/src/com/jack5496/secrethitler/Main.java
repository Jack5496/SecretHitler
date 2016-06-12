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
import com.secrethitler.entitys.LocalPlayer;
import com.secrethitler.entitys.LocalPlayerHandler;
import com.secrethitler.menu.MenuHandler;
import com.secrethitler.multiplayer.Multiplayer;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	BitmapFont font;
	Texture img;

	private static Main instance;
	private int width;
	private int height;

	public InputHandler inputHandler;
	public Multiplayer onlineConnector;
	public MenuHandler menuHandler;
	
	ResourceLoader loader;

	@Override
	public void create() {
		instance = this;
		loader = new ResourceLoader();
		
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		font = new BitmapFont();
		font.setColor(Color.BLACK);

		initInputHandler();
		onlineConnector = new Multiplayer();
		menuHandler = new MenuHandler();
		LocalPlayerHandler.openPlayerNameInput();
	}

	private void initInputHandler() {
		inputHandler = new InputHandler();
	}

	public static Main getInstance() {
		return instance;
	}

	GlyphLayout layout = new GlyphLayout();

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		MenuHandler.render(batch);
		batch.end();

	}

	public static void log(Class<?> c, String log) {
		System.out.println(c.getSimpleName() + ": " + log);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	boolean first = true;

	@Override
	public void resize(int width, int height) {
		Main.log(getClass(), "Screen resize: " + width + "x" + height);
		this.height = height;
		this.width = width;
	}
}

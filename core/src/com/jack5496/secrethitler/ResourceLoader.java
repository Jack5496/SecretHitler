package com.jack5496.secrethitler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class ResourceLoader{
	
	public AssetManager assets;
	
	public static ResourceLoader instance;
	
	public ResourceLoader(){
		instance = this;
		assets = new AssetManager();
	}
	
	public static ResourceLoader getInstance(){
		return instance;
	}
	
	public static String data = "data/";
	
	public static String buttons = data+"buttons/";
	
	public void addToLoad(String name) {
		assets.load(Gdx.files.internal(name).path(), Texture.class);
	}
	
	private void loadAsset(String name){
		addToLoad(name);
		float progress = 0;
		Main.log(getClass(), "Loading: "+name);
		while (!assets.update()) {
			if (progress != assets.getProgress()) {
				progress = assets.getProgress();
				Main.log(getClass(), "Loaded: " + assets.getProgress() * 100 + "%");
			}
		}
	}
	
	public Texture getTexture(String name){
		if(!assets.isLoaded(name)){
			loadAsset(name);
		}
		return assets.get(name);
	}
	
	
	public Texture getButton(String name){
		return getTexture(buttons+name+".png");
	}
	
}

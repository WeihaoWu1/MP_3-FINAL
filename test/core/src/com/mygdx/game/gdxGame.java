package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Enemies.Minotaur;
import com.mygdx.game.Screens.MenuScreen;

import java.util.ArrayList;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.Towers.FireTower;
import com.mygdx.game.Towers.RockTower;

public class gdxGame extends Game{
	public static SpriteBatch batch;
	Texture img;
	public static ArrayList<FireTower> firetowers = new ArrayList<FireTower>();
	public static ArrayList<RockTower> rocktowers = new ArrayList<RockTower>();

	private double cooldown = 10;
	private double startTime = System.nanoTime();
	private double currentTime;
	private double lastSlime = startTime;

	public static ArrayList<Minotaur> minotaurs = new ArrayList<Minotaur>();

	private int slimeCount;

	private int slimeIndex = -1;
//	Slime slime = new Slime(-44, 179, 25,25);


	private final int fps_set = 60;
	private final int ups_set = 200;
//	private Thread gameThread;

//	private void startGameLoop() {
//		gameThread = new Thread(this);
//		gameThread.start();	}

	public static AssetManager assetManager;
	public static Music backgroundMusic;
	public static Sound soundEffect;
	public static Sound soundEffect1;
	@Override
	public void create () {
		setScreen(new MenuScreen(this));
		batch = new SpriteBatch();
		assetManager = new AssetManager();
		assetManager.load("menuMusic.mp3", Music.class);
		assetManager.load("minotaurDeath.mp3", Sound.class);
		assetManager.load("bossDeath.mp3",Sound.class);
//		assetManager.load("audio/sound_effect.mp3", Sound.class);
		assetManager.finishLoading();
//
		backgroundMusic = assetManager.get("menuMusic.mp3", Music.class);
		soundEffect = assetManager.get("minotaurDeath.mp3", Sound.class);
		soundEffect1 = assetManager.get("bossDeath.mp3",Sound.class);

//		soundEffect = assetManager.get("audio/sound_effect.mp3", Sound.class);

		backgroundMusic.setLooping(true);
		backgroundMusic.play();
		backgroundMusic.setVolume(0.2f);


	}


	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
	}
	
//	public FireTowerAnimation getFireTower(){
//		return fireTowerAnimation;
//	}
//
//	public RockTowerAnimation getRockTower(){
//		return rockTowerAnimation;
//	}


	public String toString(){
		return "OVERRIDEN";
	}
	public boolean equals(Object obj){
		return true;
	}
}

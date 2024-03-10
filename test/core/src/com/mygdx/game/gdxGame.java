package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Enemies.Slime;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Towers.FireTowerAnimation;
import com.mygdx.game.Towers.RockTowerAnimation;
import java.util.ArrayList;

public class gdxGame extends Game implements Runnable{
	public static SpriteBatch batch;
	Texture img;
	ArrayList<FireTowerAnimation> firetowers = new ArrayList<FireTowerAnimation>();
	ArrayList<RockTowerAnimation> rocktowers = new ArrayList<RockTowerAnimation>();

	private double cooldown = 10;
	private double startTime = System.nanoTime();
	private double currentTime;
	private double lastSlime = startTime;

	public static ArrayList<Slime> slimes = new ArrayList<Slime>();

	private int slimeCount;

	private int slimeIndex = -1;
//	Slime slime = new Slime(-44, 179, 25,25);


	private final int fps_set = 60;
	private final int ups_set = 200;
//	private Thread gameThread;

//	private void startGameLoop() {
//		gameThread = new Thread(this);
//		gameThread.start();	}


	@Override
	public void create () {
//		Gdx.graphics.getDeltaTime()
//		startGameLoop();
		setScreen(new PlayScreen(this));
//		currentTime = System.nanoTime();
		batch = new SpriteBatch();
//		img = new Texture("map3.png");
//		fireTowerAnimation = new FireTowerAnimation();
//		firetowers.add(fireTowerAnimation);
//		rockTowerAnimation = new RockTowerAnimation();
//		fireTowerAnimation.create();
//		rockTowerAnimation.create();
////		if (((currentTime - lastSlime) >= cooldown)) {
//		if (slimes.size() < 5) {
//			slimes.add(new Slime(-50, 179, 25, 25));
//			System.out.println("added");
//			slimeIndex++;
//		}
//		for (Slime s : slimes) s.create();
//		System.out.println("Slime number" + slimeIndex);
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

	@Override
	public void run() {
		double timePerFrame = 1000000000.0 / fps_set;
		double timePerUpdate = 1000000000.0 / ups_set;
		long previousTime = System.nanoTime();
		int updates = 0;
		int frames = 0;
		long lastCheck = System.currentTimeMillis();
		double deltaU = 0;
		double deltaF = 0;
		while(true) {
			long currentTime = System.nanoTime();
			deltaU += (currentTime - previousTime)/ timePerUpdate;
			deltaF += (currentTime - previousTime)/ timePerFrame;
			previousTime = currentTime;
			if (deltaU >= 1){
//				update();
				updates++;
				deltaU--;
			}
			if (deltaF >= 1) {
//				gamePanel.repaint();
				frames++;
				deltaF--;
			}
			if(System.currentTimeMillis() - lastCheck >= 1000){
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}
}

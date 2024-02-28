package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Enemies.Slime;

import java.util.ArrayList;

public class Game extends ApplicationAdapter implements Runnable{
	public static SpriteBatch batch;
	Texture img;
	FireTowerAnimation fireTowerAnimation = new FireTowerAnimation();
	RockTowerAnimation rockTowerAnimation = new RockTowerAnimation();
	ArrayList<FireTowerAnimation> firetowers = new ArrayList<FireTowerAnimation>();
	ArrayList<RockTowerAnimation> rocktowers = new ArrayList<RockTowerAnimation>();

	ArrayList<Slime> slimes = new ArrayList<Slime>();
	Slime slime = new Slime(-44, 179, 25,25);

	private final int fps_set = 60;
	private final int ups_set = 200;
	private Thread gameThread;

	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}

//	public void drawBackground(){
//		batch.begin();
//		batch.draw(img, 0, 0, 1760, 960);
//		batch.end();
//	}

	@Override
	public void create () {
		startGameLoop();
		batch = new SpriteBatch();
		img = new Texture("map3.png");
		fireTowerAnimation = new FireTowerAnimation();
		firetowers.add(fireTowerAnimation);
		rockTowerAnimation = new RockTowerAnimation();
		fireTowerAnimation.create();
		rockTowerAnimation.create();
		slime.create();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 1, 1, 1);
		batch.begin();
		batch.draw(img, 0, 0, 1760, 960);
		fireTowerAnimation.render();
		rockTowerAnimation.render();
		slime.render();
		slime.move();
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		fireTowerAnimation.dispose();
		rockTowerAnimation.dispose();
		slime.dispose();
	}
	
	public FireTowerAnimation getFireTower(){
		return fireTowerAnimation;
	}

	public RockTowerAnimation getRockTower(){
		return rockTowerAnimation;
	}

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

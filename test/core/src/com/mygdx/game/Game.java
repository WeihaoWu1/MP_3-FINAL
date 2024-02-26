package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.ScreenUtils;
import java.awt.*;

public class Game extends ApplicationAdapter implements Runnable{
	public static SpriteBatch batch;
	Texture img;
	FireTowerAnimation fireTowerAnimation = new FireTowerAnimation();
	RockTowerAnimation rockTowerAnimation = new RockTowerAnimation();

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
		rockTowerAnimation = new RockTowerAnimation();
		fireTowerAnimation.create();
		rockTowerAnimation.create();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 1, 1, 1);
		batch.begin();
		batch.draw(img, 0, 0, 1760, 960);
		fireTowerAnimation.render();
		rockTowerAnimation.render();
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		fireTowerAnimation.dispose();
		rockTowerAnimation.dispose();
	}
	
//	private Stage stage;
//	private Texture backgroundImage;

//	@Override
//	public void create () {
//		stage = new Stage();
//
//		// Load the background image
//		backgroundImage = new Texture("map3.png");
//
//		// Create an Image actor with the background texture
//		Image background = new Image(backgroundImage);
//		background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//
//		// Add the background to the stage
//		stage.addActor(background);
//
//		// Make sure the stage can receive input events
//		Gdx.input.setInputProcessor(stage);
//		fireTowerAnimation.create();
//		rockTowerAnimation.create();
//	}

//	@Override
//	public void render () {
//		// Clear the screen
//		Gdx.gl.glClearColor(1, 1, 1, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//		// Update and draw the stage
//		stage.act();
//		stage.draw();
////		fireTowerAnimation.render();
////		rockTowerAnimation.render();
//	}

//	@Override
//	public void dispose () {
//		fireTowerAnimation.dispose();
//		rockTowerAnimation.dispose();
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

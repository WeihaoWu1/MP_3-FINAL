package com.mygdx.game.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.gdxGame;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class EndScreen implements Screen {
    private Texture endIcon = new Texture("endScreen.png");
    private gdxGame game;
    private OrthographicCamera camera;
    private StretchViewport viewport;
    private int originalScreenWidth = 1760;
    private  int originalScreenHeight = 1080;
    public EndScreen(gdxGame game){
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(originalScreenWidth, originalScreenHeight, camera);
        camera.position.set(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2, 0);
        camera.update();
    }
    @Override
    public void show() {
        gdxGame.assetManager.load("endMusic.mp3", Music.class);
        gdxGame.assetManager.finishLoading();
        gdxGame.backgroundMusic.pause();
        gdxGame.backgroundMusic = gdxGame.assetManager.get("endMusic.mp3", Music.class);
        gdxGame.backgroundMusic.setLooping(true);
        gdxGame.backgroundMusic.setVolume(0.5f);
        gdxGame.backgroundMusic.play();
    }

    @Override
    public void render(float delta) {
        gdxGame.batch.begin();
        gdxGame.batch.draw(endIcon, 0, 0 ,1760,1080);
        gdxGame.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        gdxGame.batch.dispose();
    }
}

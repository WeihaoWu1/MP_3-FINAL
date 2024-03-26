package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.gdxGame;
import com.badlogic.gdx.audio.Music;

public class MapSelection implements Screen, InputProcessor {
    private Texture mapScreen = new Texture("mapSelection.png");
    private gdxGame game;

    private int mapNum;
    private OrthographicCamera camera;
    private StretchViewport viewport;
    private int originalScreenWidth = 1760;
    private  int originalScreenHeight = 1080;

    public MapSelection(gdxGame game){
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(originalScreenWidth, originalScreenHeight, camera);
        camera.position.set(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2, 0);
        camera.update();
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.input.setInputProcessor(this);
        gdxGame.batch.begin();
        gdxGame.batch.draw(mapScreen, 0, 0 ,1760,1080);
        gdxGame.batch.end();

    }

    public int getMap(){
        return mapNum;
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

    }
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (screenX >= 93 && screenX <= 797 && screenY >= 391 && screenY <= 818) {
            mapNum = 1;
            gdxGame.backgroundMusic.pause();
            gdxGame.assetManager.load("map3music.mp3", Music.class);
            gdxGame.assetManager.finishLoading();
            gdxGame.backgroundMusic = gdxGame.assetManager.get("map3music.mp3", Music.class);
            gdxGame.backgroundMusic.setLooping(true);
            game.setScreen(new PlayScreen(game, mapNum));
            gdxGame.backgroundMusic.setVolume(0.07f);
            gdxGame.backgroundMusic.play();
        }
        if (screenX >= 943 && screenX <= 1660 && screenY >= 391 && screenY <= 818){
            mapNum = 2;
            gdxGame.backgroundMusic.pause();
            gdxGame.assetManager.load("map4music.mp3", Music.class);
            gdxGame.assetManager.finishLoading();
            gdxGame.backgroundMusic = gdxGame.assetManager.get("map4music.mp3", Music.class);
            gdxGame.backgroundMusic.setLooping(true);
            game.setScreen(new PlayScreen(game, mapNum));
            gdxGame.backgroundMusic.setVolume(0.07f);
            gdxGame.backgroundMusic.play();

        }
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

}

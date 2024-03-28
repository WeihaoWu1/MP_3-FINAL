package com.mygdx.game.Screens;

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

import java.awt.*;

public class MenuScreen implements Screen, InputProcessor {
    private Texture menuIcon = new Texture("menu.png");
    private gdxGame game;
    private OrthographicCamera camera;
    private StretchViewport viewport;
    private int originalScreenWidth = 1760;
    private  int originalScreenHeight = 1080;

    public MenuScreen(gdxGame game){
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
        gdxGame.batch.draw(menuIcon, 0, 0 ,1760,1080);
        gdxGame.batch.end();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
//        System.out.println(width);
//        System.out.println(height);
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
        //656 - 1070
        //873 - 1008
        if (screenX >= 656 && screenX <= 1070 && screenY >= 873 && screenY <= 1008) {
            game.setScreen(new MapSelection(game));
            return true;
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
    public String toString(){
        return "OVERRIDEN";
    }
    public boolean equals(Object obj){
        return true;
    }
}

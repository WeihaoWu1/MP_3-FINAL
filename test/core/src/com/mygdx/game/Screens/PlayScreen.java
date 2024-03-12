package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Enemies.Slime;
import com.mygdx.game.Towers.FireTowerAnimation;
import com.mygdx.game.Towers.RockTowerAnimation;
import com.mygdx.game.gdxGame;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class PlayScreen implements Screen, InputProcessor {
    private gdxGame game;
    private Texture fireTowerButton = new Texture("firetowericon.png");
    private Texture rockTowerButton = new Texture("rocktowericon.png");
    Texture img = new Texture("map3.png");

    public static ArrayList<FireTowerAnimation> firetowers = new ArrayList<FireTowerAnimation>();
    public static ArrayList<RockTowerAnimation> rocktowers = new ArrayList<RockTowerAnimation>();
    public static ArrayList<Slime> slimes = new ArrayList<Slime>();
    int slimeCount;

    private FireTowerAnimation selectedFireTower = new FireTowerAnimation(0, 0, 80, 120);
    private RockTowerAnimation selectedRockTower  = new RockTowerAnimation(80,0,80,120);

    private int fireTowerIndex;
    private int rockTowerIndex;

    private boolean isDraggingFire;
    private boolean isDraggingRock;

    private boolean currentlyFireTower;
    private boolean currentlyRockTower;


    public PlayScreen(gdxGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        int x = -55;
        for (int i = 0; i < 5; i++) {
            slimes.add(new Slime(x, 299, 100, 100));
            x -= 180;
            System.out.println(slimeCount);
            slimeCount++;
        }
        for (Slime s : slimes) s.create();
//        firetowers.add(new FireTowerAnimation(0, 0, 50, 50));
//        rocktowers.add(new RockTowerAnimation(200, 200, 50, 50));
//        firetowers.get(0).create();
//        rocktowers.get(0).create();
        selectedFireTower.create();
        selectedRockTower.create();
    }

    @Override
    public void render(float delta) {
        Gdx.input.setInputProcessor(this);
        ScreenUtils.clear(1, 1, 1, 1);
        gdxGame.batch.begin();
        slimeCount=0;
        gdxGame.batch.draw(img, 0, 120, 1760, 960);
        gdxGame.batch.draw(fireTowerButton, 0, 0, 80,120);
        gdxGame.batch.draw(rockTowerButton, 80, 0, 80,120);
        for (Slime s : slimes) {
            s.render();
            s.spawnProjectile();
        }
//        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && Gdx.input.getX() > 0 && Gdx.input.getX() < 80 && Gdx.input.getY() > 0 && Gdx.input.getY() < 120) selectedFireTower = new FireTowerAnimation(selectedFireTowerX,selectedFireTowerY,80,120);
//        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && Gdx.input.getX() > 80 && Gdx.input.getX() < 160 && Gdx.input.getY() > 0 && Gdx.input.getY() < 120) selectedFireTower = new FireTowerAnimation(80,0,80,120);
        for (FireTowerAnimation f : firetowers) {
            f.render();
        }
        for (RockTowerAnimation r : rocktowers) {
            r.render();
        }
        if (isDraggingFire) selectedFireTower.render();
        if (isDraggingRock) selectedRockTower.render();
        gdxGame.batch.end();
    }

    @Override
    public void resize(int width, int height) {

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
        img.dispose();
//        fireTowerAnimation.dispose();
//        rockTowerAnimation.dispose();
        for (Slime s : slimes) {
            s.dispose();
        }
        for (FireTowerAnimation f : firetowers) {
            f.dispose();
        }
        for (RockTowerAnimation r : rocktowers) {
            r.dispose();
        }
        rockTowerButton.dispose();
        fireTowerButton.dispose();

    }

//    @Override
//    public void mouseClicked(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//        System.out.println("clicked");
//        if (e.getX() > 0 && e.getX() < 80 && e.getY() > 0 && e.getY() < 120){
//            selectedTower = 0;
//        }
//        if (e.getX() > 80 && e.getX() < 160 && e.getY() > 0 && e.getY() < 120){
//            selectedTower = 1;
//        }
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseExited(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseDragged(MouseEvent e) {
//        if (selectedTower == 0){
//            FireTowerAnimation selectedFireTower = new FireTowerAnimation(e.getX(), e.getY(), 100, 100);
//            System.out.println("created");
//            selectedFireTower.create();
//            selectedFireTower.render();
//            selectedFireTower.dispose();
//        }
//        if (selectedTower == 1){
//            RockTowerAnimation selectedRockTower = new RockTowerAnimation(e.getX(), e.getY(),100,100);
//            selectedRockTower.create();
//            selectedRockTower.render();
//            selectedRockTower.dispose();
//        }
//    }
//
//    @Override
//    public void mouseMoved(MouseEvent e) {
//
//
//    }

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
        if (screenX > 0 && screenX < 80 && 1080-screenY > 0 && 1080-screenY < 120){
            currentlyFireTower = true;
            System.out.println("TRUE");
            return true;
        }
        else if (screenX > 80 && screenX < 160 && 1080-screenY > 0 && 1080-screenY < 120){
            currentlyRockTower = true;
            System.out.println("ROCK");
            return true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (currentlyFireTower) {
            firetowers.add(new FireTowerAnimation(screenX, 1080 - screenY, 50, 50));
            firetowers.get(fireTowerIndex).create();
            fireTowerIndex++;
            selectedFireTower = new FireTowerAnimation(0, 0, 80, 120);
            selectedFireTower.create();
            isDraggingFire = false;
            currentlyFireTower = false;
            return true;
        }
        else if (currentlyRockTower) {
            rocktowers.add(new RockTowerAnimation(screenX, 1080 - screenY, 50, 50));
            rocktowers.get(rockTowerIndex).create();
            rockTowerIndex++;
            selectedRockTower = new RockTowerAnimation(80, 0, 80, 120);
            selectedRockTower.create();
            isDraggingRock = false;
            currentlyRockTower = false;
             System.out.println("GAY");
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
        if (currentlyFireTower) {
            isDraggingFire = true;
            selectedFireTower.setX(Gdx.input.getX());
            selectedFireTower.setY(1080 - Gdx.input.getY());
            return true;
        }
        else if (currentlyRockTower){
            isDraggingRock = true;
            selectedRockTower.setX(screenX);
            selectedRockTower.setY(1080 - screenY);
            System.out.println("DRAGROCK");
            System.out.println(screenX);
            System.out.println(screenY);
            return true;
        }
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

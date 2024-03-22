package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Enemies.Minotaur;
import com.mygdx.game.Projectiles.Fire;
import com.mygdx.game.Projectiles.Rock;
import com.mygdx.game.Towers.FireTowerAnimation;
import com.mygdx.game.Towers.RockTowerAnimation;
import com.mygdx.game.gdxGame;

import java.util.ArrayList;

public class PlayScreen implements Screen, InputProcessor {
    private gdxGame game;
    private Texture fireTowerButton = new Texture("firetowericon.png");
    private Texture rockTowerButton = new Texture("rocktowericon.png");
    Texture img = new Texture("map4.png");

    public static ArrayList<FireTowerAnimation> firetowers = new ArrayList<FireTowerAnimation>();
    public static ArrayList<RockTowerAnimation> rocktowers = new ArrayList<RockTowerAnimation>();
    public static ArrayList<Minotaur> minotaurs = new ArrayList<Minotaur>();
//    public static ArrayList<Fire> fires = new ArrayList<Fire>();
    public static ArrayList<Rock> rocks = new ArrayList<Rock>();
    int slimeCount;

    private FireTowerAnimation selectedFireTower = new FireTowerAnimation(0, 0, 80);
    private RockTowerAnimation selectedRockTower  = new RockTowerAnimation(80,0,80);

    private int fireTowerIndex;
    private int rockTowerIndex;

    private boolean isDraggingFire;
    private boolean isDraggingRock;

    private boolean currentlyFireTower;
    private boolean currentlyRockTower;
    private long lastAttack = 0;
    private long coolDownTime = 1000;
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    public static int level = 1;
    public BitmapFont font = new BitmapFont();
    private String displayLevel = "Level: 1";
    public static int dinero = 100;
    private String displayDinero = "$ " + dinero;


    public PlayScreen(gdxGame game) {
        this.game = game;
    }


//    public void cooldown() {
//        long time = System.currentTimeMillis();
//        if (time > lastAttack + coolDownTime) {
//            // Do something
//            lastAttack = time;
//        }
//    }

    @Override
    public void show() {
        int x = 1650;
        //1650
        //-55
        for (int i = 0; i < level*5; i++) {
            minotaurs.add(new Minotaur(x, 835));
            //299
            //835
            x += 180;
//            System.out.println(slimeCount);
            slimeCount++;
        }
        for (Minotaur s : minotaurs) {
            s.create();
            System.out.println(s);
        }
//        firetowers.add(new FireTowerAnimation(0, 0, 50, 50));
//        rocktowers.add(new RockTowerAnimation(200, 200, 50, 50));
//        firetowers.get(0).create();
//        rocktowers.get(0).create();
        selectedFireTower.create();
        selectedRockTower.create();
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
    }

    @Override
    public void render(float delta) {
        displayDinero = "$ " + dinero;
        Gdx.input.setInputProcessor(this);
        ScreenUtils.clear(1, 1, 1, 1);
        gdxGame.batch.begin();
//        shapeRenderer.setColor(Color.BLUE);

        slimeCount=0;
        gdxGame.batch.draw(img, 0, 120, 1760, 960);
        font.getData().setScale(5f);
        font.draw(gdxGame.batch, displayLevel, 1450, 1050);
        font.draw(gdxGame.batch, displayDinero, 0, 1050);
        gdxGame.batch.draw(fireTowerButton, 0, 0, 80,120);
        gdxGame.batch.draw(rockTowerButton, 80, 0, 80,120);
        for (Minotaur s : minotaurs) {
            s.render();
        }
        for (FireTowerAnimation f : firetowers) {
            f.render();
            f.spawnProjectile();
//            shapeRenderer.circle(f.getX(), f.getY(), f.getRadius());

            for (Fire g : f.getFires()){
                g.render(f);
                g.getClosest();
                g.shoot(f);
                g.intersects(f);
            }
        }
        for (RockTowerAnimation r : rocktowers) {
            r.render();
            r.spawnProjectile();
//            shapeRenderer.circle(r.getX(), r.getY(), r.getRadius());
            for (Rock k : r.getRocks()){
                k.render(r);
                k.getClosest();
                k.shoot(r);
//                k.getClosest();
                k.intersects(r);
//                k.shoot();
            }
        }

        if (isDraggingFire) selectedFireTower.render();
        if (isDraggingRock) selectedRockTower.render();
        if (minotaurs.size() == 0) {
            level++;
            displayLevel = "Level: " + level;
            show();
//            shapeRenderer.end();
        }
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
        for (Minotaur s : minotaurs) {
            s.dispose();
        }
        for (FireTowerAnimation f : firetowers) {
            f.dispose();
            for (Fire d : f.getFires()){
                d.dispose();
            }
        }
        for (RockTowerAnimation r : rocktowers) {
            r.dispose();
        }
        font.dispose();
        rockTowerButton.dispose();
        fireTowerButton.dispose();

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
        if (screenX > 0 && screenX < 80 && 1080-screenY > 0 && 1080-screenY < 120){
            currentlyFireTower = true;
            return true;
        }
        else if (screenX > 80 && screenX < 160 && 1080-screenY > 0 && 1080-screenY < 120){
            currentlyRockTower = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (currentlyFireTower && selectedFireTower.checkBox()) {
            dinero -= 80;
            firetowers.add(new FireTowerAnimation(screenX, 1080 - screenY, 300));
            firetowers.get(fireTowerIndex).create();
            fireTowerIndex++;
            selectedFireTower = new FireTowerAnimation(0, 0, 80);
            selectedFireTower.create();
            isDraggingFire = false;
            currentlyFireTower = false;
            return true;
        }
        else{
            selectedFireTower.setX(9999999);
            selectedFireTower.setY(99999999);
        }
        if (currentlyRockTower && selectedRockTower.checkBox()) {
            dinero -= 100;
            rocktowers.add(new RockTowerAnimation(screenX, 1080 - screenY, 150));
            rocktowers.get(rockTowerIndex).create();
            rockTowerIndex++;
            selectedRockTower = new RockTowerAnimation(80, 0, 80);
            selectedRockTower.create();
            isDraggingRock = false;
            currentlyRockTower = false;

            return true;
        }
        else{
            selectedRockTower.setX(99999999);
            selectedRockTower.setY(99999999);
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

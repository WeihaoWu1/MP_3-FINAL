package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Enemies.Minotaur;
import com.mygdx.game.Projectiles.Arrow;
import com.mygdx.game.Projectiles.Fire;
import com.mygdx.game.Projectiles.Harpoon;
import com.mygdx.game.Projectiles.Rock;
import com.mygdx.game.Towers.ArrowTower;
import com.mygdx.game.Towers.FireTower;
import com.mygdx.game.Towers.HarpoonTower;
import com.mygdx.game.Towers.RockTower;
import com.mygdx.game.gdxGame;

import java.util.ArrayList;

public class PlayScreen implements Screen, InputProcessor {
    private gdxGame game;
    private Texture fireTowerButton = new Texture("firetowericon.png");
    private Texture rockTowerButton = new Texture("rocktowericon.png");
    private Texture arrowTowerButton = new Texture("archertowericon.png");
    private Texture harpoonTowerButton = new Texture("harpoontowericon.png");
    Texture map = new Texture("map4.png");

    public static ArrayList<FireTower> firetowers = new ArrayList<FireTower>();
    public static ArrayList<RockTower> rocktowers = new ArrayList<RockTower>();
    public static ArrayList<ArrowTower> arrowtowers = new ArrayList<ArrowTower>();
    public static ArrayList<HarpoonTower> harpoontowers = new ArrayList<HarpoonTower>();
    public static ArrayList<Minotaur> minotaurs = new ArrayList<Minotaur>();

    int slimeCount;

    private FireTower selectedFireTower = new FireTower(0, 0+100, 80);
    private RockTower selectedRockTower  = new RockTower(80,0+100,80);
    private HarpoonTower selectedHarpoonTower  = new HarpoonTower(160,0+100,80);
    private ArrowTower selectedArrowTower  = new ArrowTower(240,0+100,80);


    private int fireTowerIndex;
    private int rockTowerIndex;
    private int harpoonTowerIndex;
    private int arrowTowerIndex;

    private boolean isDraggingFire;
    private boolean isDraggingRock;
    private boolean isDraggingHarpoon;
    private boolean isDraggingArrow;

    private boolean currentlyFireTower;
    private boolean currentlyRockTower;
    private boolean currentlyHarpoonTower;
    private boolean currentlyArrowTower;

    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    public static int level = 1;
//    public BitmapFont font = new BitmapFont();
    private  FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/04B_03__.TTF"));
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();


    private BitmapFont font = generator.generateFont(parameter);

    private String displayLevel = "Level: 1";
    public static int dinero = 100;
    private String displayDinero = "$ " + dinero;
    public static int mapNum;


    public PlayScreen(gdxGame game, int mapNum) {
        this.game = game;
        this.mapNum = mapNum;
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
        parameter.size = 20;
        if (mapNum == 1){
            map = new Texture("map3.png");
            int x = -55;
            for (int i = 0; i < level*5;i++){
                minotaurs.add(new Minotaur(x, 299));
                x-=180;
                slimeCount++;
            }
        }
        if (mapNum == 2){
            map = new Texture("map4.png");
            int x = 1650;
            for (int i = 0; i < level*5; i++) {
                minotaurs.add(new Minotaur(x, 835));
                x += 180;
                slimeCount++;
            }
        }
//        int x = 1650;
//        //1650map4
//        //-55 map3
//        for (int i = 0; i < level*5; i++) {
//            minotaurs.add(new Minotaur(x, 835));
//            //299 map3
//            //835 map4
//            x += 180;
////            System.out.println(slimeCount);
//            slimeCount++;
//        }
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
        selectedHarpoonTower.create();
        selectedArrowTower.create();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
    }

    @Override
    public void render(float delta) {
//        Gdx.gl.glViewport(0, 0, 1760, 1080);
        displayDinero = "$ " + dinero;
        Gdx.input.setInputProcessor(this);
        ScreenUtils.clear(1, 1, 1, 1);
        gdxGame.batch.begin();
        shapeRenderer.setColor(Color.BLUE);
//        shapeRenderer.begin();
        slimeCount=0;
        gdxGame.batch.draw(map, 0, 120, 1760, 960);
        font.getData().setScale(5f);
        font.setColor(Color.BLACK);
        font.draw(gdxGame.batch, displayLevel, 1450, 1050);
        font.draw(gdxGame.batch, displayDinero, 0, 1050);
        gdxGame.batch.draw(fireTowerButton, 0, 0, 80,120);
        gdxGame.batch.draw(rockTowerButton, 80, 0, 80,120);
        gdxGame.batch.draw(harpoonTowerButton, 160, 0, 80,120);
        gdxGame.batch.draw(arrowTowerButton, 240, 0, 80, 120);
        for (Minotaur s : minotaurs) {
            s.render();
        }
        for (FireTower f : firetowers) {
            f.render();
            f.spawnProjectile();
            shapeRenderer.circle(f.getX(), f.getY(), f.getRadius());

            for (Fire g : f.getFires()){
                g.render(f);
                g.getClosest();
                g.shoot(f);
                g.intersects(f);
            }
        }
        for (RockTower r : rocktowers) {
            r.render();
            r.spawnProjectile();
//            shapeRenderer.circle(r.getX(), r.getY(), r.getRadius());
            for (Rock k : r.getRocks()){
                k.render(r);
                k.getClosest();
                k.shoot(r);
                k.intersects(r);
            }
        }
        for (HarpoonTower h : harpoontowers) {
            h.render();
            h.spawnProjectile();
//            shapeRenderer.circle(r.getX(), r.getY(), r.getRadius());
            for (Harpoon k : h.getHarpoons()) {
                k.render(h);
                k.getClosest();
                k.shoot(h);
                k.intersects(h);
            }
        }

        for (ArrowTower a : arrowtowers) {
            a.render();
            a.spawnProjectile();
//            shapeRenderer.circle(r.getX(), r.getY(), r.getRadius());
            for (Arrow b : a.getArrows()) {
                b.render(a);
                b.getClosest();
                b.shoot(a);
                b.intersects(a);
            }
        }

        if (isDraggingFire) selectedFireTower.render();
        if (isDraggingRock) selectedRockTower.render();
        if (isDraggingHarpoon) selectedHarpoonTower.render();
        if (isDraggingArrow) selectedArrowTower.render();
        if (minotaurs.size() == 0) {
            level++;
            displayLevel = "Level: " + level;
            shapeRenderer.end();
            show();
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
        map.dispose();
        for (Minotaur s : minotaurs) {
            s.dispose();
        }
        for (FireTower f : firetowers) {
            f.dispose();
            for (Fire d : f.getFires()){
                d.dispose();
            }
        }
        for (RockTower r : rocktowers) {
            r.dispose();
        }
        for (HarpoonTower h : harpoontowers) {
            h.dispose();
        }
        for (ArrowTower a : arrowtowers){
            a.dispose();
        }
        font.dispose();
        rockTowerButton.dispose();
        fireTowerButton.dispose();
        generator.dispose();

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
        if (screenX > 0 && screenX < 80 && 1080-screenY-80 > 0 && 1080-screenY-80 < 120){
            currentlyFireTower = true;
            return true;
        }
        else if (screenX > 80 && screenX < 160 && 1080-screenY-80 > 0 && 1080-screenY-80 < 120){
            currentlyRockTower = true;
            return true;
        }
        else if (screenX > 160 && screenX < 240 && 1080-screenY-80 > 0 && 1080-screenY-80 < 120){
            currentlyHarpoonTower = true;
            return true;
        }
        else if (screenX > 240 && screenX < 320 && 1080-screenY-80 > 0 && 1080-screenY -80< 120){
            currentlyArrowTower = true;
            return true;
        }
        return false;

    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (currentlyFireTower && selectedFireTower.checkBox()) {
            dinero -= 80;
            firetowers.add(new FireTower(screenX, 1080 - screenY-80, 300));
            firetowers.get(fireTowerIndex).create();
            fireTowerIndex++;
            selectedFireTower = new FireTower(0, 0, 80);
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
            rocktowers.add(new RockTower(screenX, 1080 - screenY-80, 150));
            rocktowers.get(rockTowerIndex).create();
            rockTowerIndex++;
            selectedRockTower = new RockTower(80, 0, 80);
            selectedRockTower.create();
            isDraggingRock = false;
            currentlyRockTower = false;

            return true;
        }
        else{
            selectedRockTower.setX(99999999);
            selectedRockTower.setY(99999999);
        }
        if (currentlyHarpoonTower && selectedHarpoonTower.checkBox()) {
            dinero -= 100;
            harpoontowers.add(new HarpoonTower(screenX, 1080 - screenY-80, 150));
            harpoontowers.get(harpoonTowerIndex).create();
            harpoonTowerIndex++;
            selectedHarpoonTower = new HarpoonTower(80, 0, 80);
            selectedHarpoonTower.create();
            isDraggingHarpoon = false;
            currentlyHarpoonTower = false;

            return true;
        }
        else{
            selectedHarpoonTower.setX(99999999);
            selectedHarpoonTower.setY(99999999);
        }
        if (currentlyArrowTower && selectedArrowTower.checkBox()) {
            dinero -= 100;
            arrowtowers.add(new ArrowTower(screenX, 1080 - screenY-80, 150));
            arrowtowers.get(arrowTowerIndex).create();
            arrowTowerIndex++;
            selectedArrowTower = new ArrowTower(80, 0, 80);
            selectedArrowTower.create();
            isDraggingArrow = false;
            currentlyArrowTower = false;

            return true;
        }
        else{
            selectedArrowTower.setX(99999999);
            selectedArrowTower.setY(99999999);
        }
        System.out.println("x:"+screenX+"y"+screenY);
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
            selectedFireTower.setY(1080 - Gdx.input.getY()-80);
            return true;
        }
        else if (currentlyRockTower){
            isDraggingRock = true;
            selectedRockTower.setX(screenX);
            selectedRockTower.setY(1080 - screenY-80);
            return true;
        }
        else if (currentlyHarpoonTower){
            isDraggingHarpoon = true;
            selectedHarpoonTower.setX(screenX);
            selectedHarpoonTower.setY(1080 - screenY-80);
            return true;
        }
        else if (currentlyArrowTower){
            isDraggingArrow = true;
            selectedArrowTower.setX(screenX);
            selectedArrowTower.setY(1080-screenY-80);
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

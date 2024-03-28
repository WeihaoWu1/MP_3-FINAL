package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Enemies.Minotaur;
//import com.mygdx.game.Enemies.Slime;
import com.mygdx.game.Projectiles.*;
import com.mygdx.game.Towers.*;
import com.mygdx.game.gdxGame;

import java.util.ArrayList;

public class PlayScreen implements Screen, InputProcessor {
    private gdxGame game;
    private OrthographicCamera camera;
    private Texture fireTowerButton = new Texture("firetowericon.png");
    private Texture rockTowerButton = new Texture("rocktowericon.png");
    private Texture arrowTowerButton = new Texture("archertowericon.png");
    private Texture harpoonTowerButton = new Texture("harpoontowericon.png");
    private Texture alphaTowerButton = new Texture("alphaIcon.png");
    private Texture betaTowerButton = new Texture("betaIcon.png");
    private Texture bombButton = new Texture("nukeButton.png");
    Texture map = new Texture("map4.png");

    public static ArrayList<FireTower> firetowers = new ArrayList<FireTower>();
    public static ArrayList<RockTower> rocktowers = new ArrayList<RockTower>();
    public static ArrayList<ArrowTower> arrowtowers = new ArrayList<ArrowTower>();
    public static ArrayList<HarpoonTower> harpoontowers = new ArrayList<HarpoonTower>();
    public static ArrayList<AlphaTower> alphatowers = new ArrayList<AlphaTower>();
    public static ArrayList<BetaTower> betatowers = new ArrayList<BetaTower>();
    public static ArrayList<Minotaur> minotaurs = new ArrayList<Minotaur>();
//    public static ArrayList<Slime> slimes = new ArrayList<Slime>();


    int slimeCount;

    private FireTower selectedFireTower = new FireTower(0, 100, 80);
    private RockTower selectedRockTower  = new RockTower(80, 100,80);
    private HarpoonTower selectedHarpoonTower  = new HarpoonTower(160, 100,80);
    private ArrowTower selectedArrowTower  = new ArrowTower(240, 100,80);
    private AlphaTower selectedAlphaTower = new AlphaTower(320, 100, 80);
    private BetaTower selectedBetaTower = new BetaTower(400, 100, 80);


    private int fireTowerIndex;
    private int rockTowerIndex;
    private int harpoonTowerIndex;
    private int arrowTowerIndex;
    private int alphaTowerIndex;
    private int betaTowerIndex;

    private boolean isDraggingFire;
    private boolean isDraggingRock;
    private boolean isDraggingHarpoon;
    private boolean isDraggingArrow;
    private boolean isDraggingAlpha;
    private boolean isDraggingBeta;

    private boolean currentlyFireTower;
    private boolean currentlyRockTower;
    private boolean currentlyHarpoonTower;
    private boolean currentlyArrowTower;
    private boolean currentlyAlphaTower;
    private boolean currentlyBetaTower;

    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    public static int level = 1;
    //    public BitmapFont font = new BitmapFont();
    private  FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/CandyCloud-yYYBY.ttf"));
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();


    private BitmapFont font = generator.generateFont(parameter);

    private String displayLevel = "Level: " + level;
    public static int dinero = 300;//150
    private String displayDinero = "$ " + dinero;
    public static int mapNum;
    private int originalScreenWidth = 1760;
    private int originalScreenHeight = 1080;

    public static float xScaleFactor;
    public static float yScaleFactor;
    private StretchViewport viewport;

    private float xScale;
    private float yScale;
    public static int health = 100;
    private Texture heartIcon = new Texture("heart.png");
    private String displayHealth = "" + health;
    private int[][] stats = new int[2][2];

    private float startTime;
    private float endTime;

    private float minTime = Float.MAX_VALUE;
    private  float maxTime = Float.MIN_VALUE;
//    private boolean useBomb;
    public static boolean canBomb = false;

    public static boolean drainHealth;
    Bomb bomb = new Bomb();

    private int bombLimit;

    private String displayPrices = "$150    $150    $100    $100    $200    $200";
    private String displayDMG = "10       12       05       07       17      20";

    public PlayScreen(gdxGame game, int mapNum) {
        this.game = game;
        this.mapNum = mapNum;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(originalScreenWidth, originalScreenHeight, camera);
        camera.position.set(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2, 0);
        camera.update();
//        originalScreenWidth = Gdx.graphics.getWidth();
//        originalScreenHeight = Gdx.graphics.getHeight();
//        camera.setToOrtho(false, camera.viewportWidth/2, camera.viewportHeight/2);
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
        bomb.create();
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        shapeRenderer.setColor(Color.RED);
        parameter.size = 20;
        if (level==10){
            gdxGame.assetManager.load("bossMusic.mp3", Music.class);
            gdxGame.assetManager.finishLoading();
            gdxGame.backgroundMusic.pause();
            gdxGame.backgroundMusic = gdxGame.assetManager.get("bossMusic.mp3", Music.class);
            gdxGame.backgroundMusic.setLooping(true);
            gdxGame.backgroundMusic.setVolume(0.07f);
            gdxGame.backgroundMusic.play();
        }
        if (mapNum == 1){
            map = new Texture("map3.png");
            if (level <10) {
                int x = -55;
//                int slimeX = -246;
                for (int i = 0; i < level * 1.5; i++) {
                    minotaurs.add(new Minotaur(x, 299));
                    minotaurs.get(i).setHealth((minotaurs.get(i).getHealth()*1.1f*level));
//                slimes.add(new Slime(x,222));
                    x -= 180;
                    slimeCount++;
                }
            }
            if (level >= 10){
                int slimeX = -400;
                for (int i = 0; i < level/3;i++){
                    minotaurs.add(new Minotaur(slimeX + 50, 222+40));
                    minotaurs.get(i).setHealth((minotaurs.get(i).getHealth()*1.1f*level));
                    slimeX-=180;
                }
            }
        }
        if (mapNum == 2){
            map = new Texture("map4.png");
            int x = 1650;

            if (level <10) {
                for (int i = 0; i < level*1.5; i++){
                   minotaurs.add(new Minotaur(x, 835));
                   minotaurs.get(i).setHealth((minotaurs.get(i).getHealth()*1.1f*level));
//                slimes.add(new Slime(x,770));
                    x += 180;
                    slimeCount++;
                }
            }
            if (level >= 10){
                int slimeX = 2500;
                for (int i = 0; i < level/3;i++){
                    minotaurs.add(new Minotaur(slimeX+50, 770+40)); //+40
                    minotaurs.get(i).setHealth((minotaurs.get(i).getHealth()*1.1f*level));
                    slimeX-=300;
                    slimeCount++;
                }
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

//            shapeRenderer.rect(s.getX(), s.getY(), 10,20);
        }
//        if (level > 15) {
//            for (Slime s : slimes) {
//                s.create();
//            }
//        }
//        firetowers.add(new FireTowerAnimation(0, 0, 50, 50));
//        rocktowers.add(new RockTowerAnimation(200, 200, 50, 50));
//        firetowers.get(0).create();
//        rocktowers.get(0).create();
        selectedFireTower.create();
        selectedRockTower.create();
        selectedHarpoonTower.create();
        selectedArrowTower.create();
        selectedAlphaTower.create();
        selectedBetaTower.create();
        startTime =  TimeUtils.millis();
//        shapeRenderer.rect(f.getX(), f.getY(), f.getRadius());

    }

    @Override
    public void render(float delta) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
//        Gdx.gl.glViewport(0, 0, 1760, 1080);
        displayDinero = "$ " + dinero;
        displayHealth = ""+health;
        displayLevel = "Level: " + level;
        Gdx.input.setInputProcessor(this);
        ScreenUtils.clear(1, 1, 1, 1);
//        viewport.apply();
//        gdxGame.batch.setProjectionMatrix(camera.combined);
        gdxGame.batch.begin();
//        shapeRenderer.setColor(Color.BLUE);
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        slimeCount=0;
        gdxGame.batch.draw(map, 0, 110, 1760, 980);
        font.getData().setScale(4.5f);
        if (mapNum==1) font.setColor(Color.BLACK);
        if (mapNum==2) font.setColor(Color.BLACK);
        font.draw(gdxGame.batch, displayLevel, 1400, 90);
        font.draw(gdxGame.batch, displayDinero, 700, 90);
        gdxGame.batch.draw(heartIcon, 1000, 0, 80, 110);
        font.draw(gdxGame.batch, displayHealth, 1100, 90);
        gdxGame.batch.draw(fireTowerButton, 0*xScale, 0*yScale, 80*xScale,120*yScale);
        gdxGame.batch.draw(rockTowerButton, 80*xScale, 0*yScale, 80*xScale,120*yScale);
        gdxGame.batch.draw(harpoonTowerButton, 160*xScale, 0*yScale, 80*xScale,120*yScale);
        gdxGame.batch.draw(arrowTowerButton, 240*xScale, 0*yScale, 80*xScale, 120*yScale);
        gdxGame.batch.draw(alphaTowerButton, 320*xScale, 0 * yScale, 80*xScale, 120*yScale);
        gdxGame.batch.draw(betaTowerButton,400*xScale, 0 * yScale, 80*xScale, 120*yScale);
        gdxGame.batch.draw(bombButton, 550, -15, 150, 150);
        font.getData().setScale(1.5f);
        font.setColor(Color.YELLOW);
        font.draw(gdxGame.batch, displayPrices,0, 100);
        font.draw(gdxGame.batch, displayDMG, 0, 20);
        for (Minotaur s : minotaurs) {
            s.render();
//            shapeRenderer.rect(s.getX(), s.getY(), 10,10);

        }
        if (Minotaur.someoneDied){
            for (int i = minotaurs.size()-1; i >=0; i--){
                minotaurs.get(i).bombKill();
            }
        }
        for (FireTower f : firetowers) {
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
        for (AlphaTower a : alphatowers){
            a.render();
            a.spawnProjectile();
            for (AlphaStrike b : a.getAlphaStrikes()){
                b.render(a);
                b.getClosest();
                b.shoot(a);
                b.intersects(a);
            }
        }

        for (BetaTower a : betatowers) {
            a.render();
            a.spawnProjectile();
//           shapeRenderer.circle(r.getX(), r.getY(), r.getRadius());
            for (BetaStrike b : a.getBetaStrikes()) {
                b.render(a);
                b.getClosest();
                b.shoot(a);
                b.intersects(a);
            }
        }

        if (canBomb){
            bomb.render();
        }

        if (drainHealth){
            for (int j = PlayScreen.minotaurs.size() - 1; j >= 0; j--){
                minotaurs.get(j).setHealth(-500);
                minotaurs.get(j).bombKill();
            }
            drainHealth = false;
        }



        if (isDraggingFire){
            selectedFireTower.render();

            shapeRenderer.circle(selectedFireTower.getX()+selectedFireTower.getRadius(), selectedFireTower.getY(), selectedFireTower.getRadius()+130);
        }
        if (isDraggingRock) {
            selectedRockTower.render();
            shapeRenderer.circle(selectedRockTower.getX()+selectedRockTower.getRadius(), selectedRockTower.getY(), selectedRockTower.getRadius()+150);
        }
        if (isDraggingHarpoon){
            selectedHarpoonTower.render();
            shapeRenderer.circle(selectedHarpoonTower.getX()+selectedHarpoonTower.getRadius(), selectedHarpoonTower.getY(), selectedHarpoonTower.getRadius()+100);
        }
        if (isDraggingArrow){
            selectedArrowTower.render();
            shapeRenderer.circle(selectedArrowTower.getX()+selectedArrowTower.getRadius(), selectedArrowTower.getY(), selectedArrowTower.getRadius()+200);//+170
        }
        if (isDraggingAlpha){
            selectedAlphaTower.render();//+70
            shapeRenderer.circle(selectedAlphaTower.getX()+selectedAlphaTower.getRadius(), selectedAlphaTower.getY(), selectedAlphaTower.getRadius()+100);

        }
        if (isDraggingBeta){
            selectedBetaTower.render();//+120
            shapeRenderer.circle(selectedBetaTower.getX()+selectedBetaTower.getRadius(), selectedBetaTower.getY(), selectedBetaTower.getRadius()+150);
        }

        if (minotaurs.size() == 0) {
            endTime =  TimeUtils.millis();
            if (endTime - startTime < minTime) stats[0][0] = (int) (endTime-startTime);
            if (endTime - startTime > maxTime) stats[0][1] = (int)(endTime-startTime);
            System.out.println("level increase");
            level++;
            displayLevel = "Level: " + level;
//            shapeRenderer.end();
            show();
        }
//            for (Slime s : slimes) {
//                s.render();
//            }

        gdxGame.batch.end();
        shapeRenderer.end();
        if (level >15 || health <=0){
            game.setScreen(new EndScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        System.out.println(width);
        System.out.println(height);
        viewport.update(width, height);
        xScale = (float) width /originalScreenWidth;
        yScale = (float) height /originalScreenHeight;
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
//        for (Slime s : slimes) {
//            s.dispose();
//        }
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
        for (AlphaTower a : alphatowers){
            a.dispose();
        }
        for (BetaTower a : betatowers){
            a.dispose();
        }
        font.dispose();
        rockTowerButton.dispose();
        fireTowerButton.dispose();
        harpoonTowerButton.dispose();
        arrowTowerButton.dispose();
        alphaTowerButton.dispose();
        betaTowerButton.dispose();
        generator.dispose();
        bomb.dispose();

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
        if (screenX > 0 *xScale && screenX < 80*xScale && 1080-screenY-80 > 0*yScale && 1080-screenY-80 < 120*yScale&& dinero >= 150){
            currentlyFireTower = true;
            return true;
        }
        else if (screenX > 80*xScale && screenX < 160*xScale && 1080-screenY-80 > 0*yScale && 1080-screenY-80 < 120*yScale&& dinero >= 150){
            currentlyRockTower = true;
            return true;
        }
        else if (screenX > 160*xScale && screenX < 240*xScale && 1080-screenY-80 > 0 *yScale&& 1080-screenY-80 < 120*yScale&& dinero >= 100){
            currentlyHarpoonTower = true;
            return true;
        }
        else if (screenX > 240 *xScale && screenX < 320*xScale && 1080-screenY-80 > 0*yScale && 1080-screenY -80< 120*yScale&& dinero >= 100){
            currentlyArrowTower = true;
            return true;
        }
        else if (screenX > 320 *xScale && screenX < 400 *xScale && 1080-screenY-80 > 0*yScale && 1080-screenY -80< 120*yScale&& dinero >= 200){
            currentlyAlphaTower = true;
            return true;
        }
        else if (screenX > 400 *xScale && screenX < 480*xScale && 1080-screenY-80 > 0*yScale && 1080-screenY -80< 120*yScale&& dinero >= 200){
            currentlyBetaTower = true;
            return true;
        }
        return false;

    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (level >= 10 && screenX >= 639 && screenX <= 726 && screenY <= 982 && screenY >= 916 && bombLimit < 1){
            bombLimit++;
            canBomb = true;
            System.out.println("bomb");
        }
        if (currentlyFireTower && selectedFireTower.checkBox() ) {
            dinero -= 150;
            firetowers.add(new FireTower(screenX, 1080 - screenY, 180));
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
        isDraggingFire = false;
        currentlyFireTower = false;

        if (currentlyRockTower && selectedRockTower.checkBox()) {
            dinero -= 150;
            rocktowers.add(new RockTower(screenX, 1080 - screenY-80, 230));
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
        isDraggingRock = false;
        currentlyRockTower = false;

        if (currentlyHarpoonTower && selectedHarpoonTower.checkBox()) {
            dinero -= 100;
            harpoontowers.add(new HarpoonTower(screenX, 1080 - screenY-80, 180));
            harpoontowers.get(harpoonTowerIndex).create();
            harpoonTowerIndex++;
            selectedHarpoonTower = new HarpoonTower(160, 0,80);
            selectedHarpoonTower.create();
            isDraggingHarpoon = false;
            currentlyHarpoonTower = false;
            return true;
        }

        else{
            selectedHarpoonTower.setX(99999999);
            selectedHarpoonTower.setY(99999999);
        }
        isDraggingHarpoon = false;
        currentlyHarpoonTower = false;

        if (currentlyArrowTower && selectedArrowTower.checkBox()) {
            dinero -= 100;
            arrowtowers.add(new ArrowTower(screenX, 1080 - screenY-80, 240));
            arrowtowers.get(arrowTowerIndex).create();
            arrowTowerIndex++;
            selectedArrowTower = new ArrowTower(240, 0, 80);
            selectedArrowTower.create();
            isDraggingArrow = false;
            currentlyArrowTower = false;
            return true;
        }
        else{
            selectedArrowTower.setX(99999999);
            selectedArrowTower.setY(99999999);
        }
        isDraggingArrow = false;
        currentlyArrowTower = false;

        if (currentlyAlphaTower && selectedAlphaTower.checkBox()) {
            dinero -= 200;
            alphatowers.add(new AlphaTower(screenX, 1080 - screenY-80, 140));
            alphatowers.get(alphaTowerIndex).create();
            alphaTowerIndex++;
            selectedAlphaTower = new AlphaTower(320, 0, 80);
            selectedAlphaTower.create();
            isDraggingAlpha = false;
            currentlyAlphaTower = false;

            return true;
        }
        else{
            selectedAlphaTower.setX(99999999);
            selectedAlphaTower.setY(99999999);
        }
        isDraggingAlpha = false;
        currentlyAlphaTower = false;
        if (currentlyBetaTower && selectedBetaTower.checkBox()) {
            dinero -= 200;
            betatowers.add(new BetaTower(screenX, 1080 - screenY-80, 190));
            betatowers.get(betaTowerIndex).create();
            betaTowerIndex++;
            selectedBetaTower = new BetaTower(400, 0, 80);
            selectedBetaTower.create();
            isDraggingBeta = false;
            currentlyBetaTower = false;
            return true;
        }
        else{
            selectedBetaTower.setX(99999999);
            selectedBetaTower.setY(99999999);
        }
        isDraggingBeta = false;
        currentlyBetaTower = false;
        System.out.println("x:"+screenX+"y"+(1080-screenY));
        return false;

    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (currentlyFireTower) {
//            shapeRenderer.circle(selectedFireTower.getX(), selectedFireTower.getY(), selectedFireTower.getRadius());
            isDraggingFire = true;
            selectedFireTower.setX(Gdx.input.getX());
            selectedFireTower.setY(1080 - Gdx.input.getY());
//            shapeRenderer.end();
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
            return true;
        }
        else if (currentlyAlphaTower){
            isDraggingAlpha = true;
            selectedAlphaTower.setX(screenX);
            selectedAlphaTower.setY(1080-screenY-80);
            return true;
        }
        else if (currentlyBetaTower){
            isDraggingBeta = true;
            selectedBetaTower.setX(screenX);
            selectedBetaTower.setY(1080-screenY-80);
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
    public String toString(){
        return "OVERRIDEN";
    }
    public boolean equals(Object obj){
        return true;
    }
}

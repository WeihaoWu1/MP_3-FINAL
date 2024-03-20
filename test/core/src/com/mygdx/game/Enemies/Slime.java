package com.mygdx.game.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Projectiles.Fire;
import com.mygdx.game.Projectiles.Rock;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Towers.FireTowerAnimation;
import com.mygdx.game.Towers.RockTowerAnimation;
import com.mygdx.game.gdxGame;

import java.util.ArrayList;

public class Slime{

    private static final int FRAME_COLS = 8, FRAME_ROWS = 1;
//    private static final long COOLDOWN_TIME = 1;
//    private long lastTimeMoved;

    // Objects used
    Animation<TextureRegion> walkAnimation; // Must declare frame type (TextureRegion)
    Texture walkSheet = new Texture(Gdx.files.internal("slime.png"));;
    SpriteBatch spriteBatch;

    // A variable for tracking elapsed time for the animation
    float stateTime;

    public static final int LEFT = 0;
    public static final int RIGHT = 0;
    public static final int UP = 0;
    public static final int DOWN = 0;

    public static boolean left;
    public static boolean right = true;
//    public boolean up;
//    public boolean down;
    private double cooldown = 1;
    private double startTime = System.currentTimeMillis();
    private double currentTime;
    private double lastSlime = startTime;

    public static boolean up = true;
    public static boolean down;

    public int health = 100;

    private Rectangle hitBox;

    private float x;
    private float y;

    Sprite slime;


    public Slime(float x, float y){
//        super(x,y,width,height);
        this.x = x;
        this.y=y;

//        initializeHitBox(x,y,width,height);
    }


    public void create() {
        slime = new Sprite(walkSheet);
        hitBox = new Rectangle(x,y,30, 30);
        hitBox.setX(x);
        hitBox.setY(y);

//        walkSheet = new Texture(Gdx.files.internal("slime.png"));

        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / FRAME_COLS,
                walkSheet.getHeight() / FRAME_ROWS);

        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }

        walkAnimation = new Animation<TextureRegion>(0.025f, walkFrames);
        stateTime = 0f;
        currentTime = System.currentTimeMillis();

        }


    public void render() {
            stateTime += Gdx.graphics.getDeltaTime();
            TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
            gdxGame.batch.draw(currentFrame, hitBox.x, hitBox.y);
        if ( hitBox.x < 145) {
            hitBox.x += 1f;
        }
        if (hitBox.y < 345 && hitBox.x >= 145 && hitBox.x < 190) {
            hitBox.y += 1f;
            hitBox.x += 1f;
        }
        if (hitBox.x >= 190 && hitBox.y < 538) {
            hitBox.y += 1f;
        }
        if (hitBox.y >= 538 && hitBox.x >= 190 && hitBox.x <= 243) {
            hitBox.y += 1f;
            hitBox.x +=1f;
        }
        if (hitBox.x < 384 && hitBox.y == 592){
            hitBox.x += 1f;
        }
        if (hitBox.y < 729 && hitBox.x >= 384&& hitBox.x<521){
            hitBox.x += 1f;
            hitBox.y+=1f;
        }
        if (hitBox.y < 760 && hitBox.x >= 521&& hitBox.x<552){
            hitBox.x += 1f;
            hitBox.y+=1f;
        }
        if (hitBox.x == 552 && hitBox.y < 820) {
            hitBox.y += 1f;
        }

        if (hitBox.y < 906 && hitBox.x >= 552&& hitBox.x<639&& hitBox.y>=820){
            hitBox.x += 1f;
            hitBox.y+=1f;
        }
        if (hitBox.x < 985 && hitBox.y == 906){
            hitBox.x += 1f;
        }
        if (hitBox.y < 961 && hitBox.x >= 985&& hitBox.x<1057&& hitBox.y>=906){
            hitBox.x += 1f;
            hitBox.y+=1f;
        }
        if (hitBox.x < 1145 && hitBox.y == 961){
            hitBox.x += 1f;
        }
        if (hitBox.y <= 961 && hitBox.x >= 1145&& hitBox.x<1206&& hitBox.y>=906){
            hitBox.x += 1f;
            hitBox.y-=1f;
            down = true;
            up=false;
        }
        if (hitBox.x < 1470 && hitBox.y == 905){
            hitBox.x += 1f;
        }
        if (hitBox.y <= 905 && hitBox.x >= 1470&& hitBox.x<1563&& hitBox.y>=812){
            hitBox.x += 1f;
            hitBox.y-=1f;
            up = false;
            down = true;
        }
        if (hitBox.x == 1563 && hitBox.y >= 721) {
            hitBox.y -= 1f;
        }
        if (hitBox.x >= 1477 && hitBox.y >=635 && hitBox.x <= 1563 && hitBox.y <= 721) {
            hitBox.y -= 1f;
            hitBox.x -=1f;
            right = false;
            left = true;
        }
        if (hitBox.x >= 1441 && hitBox.y >=599 && hitBox.x <= 1477 && hitBox.y <= 635) {
            hitBox.y -= 1f;
            hitBox.x -=1f;
        }
        if (hitBox.y == 599 && hitBox.x >= 1319) {
            hitBox.x -= 1f;
        }
        if (hitBox.x >= 1266 && hitBox.y >=538 && hitBox.x <= 1319 && hitBox.y <= 599) {
            hitBox.y -= 1f;
            hitBox.x -=1f;
        }
        if (hitBox.x == 1265 && hitBox.y >= 398&&hitBox.y <= 599) {
            hitBox.y -= 2f;
        }
        if (hitBox.y <= 398 && hitBox.x >= 1265&& hitBox.x<1411&& hitBox.y>=302){
            hitBox.x += 1f;
            hitBox.y-=2f;
            right = true;
            left = false;
        }
        if (  hitBox.x >= 1411&& hitBox.x<1520&& hitBox.y==302){
            hitBox.x += 1f;
            hitBox.y-=1f;
        }
        if (hitBox.y <= 302 && hitBox.x >= 1520&& hitBox.x<1555&& hitBox.y>=282){
            hitBox.x += 1f;
            hitBox.y-=2f;
        }
        if (hitBox.x == 1555 && hitBox.y >= Integer. MIN_VALUE&&hitBox.y <= 282) {
            hitBox.y -= 2f;
        }
        hitBox = new Rectangle(hitBox.x,hitBox.y,30, 30);
//        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
//            hitBox.x-=1;
//            System.out.println("x:" + hitBox.x + " y:" + hitBox.y);
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
//            hitBox.x+=2;
//            System.out.println("x:" + hitBox.x + " y:" + hitBox.y);
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
//            hitBox.y+=1;
//            System.out.println("x:" + hitBox.x + " y:" + hitBox.y);
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
//            hitBox.y-=2;
//            System.out.println("x:" + hitBox.x + " y:" + hitBox.y);
//        }
    }



//    public void mousePressed(MouseEvent e) {
//        System.out.println(e.getX() + " " + e.getY());
//    }
//    public void move(){


//    public void setLeft(boolean left){
//        this.left = left;
//    }
//
//    public void setRight(boolean right){
//        this.right = right;
//    }

    public void setUp(boolean up){
        this.up = up;
    }

    public void setDown(boolean down){
        this.down = down;
    }

    public void dispose() { // SpriteBatches and Textures must always be disposed
            walkSheet.dispose();
    }

    public void move(float speed, int direction){
    }
    public void setX(float x){
        hitBox.x = x;
        hitBox = new Rectangle(hitBox.x,hitBox.y,300, 300);

    }

    public void setY(float y){
        hitBox.y = y;
        hitBox = new Rectangle(hitBox.x,hitBox.y,300, 300);
    }

    public float getX(){
        return hitBox.x;
    }

    public float getY(){
        return hitBox.y;
    }

    public Rectangle getHitBox() {
        return hitBox;

    }


}

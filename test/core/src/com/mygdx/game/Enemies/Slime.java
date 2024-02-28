package com.mygdx.game.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Game;
import com.badlogic.gdx.ApplicationListener;

public class Slime extends Hitboxes {

    private static final int FRAME_COLS = 8, FRAME_ROWS = 1;
//    private static final long COOLDOWN_TIME = 1;
    private long currentTime = System.currentTimeMillis();
    private long lastTimeMoved;

    // Objects used
    Animation<TextureRegion> walkAnimation; // Must declare frame type (TextureRegion)
    Texture walkSheet;
    SpriteBatch spriteBatch;

    // A variable for tracking elapsed time for the animation
    float stateTime;

    public static final int LEFT = 0;
    public static final int RIGHT = 0;
    public static final int UP = 0;
    public static final int DOWN = 0;

    public boolean left;
    public boolean right;
    public boolean up;
    public boolean down;


    public Slime(float x, float y, int width, int height){
        super(x,y,width,height);
        initializeHitBox(x,y,width,height);
    }

//    public move(){
//
//    }
    public void create() {

        // Load the sprite sheet as a Texture
        walkSheet = new Texture(Gdx.files.internal("slime.png"));

        // Use the split utility method to create a 2D array of TextureRegions. This is
        // possible because this sprite sheet contains frames of equal size and they are
        // all aligned.
        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / FRAME_COLS,
                walkSheet.getHeight() / FRAME_ROWS);

        // Place the regions into a 1D array in the correct order, starting from the top
        // left, going across first. The Animation constructor requires a 1D array.
        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }

        // Initialize the Animation with the frame interval and array of frames
        walkAnimation = new Animation<TextureRegion>(0.025f, walkFrames);

        // Instantiate a SpriteBatch for drawing and reset the elapsed animation
        // time to 0
//        spriteBatch = new SpriteBatch();
        stateTime = 0f;
    }


    public void render() {
        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time

        // Get current frame of animation for the current stateTime
        TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        Game.batch.draw(currentFrame,hitBox.x , hitBox.y);
    }
//    public float currentHitBoxX = hitBox.x;
//    public float currentHitBoxY = hitBox.y;
    public void move(){
        currentTime = System.currentTimeMillis();
        // (-44,179) (145,179) (145,219) (190,219) (190,418)(243,418) (243,446) (419,446) (419,515)
        if ( hitBox.x < 145) {
            hitBox.x += 1f;
            System.out.println("x:" + hitBox.x + " y:" + hitBox.y);
        }
        //Weihao <3 kids
        if (hitBox.y < 225 && hitBox.x >= 145 && hitBox.x < 190) {
                hitBox.y += 1f;
                hitBox.x += 1f;
                System.out.println("x:" + hitBox.x + " y:" + hitBox.y);
        }
        if (hitBox.x == 190 && hitBox.y < 418) {
            hitBox.y += 1f;
            System.out.println("x:" + hitBox.x + " y:" + hitBox.y);
        }
        if (hitBox.y >= 418 && hitBox.x >= 190 && hitBox.x <= 243) {
            hitBox.y += 1f;
            hitBox.x +=1f;
            System.out.println("x:" + hitBox.x + " y:" + hitBox.y);
        }
        if (hitBox.x < 243 && hitBox.y == 418){
            hitBox.x += 1f;
        }
        if (hitBox.y < 446  && hitBox.x == 243) {
            hitBox.y += 1f;
            System.out.println("x:" + hitBox.x + " y:" + hitBox.y);
        }
        if (hitBox.x < 419 && hitBox.y == 446){
            hitBox.x += 1f;
        }
        if (hitBox.y < 515 && hitBox.x == 419){
            hitBox.y += 1f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            hitBox.x-=1;
            System.out.println("x:" + hitBox.x + " y:" + hitBox.y);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            hitBox.x+=1;
            System.out.println("x:" + hitBox.x + " y:" + hitBox.y);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            hitBox.y+=1;
            System.out.println("x:" + hitBox.x + " y:" + hitBox.y);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            hitBox.y-=1;
        }
    }

    public void setLeft(boolean left){
        this.left = left;
    }

    public void setRight(boolean right){
        this.right = right;
    }

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

//    private void explodeOnEnemies(Projectile p) {
//        for (Enemy e : playing.getEnemyManger().getEnemies()) {
//            if (e.isAlive()) {
//                float radius = 40.0f;
//
//                float xDist = Math.abs(p.getPos().x - e.getX());
//                float yDist = Math.abs(p.getPos().y - e.getY());
//
//                float realDist = (float) Math.hypot(xDist, yDist);
//
//                if (realDist <= radius)
//                    e.hurt(p.getDmg());
//            }
//
//        }
//
//    }

}

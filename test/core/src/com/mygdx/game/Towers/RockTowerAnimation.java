package com.mygdx.game.Towers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.mygdx.game.Enemies.Hitboxes;
import com.mygdx.game.Enemies.Slime;
import com.mygdx.game.Projectiles.Fire;
import com.mygdx.game.Projectiles.Rock;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.gdxGame;

import java.util.ArrayList;

public class RockTowerAnimation extends TowerHitbox {
    private static final int FRAME_COLS = 24, FRAME_ROWS = 1;

    // Objects used
    Animation<TextureRegion> walkAnimation; // Must declare frame type (TextureRegion)
    Texture walkSheet;
    SpriteBatch spriteBatch;

    // A variable for tracking elapsed time for the animation
    float stateTime;

    private ArrayList<Rock> rocks  = new ArrayList<Rock>();

    private float increment;


    public RockTowerAnimation(float x, float y, float radius){
        super(x,y,radius);
        initializeHitBox(x,y,radius);
    }
    public void spawnProjectile() {
        for (Slime s : PlayScreen.slimes) {
            if (Intersector.overlaps(hitBox, s.getHitBox())) {
                Rock rock = new Rock((float) this.getX()-40 , (float) this.getY()-40 , 100, 100);rocks.add(rock);
                rock.create();
            }
        }

//        for (int r = 0; r < rocks.size(); r++) {
//            if (Intersector.overlaps(rocks.get(r).getHitBox(), rocks.get(r).getClosest().getHitBox())) {
//                rocks.get(r).getClosest().health -= 50;
//                if (rocks.get(r).getClosest().health <= 0) {
//                    rocks.get(r).getClosest().setX(-9999999f);
//                    rocks.get(r).getClosest().setY(-9999999f);
//                }
//            }
//        }
    }
    public void create() {

        // Load the sprite sheet as a Texture
        walkSheet = new Texture(Gdx.files.internal("rock_tower.png"));

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
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen
        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time

        // Get current frame of animation for the current stateTime
        TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);;
        gdxGame.batch.draw(currentFrame, hitBox.x-80, hitBox.y-80);
    }
    public void setX(float x){
        hitBox.x = x;
    }
    public void setY(float y){
        hitBox.y = y;
    }

    public ArrayList<Rock> getRocks(){
        return rocks;
    }

    public void dispose() {
        walkSheet.dispose();
    }
}

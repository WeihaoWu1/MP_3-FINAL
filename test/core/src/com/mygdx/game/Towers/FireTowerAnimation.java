package com.mygdx.game.Towers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Enemies.Hitboxes;
import com.mygdx.game.Enemies.Slime;
import com.mygdx.game.Projectiles.Fire;
import com.mygdx.game.Projectiles.Rock;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.gdxGame;

public class FireTowerAnimation extends TowerHitbox {
    // Constant rows and columns of the sprite sheet
    private static final int FRAME_COLS = 24, FRAME_ROWS = 1;

    // Objects used
    Animation<TextureRegion> walkAnimation; // Must declare frame type (TextureRegion)
    Texture walkSheet;
    SpriteBatch spriteBatch;

    // A variable for tracking elapsed time for the animation
    float stateTime;
    
    public FireTowerAnimation (double x, double y, double radius){
        super(x,y,radius);
        initializeHitBox(x,y,radius);
    }

    public void spawnProjectile() {
        for (Slime s : PlayScreen.slimes) {
            if (hitBox.intersects(s.hitBox)){
                Fire fire = new Fire((float)this.getX() - (float)this.getRadius(), (float)this.getY() - (float)this.getRadius(), 5,5);
                System.out.println("SPAWN");
                PlayScreen.fires.add(fire);
                fire.create();
            }
        }

//        for (RockTowerAnimation r : PlayScreen.rocktowers){
//            if (hitBox.intersects(r.hitBox)){
//                PlayScreen.rocks.add(new Rock(r.getX(), r.getY(), 5, 5));
//            }
//        }
    }


    public void create() {

        // Load the sprite sheet as a Texture
        walkSheet = new Texture(Gdx.files.internal("fire_tower.png"));

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

    public void setX(float x){
        hitBox.x = x;
    }
    public void setY(float y){
        hitBox.y = y;
    }


    public void render() {
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        gdxGame.batch.draw(currentFrame, (float) hitBox.getX(), (float) hitBox.getY());
    }

    public void dispose() { // SpriteBatches and Textures must always be disposed
        walkSheet.dispose();
    }
}

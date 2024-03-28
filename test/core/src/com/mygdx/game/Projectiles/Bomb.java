package com.mygdx.game.Projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.gdxGame;

public class Bomb {
    private  int FRAME_COLS = 11;
    private  int FRAME_ROWS = 1;
//    private static final long COOLDOWN_TIME = 1;
//    private long lastTimeMoved;
    //minotaur: 18/1 , slime:8/1

    // Objects used
    Animation<TextureRegion> walkAnimation;
    Texture walkSheet = new Texture(Gdx.files.internal("bomb.png"));
    ;
    SpriteBatch spriteBatch;

    float stateTime;

    private float x;
    private float y;
    private int rand;
    private TextureRegion currentFrame;

    Sprite slime;

    private boolean needFlip = true;

    private boolean stopFlip;

    public void create() {
//        hitBox.setX(x);
//        hitBox.setY(y);
        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / FRAME_COLS,
                walkSheet.getHeight() / FRAME_ROWS);


        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++){
                walkFrames[index++] = tmp[i][j];
            }
        }

        walkAnimation = new Animation<TextureRegion>(.025f, walkFrames);
        walkAnimation.setPlayMode(Animation.PlayMode.NORMAL);
//        if(PlayScreen.mapNum == 2) {
//            for (TextureRegion region : walkAnimation.getKeyFrames()) {
//                region.flip(true, false); // Flip along the x-axis
//            }//            }
//        }
        stateTime = 0f;
        currentFrame = walkAnimation.getKeyFrame(stateTime, true);

    }

    public void render(){
//        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, false);
        gdxGame.batch.draw(currentFrame, 500, 200, 1000, 1000);
        stateTime += Gdx.graphics.getDeltaTime();
        if (walkAnimation.isAnimationFinished(stateTime)) {
            PlayScreen.canBomb = false;
            PlayScreen.drainHealth = true;
        }

    }

    public void dispose() {
        walkSheet.dispose();
    }
    public String toString(){
        return "OVERRIDEN";
    }
    public boolean equals(Object obj){
        return true;
    }
}

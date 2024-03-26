package com.mygdx.game.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.gdxGame;


public class Minotaur {

    private static int FRAME_COLS;
    private static int FRAME_ROWS = 1;
//    private static final long COOLDOWN_TIME = 1;
//    private long lastTimeMoved;
    //minotaur: 18/1 , slime:8/1

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

    public int health = 300;

    private Rectangle hitBox;

    private float x;
    private float y;
    private int rand;
    private TextureRegion currentFrame;

    Sprite slime;

    private boolean needFlip = true;

    private boolean stopFlip;


    public Minotaur(float x, float y){
//        super(x,y,width,height);
        this.x = x;
        this.y=y;
        if (PlayScreen.level >= 10){
            FRAME_COLS = 8;
            walkSheet = new Texture(Gdx.files.internal("slime.png"));


        }
        else{
            FRAME_COLS = 18;
            walkSheet = new Texture(Gdx.files.internal("minotaur3.png"));
        }

//        initializeHitBox(x,y,width,height);
    }


    public void create() {
        if (PlayScreen.level < 10) {
            hitBox = new Rectangle(x, y, 10, 10);
        }
        else{
            hitBox = new Rectangle(x,y,50,50);
        }
        hitBox.setX(x);
        hitBox.setY(y);

//        walkSheet = new Texture(Gdx.files.internal("slime.png"));

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

        walkAnimation = new Animation<TextureRegion>(0.025f, walkFrames);
//        if(PlayScreen.mapNum == 2) {
//            for (TextureRegion region : walkAnimation.getKeyFrames()) {
//                region.flip(true, false); // Flip along the x-axis
//            }//            }
//        }
        stateTime = 0f;
        currentTime = System.currentTimeMillis();
        currentFrame = walkAnimation.getKeyFrame(stateTime, true);
//        if (PlayScreen.mapNum == 2){
//            currentFrame.flip(true, false);
//        }

        }


    public void render() {
        if (PlayScreen.level < 10) {
            stateTime += Gdx.graphics.getDeltaTime();
            if (PlayScreen.mapNum == 2 && !stopFlip) {
                System.out.println("flipped");
                for (TextureRegion region : walkAnimation.getKeyFrames()) {
                    region.flip(true, false); // Flip along the x-axis
                }
//            currentFrame.flip(true, false);
                stopFlip = true;
            }
            TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
            if (PlayScreen.mapNum == 1) {
                if (hitBox.x < 145) {
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
                    hitBox.x += 1f;
                }
                if (hitBox.x < 384 && hitBox.y == 592) {
                    hitBox.x += 1f;
                }
                if (hitBox.y < 729 && hitBox.x >= 384 && hitBox.x < 521) {
                    hitBox.x += 1f;
                    hitBox.y += 1f;
                }
                if (hitBox.y < 760 && hitBox.x >= 521 && hitBox.x < 552) {
                    hitBox.x += 1f;
                    hitBox.y += 1f;
                }
                if (hitBox.x == 552 && hitBox.y < 820) {
                    hitBox.y += 1f;
                }

                if (hitBox.y < 906 && hitBox.x >= 552 && hitBox.x < 639 && hitBox.y >= 820) {
                    hitBox.x += 1f;
                    hitBox.y += 1f;
                }
                if (hitBox.x < 985 && hitBox.y == 906) {
                    hitBox.x += 1f;
                }
                if (hitBox.y < 961 && hitBox.x >= 985 && hitBox.x < 1057 && hitBox.y >= 906) {
                    hitBox.x += 1f;
                    hitBox.y += 1f;
                }
                if (hitBox.x < 1145 && hitBox.y == 961) {
                    hitBox.x += 1f;
                }
                if (hitBox.y <= 961 && hitBox.x >= 1145 && hitBox.x < 1206 && hitBox.y >= 906) {
                    hitBox.x += 1f;
                    hitBox.y -= 1f;
                    down = true;
                    up = false;
                }
                if (hitBox.x < 1470 && hitBox.y == 905) {
                    hitBox.x += 1f;
                }
                if (hitBox.y <= 905 && hitBox.x >= 1470 && hitBox.x < 1563 && hitBox.y >= 812) {
                    hitBox.x += 1f;
                    hitBox.y -= 1f;
                    up = false;
                    down = true;
                }
                if (hitBox.x == 1563 && hitBox.y >= 721) {
                    hitBox.y -= 1f;
                }
                if (hitBox.x >= 1477 && hitBox.y >= 635 && hitBox.x <= 1563 && hitBox.y <= 721) {
                    hitBox.y -= 1f;
                    hitBox.x -= 1f;
                    right = false;
                    left = true;
                }
                if (hitBox.x >= 1441 && hitBox.y >= 599 && hitBox.x <= 1477 && hitBox.y <= 635) {
                    hitBox.y -= 1f;
                    hitBox.x -= 1f;
                }
                if (hitBox.y == 599 && hitBox.x >= 1319) {
                    hitBox.x -= 1f;
                }
                if (hitBox.x >= 1266 && hitBox.y >= 538 && hitBox.x <= 1319 && hitBox.y <= 599) {
                    hitBox.y -= 1f;
                    hitBox.x -= 1f;
                }
                if (hitBox.x == 1265 && hitBox.y >= 398 && hitBox.y <= 599) {
                    hitBox.y -= 2f;
                }
                if (hitBox.y <= 398 && hitBox.x >= 1265 && hitBox.x < 1411 && hitBox.y >= 302) {
                    hitBox.x += 1f;
                    hitBox.y -= 2f;
                    right = true;
                    left = false;
                }
                if (hitBox.x >= 1411 && hitBox.x < 1520 && hitBox.y == 302) {
                    hitBox.x += 1f;
                    hitBox.y -= 1f;
                }
                if (hitBox.y <= 302 && hitBox.x >= 1520 && hitBox.x < 1555 && hitBox.y >= 282) {
                    hitBox.x += 1f;
                    hitBox.y -= 2f;
                }
                if (hitBox.x == 1555 && hitBox.y >= Integer.MIN_VALUE && hitBox.y <= 282) {
                    hitBox.y -= 2f;
                }
            }
            if (PlayScreen.mapNum == 2) {
                if (hitBox.x > 627 && hitBox.y == 835) {
//                if (needFlip) {
//                    currentFrame.flip(true, false);
//                    create();
//                    needFlip = false;
//                }
                    hitBox.x -= 1f;
                }
                if (hitBox.x >= 526 && hitBox.y >= 726 && hitBox.x <= 627 && hitBox.y <= 835) {
                    hitBox.y -= 1f;
                    hitBox.x -= 1f;
                }
                if (hitBox.y == 725) {

//                stopFlip=false;
//                hitBox.x = (hitBox.x+hitBox.width)*-1;
//                hitBox.x+=10;
                }
                if (hitBox.x == 525 && hitBox.y > 635) {
                    hitBox.y -= 1f;
                }
                if (hitBox.y <= 635 && hitBox.x >= 525 && hitBox.x < 628 && hitBox.y >= 532) {
                    hitBox.x += 1f;
                    hitBox.y -= 1f;
                }
                if (hitBox.x < 738 && hitBox.y == 532) {
                    hitBox.x += 1f;
                }
                if (hitBox.y <= 532 && hitBox.x >= 738 && hitBox.x < 828 && hitBox.y >= 442) {
                    hitBox.x += 1f;
                    hitBox.y -= 1f;
                }
                if (hitBox.x == 828 && hitBox.y > 291 && hitBox.y <= 442) {
                    hitBox.y -= 1f;
                    int max = 10;
                    int min = 1;
                    int range = max - min + 1;
                    rand = (int) (Math.random() * range) + min;
                    System.out.println(rand);
                }
                if (rand > 3 && hitBox.y == 291 && hitBox.x < 882) {
                    hitBox.x++;
                }
                if (rand > 3 && hitBox.y <= 291 && hitBox.x >= 882 && hitBox.x < 954 && hitBox.y >= 219) {
                    hitBox.x += 1f;
                    hitBox.y -= 1f;
                }
                if (rand > 3 && hitBox.y == 219 && hitBox.x < Integer.MAX_VALUE) {
                    hitBox.x++;
                }

                if (rand <= 3 && hitBox.y == 291 && hitBox.x > 728) {
                    hitBox.x--;
                }
                if (rand <= 3 && hitBox.x == 827 && hitBox.y == 291) {
//                stopFlip = false;
//                hitBox.x = (hitBox.x+hitBox.width)*-1;
                }
                if (rand <= 3 && hitBox.x <= 728 && hitBox.y >= 181 && hitBox.x >= 624 && hitBox.y <= 291) {
                    hitBox.y -= 1f;
                    hitBox.x -= 1f;

                }
                if (rand <= 3 && hitBox.x == 623 && hitBox.y > Integer.MIN_VALUE) {
                    hitBox.y -= 1f;

                }
        hitBox = new Rectangle(hitBox.x, hitBox.y, 10, 10);
//        }
                if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                    hitBox.x -= 1;
                    System.out.println("x:" + hitBox.x + " y:" + hitBox.y);
                }
                if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                    hitBox.x += 2;
                    System.out.println("x:" + hitBox.x + " y:" + hitBox.y);
                }
                if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                    hitBox.y += 1;
                    System.out.println("x:" + hitBox.x + " y:" + hitBox.y);
                }
                if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                    hitBox.y -= 2;
                    System.out.println("x:" + hitBox.x + " y:" + hitBox.y);
                }
                if(rand>3 && hitBox.x + 50 >=1760){
                    System.out.println("jajaaj");
                    PlayScreen.health-=10;
                }
                if (rand <= 3 &&  hitBox.y +40 > 1017) {
                    System.out.println("ooga booga");
                    PlayScreen.minotaurs.remove(this);
                    PlayScreen.minotaurs.remove(this);
//                    hitBox.y -= 999999999999f;
//                    hitBox.x -= 999999999999f;
                    PlayScreen.health-=10;
                }
            }
        gdxGame.batch.draw(currentFrame, hitBox.x, hitBox.y);
        }
        else{   //BOSS
            stateTime += Gdx.graphics.getDeltaTime();
            if (PlayScreen.mapNum == 2 && !stopFlip) {
                System.out.println("flipped");
                for (TextureRegion region : walkAnimation.getKeyFrames()) {
                    region.flip(true, false); // Flip along the x-axis
                }
//            currentFrame.flip(true, false);
                stopFlip = true;
            }
            TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
            if (PlayScreen.mapNum == 1) {
                if (hitBox.x < 16 + 50&&hitBox.y==222+40) {
                    hitBox.x += 2f;
                }
                if (hitBox.y <= 360+40 && hitBox.x >= 16+50 && hitBox.x <= 152+50) {
                    hitBox.y += 2f;
                    hitBox.x += 2f;
                }
                if (hitBox.x == 154+50 && hitBox.y <= 456+40&&hitBox.y>=358+40) {
                    hitBox.y += 2f;
                }
                if (hitBox.y >= 456+40 && hitBox.x >= 152 +50 && hitBox.x <= 236 + 50 && hitBox.y < 536+40) {
                    hitBox.y += 2f;
                    hitBox.x += 2f;
                }
                if (hitBox.x < 334+50 && hitBox.y == 536+40) {
                    hitBox.x += 2f;
                }
                if (hitBox.y <= 670+40 && hitBox.x >= 334+50 && hitBox.x < 464+50 &&hitBox.y >= 536+40) {
                    hitBox.x += 2f;
                    hitBox.y += 2f;
                }

                if (hitBox.x == 464 +50&& hitBox.y < 772+40) {
                    hitBox.y += 2f;
                }
                if (hitBox.y < 850+40 && hitBox.x >= 464+50 && hitBox.x < 548+50 && hitBox.y >= 772+40) {
                    hitBox.x += 2f;
                    hitBox.y += 2f;
                }
                if (hitBox.x < 934+50 && hitBox.y == 850+40) {
                    hitBox.x += 2f;
                }
                if (hitBox.y < 902+40 && hitBox.x >= 934+50 && hitBox.x < 988+50 && hitBox.y >= 850+40) {
                    hitBox.x += 2f;
                    hitBox.y += 2f;
                }
                if (hitBox.x < 1120+50 && hitBox.y == 902+40) {
                    hitBox.x += 2f;
                }
                if (hitBox.y <= 902+40 && hitBox.x >= 1120+50 && hitBox.x < 1176+50 && hitBox.y >= 848+40) {
                    hitBox.x += 2f;
                    hitBox.y -= 2f;

                }
                if (hitBox.x < 1436+50 && hitBox.y == 848+40) {
                    hitBox.x += 2f;
                }
                if (hitBox.y <= 848+40 && hitBox.x >= 1436 +50&& hitBox.x < 1502+50 && hitBox.y >= 776+40) {
                    hitBox.x += 2f;
                    hitBox.y -= 2f;
                }
                if (hitBox.x == 1502 +50&& hitBox.y >= 662+40) {
                    hitBox.y -= 2f;
                }
                if (hitBox.x >= 1394+50 && hitBox.y >= 552+40 && hitBox.x <= 1502+50 && hitBox.y <= 662+40) {
                    hitBox.y -= 2f;
                    hitBox.x -= 2f;
                }
                if (hitBox.y == 552+40 && hitBox.x >= 1288+50) {
                    hitBox.x -= 2f;
                }
                if (hitBox.x >= 1182 +50&& hitBox.y >= 454+40 && hitBox.x <= 1288+50 && hitBox.y <= 552+40) {
                    hitBox.y -= 2f;
                    hitBox.x -= 2f;
                }
                if (hitBox.x == 1188 +50&& hitBox.y >= 358+40 && hitBox.y <= 454+40) {
                    hitBox.y -= 2f;
                }
                if (hitBox.y <= 358+40 && hitBox.x >= 1188+50 && hitBox.x < 1316+50 && hitBox.y >= 236+40) {
                    hitBox.x += 2f;
                    hitBox.y -= 2f;

                }
                if (hitBox.x <= 1390+50 && hitBox.y == 234+40) {
                    hitBox.x += 2f;
                }

                if (hitBox.y <= 234+40 && hitBox.x >= 1390+50 && hitBox.x < 1492 +50&& hitBox.y >= 136+40) {
                    hitBox.x += 2f;
                    hitBox.y -= 2f;
                }
                if (hitBox.x == 1490+50 && hitBox.y >= Integer.MIN_VALUE && hitBox.y <= 136+40) {
                    hitBox.y -= 2f;
                }

            }
            if (PlayScreen.mapNum == 2) {
                if (hitBox.x > 560+50 && hitBox.y == 770+40) {
//                if (needFlip) {
//                    currentFrame.flip(true, false);
//                    create();
//                    needFlip = false;
//                }
                    hitBox.x -= 2f;
                }

                if (hitBox.x >= 456+50 && hitBox.y >= 658 +40&& hitBox.x <= 560 +50&& hitBox.y <= 770+40) {
                    hitBox.y -= 2f;
                    hitBox.x -= 2f;
                }
                if (hitBox.y == 658+40){


//                stopFlip=false;
//                hitBox.x = (hitBox.x+hitBox.width)*-1;
//                hitBox.x+=10;
                }
                if (hitBox.x == 454+50 && hitBox.y > 576+40) {
                    hitBox.y -= 2f;
                }
                if (hitBox.y <= 576+40 && hitBox.x >= 454+50 && hitBox.x < 575+50 && hitBox.y >= 458+40) {
                    hitBox.x += 2f;
                    hitBox.y -= 2f;
                }
                if (hitBox.x < 669+50 && hitBox.y == 456+40) {
                    hitBox.x += 2f;
                }
                if (hitBox.y <= 456+40 && hitBox.x >= 669+50 && hitBox.x < 783+50 && hitBox.y >= 342+40) {
                    hitBox.x += 2f;
                    hitBox.y -= 2f;
                }
                if (hitBox.x == 784+50 && hitBox.y > 222+40 && hitBox.y <= 342+40) {
                    hitBox.y -= 2f;
                    int max = 10;
                    int min = 1;
                    int range = max - min + 1;
                    rand = (int) (Math.random() * range) + min;
                    System.out.println(rand);
                }

                if (rand > 3 && hitBox.y <= 222+40 && hitBox.x >= 784+50 && hitBox.x < 882+50 && hitBox.y >= 142+40) {
                    hitBox.x += 1f;
                    hitBox.y -= 1f;
                }
                if (rand > 3 && hitBox.y == 141+40 && hitBox.x < Integer.MAX_VALUE) {
                    hitBox.x+=2;
                }


                if (rand <= 3 && hitBox.y == 222+40 && hitBox.x > 648+50) {
                    hitBox.x-=2;
                }
                if (rand <= 3 && hitBox.x == 827+50&&hitBox.y==291+40){
//                stopFlip = false;
//                hitBox.x = (hitBox.x+hitBox.width)*-1;
                }
                if (rand <= 3 && hitBox.x <= 648+50 && hitBox.y >= 134+40 && hitBox.x >= 560+50 && hitBox.y <= 222+40) {
                    hitBox.y -= 2f;
                    hitBox.x -= 2f;

                }
                if (rand <= 3 && hitBox.x == 560+50 && hitBox.y > 100) {
                    hitBox.y -= 2f;
                }
                if(rand>3 && hitBox.x + 50 >=1920){
                    System.out.println("jajaaj");
                    PlayScreen.health-=10;
                }
                if (rand <= 3 &&  hitBox.y +40 > 1017) {
                    System.out.println("ooga booga");
                    PlayScreen.minotaurs.remove(this);
                    PlayScreen.minotaurs.remove(this);
//                    hitBox.y -= 999999999999f;
//                    hitBox.x -= 999999999999f;
                    PlayScreen.health-=10;
                }
            }
        hitBox = new Rectangle(hitBox.x, hitBox.y, 10, 10);
        gdxGame.batch.draw(currentFrame, hitBox.x-50, hitBox.y-40);
        }
//        hitBox = new Rectangle(hitBox.x,hitBox.y,10, 10);
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
//        hitBox = new Rectangle(hitBox.x,hitBox.y,10, 20);

    }

    public void setY(float y){
        hitBox.y = y;
//        hitBox = new Rectangle(hitBox.x,hitBox.y,10, 20);
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

package com.mygdx.game.Projectiles;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Enemies.Minotaur;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Towers.FireTower;
import com.mygdx.game.gdxGame;
import com.badlogic.gdx.math.Rectangle;

import static com.mygdx.game.Screens.PlayScreen.level;


public class Fire {

    public Minotaur shortestMinotaur;
    private float initialX;
    private float initialY;
    Texture fireTexture = new Texture("fire.png");
    TextureRegion fireicon = new TextureRegion(fireTexture);
    Sprite fire;
    private float shortest = Float.MAX_VALUE;

    private Rectangle hitBox;

    private long cooldown = 1000;
    private long lastTime = 0;

    private int count;
    public Fire(float x, float y, int width, int height) {
//        super(x, y, width, height);
        initialX = x;
        initialY = y;
//        initializeHitBox(x,y,width,height);
    }

    public void create() {
//        fire = new Sprite(fireicon);
//        hitBox = fire.getBoundingRectangle();
        hitBox = new Rectangle(initialX, initialY, 5,7);
        hitBox.setX(initialX);
        hitBox.setY(initialY);
    }

    public void render(FireTower f) {
        if (getClosest() != null && Intersector.overlaps(f.getHitBox(), getClosest().getHitBox())) {
            if (shortestMinotaur.getX() == f.getX() && shortestMinotaur.getY() > f.getY()){
                gdxGame.batch.draw(fireicon, hitBox.x, hitBox.y, (float) fireTexture.getWidth()/2, (float) fireTexture.getHeight()/2, fireTexture.getWidth(), fireTexture.getHeight(), 1,1,0,false);
            }
            else if (shortestMinotaur.getX() == f.getX() && shortestMinotaur.getY() < f.getY()){
                gdxGame.batch.draw(fireicon, hitBox.x, hitBox.y, (float) fireTexture.getWidth()/2, (float) fireTexture.getHeight()/2, fireTexture.getWidth(), fireTexture.getHeight(), 1,1,180,false);
            }
            else if (shortestMinotaur.getX() <= f.getX() && shortestMinotaur.getY() > f.getY()) {
//                gdxGame.batch.draw(fireicon, hitBox.x, hitBox.y, (float) fireTexture.getWidth()/2, (float) fireTexture.getHeight()/2, fireTexture.getWidth(), fireTexture.getHeight(), 1,1,135,false);
                gdxGame.batch.draw(fireicon, hitBox.x, hitBox.y, (float) fireTexture.getWidth()/2, (float) fireTexture.getHeight()/2, fireTexture.getWidth(), fireTexture.getHeight(), 1,1,315,false);

            }
            else if (shortestMinotaur.getX() > initialX && shortestMinotaur.getY() > initialY) {
                //                System.out.println("GAy1");
//                gdxGame.batch.draw(fireicon, hitBox.x, hitBox.y, hitBox.x+hitBox.width/2, hitBox.y + hitBox.height/2, hitBox.width, hitBox.height,1,1,-45,0,0,(int)hitBox.width, (int)hitBox.height,false, false);
                gdxGame.batch.draw(fireicon, hitBox.x, hitBox.y, (float) fireTexture.getWidth()/2, (float) fireTexture.getHeight()/2, fireTexture.getWidth(), fireTexture.getHeight(), 1,1,225,false);
            }
            else if (shortestMinotaur.getX() < initialX && shortestMinotaur.getY() < initialY) {
                gdxGame.batch.draw(fireicon, hitBox.x, hitBox.y, (float) fireTexture.getWidth()/2, (float) fireTexture.getHeight()/2, fireTexture.getWidth(), fireTexture.getHeight(), 1,1,45,false);
            }
            else if (shortestMinotaur.getX() > initialX && shortestMinotaur.getY() < initialY) {
                gdxGame.batch.draw(fireicon, hitBox.x, hitBox.y, (float) fireTexture.getWidth()/2, (float) fireTexture.getHeight()/2, fireTexture.getWidth(), fireTexture.getHeight(), 1,1,135,false);
            }
//            gdxGame.batch.draw(fireicon, hitBox.x, hitBox.y);
        }
    }

    public Minotaur getClosest() {

        for (Minotaur s : PlayScreen.minotaurs) {
            float xDist = Math.abs(s.getX() - initialX);
            float yDist = Math.abs(s.getY() - initialY);
            float realDist = (float) Math.hypot(xDist, yDist);
            if (realDist < shortest) {
                shortest = realDist;
                shortestMinotaur = s;
            }
        }
        if (PlayScreen.minotaurs.size() == 0) return null;
        return shortestMinotaur;
    }

    public  void intersects(FireTower r) {
        for (int i = PlayScreen.minotaurs.size() - 1; i >= 0; i--) {
            if (PlayScreen.minotaurs.get(i) == (getClosest())) {
                if (Intersector.overlaps(hitBox, getClosest().getHitBox()) && (Intersector.overlaps(r.getHitBox(), hitBox))) {
                    getClosest().setHealth(-10);
                    for (int v=0;v<=r.getFires().size()-1;v++) {
                        r.getFires().get(v).setX(999999);
                        r.getFires().get(v).setY(999999);
                    }
                    if (getClosest().getHealth() <= 0) {
                        PlayScreen.dinero += 30 * (level);
                        for (int j = PlayScreen.minotaurs.size() - 1; j >= 0; j--) {
//                            for (Slime s : PlayScreen.slimes) {
                            if (PlayScreen.minotaurs.get(j).equals(shortestMinotaur)) {
                                if(level<10){
                                    gdxGame.soundEffect.play(0.1f);
                                }
                                else {
                                    gdxGame.soundEffect1.play(0.07f);
                                }
                                PlayScreen.minotaurs.get(j).setX(-99999999f);
                                PlayScreen.minotaurs.get(j).setY(-99999999f);
                                slimeDestroyedByOtherTower(r);
                                PlayScreen.minotaurs.remove(j);
                                getClosest();
                            }
                        }
                    }
                }

            }
        }
//        for (int i = PlayScreen.minotaurs.size() - 1; i >= 0; i--) {
//            if (PlayScreen.minotaurs.get(i) == (getClosest())) {
////                System.out.println("affs");
//                if (Intersector.overlaps(hitBox, getClosest().getHitBox()) && (Intersector.overlaps(f.getHitBox(), hitBox))) {
////                    System.out.println("hit");
//                    getClosest().health -= 20;
//                    for (int v=0;v<=f.getFires().size()-1;v++) {
//                        f.getFires().get(v).setX(999999);
//                        f.getFires().get(v).setY(999999);
//                    }
//                    System.out.println("gay");
////                    f.getFires().get(f.getFires().size()-1).setX(99999999);
////                    f.getFires().get(f.getFires().size()-1).setY(99999999);
//
////                    f.getFires().remove(this);
//                    if (getClosest() != null && getClosest().health <= 0) {
//                        PlayScreen.dinero += 10 * (PlayScreen.level);
//                        for (int j = PlayScreen.minotaurs.size() - 1; j >= 0; j--) {
////                            for (Slime s : PlayScreen.slimes) {
//                            if (PlayScreen.minotaurs.get(j).equals(shortestMinotaur)) {
//                                PlayScreen.minotaurs.get(j).setX(-99999999f);
//                                System.out.println("slime died");
//                                PlayScreen.minotaurs.get(j).setY(-99999999f);
//                                slimeDestroyedByOtherTower(f);
//                                PlayScreen.minotaurs.remove(j);
//                                getClosest();
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        if (!Intersector.overlaps(f.getHitBox(), getClosest().getHitBox())){
//            for (int v=0;v<=f.getFires().size()-1;v++) {
//                f.getFires().get(v).setX(999999);
//                f.getFires().get(v).setY(999999);
//            }
//        }
    }

//    public void intersects(FireTowerAnimation r) {
//        if (Intersector.overlaps(hitBox, getClosest().getHitBox())) {
//            getClosest().health -= 20;
//            if (getClosest().health <= 0) {
//                getClosest().setX(-99999999f);
//                getClosest().setY(-99999999f);
//                for (Fire e : r.getFires()) {
//                    e.setX(-9999999);
//                    e.setY(-9999999);
//                }
//            }
//        }
//    }

    public void slimeDestroyedByOtherTower(FireTower f) {
        if (shortestMinotaur != null) {
            for(int i = 0;i<f.getFires().size()-1;i++){
                f.getFires().get(i).setX(-9999999999f);
                f.getFires().get(i).setY(-9999999999f);
//                f.getFires().remove(i);

//                    e.setX(-9999999);
//                    e.setY(-9999999);
            }
        }
    }



    public void shoot(FireTower f) {
        long currentTime = TimeUtils.millis();
        if (shortestMinotaur != null&& getClosest()!=null) {
            if ((Intersector.overlaps(f.getHitBox(), hitBox) && (Intersector.overlaps(f.getHitBox(), getClosest().getHitBox())))) {
                lastTime = currentTime;
                float xIncrement;
                float yIncrement;
                float xDist = Math.abs(shortestMinotaur.getX() - initialX);
                float yDist = Math.abs(shortestMinotaur.getY() - initialY);
                float realDist = (float) Math.hypot(xDist, yDist);
                float ratioy = yDist / realDist;
                float ratiox = xDist / realDist;
                xIncrement = realDist * (ratiox / 10);
                yIncrement = realDist * (ratioy / 10);
                if (PlayScreen.level >= 10){
                    xIncrement *= 2;
                    yIncrement*=2;
                }
                if (Intersector.overlaps(f.getHitBox(), hitBox)) {
                    if (shortestMinotaur.getX() < initialX && shortestMinotaur.getY() > initialY) {
                        hitBox.x -= xIncrement;
                        hitBox.y += yIncrement;
                    }
                    if (shortestMinotaur.getX() > initialX && shortestMinotaur.getY() > initialY) {
                        //                System.out.println("GAy1");
                        hitBox.x += xIncrement;
                        hitBox.y += yIncrement;
                    }
                    if (shortestMinotaur.getX() < initialX && shortestMinotaur.getY() < initialY) {
                        hitBox.x -= xIncrement;
                        hitBox.y -= yIncrement;
                    }
                    if (shortestMinotaur.getX() > initialX && shortestMinotaur.getY() < initialY) {
                        hitBox.x += xIncrement;
                        hitBox.y -= yIncrement;
                    }
//                    count++;
//                    hitBox.setX(hitBox.x);
//                    hitBox.setY(hitBox.y);
//                    hitBox = new Rectangle(hitBox.x,hitBox.y,5, 7);
                }
//                else {
//                    slimeDestroyedByOtherTower(f);
//                }
            }
        }
    }
    public void setX(float x){
        this.hitBox.x = x;
    }

    public void setY(float y){
        this.hitBox.y = y;}

//    public void yeetem(){
//        if (this.hitBox.intersects(shortestSlime.getHitBox())) {
//            setX(-9999f);
//            setY(-9999f);
//            shortestSlime.setX(-9999f);
//            shortestSlime.setY(-9999f);
////            getClosest();
//
//        }
//    }


    long lastAttack = 0;
    long coolDownTime = 1000;
    public void cooldown() {
        long time = System.currentTimeMillis();
        if (time > lastAttack + coolDownTime) {
            // Do something
            lastAttack = time;
        }
    }

    public Rectangle getHitBox(){
        return hitBox;
    }


    public void dispose(){
        fire.getTexture().dispose();
    }
    public String toString(){
        return "OVERRIDEN";
    }
    public boolean equals(Object obj){
        return true;
    }

}

package com.mygdx.game.Projectiles;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Enemies.Hitboxes;
import com.mygdx.game.Enemies.Slime;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Towers.FireTowerAnimation;
import com.mygdx.game.Towers.RockTowerAnimation;
import com.mygdx.game.gdxGame;
import com.mygdx.game.Screens.PlayScreen.*;
import com.badlogic.gdx.math.Rectangle;


public class Fire {

    public Slime shortestSlime;
    private float initialX;
    private float initialY;
    Texture fireicon = new Texture("fire.png");
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
        fire = new Sprite(fireicon);
        hitBox = fire.getBoundingRectangle();
        hitBox.setX(initialX);
        hitBox.setY(initialY);
    }

    public void render(FireTowerAnimation f) {
        if (getClosest() != null && Intersector.overlaps(f.getHitBox(), getClosest().getHitBox()))
            gdxGame.batch.draw(fire, hitBox.x, hitBox.y);
    }

    public Slime getClosest() {

        for (Slime s : PlayScreen.slimes) {
            float xDist = Math.abs(s.getX() - initialX);
            float yDist = Math.abs(s.getY() - initialY);
            float realDist = (float) Math.hypot(xDist, yDist);
            if (realDist < shortest) {
                shortest = realDist;
                shortestSlime = s;
            }
        }
        if (PlayScreen.slimes.size() == 0) return null;
        return shortestSlime;
    }

    public  void intersects(FireTowerAnimation f) {
        for (int i = PlayScreen.slimes.size() - 1; i >= 0; i--) {
            if (PlayScreen.slimes.get(i) == (getClosest())) {
//                System.out.println("affs");
                if (Intersector.overlaps(hitBox, getClosest().getHitBox()) && (Intersector.overlaps(f.getHitBox(), hitBox))) {
                    System.out.println("hit");
                    getClosest().health -= 20;
                    if (getClosest().health <= 0) {
                        PlayScreen.dinero += 10 * (PlayScreen.level);
                        for (int j = PlayScreen.slimes.size() - 1; j >= 0; j--) {
//                            for (Slime s : PlayScreen.slimes) {
                            if (PlayScreen.slimes.get(j).equals(shortestSlime)) {
                                PlayScreen.slimes.get(j).setX(-99999999f);
                                System.out.println("slime died");
                                PlayScreen.slimes.get(j).setY(-99999999f);
                                slimeDestroyedByOtherTower(f);
                                PlayScreen.slimes.remove(j);
                                getClosest();
                            }
                        }
                    }
                }

            }
        }
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

    public void slimeDestroyedByOtherTower(FireTowerAnimation f) {
        System.out.println("hit1");
        if (shortestSlime != null) {
            for(int i = 0;i<f.getFires().size()-1;i++){
                f.getFires().get(i).setX(-9999999999f);
                f.getFires().get(i).setY(-9999999999f);
//                f.getFires().remove(i);

//                    e.setX(-9999999);
//                    e.setY(-9999999);
            }
        }
    }



    public void shoot(FireTowerAnimation f) {
        long currentTime = TimeUtils.millis();
        if (shortestSlime != null&& getClosest()!=null) {
            if ((Intersector.overlaps(f.getHitBox(), hitBox) && (Intersector.overlaps(f.getHitBox(), getClosest().getHitBox())))) {
                lastTime = currentTime;
                float xIncrement;
                float yIncrement;
                float xDist = Math.abs(shortestSlime.getX() - initialX);
                float yDist = Math.abs(shortestSlime.getY() - initialY);
                float realDist = (float) Math.hypot(xDist, yDist);
                float ratioy = yDist / realDist;
                float ratiox = xDist / realDist;
                xIncrement = realDist * (ratiox / 10);
                yIncrement = realDist * (ratioy / 10);
                if (Intersector.overlaps(f.getHitBox(), hitBox)) {
                    if (shortestSlime.getX() <= f.getX() && shortestSlime.getY() > f.getY()) {
                        hitBox.x -= xIncrement;
                        hitBox.y += yIncrement;
                    }
                    if (shortestSlime.getX() > initialX && shortestSlime.getY() > initialY) {
                        //                System.out.println("GAy1");
                        hitBox.x += xIncrement;
                        hitBox.y += yIncrement;
                    }
                    if (shortestSlime.getX() < initialX && shortestSlime.getY() < initialY) {
                        hitBox.x -= xIncrement;
                        hitBox.y -= yIncrement;
                    }
                    if (shortestSlime.getX() >= initialX && shortestSlime.getY() <= initialY) {
                        hitBox.x += xIncrement;
                        hitBox.y -= yIncrement;
                    }
                    count++;
                    hitBox.setX(hitBox.x);
                    hitBox.setY(hitBox.y);
                } else {
                    slimeDestroyedByOtherTower(f);
                }
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

}

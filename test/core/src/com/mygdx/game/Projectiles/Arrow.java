package com.mygdx.game.Projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Enemies.Minotaur;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Towers.ArrowTower;
import com.mygdx.game.gdxGame;

public class Arrow {
    public Minotaur shortestMinotaur;
    private float initialX;
    private float initialY;
    Texture arrowicon = new Texture("arrow.png");
    Sprite arrow;
    private float shortest = Float.MAX_VALUE;

    private Rectangle hitBox;

    private long cooldown = 1000;
    private long lastTime = 0;

    private int count;
    public Arrow(float x, float y, int width, int height) {
//        super(x, y, width, height);
        initialX = x;
        initialY = y;
//        initializeHitBox(x,y,width,height);
    }

    public void create() {
        arrow = new Sprite(arrowicon);
        hitBox = arrow.getBoundingRectangle();
        hitBox.setX(initialX);
        hitBox.setY(initialY);
    }

    public void render(ArrowTower a) {
        if (getClosest() != null && Intersector.overlaps(a.getHitBox(), getClosest().getHitBox()))
            gdxGame.batch.draw(arrow, hitBox.x, hitBox.y);
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

    public  void intersects(ArrowTower a) {
        for (int i = PlayScreen.minotaurs.size() - 1; i >= 0; i--) {
            if (PlayScreen.minotaurs.get(i) == (getClosest())) {
//                System.out.println("affs");
                if (Intersector.overlaps(hitBox, getClosest().getHitBox()) && (Intersector.overlaps(a.getHitBox(), hitBox))) {
                    System.out.println("hit");
                    getClosest().health -= 20;
                    a.getArrows().get(a.getArrows().size()-1).setX(999999);
                    a.getArrows().get(a.getArrows().size()-1).setY(999999);
                    if (getClosest() != null && getClosest().health <= 0) {
                        PlayScreen.dinero += 10 * (PlayScreen.level);
                        for (int j = PlayScreen.minotaurs.size() - 1; j >= 0; j--) {
//                            for (Slime s : PlayScreen.slimes) {
                            if (PlayScreen.minotaurs.get(j).equals(shortestMinotaur)) {
                                PlayScreen.minotaurs.get(j).setX(-99999999f);
                                System.out.println("slime died");
                                PlayScreen.minotaurs.get(j).setY(-99999999f);
                                slimeDestroyedByOtherTower(a);
                                PlayScreen.minotaurs.remove(j);
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

    public void slimeDestroyedByOtherTower(ArrowTower a) {
        System.out.println("hit1");
        if (shortestMinotaur != null) {
            for(int i = 0;i<a.getArrows().size()-1;i++){
                a.getArrows().get(i).setX(-9999999999f);
                a.getArrows().get(i).setY(-9999999999f);
//                f.getFires().remove(i);

//                    e.setX(-9999999);
//                    e.setY(-9999999);
            }
        }
    }



    public void shoot(ArrowTower a) {
        long currentTime = TimeUtils.millis();
        if (shortestMinotaur != null&& getClosest()!=null) {
            if ((Intersector.overlaps(a.getHitBox(), hitBox) && (Intersector.overlaps(a.getHitBox(), getClosest().getHitBox())))) {
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
                if (Intersector.overlaps(a.getHitBox(), hitBox)) {
                    if (shortestMinotaur.getX() <= a.getX() && shortestMinotaur.getY() > a.getY()) {
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
                    if (shortestMinotaur.getX() >= initialX && shortestMinotaur.getY() <= initialY) {
                        hitBox.x += xIncrement;
                        hitBox.y -= yIncrement;
                    }
                    count++;
                    hitBox.setX(hitBox.x);
                    hitBox.setY(hitBox.y);
                } else {
                    slimeDestroyedByOtherTower(a);
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
        arrow.getTexture().dispose();
    }
}

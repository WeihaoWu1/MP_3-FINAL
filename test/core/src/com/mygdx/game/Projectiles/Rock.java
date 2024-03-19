package com.mygdx.game.Projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Enemies.Hitboxes;
import com.mygdx.game.Enemies.Slime;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Towers.FireTowerAnimation;
import com.mygdx.game.Towers.RockTowerAnimation;
import com.mygdx.game.gdxGame;

public class Rock {
    Slime shortestSlime;
    private float initialX;
    private float initialY;
    long lastAttack = 0;
    long coolDownTime = 1000;
    private float shortest = Float.MAX_VALUE;
    Texture rockicon = new Texture("rock.png");
    Sprite rock;
    private Rectangle hitBox;
    public Rock(float x, float y, int width, int height) {
        initialX = x;
        initialY = y;
    }
    public void create(){
        rock = new Sprite(rockicon);
        hitBox = rock.getBoundingRectangle();
        hitBox.setX(initialX);
        hitBox.setY(initialY);
    }

    public void render(RockTowerAnimation r){
        if (Intersector.overlaps(r.getHitBox(), getClosest().getHitBox()))
            gdxGame.batch.draw(rock, hitBox.x, hitBox.y);
    }
    public Slime getClosest(){
        for (Slime s : PlayScreen.slimes){
            float xDist = Math.abs(s.getX() - initialX);
            float yDist = Math.abs(s.getY() - initialY);
            float realDist = (float) Math.hypot(xDist, yDist);
            if (realDist < shortest) {
                shortest = realDist;
                shortestSlime = s;
            }
        }
        return shortestSlime;
    }


    public void cooldown() {
        long time = System.currentTimeMillis();
        if (time > lastAttack + coolDownTime) {
            // Do something
            lastAttack = time;
        }
    }
    public  void intersects(RockTowerAnimation r) {
        for (int i = PlayScreen.slimes.size() - 1; i >= 0; i--) {
            if (PlayScreen.slimes.get(i) == (getClosest())) {
                if (Intersector.overlaps(hitBox, getClosest().getHitBox()) && (Intersector.overlaps(r.getHitBox(), hitBox))) {
                    System.out.println("hit");
                    getClosest().health -= 20;
                    if (getClosest().health <= 0) {
                        for (int j = PlayScreen.slimes.size() - 1; j >= 0; j--) {
//                            for (Slime s : PlayScreen.slimes) {
                            if (PlayScreen.slimes.get(j).equals(shortestSlime)) {
                                PlayScreen.slimes.get(j).setX(-99999999f);
                                System.out.println("slime died");
                                PlayScreen.slimes.get(j).setY(-99999999f);
                                slimeDestroyedByOtherTower(r);
                                PlayScreen.slimes.remove(j);
                                getClosest();
                            }
                        }
                    }
                }

            }
        }
    }

    public void slimeDestroyedByOtherTower(RockTowerAnimation r) {
        System.out.println("hit1");
        if (shortestSlime != null) {
            for(int i = 0;i<r.getRocks().size()-1;i++){
                r.getRocks().get(i).setX(-9999999999f);
                r.getRocks().get(i).setY(-9999999999f);
//                f.getFires().remove(i);

//                    e.setX(-9999999);
//                    e.setY(-9999999);
            }
        }
    }

    public void shoot(RockTowerAnimation r) {
        if (shortestSlime != null) {
            if (Intersector.overlaps(r.getHitBox(), hitBox) && (Intersector.overlaps(r.getHitBox(), getClosest().getHitBox()))) {
                float xIncrement;
                float yIncrement;
                float xDist = Math.abs(shortestSlime.getX() - initialX);
                float yDist = Math.abs(shortestSlime.getY() - initialY);
                float realDist = (float) Math.hypot(xDist, yDist);
                float ratioy = yDist / realDist;
                float ratiox = xDist / realDist;
                xIncrement = realDist * (ratiox / 10);
                yIncrement = realDist * (ratioy / 10);
                if (shortestSlime.getX() < initialX && shortestSlime.getY() > initialY) {

                    hitBox.x -= xIncrement;
                    hitBox.y += yIncrement;
                }
                if (shortestSlime.getX() > initialX && shortestSlime.getY() > initialY) {
                    hitBox.x += xIncrement;
                    hitBox.y += yIncrement;
                }
                if (shortestSlime.getX() < initialX && shortestSlime.getY() < initialY) {
                    hitBox.x -= xIncrement;
                    hitBox.y -= yIncrement;

                }
                if (shortestSlime.getX() > initialX && shortestSlime.getY() < initialY) {
                    hitBox.x += xIncrement;
                    hitBox.y -= yIncrement;
                }
            }
        }
    }
    public Rectangle getHitBox(){
        return hitBox;
    }

    public void setX(float newX){
        hitBox.x = newX;
    }

    public void setY(float newY){
        hitBox.y= newY;
    }


    public void dispose(){
        rock.getTexture().dispose();
    }

}

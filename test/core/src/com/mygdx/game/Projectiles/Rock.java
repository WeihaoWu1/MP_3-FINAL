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

    public void render(){
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
    public void intersects(RockTowerAnimation r) {
        if (Intersector.overlaps(hitBox, getClosest().getHitBox())) {
            getClosest().health -= 20;
            if (getClosest().health <= 0) {
                getClosest().setX(-99999999f);
                getClosest().setY(-99999999f);
                for (Rock e : r.getRocks()) {
                    e.setX(-9999999);
                    e.setY(-9999999);
                }
            }
        }
    }

    public void shoot(RockTowerAnimation r) {
        if (Intersector.overlaps(r.getHitBox(), getClosest().getHitBox())) {
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

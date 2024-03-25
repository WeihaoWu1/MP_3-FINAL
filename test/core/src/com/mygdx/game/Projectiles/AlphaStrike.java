package com.mygdx.game.Projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Enemies.Minotaur;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Towers.AlphaTower;
import com.mygdx.game.gdxGame;

public class AlphaStrike {
    Minotaur shortestMinotaur;
    private float initialX;
    private float initialY;
    long lastAttack = 0;
    long coolDownTime = 1000;
    private float shortest = Float.MAX_VALUE;
    Texture alphaicon = new Texture("alphaStrike.png");
    Sprite alphaStrike;
    private Rectangle hitBox;
    public AlphaStrike(float x, float y, int width, int height) {
        initialX = x;
        initialY = y;
    }
    public void create(){
//        rock = new Sprite(rockicon);
//        hitBox = rock.getBoundingRectangle();
        hitBox = new Rectangle(initialX, initialY, 5,7);
        hitBox.setX(initialX);
        hitBox.setY(initialY);
    }

    public void render(AlphaTower r){
        if (getClosest() != null && Intersector.overlaps(r.getHitBox(), getClosest().getHitBox()))
            gdxGame.batch.draw(alphaicon, hitBox.x, hitBox.y);
    }
    public Minotaur getClosest(){
        for (Minotaur s : PlayScreen.minotaurs){
            float xDist = Math.abs(s.getX() - initialX);
            float yDist = Math.abs(s.getY() - initialY);
            float realDist = (float) Math.hypot(xDist, yDist);
            if (realDist < shortest) {
                shortest = realDist;
                shortestMinotaur = s;
            }
        }
        return shortestMinotaur;
    }


    public void cooldown() {
        long time = System.currentTimeMillis();
        if (time > lastAttack + coolDownTime) {
            // Do something
            lastAttack = time;
        }
    }
    public  void intersects(AlphaTower r) {
        for (int i = PlayScreen.minotaurs.size() - 1; i >= 0; i--) {
            if (PlayScreen.minotaurs.get(i) == (getClosest())) {
                if (Intersector.overlaps(hitBox, getClosest().getHitBox()) && (Intersector.overlaps(r.getHitBox(), hitBox))) {
                    System.out.println("hit");
                    getClosest().health -= 20;
                    for (int v=0;v<=r.getAlphaStrikes().size()-1;v++) {
                        r.getAlphaStrikes().get(v).setX(999999);
                        r.getAlphaStrikes().get(v).setY(999999);
                    }
                    if (getClosest().health <= 0) {
                        PlayScreen.dinero += 10 * (PlayScreen.level);
                        for (int j = PlayScreen.minotaurs.size() - 1; j >= 0; j--) {
//                            for (Slime s : PlayScreen.slimes) {
                            if (PlayScreen.minotaurs.get(j).equals(shortestMinotaur)) {
                                PlayScreen.minotaurs.get(j).setX(-99999999f);
                                System.out.println("slime died");
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
    }

    public void slimeDestroyedByOtherTower(AlphaTower r) {
        System.out.println("hit1");
        if (shortestMinotaur != null) {
            for(int i = 0;i<r.getAlphaStrikes().size()-1;i++){
                r.getAlphaStrikes().get(i).setX(-9999999999f);
                r.getAlphaStrikes().get(i).setY(-9999999999f);
//                f.getFires().remove(i);

//                    e.setX(-9999999);
//                    e.setY(-9999999);
            }
        }
    }

    public void shoot(AlphaTower r) {
        if (shortestMinotaur != null) {
            if (Intersector.overlaps(r.getHitBox(), hitBox) && (Intersector.overlaps(r.getHitBox(), getClosest().getHitBox()))) {
                float xIncrement;
                float yIncrement;
                float xDist = Math.abs(shortestMinotaur.getX() - initialX);
                float yDist = Math.abs(shortestMinotaur.getY() - initialY);
                float realDist = (float) Math.hypot(xDist, yDist);
                float ratioy = yDist / realDist;
                float ratiox = xDist / realDist;
                xIncrement = realDist * (ratiox / 10);
                yIncrement = realDist * (ratioy / 10);
                if (shortestMinotaur.getX() < initialX && shortestMinotaur.getY() > initialY) {
                    hitBox.x -= xIncrement;
                    hitBox.y += yIncrement;
                }
                if (shortestMinotaur.getX() > initialX && shortestMinotaur.getY() > initialY) {
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
        alphaStrike.getTexture().dispose();
    }
}

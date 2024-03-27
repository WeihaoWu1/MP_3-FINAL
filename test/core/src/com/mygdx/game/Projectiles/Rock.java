package com.mygdx.game.Projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Enemies.Minotaur;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Towers.RockTower;
import com.mygdx.game.gdxGame;

import static com.mygdx.game.Screens.PlayScreen.level;

public class Rock {
    Minotaur shortestMinotaur;
    private float initialX;
    private float initialY;
    long lastAttack = 0;
    long coolDownTime = 1000;
    private float shortest = Float.MAX_VALUE;
    Texture rockTexture = new Texture("rock.png");
    TextureRegion rockicon = new TextureRegion(rockTexture);
    Sprite rock;
    private Rectangle hitBox;
    public Rock(float x, float y, int width, int height) {
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

    public void render(RockTower f){
        if (getClosest() != null && Intersector.overlaps(f.getHitBox(), getClosest().getHitBox())) {
            if (shortestMinotaur.getX() == f.getX() && shortestMinotaur.getY() > f.getY()){
                gdxGame.batch.draw(rockicon, hitBox.x, hitBox.y, (float) rockTexture.getWidth()/2, (float) rockTexture.getHeight()/2, rockTexture.getWidth(), rockTexture.getHeight(), 1,1,0,false);
            }
            else if (shortestMinotaur.getX() == f.getX() && shortestMinotaur.getY() < f.getY()){
                gdxGame.batch.draw(rockicon, hitBox.x, hitBox.y, (float) rockTexture.getWidth()/2, (float) rockTexture.getHeight()/2, rockTexture.getWidth(), rockTexture.getHeight(), 1,1,180,false);
            }
            else if (shortestMinotaur.getX() <= f.getX() && shortestMinotaur.getY() > f.getY()) {
                gdxGame.batch.draw(rockicon, hitBox.x, hitBox.y, (float) rockTexture.getWidth()/2, (float) rockTexture.getHeight()/2, rockTexture.getWidth(), rockTexture.getHeight(), 1,1,315,false);

            }
            else if (shortestMinotaur.getX() > initialX && shortestMinotaur.getY() > initialY) {
                //                System.out.println("GAy1");
//                gdxGame.batch.draw(fireicon, hitBox.x, hitBox.y, hitBox.x+hitBox.width/2, hitBox.y + hitBox.height/2, hitBox.width, hitBox.height,1,1,-45,0,0,(int)hitBox.width, (int)hitBox.height,false, false);
                gdxGame.batch.draw(rockicon, hitBox.x, hitBox.y, (float) rockTexture.getWidth()/2, (float) rockTexture.getHeight()/2, rockTexture.getWidth(), rockTexture.getHeight(), 1,1,225,false);
            }
            else if (shortestMinotaur.getX() < initialX && shortestMinotaur.getY() < initialY) {
                gdxGame.batch.draw(rockicon, hitBox.x, hitBox.y, (float) rockTexture.getWidth()/2, (float) rockTexture.getHeight()/2, rockTexture.getWidth(), rockTexture.getHeight(), 1,1,45,false);
            }
            else if (shortestMinotaur.getX() > initialX && shortestMinotaur.getY() < initialY) {
                gdxGame.batch.draw(rockicon, hitBox.x, hitBox.y, (float) rockTexture.getWidth()/2, (float) rockTexture.getHeight()/2, rockTexture.getWidth(), rockTexture.getHeight(), 1,1,135,false);
            }
//            gdxGame.batch.draw(fireicon, hitBox.x, hitBox.y);
        }
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
    public  void intersects(RockTower r) {
        for (int i = PlayScreen.minotaurs.size() - 1; i >= 0; i--) {
            if (PlayScreen.minotaurs.get(i) == (getClosest())) {
                if (Intersector.overlaps(hitBox, getClosest().getHitBox()) && (Intersector.overlaps(r.getHitBox(), hitBox))) {
                    getClosest().setHealth(-12);
                    for (int v=0;v<=r.getRocks().size()-1;v++) {
                        r.getRocks().get(v).setX(999999);
                        r.getRocks().get(v).setY(999999);
                    }
                    if (getClosest().getHealth() <= 0) {
                        PlayScreen.dinero += 30 * (PlayScreen.level);
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
    }

    public void slimeDestroyedByOtherTower(RockTower r) {
        System.out.println("hit1");
        if (shortestMinotaur != null) {
            for(int i = 0;i<r.getRocks().size()-1;i++){
                r.getRocks().get(i).setX(-9999999999f);
                r.getRocks().get(i).setY(-9999999999f);
//                f.getFires().remove(i);

//                    e.setX(-9999999);
//                    e.setY(-9999999);
            }
        }
    }

    public void shoot(RockTower r) {
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
                if (PlayScreen.level >= 10){
                    xIncrement *= 2;
                    yIncrement*=2;
                }
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
        rock.getTexture().dispose();
    }

}

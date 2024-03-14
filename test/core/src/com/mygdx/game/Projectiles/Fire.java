package com.mygdx.game.Projectiles;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Enemies.Hitboxes;
import com.mygdx.game.Enemies.Slime;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Towers.FireTowerAnimation;
import com.mygdx.game.gdxGame;
import com.mygdx.game.Screens.PlayScreen.*;

public class Fire extends Hitboxes{


    Slime shortestSlime;
    private float initialX;
    private float initialY;
    Texture fire = new Texture("fire.png");
    private float shortest = Float.MAX_VALUE;
    public Fire(float x, float y, int width, int height) {
        super(x, y, width, height);
        initialX=x;
        initialY=y;
        initializeHitBox(x,y,width,height);
    }

    public void create(){
        fire = new Texture("fire.png");
    }

    public void render(){
        gdxGame.batch.draw(fire, hitBox.x, hitBox.y);
    }

    public void getClosest(){
        for (Slime s : PlayScreen.slimes){
            float xDist = Math.abs(s.getX() - initialX);
            float yDist = Math.abs(s.getY() - initialY);
            float realDist = (float) Math.hypot(xDist, yDist);
            if (realDist < shortest){
                shortest = realDist;
                shortestSlime = s;
            }
        }
    }

    public void shoot() {
        float xIncrement;
        float yIncrement;
        float xDist = Math.abs(shortestSlime.getX() - initialX);
        float yDist = Math.abs(shortestSlime.getY() - initialY);
        float realDist = (float) Math.hypot(xDist, yDist);
        float ratioy = yDist/realDist;
        float ratiox = xDist/realDist;
        xIncrement = realDist * (ratiox/10);
        yIncrement = realDist *  (ratioy/10);
//        if(xDist>=yDist){
//            xIncrement = yDist / xDist*30;
//            yIncrement = 1f*30;
//            }
//        if(xDist<yDist){
//                xIncrement = 1f*30;
//                yIncrement = yDist / xDist*30;
//            }
        if (shortestSlime.getX()<initialX && shortestSlime.getY()>initialY) {

            hitBox.x -= xIncrement;
            hitBox.y += yIncrement;
        }
        if (shortestSlime.getX()>initialX && shortestSlime.getY()>initialY) {
            hitBox.x += xIncrement;
            hitBox.y += yIncrement;
        }
         if (shortestSlime.getX()<initialX && shortestSlime.getY()<initialY) {
            hitBox.x -= xIncrement;
            hitBox.y -= yIncrement;
        }
         if (shortestSlime.getX()>initialX && shortestSlime.getY()<initialY) {
            hitBox.x += xIncrement;
            hitBox.y -= yIncrement;
        }
    }

    public void yeetem(){
        if (this.hitBox.intersects(shortestSlime.hitBox)) {
            hitBox.x = 9999;
            hitBox.y =9999;
            shortestSlime.hitBox.x = 9999;
            shortestSlime.hitBox.y =9999;
            getClosest();
            System.out.println("gone");
        }
    }

//    private void explodeOnEnemies(Projectile p) {
//        for (Enemy e : playing.getEnemyManger().getEnemies()) {
//            if (e.isAlive()) {
//                float radius = 40.0f;
//
//                float xDist = Math.abs(p.getPos().x - e.getX());
//                float yDist = Math.abs(p.getPos().y - e.getY());
//
//                float realDist = (float) Math.hypot(xDist, yDist);
//
//                if (realDist <= radius)
//                    e.hurt(p.getDmg());
//            }
//
//        }
//
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


    public void dispose(){
        fire.dispose();
    }

}

package com.mygdx.game.Projectiles;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Enemies.Hitboxes;
import com.mygdx.game.Enemies.Slime;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Towers.FireTowerAnimation;
import com.mygdx.game.gdxGame;
import com.mygdx.game.Screens.PlayScreen.*;

public class Fire extends Hitboxes{


    Slime shortestSlime = PlayScreen.slimes.get(0);
    private float initialX;
    private float initialY;
    Texture fire = new Texture("fire.png");
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
        float shortest = Float.MAX_VALUE;
        for (Slime s : PlayScreen.slimes){
            float xDist = Math.abs(s.getX() - initialX);
            float yDist = Math.abs(s.getY() - initialY);
            float realDist = (float) Math.hypot(xDist, yDist);
            if (realDist < shortest){
                shortest = realDist;
                shortestSlime = s;
                System.out.println(realDist);
                System.out.println(shortest);
                System.out.println("run");
            }
        }
    }

    public void shoot() {
        float xIncrement;
        float yIncrement;
        float xDist = Math.abs(shortestSlime.getX() - initialX);
        float yDist = Math.abs(shortestSlime.getY() - initialY);
        float realDist = (float) Math.hypot(xDist, yDist);
        xIncrement = realDist/5;
        yIncrement = realDist/5;
        if(xDist>=yDist){
            xIncrement = yDist / xDist*30;
            yIncrement = 1f*30;
            }
        if(xDist<yDist){
                xIncrement = 1f*30;
                yIncrement = yDist / xDist*30;
            }
        if (shortestSlime.getX()<initialX && shortestSlime.getY()>initialY) {

            hitBox.x -= xIncrement;
            hitBox.y += yIncrement;
        }
        if (shortestSlime.getX()>initialX && shortestSlime.getY()>initialY) {
            hitBox.x += xIncrement;
            hitBox.y += yIncrement;
        }
         if (shortestSlime.getX()<initialX && shortestSlime.getY()<initialY) {
            hitBox.x += xIncrement;
            hitBox.y -= yIncrement;
        }
         if (shortestSlime.getX()>initialX && shortestSlime.getY()<initialY) {
            hitBox.x -= xIncrement;
            hitBox.y -= yIncrement;
        }
    }

    public void yeetem(){
        if (this.hitBox.intersects(shortestSlime.hitBox)) {
            hitBox.x = 9999;
            hitBox.y =9999;
            shortestSlime.hitBox.x = 9999;
            shortestSlime.hitBox.y =9999;
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

package com.mygdx.game.Projectiles;
import com.mygdx.game.Enemies.Hitboxes;

public class Fire extends Hitboxes {
    long lastAttack = 0;
    long coolDownTime = 1000;
    public Fire(float x, float y, int width, int height) {
        super(x, y, width, height);
        initializeHitBox(x,y,width,height);
    }



    public void cooldown() {
        long time = System.currentTimeMillis();
        if (time > lastAttack + coolDownTime) {
            // Do something
            lastAttack = time;
        }
    }

    public void shoot(){
        hitBox.x++;
        hitBox.y++;
    }

}

package com.mygdx.game.Projectiles;

public class fire {
    long lastAttack = 0;
    long coolDownTime = 2000; // 2000 milliseconds
    public void cooldown() {
        long time = System.currentTimeMillis();
        if (time > lastAttack + coolDownTime) {
            // Do something
            lastAttack = time;
        }
    }

}

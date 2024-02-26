package com.mygdx.game;

public class Enemies {
    public static class directions() {
        public static final int LEFT = 0;
        public static final int RIGHT = 0;
        public static final int UP = 0;
        public static final int DOWN = 0;
    }

    public void move(float speed, int direction){


    }

    private void explodeOnEnemies(Projectile p) {
        for (Enemy e : playing.getEnemyManger().getEnemies()) {
            if (e.isAlive()) {
                float radius = 40.0f;

                float xDist = Math.abs(p.getPos().x - e.getX());
                float yDist = Math.abs(p.getPos().y - e.getY());

                float realDist = (float) Math.hypot(xDist, yDist);

                if (realDist <= radius)
                    e.hurt(p.getDmg());
            }

        }

    }

}

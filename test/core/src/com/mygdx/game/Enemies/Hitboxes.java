package com.mygdx.game.Enemies;

import java.awt.geom.Rectangle2D;

public class Hitboxes {

    private float x ,y;
    private int width, height;
    public Rectangle2D.Float hitBox;
    public Hitboxes(float x, float y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected void initializeHitBox(float x, float y, float width, float height) {
        hitBox = new Rectangle2D.Float((int) x, (int) y, width, height);
    }

    public  float getX(){
        return x;
    }

    public  float getY(){
        return y;
    }

    public int getWidth(){return width;}
    public int getHeight(){return height;}
}
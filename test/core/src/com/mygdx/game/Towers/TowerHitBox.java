package com.mygdx.game.Towers;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Circle;
import com.mygdx.game.gdxGame;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class TowerHitBox {
    public Circle hitBox;
    private float radius;
    private float x;
    private float y;
    public TowerHitBox(float x, float y, float radius) {
        this.x = x;
        this.y =y;
        this.radius = radius;
    }
    public TowerHitBox(){}

    protected void initializeHitBox(float x, float y, float radius) {
        this.x = x;
        this.y =y;
        this.radius = radius;
        hitBox = new Circle(x,y,radius);

    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public float getRadius(){
        return radius;
    }

    public Circle getHitBox(){
        return hitBox;
    }
}

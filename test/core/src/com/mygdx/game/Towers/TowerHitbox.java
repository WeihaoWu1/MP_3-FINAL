package com.mygdx.game.Towers;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class TowerHitbox {
    public Ellipse2D.Double hitBox;
    private double radius;
    private double x;
    private double y;
    public TowerHitbox(double x, double y, double radius) {
        this.x = x;
        this.y =y;
        this.radius = radius;
    }

    protected void initializeHitBox(double x, double y, double radius) {
        hitBox = new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius);
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getRadius(){return radius;}

}

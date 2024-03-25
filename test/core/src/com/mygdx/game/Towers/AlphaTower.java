package com.mygdx.game.Towers;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.mygdx.game.Enemies.Minotaur;
import com.mygdx.game.Projectiles.AlphaStrike;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.gdxGame;


import java.util.ArrayList;


public class AlphaTower extends TowerHitBox {
    // Objects used
    private Texture alphaTower = new Texture("alphaTower.png");
    // A variable for tracking elapsed time for the animation
    private ArrayList<AlphaStrike> alphaStrikes  = new ArrayList<AlphaStrike>();




    public AlphaTower(float x, float y, float radius){
        super(x,y,radius);
        initializeHitBox(x,y,radius);
    }
    public void spawnProjectile() {
        for (Minotaur s : PlayScreen.minotaurs) {
            if (Intersector.overlaps(hitBox, s.getHitBox())) {
                AlphaStrike alphaStrike = new AlphaStrike((float) this.getX()-40 , (float) this.getY()-40 , 100, 100);
                alphaStrikes.add(alphaStrike);
                alphaStrike.create();
            }
        }


//        for (int r = 0; r < alphaStrikes.size(); r++) {
//            if (Intersector.overlaps(alphaStrikes.get(r).getHitBox(), alphaStrikes.get(r).getClosest().getHitBox())) {
//                alphaStrikes.get(r).getClosest().health -= 50;
//                if (alphaStrikes.get(r).getClosest().health <= 0) {
//                    alphaStrikes.get(r).getClosest().setX(-9999999f);
//                    alphaStrikes.get(r).getClosest().setY(-9999999f);
//                }
//            }
//        }
    }
    public void create() {

    }




    public void render() {
        gdxGame.batch.draw(alphaTower, hitBox.x-80, hitBox.y-80);


    }


    public boolean checkBox() {
        if(PlayScreen.mapNum==1) {
            if (hitBox.x <= 145 && hitBox.y > 289 && hitBox.y < 450) {
                System.out.println("1");
                return false;
            }
            if (hitBox.y < 495 && hitBox.y > 289 && hitBox.x >= 145 && hitBox.x < 243) {
                System.out.println("2");
                return false;
            }
            if (hitBox.x >= 243 && hitBox.x <= 361 && hitBox.y < 688 && hitBox.y > 345) {
                System.out.println("3"); // works
                return false;
            }
            if (hitBox.y > 524 && hitBox.y < 669 && hitBox.x > 243 && hitBox.x < 362) {
                System.out.println("4");
                return false;
            }
            if (hitBox.x < 402 && hitBox.x > 362 && hitBox.y > 562 && hitBox.y < 682) {
                System.out.println("5");
                return false;
            }
            if (hitBox.y < 730 && hitBox.y > 587 && hitBox.x >= 402 && hitBox.x < 610) {
                System.out.println("6"); // works
                return false;
            }
            if (hitBox.y < 760 && hitBox.y > 690 && hitBox.x >= 518 && hitBox.x < 649) {
                System.out.println("7");
                return false;
            }
            if (hitBox.x > 518 && hitBox.x < 680 && hitBox.y < 820 && hitBox.y > 690) {
                System.out.println("8");
                return false;
            }
            if (hitBox.y < 1000 && hitBox.x >= 552 && hitBox.x < 710) {
                System.out.println("9");
                return false;


            }
            if (hitBox.x < 1540 && hitBox.x > 638 && hitBox.y > 877 && hitBox.y < 992) {
                System.out.println("10");
                return false;
            }
            if (hitBox.x < 1667 && hitBox.x > 1541 && hitBox.y > 789 && hitBox.y < 992) {
                System.out.println("11");
                return false;
            }
            if (hitBox.x < 1687 && hitBox.x > 1541 && hitBox.y > 678 && hitBox.y < 880) {
                System.out.println("12");
                return false;
            }
            if (hitBox.x < 1662 && hitBox.x > 1214 && hitBox.y > 592 && hitBox.y < 740) {
                System.out.println("13");
                return false;
            }
            if (hitBox.x < 1430 && hitBox.x > 1234 && hitBox.y > 380 && hitBox.y < 592) {
                System.out.println("14");
                return false;
            }
            if (hitBox.x < 1675 && hitBox.x > 1325 && hitBox.y > 292 && hitBox.y < 432) {
                System.out.println("15");
                return false;
            }
            if (hitBox.x > 1550 && hitBox.x < 1675 && hitBox.y >= 0 && hitBox.y <= 300) {
                System.out.println("25");
                return false;
            }
        }


//        //SNOWY MAP
        if (PlayScreen.mapNum==2) {
            if (hitBox.y < 1000 && hitBox.y > 810 && hitBox.x > 590 && hitBox.x < 1763) {
                System.out.println("1");
                return false;
            }
            if (hitBox.y < 845 && hitBox.y > 550 && hitBox.x > 510 && hitBox.x < 715) {
                System.out.println("2");
                return false;
            }
            if (hitBox.y < 685 && hitBox.y > 510 && hitBox.x > 628 && hitBox.x < 915) {
                System.out.println("3");
                return false;
            }
            if (hitBox.y < 565 && hitBox.y > 430 && hitBox.x > 816 && hitBox.x < 985) {
                System.out.println("4");
                return false;
            }
            if (hitBox.y < 638 && hitBox.y > 564 && hitBox.x > 628 && hitBox.x < 938) {
                System.out.println("5");
                return false;
            }
            if (hitBox.y < 500 && hitBox.y > 244 && hitBox.x > 790 && hitBox.x < 1025) {
                System.out.println("5");
                return false;
            }
            if (hitBox.y < 375 && hitBox.y > 183 && hitBox.x > 971 && hitBox.x < 1900) {
                System.out.println("5");
                return false;
            }
            if (hitBox.y < 434 && hitBox.y > 266 && hitBox.x > 745 && hitBox.x < 892) {
                System.out.println("5");
                return false;
            }
            if (hitBox.y < 250 && hitBox.y > 178 && hitBox.x > 566 && hitBox.x < 825) {
                System.out.println("5");
                return false;
            }
            if (hitBox.y < 386 && hitBox.y > 151 && hitBox.x > 639 && hitBox.x < 758) {
                System.out.println("5");
                return false;
            }
        }
        return true;
    }
    public void setX(float x){
        hitBox.x = x;
    }
    public void setY(float y){
        hitBox.y = y;
    }


    public ArrayList<AlphaStrike> getAlphaStrikes(){
        return alphaStrikes;
    }


    public void dispose() {
        alphaTower.dispose();
    }
}


package com.project.alien.Missiles;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by cade on 4/6/17.
 */
public abstract class Missile {
    Texture missImg;
    //boolean exploded;
    int xLoc;
    int yLoc;

    int ogXLoc;
    int ogYLoc;

    int missWidth;

    public boolean visible = true;

    public Missile(){
        missImg = null;
        xLoc = 0;
        yLoc = 0;

        ogXLoc = xLoc;
        ogYLoc = yLoc;
    }


    public Texture getImg(){
        return this.missImg;
    }
    public int getXLoc(){ return this.xLoc; }
    public int getYLoc(){ return this.yLoc; }
    public void setXLoc(int newX){ this.xLoc = newX; }
    public void setYLoc(int newY){ this.yLoc = newY; }

    public int getOGX(){ return ogXLoc; }
    public int getOGY(){ return ogYLoc; }

    public void createCratr(){
        missImg = new Texture("img/Craters/towerDefense_tile021.png");
    }



    public abstract void explode();     //may need to be void
    public abstract void fly();            //abstract because big missile may fly slower than little



}

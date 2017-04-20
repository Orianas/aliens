package com.project.alien.Towers;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by cade on 4/6/17.
 */
public abstract class Tower {
    Texture tower;
    int xLoc;
    int yLoc;


    public Tower(){
        this.tower = null;
        this.xLoc = 0;
        this.yLoc = 0;
    }


    public Tower(String imgLoc, int xLoc, int yLoc){
        this.tower = new Texture(imgLoc);
        this.xLoc = xLoc;
        this.yLoc = yLoc;
    }

    public Texture getImg(){
        return this.tower;
    }

    public int getXLoc(){
        return this.xLoc;
    }

    public int getYLoc(){
        return this.yLoc;
    }

    public void setxLoc(int newXLoc){
        this.xLoc = newXLoc;
    }

    public void setyLoc(int newYLoc){
        this.yLoc = newYLoc;
    }

    public abstract void towerShoot();
    public abstract void towerDestroyed();








}

package com.project.alien.Towers;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by cade on 4/6/17.
 */
public abstract class Tower {
    private Texture tower;
    private int xLoc;
    private int yLoc;
    public Tower(){
        tower = null;
        xLoc = 0;
        yLoc = 0;
    }
    public Tower(String imgLoc, int xLoc, int yLoc){
        tower = new Texture(imgLoc);
        xLoc = xLoc;
        yLoc = yLoc;
    }
    public Texture getImg(){
        return tower;
    }
    public void setImg(Texture t){ tower = t; }
    public int getXLoc(){
        return xLoc;
    }
    public int getYLoc(){
        return yLoc;
    }
    public void setxLoc(int newXLoc){
        xLoc = newXLoc;
    }
    public void setyLoc(int newYLoc){
        yLoc = newYLoc;
    }
    public abstract void towerShoot();
    public abstract void towerDestroyed();
}

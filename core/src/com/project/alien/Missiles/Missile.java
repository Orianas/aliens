package com.project.alien.Missiles;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by cade on 4/6/17.
 */
public abstract class Missile {
    private Texture missImg;
    //boolean exploded;
    private int xLoc;
    private int yLoc;
    private int ogXLoc;
    private int ogYLoc;
    private int missWidth;
    private int tipOfMiss;
    private boolean visible = true;
    private boolean blewUp = false;
    public Missile(){
    }
    public Texture getImg(){
        return missImg;
    }
    public void setImg(Texture t){ missImg = t; }
    public void setMissWidth(int newWidth){
        missWidth = newWidth;
        setMissTipLoc();
    }
    public int getMissWidth(){ return missWidth; }
    public int getXLoc(){ return xLoc; }
    public int getYLoc(){ return yLoc; }
    public void setXLoc(int newX){
        xLoc = newX;
        setMissTipLoc();
    }
    public void setYLoc(int newY){ yLoc = newY; }
    public int getOGX(){ return ogXLoc; }
    public int getOGY(){ return ogYLoc; }
    public void setOGX(int originalX){ ogXLoc = originalX; }
    public void setOGY(int originalY){ ogYLoc = originalY; }
    public int getMissTipLoc(){
        return tipOfMiss;
    }
    public void setMissTipLoc(){
        tipOfMiss = xLoc + missWidth;
    }
    public boolean isVisible(){ return visible; }
    public void setVisible(){ visible = true; }
    public void resetVisible(){ visible = false; }
    public boolean isBlewUp(){ return blewUp; }
    public void setBlewUp(){ blewUp = true; }
    public void resetBlewUp(){ blewUp = false; }
    public void createCratr(){
        missImg = new Texture("img/Craters/towerDefense_tile021.png");
    }
    public abstract void explode();     //may need to be void
    public abstract void fly();         //abstract because big missile may fly slower than little
}

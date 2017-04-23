package com.project.alien.Missiles;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by cade on 4/6/17.
 */
public class BigMissile extends Missile {
    public BigMissile(String imgLoc, int xLoc, int yLoc){
        setImg(new Texture(imgLoc));
        setMissWidth(60);
        setXLoc(xLoc);
        setYLoc(yLoc);
        setOGX(xLoc);
        setOGY(yLoc);
    }
    public void reload(){
        setVisible();
        setXLoc(getOGX());
        setYLoc(getOGY());
        setMissTipLoc();
    }
    @Override
    public void fly() {
        setXLoc(getXLoc() + 7);
        setMissTipLoc();
    }
    @Override
    public void explode() {
        setImg(new Texture("img/Explosions/towerDefense_tile298.png"));
    }
}

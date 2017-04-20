package com.project.alien.Missiles;

import com.badlogic.gdx.graphics.Texture;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * Created by cade on 4/6/17.
 */
public class BigMissile extends Missile {

    int tipOfMiss;


    public BigMissile(String imgLoc, int xLoc, int yLoc){
        this.missImg = new Texture(imgLoc);
        this.xLoc = xLoc;
        this.yLoc = yLoc;

        this.ogXLoc = xLoc;
        this.ogYLoc = yLoc;
        this.missWidth = 60;
    }

    public void reload(){

        //this.missImg = new Texture("img/Missile/towerDefense_tile252.png");
        visible = true;
        this.xLoc = ogXLoc;
        this.yLoc = ogYLoc;
    }

    public int getMissTipLoc(){
        return tipOfMiss;
    }

    @Override
    public void fly() {
        this.setXLoc(this.getXLoc() + 3);
        tipOfMiss = missWidth + xLoc;

    }

    @Override
    public void explode() {
        this.missImg = new Texture("img/Explosions/towerDefense_tile298.png");

//        this.missImg = new Texture("Tower Defense (top-down)/PNG/Zombies!/towerDefense_tile021.png");

    }
}

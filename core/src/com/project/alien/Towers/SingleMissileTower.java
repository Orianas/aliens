package com.project.alien.Towers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by cade on 4/5/17.
 */
public class SingleMissileTower extends Tower{

    public SingleMissileTower(String imgLoc, int xLoc, int yLoc){
        this.tower = new Texture (imgLoc);
        this.xLoc = xLoc;
        this.yLoc = yLoc;

    }


    @Override
    public void towerDestroyed() {

    }

    @Override
    public void towerShoot() {

    }
}

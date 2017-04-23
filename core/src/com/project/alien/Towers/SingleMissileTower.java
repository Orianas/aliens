package com.project.alien.Towers;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by cade on 4/5/17.
 */
public class SingleMissileTower extends Tower{
    public SingleMissileTower(String imgLoc, int xLoc, int yLoc){
        setImg(new Texture (imgLoc));
        setxLoc(xLoc);
        setyLoc(yLoc);
    }
    @Override
    public void towerDestroyed() {}
    @Override
    public void towerShoot() {}
}

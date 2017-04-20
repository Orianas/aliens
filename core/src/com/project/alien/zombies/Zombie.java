package com.project.alien.zombies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

abstract class Zombie {
    private TextureAtlas textureAtlas;
    private Animation<TextureRegion> animation;
    private int x = 0;
    private int y = 0;
    private float duration;
    public Zombie(){}
    public void setAtlasAnimation(TextureAtlas atlas){
        textureAtlas = atlas;
        animation = new Animation<TextureRegion>(duration, textureAtlas.getRegions());
    }
    public Animation<TextureRegion> getAnimation(){
        return animation;
    }
    public void setDuration(float d){
        duration = d;
    }
    public float getDuration(){
        return duration;
    }
    public void dispose(){
        textureAtlas.dispose();
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int x_init){
        x = x_init;
    }
    public void setY(int y_init){
        y = y_init;
    }
    public int addX(int addition){
        x += addition;
        return x;
    }
    public int addY(int addition){
        y += addition;
        return y;
    }
    abstract public void draw(SpriteBatch batch, float elapsedTime);
    abstract public void appear();
    abstract public void stop();
    abstract public void rightAttack();
    abstract public void leftAttack();
    abstract public void moveRight();
    abstract public void moveLeft();
    abstract public void moveUp();
    abstract public void moveDown();
    abstract public void die();
}

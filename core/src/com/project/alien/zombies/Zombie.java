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
    private float deadLastElapsedTime = 0f;
    private float appearLastElapsedTime = 0f;
    private float deadDuration = 0f;                    //how long "dead" sprites has been drawn
    private float appearDuration = 0f;                  //how long "appear" sprites has been drawn
    private boolean dead = false;
    private boolean appearing = false;
    private boolean rightAttack = false;
    private boolean leftAttack = false;
    private boolean movingRight = false;
    private boolean movingLeft = false;
    private boolean movingUp = false;
    private boolean movingDown = false;
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
    public void updateDeadTime(float elapsedTime){
        if(deadLastElapsedTime > 0f){
            deadDuration += elapsedTime - deadLastElapsedTime;
        }
        deadLastElapsedTime = elapsedTime;
    }
    public void updateAppearTime(float elapsedTime){
        if(appearLastElapsedTime > 0f){
            appearDuration += elapsedTime - appearLastElapsedTime;
        }
        appearLastElapsedTime = elapsedTime;
    }
    public float getDeadDuration(){ return deadDuration; }
    public float getAppearDuration(){ return appearDuration; }
    public boolean isDead(){ return dead; }
    public void setDead(){ dead = true; }
    public void resetDead(){
        dead = false;
        deadLastElapsedTime = 0f;
    }
    public boolean isAppearing(){ return appearing; }
    public void setAppearing(){ appearing = true; }
    public void resetAppearing(){
        appearing = false;
        appearLastElapsedTime = 0f;
    }
    public boolean isRightAttack(){ return rightAttack; }
    public void setRightAttack(){ rightAttack = true; }
    public void resetRightAttack(){ rightAttack = false; }
    public boolean isLeftAttack(){ return leftAttack; }
    public void setLeftAttack(){ leftAttack = true; }
    public void resetLeftAttack(){ leftAttack = false; }
    public boolean isMovingRight(){ return movingRight; }
    public void setMovingRight(){ movingRight = true; }
    public void resetMovingRight(){ movingRight = false; }
    public boolean isMovingLeft(){ return movingLeft; }
    public void setMovingLeft(){ movingLeft = true; }
    public void resetMovingLeft(){ movingLeft = false; }
    public boolean isMovingUp(){ return movingUp; }
    public void setMovingUp(){ movingUp = true; }
    public void resetMovingUp(){ movingUp = false; }
    public boolean isMovingDown(){ return movingDown; }
    public void setMovingDown(){ movingDown = true; }
    public void resetMovingDown(){ movingDown = false; }
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

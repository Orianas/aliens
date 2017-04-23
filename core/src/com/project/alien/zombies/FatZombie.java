package com.project.alien.zombies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class FatZombie extends Zombie {
    private final int DEAD_FRAME_NUM = 7;
    private final int RIGHT_ATTACK_FRAME_NUM = 7;
    private final int LEFT_ATTACK_FRAME_NUM = 7;
    private final int APPEAR_FRAME_NUM = 12;
    private int currentDeadFrame = 0;
    private int currentRightAttackFrame = 0;
    private int currentLeftAttackFrame = 0;
    private int currentAppearFrame = 0;
    private int leftAttackRepeat = 0;
    private float lastElapsedTime = 0f;
    private Sprite[] deadSprites;
    private Sprite[] rightAttackSprites;
    private Sprite[] leftAttackSprites;
    private Sprite[] appearSprites;
    public FatZombie(){
        //Default time duration of fat zombie is 1/7f
        //Default zombie movement is to appear
        setSprites("die", DEAD_FRAME_NUM,"fatzombie-data/fatzom-texture-die.atlas");
        setSprites("right-attack", RIGHT_ATTACK_FRAME_NUM,"fatzombie-data/fatzom-texture-attack-right.atlas");
        setSprites("left-attack", LEFT_ATTACK_FRAME_NUM,"fatzombie-data/fatzom-texture-attack-left.atlas");
        setSprites("appear", APPEAR_FRAME_NUM,"fatzombie-data/fatzom-texture-appear.atlas");
        setDuration(1/7f);
        appear();
    }
    public FatZombie(float duration){
        setSprites("die", DEAD_FRAME_NUM,"fatzombie-data/fatzom-texture-die.atlas");
        setSprites("right-attack", RIGHT_ATTACK_FRAME_NUM,"fatzombie-data/fatzom-texture-attack-right.atlas");
        setSprites("left-attack", LEFT_ATTACK_FRAME_NUM,"fatzombie-data/fatzom-texture-attack-left.atlas");
        setSprites("appear", APPEAR_FRAME_NUM,"fatzombie-data/fatzom-texture-appear.atlas");
        setDuration(duration);
        appear();
    }
    private void setSprites(String action, int maxFrameNum, String filePath){
        TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal(filePath));
        Sprite[] copySprite = null;
        if(action.equals("die")) {
            deadSprites = new Sprite[maxFrameNum];
            copySprite = deadSprites;
        }else if(action.equals("right-attack")) {
            rightAttackSprites = new Sprite[maxFrameNum];
            copySprite = rightAttackSprites;
        }else if(action.equals("left-attack")) {
            leftAttackSprites = new Sprite[maxFrameNum];
            copySprite = leftAttackSprites;
        }else if(action.equals("appear")){
            appearSprites = new Sprite[maxFrameNum];
            copySprite = appearSprites;
        }
        for(int i = 0; i < maxFrameNum; i++) {
            if(i + 1 < 10)
                copySprite[i] = new Sprite(textureAtlas.findRegion("00" + Integer.toString(i + 1)));
            else if(10 <= i + 1 && i + 1 < 100)
                copySprite[i] = new Sprite(textureAtlas.findRegion("0" + Integer.toString(i + 1)));
            else
                copySprite[i] = new Sprite(textureAtlas.findRegion(Integer.toString(i + 1)));
        }
    }
    public void draw(SpriteBatch batch, float elapsedTime){
        if(!isDead() && !isRightAttack() && !isLeftAttack() && !isAppearing())
            batch.draw(getAnimation().getKeyFrame(elapsedTime, true), getX(), getY());
        //draw the zombie die
        else if(isDead()){
            updateDeadTime(elapsedTime);
            if(lastElapsedTime == 0f) {
                lastElapsedTime = elapsedTime;
            }
            else if(elapsedTime - lastElapsedTime > getDuration()){
                if(currentDeadFrame != DEAD_FRAME_NUM - 1)
                    currentDeadFrame++;
                lastElapsedTime = elapsedTime;
            }
            deadSprites[currentDeadFrame].setPosition(getX(), getY());
            deadSprites[currentDeadFrame].draw(batch);
        }
        //draw the zombie attack right
        else if(isRightAttack()){
            if(lastElapsedTime == 0f) {
                lastElapsedTime = elapsedTime;
            }
            else if(elapsedTime - lastElapsedTime > getDuration()){
                if(currentRightAttackFrame != RIGHT_ATTACK_FRAME_NUM - 1)
                    currentRightAttackFrame++;
                lastElapsedTime = elapsedTime;
            }
            rightAttackSprites[currentRightAttackFrame].setPosition(getX(), getY());
            rightAttackSprites[currentRightAttackFrame].draw(batch);
        }
        else if(isLeftAttack()){
            if(lastElapsedTime == 0f) {
                lastElapsedTime = elapsedTime;
            }
            else if(elapsedTime - lastElapsedTime > getDuration()){
                if(currentLeftAttackFrame != LEFT_ATTACK_FRAME_NUM - 1)
                    currentLeftAttackFrame++;
                else if(leftAttackRepeat > 110) {
                    currentLeftAttackFrame = 0;
                    leftAttackRepeat = 0;
                }
                lastElapsedTime = elapsedTime;
            }
            leftAttackSprites[currentLeftAttackFrame].setPosition(getX(), getY());
            leftAttackSprites[currentLeftAttackFrame].draw(batch);
        }
        //draw the zombie appear
        else if(isAppearing()){
            updateAppearTime(elapsedTime);
            if(lastElapsedTime == 0f) {
                lastElapsedTime = elapsedTime;
            }
            else if(elapsedTime - lastElapsedTime > getDuration()){
                if(currentAppearFrame != APPEAR_FRAME_NUM - 1)
                    currentAppearFrame++;
                lastElapsedTime = elapsedTime;
            }
            appearSprites[currentAppearFrame].setPosition(getX(), getY());
            appearSprites[currentAppearFrame].draw(batch);
        }
    }
    public void appear(){
        if(isDead() || isRightAttack() || isLeftAttack())
            resetActions();
        setAppearing();
        resetMovingLeft();
        resetMovingRight();
        resetMovingUp();
        resetMovingDown();
    }
    public void stop(){}
    public void moveRight() {
        if (!isMovingRight()) {
            setAtlasAnimation(new TextureAtlas(Gdx.files.internal("fatzombie-data/fatzom-texture-walk-right.atlas")));
            setMovingRight();
            resetActions();
            resetMovingLeft();
            resetMovingUp();
            resetMovingDown();
        }
    }
    public void moveLeft(){
        if (!isMovingLeft()) {
            setAtlasAnimation(new TextureAtlas(Gdx.files.internal("fatzombie-data/fatzom-texture-walk-left.atlas")));
            setMovingLeft();
            resetActions();
            resetMovingRight();
            resetMovingUp();
            resetMovingDown();
        }
    }
    public void moveUp(){
        if(!isMovingUp()){
            setAtlasAnimation(new TextureAtlas(Gdx.files.internal("fatzombie-data/fatzom-texture-walk-up.atlas")));
            setMovingUp();
            resetActions();
            resetMovingRight();
            resetMovingLeft();
            resetMovingDown();
        }
    }
    public void moveDown() {
        if (!isMovingDown()) {
            setAtlasAnimation(new TextureAtlas(Gdx.files.internal("fatzombie-data/fatzom-texture-walk-down.atlas")));
            setMovingDown();
            resetActions();
            resetMovingRight();
            resetMovingLeft();
            resetMovingUp();
        }
    }
    public void die(){
        if(isRightAttack() || isAppearing() || isLeftAttack())
            resetActions();
        setDead();
        resetMovingRight();
        resetMovingLeft();
        resetMovingUp();
        resetMovingDown();
    }
    public void rightAttack(){
        if(isDead() || isAppearing() || isLeftAttack())
            resetActions();
        setRightAttack();
        resetMovingRight();
        resetMovingLeft();
        resetMovingUp();
        resetMovingDown();
    }
    public void leftAttack(){
        if(isDead() || isAppearing() || isRightAttack())
            resetActions();
        setLeftAttack();
        resetMovingRight();
        resetMovingLeft();
        resetMovingUp();
        resetMovingDown();
        leftAttackRepeat++;
    }
    private void resetActions(){
        resetDead();
        resetRightAttack();
        resetLeftAttack();
        resetAppearing();
        currentDeadFrame = 0;
        currentRightAttackFrame = 0;
        currentLeftAttackFrame = 0;
        currentAppearFrame = 0;
        lastElapsedTime = 0f;
    }
}

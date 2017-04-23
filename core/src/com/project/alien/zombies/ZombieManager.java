package com.project.alien.zombies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.project.alien.Missiles.Missile;

import java.util.*;
/**
 * Created by ohkih on 4/20/2017.
 */
public class ZombieManager {
    private int level;                         //the level of the overall game; optional at this moment
    private int trailNumber;
    private final int TRAIL_MAX = 50;          //the maximum number of zombie per trail
    private final int TRAIL_TILE_WIDTH = 60;
    private final int TRAIL_TILE_HEIGHT = 60;
    private final int TRAIL_GAP = 10;          //the little gap between trails
    private final int BOTTOM_GAP = 50;         //the gap between the bottom trail and the bottom of the window
    private final float APPEARING_TIME = 2.0f; //the duration of time for a zombie to appear
    private final float DYING_TIME = 1.5f;     //the duration of time for a zombie to die
    private Zombie[][] zombies;
    public ZombieManager(int trailN){
        trailNumber = trailN;
        zombies = new Zombie[trailNumber][TRAIL_MAX];
        for(int i = 0; i < trailNumber; i++)
            for(int j = 0; j < TRAIL_MAX; j++)
                zombies[i][j] = null;
        for(int i = 0; i < 10; i++)
            generateZombie();
    }
    public void generateZombie(){
        Random temp = new Random();
        int trailInsert = temp.nextInt(trailNumber);
        int offsetX = TRAIL_TILE_WIDTH * (2 - temp.nextInt(5));
        int zombie_num = getZombieNumPerRow(zombies[trailInsert]);
        //zombie being instantiated can be of any kinds
        if(0 < zombie_num) {
            if(zombie_num < TRAIL_MAX - 1) {
                zombies[trailInsert][zombie_num] = new FatZombie();
                zombie_num++;
                if (TRAIL_TILE_WIDTH * 11 + offsetX == zombies[trailInsert][zombie_num - 2].getX())
                    offsetX += TRAIL_TILE_WIDTH;
            }
        } else {
            zombies[trailInsert][0] = new FatZombie();
            zombie_num++;
        }
        zombies[trailInsert][zombie_num - 1].setX(TRAIL_TILE_WIDTH * 11 + offsetX);
        zombies[trailInsert][zombie_num - 1].setY(BOTTOM_GAP + (TRAIL_TILE_HEIGHT + TRAIL_GAP) * trailInsert);
    }
    public void drawZombies(SpriteBatch batch, float elapsedTime){
        for(int i = 0; i < trailNumber; i++) {
            int zombie_num = getZombieNumPerRow(zombies[i]);
            for (int j = 0; j < zombie_num; j++)
                zombies[i][j].draw(batch, elapsedTime);
        }
    }
    public void zombieMarch(){
        for(int i = 0; i < trailNumber; i++){
            int zombie_num = getZombieNumPerRow(zombies[i]);
            for(int j = 0; j < zombie_num; j++) {
                if(zombies[i][j].isAppearing()){
                    if(zombies[i][j].getAppearDuration() > APPEARING_TIME){
                        zombies[i][j].resetAppearing();
                        zombies[i][j].moveLeft();
                    }
                }
                else if(zombies[i][j].isDead()){
                    if(zombies[i][j].getDeadDuration() > DYING_TIME){
                        zombies[i][j] = null;
                        for(int z = j; z < zombie_num - 1; z++){
                            zombies[i][z] = zombies[i][z + 1];
                        }
                        zombies[i][zombie_num - 1] = null;
                        zombie_num--;
                    }
                }
                else if(zombies[i][j].isMovingLeft()) {
                    if(zombies[i][j].getX() <= TRAIL_TILE_WIDTH - 20)
                        zombies[i][j].leftAttack();
                    else
                        zombies[i][j].setX(zombies[i][j].getX() - 1);
                }
                else if(zombies[i][j].isLeftAttack())
                    zombies[i][j].leftAttack();
            }
        }
    }
    public void collision(Missile missile){
        for(int i = 0; i < trailNumber; i++) {
            int zombie_num = getZombieNumPerRow(zombies[i]);
            for (int j = 0; j < zombie_num; j++){
                if(zombies[i][j].getX() + TRAIL_TILE_WIDTH / 2 <= missile.getMissTipLoc()
                && zombies[i][j].getX() + TRAIL_TILE_WIDTH / 2 >= missile.getXLoc()
                && zombies[i][j].getY() + TRAIL_TILE_HEIGHT / 2 >= missile.getYLoc()
                && zombies[i][j].getY() + TRAIL_TILE_HEIGHT / 2 <= missile.getYLoc() + TRAIL_TILE_HEIGHT
                && !zombies[i][j].isDead()) {
                    zombies[i][j].die();
                    missile.setBlewUp();
                    return;
                }
            }
        }
    }
    private int getZombieNumPerRow(Zombie[] row){
        int zombie_num = 0;
        for(int i = 0; i < TRAIL_MAX; i++)
            if(row[i] != null)
                zombie_num++;
            else
                break;
        return zombie_num;
    }
}

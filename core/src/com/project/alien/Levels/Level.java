package com.project.alien.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.project.alien.Missiles.BigMissile;
import com.project.alien.Towers.SingleMissileTower;
import com.project.alien.zombies.ZombieManager;

public class Level {
    private Texture[] towerPlots;
    private Texture[][] trailPlots;
    private float elapsedTime = 0f;
    private float lastElapsedTime = 0f;
//	private Tower[] currentTowers = new Tower[5];
    private SpriteBatch batch;
    private SingleMissileTower tower1;
    private SingleMissileTower tower2;
    private BigMissile missile1;
    private ZombieManager enemy;
    private boolean key1Pressed = false;
    public void create () {
        batch = new SpriteBatch();
        tower1 = new SingleMissileTower("img/Tower/towerDefense_tile205.png", 0, 50);
        tower2 = new SingleMissileTower("img/Tower/towerDefense_tile229.png", 0, 120);
        missile1 = new BigMissile("img/Missile/towerDefense_tile252.png", 0, 120);
        towerPlots = new Texture[5];
        for (int i = 0; i < 5; i++)
            towerPlots[i] = new Texture("img/Tiles/towerDefense_tile086.png");
        trailPlots = new Texture[20][20];
        for(int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++)
                trailPlots[i][j] = new Texture("img/Tiles/towerDefense_tile093.png");
        }
        enemy = new ZombieManager(5);
    }
    public void render () {
        Gdx.gl.glClearColor(0, 1, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        elapsedTime += Gdx.graphics.getDeltaTime();
        if(elapsedTime - lastElapsedTime > 3.0f){                          //generate one zombie every 3 seconds
            lastElapsedTime = elapsedTime;
            enemy.generateZombie();
        }
        drawTowers();
        drawTrails();
        keyControlTemp();
        enemy.collision(missile1);
        enemy.zombieMarch();
        if(missile1.isVisible())
            batch.draw(missile1.getImg(), missile1.getXLoc(), missile1.getYLoc());
        enemy.drawZombies(batch, elapsedTime);
        batch.end();
    }
    private void drawTowers(){
        int startY = -20;
        int startX = 0;
        for (int i = 0; i < 5; i++) {
            startY += 70;
            batch.draw(towerPlots[i], startX, startY);
        }
        /* Temporally disabled for the sake of the final presentation
        batch.draw(tower1.getImg(), tower1.getXLoc(), tower1.getYLoc());   //Drawing double-missiles
        batch.draw(tower2.getImg(), tower2.getXLoc(), tower2.getYLoc());   //Drawing the tower under the double-missiles
        */
    }
    private void drawTrails(){
        int startX;
        int startY = -20;
        for (int i = 0; i < 5; i++) {
            startX = 0;
            startY += 70;
            for (int j = 0; j < 10; j++) {
                startX += 60;
                batch.draw(trailPlots[i][j], startX, startY);
            }
        }
    }
    //temporal key event controller. It is temporal. Needs to be encapsulated
    private void keyControlTemp(){
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_0)) {
            if (!missile1.isVisible()) {
                missile1.reload();
                missile1.setVisible();
                key1Pressed = false;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_1))
            key1Pressed = true;
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP) && !key1Pressed && missile1.getYLoc() <= 300){
            missile1.setYLoc(missile1.getYLoc() + 70);
            missile1.setOGY(missile1.getYLoc());
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && !key1Pressed && 80 < missile1.getYLoc()){
            missile1.setYLoc(missile1.getYLoc() - 70);
            missile1.setOGY(missile1.getYLoc());
        }
        if(key1Pressed) {
            if(!missile1.isBlewUp()) {
                missile1.fly();
                if(60 * 11 < missile1.getXLoc()) {
                    //missile1.resetVisible();
                    missile1.reload();
                    key1Pressed = false;
                }
            } else {
                missile1.resetBlewUp();
                missile1.reload();
                key1Pressed = false;
            }
        }
    }
    public void dispose(){
        batch.dispose();
    }
}


package com.project.alien.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.project.alien.Missiles.BigMissile;
import com.project.alien.Towers.SingleMissileTower;

/**
 * Created by cade on 4/20/17.
 */
public class Level {

    Texture[] towerPlots;
    Texture[][] trailPlots;
    //Zombie zombie1;
    float elapsedTime = 0;

//	Tower[] currentTowers = new Tower[5];
//	Texture[] grassPlots;



    SpriteBatch batch;
    Texture img;
    //Texture tower1;
    BitmapFont font;
    public SingleMissileTower tower1;

    SingleMissileTower tower2;
    BigMissile missile1;



    public void create () {     //buildstage
        batch = new SpriteBatch();
        tower1 = new SingleMissileTower("img/Tower/towerDefense_tile205.png", 0, 50);

        tower2 = new SingleMissileTower("img/Tower/towerDefense_tile229.png", 0, 120);
        missile1 = new BigMissile("img/Missile/towerDefense_tile252.png", 0, 120);

        font = new BitmapFont();


        towerPlots = new Texture[5];
        for (int i = 0; i < 5; i++) {
            towerPlots[i] = new Texture("img/Tower/towerDefense_tile086.png");

        }
        trailPlots = new Texture[20][20];
        for(int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                trailPlots[i][j] = new Texture("img/Tower/towerDefense_tile093.png");
            }
        }
//        zombie1 = new FatZombie();
//        zombie1.setX(300);
//        zombie1.setY(135);
//        zombie1.moveLeft();




//		grassPlots = new Texture[11];
//		for(int i = 0; i < 11; i++){
//			grassPlots[i] = new Texture("Tower Defense (top-down)/PNG/Default size/towerDefense_tile157.png");
//		}



    }

    boolean key1Pressed = false;

    public void render () {
        Gdx.gl.glClearColor(0, 1, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        //batch.draw(img, 150, 50);
        elapsedTime += Gdx.graphics.getDeltaTime();
        int startY = 50;
        int startX = 0;
        //batch.draw(towerPlots[1], 0, startY);
        for (int i = 0; i < 5; i++) {                                //Show where the towers can go
            if(i != 0)
                startY += 70;               //was 70
            batch.draw(towerPlots[i], 0, startY);

        }


        startY = 50;
        for (int i = 0; i < 5; i++) {
            startX = 0;
            if(i != 0)
                startY += 70;                   //was 70
            for (int j = 0; j < 10; j++) {
                startX += 60;

                batch.draw(trailPlots[i][j], startX, startY);
                //startX = 0;
            }
        }

//		startX = 0;
//		for(int i = 0; i < 11; i++){
//			batch.draw(grassPlots[i], startX, 0);
//			startX += 60;
//		}


//		batch.draw(tower1.getImg(), tower1.getXLoc(), tower1.getYLoc());
        batch.draw(tower1.getImg(), tower1.getXLoc(), tower1.getYLoc());                        //TEST TOWER
//		int beg = zombie1.getX();
//		for(int i = zombie1.getX(); i > 1; i--){
//			zombie1.setX(i);
//		}

        batch.draw(tower2.getImg(), tower2.getXLoc(), tower2.getYLoc());




//        if(zombie1.getX() < 30)
//            //zombie1.setX(700);
//            zombie1.die();
//        else
//            zombie1.setX(zombie1.getX() - 1);
//        zombie1.draw(batch, elapsedTime);
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
            key1Pressed = true;
        }

        if (key1Pressed) {
            if (missile1.getXLoc() < 600) {
                //missile1.explode();
                missile1.fly();
//                if(zombie1.getX() > missile1.getXLoc() && zombie1.getX() < missile1.getMissTipLoc()){
//                    zombie1.die();
//                }

            } else {
                //missile1.createCratr();
//				missile1.reload();
                missile1.visible = false;
                key1Pressed = false;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_0)){
            if(!key1Pressed)
                missile1.reload();
        }


        if(missile1.visible) {
            batch.draw(missile1.getImg(), missile1.getXLoc(), missile1.getYLoc());
        }
        //missile1.reload();




//		if(zombie1.getX() < 30)
//			//zombie1.setX(700);
//			zombie1.die();
//		else
//			zombie1.setX(zombie1.getX() - 1);
//		zombie1.draw(batch, elapsedTime);

        batch.end();
    }

    public void dispose () {
        batch.dispose();
        //img.dispose();
    }
}


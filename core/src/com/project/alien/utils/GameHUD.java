package com.project.alien.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.project.alien.Towers.SingleMissileTower;
import com.project.alien.Towers.Tower;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.Color.WHITE;

public class GameHUD {

    private Stage HUD;

    private Texture txtrHUDBG;
    private float HUDTIMER;
    private float SCORETIMER;
    private float RESOURCETIMER;
    private int SCORE;
    private int RESOURCES;
    private Label currScore;
    private Label currResources;
    private Label[] towerCost;
    private Label labelResources;
    private Label labelScore;
    private Label labelTowers;
    private BitmapFont HUDFont;
    private Texture txtrCost;
    private Tower[] towers;

    public GameHUD() {
        txtrHUDBG = new Texture("img/HudBG.png");
        txtrCost = new Texture("img/costBubble.png");
        HUD = new Stage(new FitViewport(1440.0f, 1080.0f, new OrthographicCamera()));

        towers = new Tower[7];
        HUDFont = new BitmapFont(Gdx.files.internal("font/futureThin50.fnt"), false);
        Label.LabelStyle textStyle = new Label.LabelStyle(HUDFont, WHITE);
        Label.LabelStyle labelStyle = new Label.LabelStyle(HUDFont, BLACK);

        currResources = new Label("0", textStyle);
        currResources.setBounds(410.0f, 16.0f, 50.0f, 50.0f);
        currResources.setFontScale(1.5f, 1.5f);
        currScore = new Label("0", textStyle);
        currScore.setBounds(1043.0f, 16.0f, 50.0f, 50.0f);
        currScore.setFontScale(1.5f, 1.5f);

        labelResources = new Label("Resources", labelStyle);
        labelResources.setBounds(75.0f, 16.0f, 100.0f, 50.0f);
        labelResources.setFontScale(1.0f, 1.0f);
        labelScore = new Label("Score", labelStyle);
        labelScore.setBounds(850.0f, 16.0f, 100.0f, 50.0f);
        labelScore.setFontScale(1.0f, 1.0f);
        labelTowers = new Label("Towers", labelStyle);
        labelTowers.setBounds(100.0f, 985.0f, 100.0f, 50.0f);
        labelTowers.setFontScale(1.0f, 1.0f);

        towerCost = new Label[7];
        for (int i = 0; i < 7; i++) {
            float distanceEach = 140.0f;
            float initialDist = 405.0f;
            towerCost[i] = new Label("0", textStyle);
            towerCost[i].setBounds(initialDist + (distanceEach * i), 940.0f, 50.0f, 50.0f);
            towerCost[i].setFontScale(0.7f, 0.7f);
        }
    }

    public void buildStage() {
        updateTowers();
        Image HudBG = new Image(txtrHUDBG);
        Image CostBubble = new Image(txtrCost);
        Image towerImages[] = new Image[7];

        for (int i = 0; i < 7; i++) {
            float distanceEach = 144.0f;
            float initialDist = 335.0f;
            if (towers[i] != null)
                towerImages[i] = new Image(towers[i].getImg());

            towerImages[i].setBounds(distanceEach * i + initialDist, 935.0f, 150.0f, 150.0f);
        }

        HUD.addActor(HudBG);
        for (int i = 0; i < 7; i++)
            HUD.addActor(towerImages[i]);
        HUD.addActor(currResources);
        HUD.addActor(currScore);
        HUD.addActor(labelResources);
        HUD.addActor(labelScore);
        HUD.addActor(labelTowers);
        // TODO: Add Tower Images & Logic
        HUD.addActor(CostBubble);
        for (int i = 0; i < 7; i++) {
            HUD.addActor(towerCost[i]);
        }

        HUDTIMER = 0;
        RESOURCETIMER = 0;
        SCORETIMER = 0;
        SCORE = 0;
        RESOURCES = 0;
    }

    public void updateHUD(float delta) {
        HUDTIMER += delta;
        RESOURCETIMER += delta;
        SCORETIMER += delta;
        if (HUDTIMER > 0.25f) {
            HUDTIMER -= 0.25f;

            if (RESOURCETIMER > Consts.RESOURCE_GAIN_TIME) {
                RESOURCETIMER -= Consts.RESOURCE_GAIN_TIME;
                RESOURCES += Consts.RESOURCE_GAIN_AMT;
            }

            if (SCORETIMER > Consts.SCORE_GAIN_TIME) {
                SCORETIMER -= Consts.SCORE_GAIN_TIME;
                SCORE += Consts.SCORE_GAIN_AMT;

                // Stage Multiplier function
                // SCORE += Consts.RESOURCE_GAIN_AMT * STAGEMULTIPLIER;
            }
            currScore.setText(Integer.toString(SCORE));
            currResources.setText(Integer.toString(RESOURCES));
        }
    }

    public void dispose() {
        txtrHUDBG.dispose();
        HUDFont.dispose();
        txtrCost.dispose();
    }

    public void act(float delta) {
        HUD.act(delta);
        for (int i = 0; i < 7; i++) {
            if (towers[i] != null)
                towerCost[i].setText(Integer.toString(towers[i].getCost()));
        }
    }

    public void draw() {
        for (int i = 0; i < 7; i++) {
            if (towers[i] == null) {
                towerCost[i].setVisible(false);
            }
        }
        HUD.draw();
    }

    public void addScore(int score) {
        SCORE += score;
    }

    public void addResources(int resources) {
        RESOURCES += resources;
    }

    public void updateTowers() {
        towers[0] = (new SingleMissileTower("img/Tower/towerDefense_tile206.png", 20));
        towers[1] = (new SingleMissileTower("img/Tower/towerDefense_tile205.png", 80));
        towers[2] = (new SingleMissileTower("img/Tower/towerDefense_tile204.png", 100));
        towers[3] = (new SingleMissileTower("img/Tower/towerDefense_tile249.png", 20));
        towers[4] = (new SingleMissileTower("img/Tower/towerDefense_tile250.png", 80));
        towers[5] = (new SingleMissileTower("img/Tower/towerDefense_tile204.png", 100));
        towers[6] = (new SingleMissileTower("img/Tower/towerDefense_tile206.png", 20));
    }

}

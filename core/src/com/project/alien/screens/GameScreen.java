package com.project.alien.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.project.alien.Levels.TiledLevel;
import com.project.alien.utils.Consts;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.Color.WHITE;

public class GameScreen extends AbstractScreen {

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


    private TiledLevel level1;

    public GameScreen() {
        super();
        HUDConstructor();
        level1 = new TiledLevel("maps/level_1.tmx");
    }

    @Override
    public void show() {
        /* Add all stages that process input here*/

        InputMultiplexer inputMultiplexer = new InputMultiplexer();

        // HUD input
        inputMultiplexer.addProcessor(this);
        // Game map/tiles input
        inputMultiplexer.addProcessor(level1.getStage());

        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void buildStage() {
        HUDBuildStage();
    }

    @Override
    public void render(float delta) {
        //Gdx.gl.glClearColor(0, 1, 0, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        level1.render();

        updateHUD(delta);

        super.act(delta);
        super.draw();
        HUD.act();
        HUD.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        txtrHUDBG.dispose();
        HUDFont.dispose();
        txtrCost.dispose();
    }

    private void updateHUD(float delta) {
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

    private void HUDConstructor() {
        txtrHUDBG = new Texture("img/HudBG.png");
        txtrCost = new Texture("img/costBubble.png");
        HUD = new Stage(new FitViewport(1440.0f, 1080.0f, new OrthographicCamera()));

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

    private void HUDBuildStage() {
        Image HudBG = new Image(txtrHUDBG);
        Image CostBubble = new Image(txtrCost);
        HUD.addActor(HudBG);
        HUD.addActor(currResources);
        HUD.addActor(currScore);
        HUD.addActor(labelResources);
        HUD.addActor(labelScore);
        HUD.addActor(labelTowers);
        // TODO: Add Tower Images & Logic
        HUD.addActor(CostBubble);
        for (int i = 0; i < 7; i++)
            HUD.addActor(towerCost[i]);

        HUDTIMER = 0;
        RESOURCETIMER = 0;
        SCORETIMER = 0;
        SCORE = 0;
        RESOURCES = 0;
    }
}

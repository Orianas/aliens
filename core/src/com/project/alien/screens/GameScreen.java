package com.project.alien.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.project.alien.utils.Consts;

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
    private Label.LabelStyle textStyle;
    private BitmapFont HUDFont;

    public GameScreen() {
        super();

        txtrHUDBG = new Texture("img/HudBG.png");
        HUD = new Stage(new FitViewport(1440.0f, 1080.0f, new OrthographicCamera()));

        HUDFont = new BitmapFont(Gdx.files.internal("font/futureThin50.fnt"), false);
        textStyle = new Label.LabelStyle();
        textStyle.font = HUDFont;

        currResources = new Label("0", textStyle);
        currResources.setBounds(410.0f, 16.0f, 50.0f, 50.0f);
        currResources.setFontScale(1.5f, 1.5f);
        currScore = new Label("0", textStyle);
        currScore.setBounds(1043.0f, 17.0f, 50.0f, 50.0f);
        currScore.setFontScale(1.5f, 1.5f);
    }

    @Override
    public void buildStage() {
        Image HudBG = new Image(txtrHUDBG);
        HUD.addActor(HudBG);
        HUD.addActor(currResources);
        HUD.addActor(currScore);

        HUDTIMER = 0;
        RESOURCETIMER = 0;
        SCORETIMER = 0;
        SCORE = 0;
        RESOURCES = 0;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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

            if (SCORETIMER > Consts.RESOURCE_GAIN_TIME) {
                SCORETIMER -= Consts.SCORE_GAIN_TIME;
                SCORE += Consts.RESOURCE_GAIN_AMT;

                // Stage Multiplier function
                // SCORE += Consts.RESOURCE_GAIN_AMT * STAGEMULTIPLIER;
            }
            currScore.setText(Integer.toString(SCORE));
            currResources.setText(Integer.toString(RESOURCES));
        }
    }
}

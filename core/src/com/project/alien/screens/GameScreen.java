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
import com.project.alien.utils.GameHUD;

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
    private GameHUD HUD;


    private TiledLevel level1;

    public GameScreen() {
        super();
        HUD = new GameHUD();
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
        HUD.buildStage();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        level1.render();

        HUD.updateHUD(delta);

        super.act(delta);
        super.draw();
        act(delta);
        draw();
        HUD.HUD.act(delta);
        HUD.HUD.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}

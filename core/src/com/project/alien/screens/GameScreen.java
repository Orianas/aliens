package com.project.alien.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.project.alien.Levels.TiledLevel;
import com.project.alien.utils.GameHUD;
import com.project.alien.utils.PauseMenu;
import com.project.alien.utils.State;

public class GameScreen extends AbstractScreen {

    private GameHUD HUD;
    private PauseMenu pauseMenu;
    private State currState;
    private InputMultiplexer inputMultiplexer = new InputMultiplexer();


    private TiledLevel level1;

    public GameScreen() {
        super();
        currState = State.Running;
        HUD = new GameHUD();
        pauseMenu = new PauseMenu();
        level1 = new TiledLevel("maps/level_1.tmx");
    }

    @Override
    public void show() {
        /* Add all stages that process input here*/

        // HUD input
        inputMultiplexer.addProcessor(this);
        // Game map/tiles input
        inputMultiplexer.addProcessor(level1.getStage());
        inputMultiplexer.addProcessor(pauseMenu.getStage());
    }

    @Override
    public void buildStage() {
        HUD.buildStage();
        pauseMenu.buildStage(this);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        switch (currState) {
            case Running:
                level1.render();

                HUD.updateHUD(delta);

                super.act(delta);
                act(delta);
                HUD.act(delta);
                Gdx.input.setInputProcessor(inputMultiplexer);
                break;
            case Paused:
                pauseMenu.act(delta);
                pauseMenu.draw();
                Gdx.input.setInputProcessor(pauseMenu.getStage());
                break;
            default:
        }
        super.draw();
        draw();
        HUD.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE)
            togglePause();
        return false;
    }

    public void togglePause() {
        if (currState == State.Paused)
            currState = State.Running;
        else
            currState = State.Paused;
    }
}

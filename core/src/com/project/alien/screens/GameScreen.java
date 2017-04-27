package com.project.alien.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.project.alien.Levels.Level;
import com.project.alien.utils.GameHUD;
import com.project.alien.utils.PauseMenu;
import com.project.alien.utils.State;

public class GameScreen extends AbstractScreen {

    private Level level1;
    private GameHUD HUD;
    private PauseMenu pauseMenu;
    private State currState;

    public GameScreen() {
        super();
        currState = State.Running;
        HUD = new GameHUD();
        pauseMenu = new PauseMenu();
        level1 = new Level();
        level1.create();
    }

    @Override
    public void buildStage() {
        HUD.buildStage();
        pauseMenu.buildStage();
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

                break;
            case Paused:
                pauseMenu.act(delta);
                pauseMenu.draw();
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
            if (currState == State.Paused)
                currState = State.Running;
            else
                currState = State.Paused;
        return false;
    }
}

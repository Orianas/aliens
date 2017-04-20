package com.project.alien.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.project.alien.Levels.Level;
import com.project.alien.utils.GameHUD;

public class GameScreen extends AbstractScreen {

    private Level level1;
    private GameHUD HUD;

    public GameScreen() {
        super();
        HUD = new GameHUD();
        level1 = new Level();
        level1.create();
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

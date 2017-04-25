package com.project.alien.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by john on 4/21/2017.
 */
public class TiledLevel {

    private OrthographicCamera camera;
    private TiledMapRenderer tiledLevelRenderer;
    private TiledMap tiledLevel;
    private Stage levelStage;

    private float levelHeight;
    private float levelWidth;


    public TiledLevel(String fileName) {

        levelWidth = Gdx.graphics.getWidth();
        levelHeight = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, levelWidth, levelHeight);
        camera.translate(0, -35);
        camera.update();

        // The map loader only renders the tiles
        tiledLevel = new TmxMapLoader().load(fileName);
        tiledLevelRenderer = new OrthogonalTiledMapRenderer(tiledLevel, 1/2f);

        // Add a stage, with actors for each tile, on top the of rendered map
        // TiledMapStage adds the actors that listen for input events
        levelStage = new TiledMapStage(tiledLevel);
        levelStage.getViewport().setCamera(camera);

        Gdx.input.setInputProcessor(levelStage);
    }

    public void render() {

        Gdx.gl.glClearColor(187/255f,128/255f,68/255f,1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        tiledLevelRenderer.setView(camera);
        tiledLevelRenderer.render();
        levelStage.act();
        levelStage.draw();
    }

}

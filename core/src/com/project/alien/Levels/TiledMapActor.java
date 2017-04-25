package com.project.alien.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by john on 4/24/2017.
 */
public class TiledMapActor extends Actor {

    private TiledMap tiledMap;
    private TiledMapTileLayer tiledLayer;
    private TiledMapTileLayer.Cell cell;

    private Sprite hoverImg = new Sprite(new Texture("maps/tile_hover.png"));

    public TiledMapActor (TiledMap tiledMap, TiledMapTileLayer tiledLayer, TiledMapTileLayer.Cell cell) {

        this.tiledMap = tiledMap;
        this.tiledLayer = tiledLayer;
        this.cell = cell;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        hoverImg.draw(batch);
    }

    public TiledMap getTiledMap() {

        return this.tiledMap;
    }

    public TiledMapTileLayer getTiledLayer() {

        return this.tiledLayer;
    }

    public TiledMapTileLayer.Cell getCell() {

        return this.cell;
    }

}

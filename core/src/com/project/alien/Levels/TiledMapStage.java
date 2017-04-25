package com.project.alien.Levels;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by john on 4/24/2017.
 */
public class TiledMapStage extends Stage {

    private TiledMap tiledMap;

    public TiledMapStage(TiledMap tiledMap) {

        this.tiledMap = tiledMap;

        for(MapLayer layer : tiledMap.getLayers()) {

            TiledMapTileLayer tiledLayer = (TiledMapTileLayer)layer;
            createActorsForLayer(tiledLayer);
        }
    }

    public void createActorsForLayer(TiledMapTileLayer tiledLayer) {

        // Pixel count of each tile width and height
         float tileWidth  = tiledLayer.getTileWidth();
         float tileHeight = tiledLayer.getTileHeight();

        // getWidth and getHeight return a tile count, not pixel count
        for(int x = 0; x < tiledLayer.getWidth(); x++) {

            for(int y = 0; y < tiledLayer.getHeight(); y++) {

                TiledMapTileLayer.Cell cell = tiledLayer.getCell(x, y);
                TiledMapActor cellActor = new TiledMapActor(tiledMap, tiledLayer, cell);

                cellActor.setBounds(x * tileWidth, y * tileHeight, 1, 1);

                // add the new actor bound to cell to the Stage
                // @from Stage
                addActor(cellActor);

                System.out.println("Set actor " + x + ", " + y + " at " + x * tileWidth + "px, " + y * tileHeight + "px.");

                // add a click listener to the actor bounded to cell boundries
                // to listen for mouseclick and hover
                EventListener eventListener  = new TiledMapClickListener(cellActor);
                cellActor.addListener(eventListener);

            }
        }

    }


}

package com.project.alien.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.project.alien.Missiles.BigMissile;
import com.project.alien.utils.Consts;

/**
 * Created by john on 4/24/2017.
 */
public class TiledMapActor extends Actor {

    private TiledMap tiledMap;
    private TiledMapTileLayer tiledLayer;
    private TiledMapTileLayer.Cell cell;
    private TiledMapTileSet tileSet;

    private float screenX;
    private float screenY;

    public int x;
    public int y;


    private boolean highlighted = false;
    private boolean placeable;

    private int tileId;

    private Texture hoverImage, notPlaceableImage;
    private Sprite hoverSprite, notPlaceableSprite;

    public TiledMapActor (TiledMap tiledMap, TiledMapTileLayer tiledLayer, TiledMapTileLayer.Cell cell) {

        this.tiledMap = tiledMap;
        this.tiledLayer = tiledLayer;
        this.cell = cell;

        hoverImage = new Texture("maps/tile_hover.png");
        hoverSprite = new Sprite(hoverImage, hoverImage.getWidth(), hoverImage.getHeight());

        notPlaceableImage = new Texture("maps/tile_hover_x.png");
        notPlaceableSprite = new Sprite(notPlaceableImage, notPlaceableImage.getWidth(),
                notPlaceableImage.getHeight());


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        if(highlighted) {

            if(cell == null)
                placeable = true;

            hoverSprite.setOrigin(0,0);
            hoverSprite.setScale(Consts.UNIT_SCALE);
            hoverSprite.translate(-1/2f, -1/2f);
            hoverSprite.setX(this.screenX);
            hoverSprite.setY(this.screenY);

            notPlaceableSprite.setOrigin(0,0);
            notPlaceableSprite.setScale(Consts.UNIT_SCALE);
            notPlaceableSprite.translate(-1/2f, -1/2f);
            notPlaceableSprite.setX(this.screenX);
            notPlaceableSprite.setY(this.screenY);

            if(placeable)
                hoverSprite.draw(batch, parentAlpha);
            else
                notPlaceableSprite.draw(batch, parentAlpha);
        }
    }

    public void toggleWallTile() {

        if(placeable && (this.cell == null || this.cell.getTile() == null)) {

            this.cell = new TiledMapTileLayer.Cell();
            this.cell.setTile(tiledMap.getTileSets().getTile(181));
            this.tiledLayer.setCell(this.x,this.y,this.cell);

            placeable = false;

        }

        /*
        else {

            this.cell.setTile(null);
            placeable = true;
        }
        */
    }

    public void setHoverSprite(Sprite hoverSprite) {

        this.hoverSprite = hoverSprite;
    }

    public void toggleHighlight() {

        highlighted = !highlighted;
    }

    public void setScreenX(float x) {

        this.screenX = x;
    }

    public void setScreenY(float y) {

        this.screenY = y;
    }

    public void makePlaceable(boolean placeable) {

        this.placeable = placeable;
    }

    public boolean isPlaceable() {

        return this.placeable;
    }

    public void setTileId(int id) {

        this.tileId = id;
    }

    public int getTileId() {

        return this.tileId;
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

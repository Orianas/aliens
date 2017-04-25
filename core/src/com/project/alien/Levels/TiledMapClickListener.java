package com.project.alien.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import com.project.alien.utils.Consts;

import static com.badlogic.gdx.graphics.Color.WHITE;

/**
 * Created by john on 4/24/2017.
 */
public class TiledMapClickListener extends ClickListener {

    private TiledMapActor actor;

    /*
    private SpriteBatch batch;
    private Sprite sprite;
    private Texture hoverImg;
    */
    private Label cordLabel;
    private BitmapFont HUDFont = new BitmapFont(Gdx.files.internal("font/futureThin50.fnt"), false);

    public TiledMapClickListener(TiledMapActor actor) {

        this.actor = actor;
        //batch = new SpriteBatch();

    }

    @Override
    public void clicked(InputEvent event, float x, float y) {

        System.out.println(" clicked!");


        Label.LabelStyle textStyle = new Label.LabelStyle(HUDFont, WHITE);
        cordLabel = new Label("Click!", textStyle);
        cordLabel.setBounds(0, 0, 100, 100);

        /*
        batch.begin();
        hoverImg = new Texture("maps/tile_hover.png");
        batch.end();
        */
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        super.enter(event, x, y, pointer, fromActor);
        System.out.println("here!");
        actor.getCell().getFlipHorizontally();

    }
}

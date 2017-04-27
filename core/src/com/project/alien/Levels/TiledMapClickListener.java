package com.project.alien.Levels;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class TiledMapClickListener extends ClickListener {

    private TiledMapActor actor;


    public TiledMapClickListener(TiledMapActor actor) {

        this.actor = actor;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {

        actor.toggleWallTile();
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

        super.enter(event, x, y, pointer, fromActor);
        actor.toggleHighlight();
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

        super.exit(event, x, y, pointer, toActor);
        actor.toggleHighlight();
    }
}

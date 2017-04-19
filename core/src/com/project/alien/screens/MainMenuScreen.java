package com.project.alien.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.Align;
import com.project.alien.utils.ScreenEnum;
import com.project.alien.utils.UIFactory;

public class MainMenuScreen extends AbstractScreen {

    private Texture txtrBg;
    private Texture txtrNewGame;
    private Texture txtrExit;

    public MainMenuScreen() {
        super();
        txtrBg = new Texture(Gdx.files.internal("img/MainMenuBG.png"));
        txtrNewGame = new Texture(Gdx.files.internal("img/newGame.png"));
        txtrExit = new Texture(Gdx.files.internal("img/exit.png"));
    }

    @Override
    public void buildStage() {

        Image bg = new Image(txtrBg);
        addActor(bg);

        float midX = getWidth() / 2;
        float midY = getHeight() / 2;

        ImageButton btnNewGame = UIFactory.createButton(txtrNewGame);
        btnNewGame.setPosition(midX, midY - 25f, Align.center);
        addActor(btnNewGame);

        ImageButton btnExit = UIFactory.createButton(txtrExit);
        btnExit.setPosition(midX, midY - 300f, Align.center);
        addActor(btnExit);

        btnNewGame.addListener(UIFactory.createListener(ScreenEnum.GAME));

        btnExit.addListener(
                new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        Gdx.app.exit();
                        return false;
                    }
                });
    }

    @Override
    public void dispose() {
        super.dispose();
        txtrBg.dispose();
    }
}

package com.project.alien.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class PauseMenu extends GameHUD {

    private Stage stage;

    private Label lblNewGame;
    private Label lblResumeGame;
    private Texture txtrButton;
    private Texture txtrBG;
    private BitmapFont HUDFont;

    public PauseMenu() {
        txtrButton = new Texture("img/pauseMenuButton.png");
        txtrBG = new Texture("img/pauseMenuBG.png");

        stage = new Stage(new StretchViewport(1440.0f, 1080.0f));
        HUDFont = new BitmapFont(Gdx.files.internal("font/futureThin50.fnt"), false);
        Label.LabelStyle labelStyle = new Label.LabelStyle(HUDFont, Color.BLACK);

        lblNewGame = new Label("Main Menu", labelStyle);

        lblResumeGame = new Label("Resume", labelStyle);

    }

    public void buildStage() {
        Image background = new Image(txtrBG);
        ImageButton btnNewGame = UIFactory.createButton(txtrButton);
        ImageButton btnResumeGame = UIFactory.createButton(txtrButton);

        background.setPosition(stage.getWidth() / 2 - txtrBG.getWidth() / 2, stage.getHeight() / 2 - txtrBG.getHeight() / 2);
        btnNewGame.setPosition(stage.getWidth() / 2 - txtrButton.getWidth() / 2, stage.getHeight() / 2 + txtrButton.getHeight());
        btnResumeGame.setPosition(stage.getWidth() / 2 - txtrButton.getWidth() / 2, stage.getHeight() / 2 - txtrButton.getHeight());

        lblNewGame.setBounds(btnNewGame.getX() + txtrButton.getWidth() / 2, btnNewGame.getY() + txtrButton.getHeight() / 2, 1.0f, 1.0f);
        lblNewGame.setAlignment(Align.center);
        lblNewGame.setFontScale(1.0f, 1.0f);

        lblResumeGame.setBounds(btnResumeGame.getX() + txtrButton.getWidth() / 2, btnResumeGame.getY() + txtrButton.getHeight() / 2, 1.0f, 1.0f);
        lblResumeGame.setAlignment(Align.center);
        lblResumeGame.setFontScale(1.0f, 1.0f);

        btnNewGame.addListener(UIFactory.createListener(ScreenEnum.MAIN_MENU));

        stage.addActor(background);
        stage.addActor(btnNewGame);
        stage.addActor(btnResumeGame);
        stage.addActor(lblNewGame);
        stage.addActor(lblResumeGame);
    }

    public void act(float delta) {
        stage.act(delta);
    }

    public void draw() {
        stage.draw();
    }

    public void dispose() {
        txtrButton.dispose();
        txtrBG.dispose();
    }

}

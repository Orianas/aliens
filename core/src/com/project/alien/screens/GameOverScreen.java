package com.project.alien.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.project.alien.DBManager.DBManager;
import com.project.alien.utils.ScreenEnum;
import com.project.alien.utils.ScreenManager;
import com.project.alien.utils.UIFactory;

import javax.swing.*;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.Color.GREEN;
import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.project.alien.utils.ScreenEnum.GAME_OVER;
import static com.project.alien.utils.ScreenEnum.MAIN_MENU;

/**
 * Created by cade on 4/27/17.
 */
public class GameOverScreen extends AbstractScreen{
    private Texture txtrBg;
    private Texture txtrNewGame;
    private Texture txtrExit;

    private BitmapFont OKFont;
    private Texture txtrOK;
    private Label OK;
    private Label EXIT;

    private TextField txtUserName;
    private Skin uiSkin;

    private String userName;


    public GameOverScreen() {
        super();
        //String userName = new String();
        uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
        txtUserName = new TextField("", uiSkin);
        txtrBg = new Texture(Gdx.files.internal("img/mainMenuBG.png"));
        //txtrNewGame = new Texture(Gdx.files.internal("img/newGame.png"));
        txtrExit = new Texture(Gdx.files.internal("img/pauseMenuButton.png"));

        OKFont = new BitmapFont(Gdx.files.internal("font/futureThin50.fnt"), false);
        Label.LabelStyle textStyle = new Label.LabelStyle(OKFont, BLACK);

        txtrOK = new Texture(Gdx.files.internal("img/pauseMenuButton.png"));
        OK = new Label("Confirm", textStyle);
        EXIT = new Label("Main Menu", textStyle);

        OK.setFontScale(1.0f, 1.0f);
        EXIT.setFontScale(1.0f,1.0f);
        //nameInput = new TextField();

    }

    @Override
    public void buildStage() {

        Image bg = new Image(txtrBg);
        addActor(bg);

        float midX = getWidth() / 2;
        float midY = getHeight() / 2;

        float qrtX = getWidth() / 4;
        float qrtY = getWidth() / 5;

        ImageButton btnConfirmName = UIFactory.createButton(txtrOK);
        btnConfirmName.setPosition(qrtX, qrtY, Align.center);
        addActor(btnConfirmName);
        OK.setBounds(btnConfirmName.getX() + txtrOK.getWidth()/2, btnConfirmName.getY() + txtrOK.getHeight()/2, 1.0f, 1.0f);
        OK.setAlignment(Align.center);
        ImageButton btnExit = UIFactory.createButton(txtrExit);
        btnExit.setPosition(qrtX + 700f,qrtY , Align.center);
        addActor(btnExit);
        EXIT.setBounds(btnExit.getX() + txtrExit.getWidth()/2, btnExit.getY() + txtrExit.getHeight()/2, 1.0f,1.0f);
        EXIT.setAlignment(Align.center);


        txtUserName.setMessageText("Enter Name");
        txtUserName.setPosition(midX - 300f, midY - 100f);
        txtUserName.setAlignment(Align.center);
        txtUserName.setSize(600f,100f);

        addActor(txtUserName);


//        userName = txtUserName.getMessageText();
        //System.out.println(userName);


        addActor(EXIT);
        addActor(OK);


        btnConfirmName.addListener(
                new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        userName = txtUserName.getText();
                        if(!userName.equals("")){
                            DBManager db = new DBManager();
                            db.addToUsers(userName, 1500, 35);
                            db.getUsers();
                        }
                        ScreenManager.getInstance().showScreen(MAIN_MENU);
                        return false;
                    }
                });

        btnExit.addListener(
                new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        ScreenManager.getInstance().showScreen(MAIN_MENU);
                        return false;
                    }
                });
    }

    @Override
    public void dispose() {
        super.dispose();
        txtrBg.dispose();
        txtrOK.dispose();
        txtrExit.dispose();
    }
}

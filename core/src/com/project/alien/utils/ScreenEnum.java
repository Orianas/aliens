package com.project.alien.utils;


import com.project.alien.screens.AbstractScreen;
import com.project.alien.screens.GameOverScreen;
import com.project.alien.screens.GameScreen;
import com.project.alien.screens.MainMenuScreen;

public enum ScreenEnum {

    MAIN_MENU {
        public AbstractScreen getScreen(Object... params) {
            return new MainMenuScreen();
        }
    },

    GAME {
        public AbstractScreen getScreen(Object... params) {
            return new GameScreen();
        }
    },

    GAME_OVER{
        public AbstractScreen getScreen(Object... params){
            return new GameOverScreen();
        }
    };

    public abstract AbstractScreen getScreen(Object... params);
}

package com.project.alien.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.project.alien.ProjectAlien;
import com.project.alien.utils.Consts;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = Consts.GAME_WIDTH;
        config.height = Consts.GAME_HEIGHT;
        new LwjglApplication(new ProjectAlien(), config);
    }
}

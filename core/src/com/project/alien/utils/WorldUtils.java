package com.project.alien.utils;

import com.badlogic.gdx.physics.box2d.World;

public class WorldUtils {

    public static World createWorld() {
        return new World(Consts.WORLD_GRAVITY, true);
    }
}


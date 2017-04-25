package com.project.alien.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Cursor;
/**
 * Created by John Reach on 4/18/2017.
 * Cursor when selecting where to place a resource.
 */
public class PlacementCursor {

    private boolean cursorStatus;
    Cursor placementCursor;

    PlacementCursor() {

        Pixmap pm = new Pixmap(Gdx.files.internal("PlacementCursor-Good.png"));
        placementCursor = Gdx.graphics.newCursor(pm, 0,0);

        cursorStatus = false;
    }

    public void toggleCursor() {

        if(cursorStatus == false) {

            Gdx.graphics.setCursor(placementCursor);
            cursorStatus = true;

        } else {
            //Gdx.graphics.setCursor(Cursor.SystemCursor.Arrow);
            Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            cursorStatus = false;
        }

    }

    public boolean status() {

        return cursorStatus;
    }
}

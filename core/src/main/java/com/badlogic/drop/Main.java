package com.badlogic.drop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;

import java.io.File;
import java.io.FileOutputStream;

public class Main extends Game {
    public homescreen homeScreen;
    public gamescreen gameScreen;
    public Screen settingsscreen;
    public Screen levelOneScreen;
    public Screen leveltwoScreen;
    public Screen levelThreeScreen;
    public Screen winscreen;
     public Camera camera;
     public Screen losecreen;



    @Override
    public void create() {
        homeScreen = new homescreen(this);
        gameScreen = new gamescreen(this);
        settingsscreen = new settingsscreen(this);
        levelOneScreen = new level1(this);
        winscreen= new winscreen(this);
        losecreen= new losescreen(this);
        leveltwoScreen= new leveltwo(this);
        levelThreeScreen=new level3(this);

        setScreen(homeScreen);
    }

    @Override
    public void dispose() {
        super.dispose();

        homeScreen.dispose();
        gameScreen.dispose();
        settingsscreen.dispose();
        levelOneScreen.dispose();
        winscreen.dispose();
        losecreen.dispose();
        leveltwoScreen.dispose();
        levelThreeScreen.dispose();

    }


}

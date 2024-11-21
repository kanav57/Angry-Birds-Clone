package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class homescreen implements Screen {
    private final Main game;
    private Texture bgtexture;
    private Texture starttexture;
      private SpriteBatch spriteBatch;
     private FitViewport viewport;
    private Sprite startSprite;
    private Music music;
     private Texture titletexture;
    private Sprite titlesprite;
    private Texture hightexture;
    private Sprite highsprite;

    public homescreen(Main game) {
        this.game=game;
        spriteBatch=new SpriteBatch();
        viewport=new FitViewport(8,5);

        bgtexture=new Texture("ijkl.png");
        starttexture=new Texture("START.png");
        titletexture=new Texture("abb.png");
        hightexture=new Texture("h.png");

        startSprite= new Sprite(starttexture);
        startSprite.setSize(2, 0.8f);
        titlesprite =new Sprite(titletexture);
        titlesprite.setSize(3.4f, 0.9f);
        titlesprite.setPosition(2.5f, 2);
        highsprite=new Sprite(hightexture);
        highsprite.setSize(.7f, 0.23f);
        highsprite.setPosition(7.2f, 4.7f);


        startSprite.setPosition(3, 1.15f);

        music=Gdx.audio.newMusic(Gdx.files.internal("x.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
    }

    @Override
    public void show() {
         if (!music.isPlaying()) {
            music.play();
        }
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();
         spriteBatch.draw(bgtexture,0,0,8,5);
        startSprite.draw(spriteBatch);
        titlesprite.draw(spriteBatch);
        highsprite.draw(spriteBatch);
        spriteBatch.end();

        handle();
    }

    private void handle () {
        if (Gdx.input.isTouched()) {
            float x=Gdx.input.getX();
            float y=Gdx.input.getY();
            Vector3 posn=new Vector3(x,y,0);
            viewport.unproject(posn);
            Rectangle startBounds =startSprite.getBoundingRectangle();

            if (startBounds.contains(posn.x,posn.y)) {
                 music.stop();

                if (game.gameScreen==null) {
                    game.gameScreen=new gamescreen(game);
                }

                game.setScreen(game.gameScreen);
            } else {
             }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
            music.stop();
        }
    }

    @Override
    public void resize(int width,int height) {
        viewport.update(width,height,true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
        if (music.isPlaying()) {
            music.stop();
        }
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        bgtexture.dispose();
        starttexture.dispose();
        titletexture.dispose();
        hightexture.dispose();
        music.dispose();
    }
}

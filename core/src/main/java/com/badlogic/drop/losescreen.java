package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class losescreen implements Screen {
    private Main game;
    private Texture bgtexture;
    private SpriteBatch spriteBatch;
    private FitViewport viewport;
    private Button losepagebutton;
    private Button crossbutton;
    private Music music;

    public losescreen(Main game) {
        this.game = game;
        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(8, 5);
        bgtexture = new Texture("angyy.png");

        Texture losepagetexture = new Texture(Gdx.files.internal("losepage.png"));
        Texture crossbuttonTexture = new Texture(Gdx.files.internal("crossbutton.png"));


        losepagebutton = new Button(losepagetexture, 2.1f, 1.5f, 3.3f, 2.5f, viewport);

        crossbutton = new Button(crossbuttonTexture, 7, 4, 1, 1, viewport);

        music = Gdx.audio.newMusic(Gdx.files.internal("llose.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
    }

    @Override
    public void show() {
        music.play();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();
        spriteBatch.draw(bgtexture, 0, 0, 8, 5); // Draw background
        losepagebutton.render(spriteBatch);
        crossbutton.render(spriteBatch);
        spriteBatch.end();

        handle();
    }

    private void handle() {
        if (crossbutton.isTouched()) {
            music.stop();
            game.setScreen(game.gameScreen);
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
        music.stop();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        bgtexture.dispose();

        losepagebutton.dispose();
        crossbutton.dispose();
        music.dispose();
    }
}

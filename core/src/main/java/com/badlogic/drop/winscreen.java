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

public class winscreen implements Screen {
    private Main game;
    private Texture bgtexture;
    private SpriteBatch spriteBatch;
    private FitViewport viewport;
    private Button winpagebutton;
    private Button crossbutton;
    private Music music;

    // Preloaded textures for scores
    private Texture hundredTexture, eightyTexture, fiftyTexture, twentyTexture, zeroTexture;

    public winscreen(Main game) {
        this.game = game;
        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(8, 5);
        bgtexture = new Texture("angyy.png");

        // Preloading textures for scores
        hundredTexture = new Texture(Gdx.files.internal("hundred.png"));
        eightyTexture = new Texture(Gdx.files.internal("eighty.png"));
        fiftyTexture = new Texture(Gdx.files.internal("fifty.png"));
        twentyTexture = new Texture(Gdx.files.internal("twenty.png"));
        zeroTexture = new Texture(Gdx.files.internal("zero.png"));

        // Button textures
        Texture winpagetexture = new Texture(Gdx.files.internal("winner.png"));
        Texture crossbuttonTexture = new Texture(Gdx.files.internal("crossbutton.png"));

        // Buttons
        winpagebutton = new Button(winpagetexture, 2.1f, 1.5f, 3.3f, 2.5f, viewport);
        crossbutton = new Button(crossbuttonTexture, 7, 4, 1, 1, viewport);

        // Music setup
        music = Gdx.audio.newMusic(Gdx.files.internal("lwin.mp3"));
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
        spriteBatch.draw(bgtexture, 0, 0, 8, 5);
        winpagebutton.render(spriteBatch);

        if(state.level3flag){
            if (state.level3score > 80) {
                spriteBatch.draw(hundredTexture, 3.1f, 2f, 1.2f, .5f);
            } else if (state.level3score <= 80 && state.level3score > 50) {
                spriteBatch.draw(eightyTexture, 3.1f, 2f, 1.2f, .5f);
            } else if (state.level3score <= 50 && state.level3score > 20) {
                spriteBatch.draw(fiftyTexture, 3.1f, 2f, 1.2f, .5f);
            } else if (state.level3score <= 20 && state.level3score > 0) {
                spriteBatch.draw(twentyTexture, 3.1f, 2f, 1.2f, .5f);
            } else if (state.level3score >= 0) {
                spriteBatch.draw(zeroTexture, 3.1f, 2f, 1.2f, .5f);
            }

        }

        else if (state.level2flag) {
            if (state.level2score > 80) {
                spriteBatch.draw(hundredTexture, 3.1f, 2f, 1.2f, .5f);
            } else if (state.level2score <= 80 && state.level2score > 50) {
                spriteBatch.draw(eightyTexture, 3.1f, 2f, 1.2f, .5f);
            } else if (state.level2score <= 50 && state.level2score > 20) {
                spriteBatch.draw(fiftyTexture, 3.1f, 2f, 1.2f, .5f);
            } else if (state.level2score <= 20 && state.level2score > 0) {
                spriteBatch.draw(twentyTexture, 3.1f, 2f, 1.2f, .5f);
            } else if (state.level2score >= 0) {
                spriteBatch.draw(zeroTexture, 3.1f, 2f, 1.2f, .5f);
            }}

        else if (state.level1flag) {
            if (state.level1score > 80) {
                spriteBatch.draw(hundredTexture, 3.1f, 2f, 1.2f, .5f);
            } else if (state.level1score <= 80 && state.level1score > 50) {
                spriteBatch.draw(eightyTexture, 3.1f, 2f, 1.2f, .5f);
            } else if (state.level1score <= 50 && state.level1score > 20) {
                spriteBatch.draw(fiftyTexture, 3.1f, 2f, 1.2f, .5f);
            } else if (state.level1score <= 20 && state.level1score > 0) {
                spriteBatch.draw(twentyTexture, 3.1f, 2f, 1.2f, .5f);
            } else if (state.level1score >= 0) {
                spriteBatch.draw(zeroTexture, 3.1f, 2f, 1.2f, .5f);
            }


        }

        crossbutton.render(spriteBatch);
        spriteBatch.end();

        handleInput();
    }

    private void handleInput() {
        // Check if the cross button is pressed
        if (crossbutton.isTouched()) {
            music.stop();
            game.setScreen(game.gameScreen);
        }

        // Exit on ESCAPE key press
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
            music.stop();
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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
        // Dispose resources
        spriteBatch.dispose();
        bgtexture.dispose();
        winpagebutton.dispose();
        crossbutton.dispose();
        music.dispose();
        hundredTexture.dispose();
        eightyTexture.dispose();
        fiftyTexture.dispose();
        twentyTexture.dispose();
        zeroTexture.dispose();
    }
}

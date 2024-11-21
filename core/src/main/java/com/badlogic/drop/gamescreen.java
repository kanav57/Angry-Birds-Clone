package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class gamescreen implements Screen {
    private final Main game;
     private Texture bgtexture;

    private SpriteBatch spriteBatch;
    private FitViewport viewport;
    private Music music;
    private Button back;
     private Button play;

    private Button settings;
    private Button vol;
    private boolean isMusicPlaying=true;
     private Texture vontexture;
    private Texture vofftexture;
    private Bomb bomb;
     private Red red;
    private Terence terence;
    private Pig pig1;

     private Pig pig2;
    private Pig pig3;
    private Texture bombtexture;
    private Texture redtexture;
    private Texture terencetexture;
    private Texture pigtexture;

    public gamescreen(Main game) {
        this.game=game;
        spriteBatch=new SpriteBatch( );
        viewport=new FitViewport(8, 5);
        bgtexture=new Texture(Gdx.files.internal("bgr.jpg"));

        music=Gdx.audio.newMusic(Gdx.files.internal("angry_birds_theme.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f );

        vontexture=new Texture(Gdx.files.internal("volumeon.png"));
        vofftexture=new Texture(Gdx.files.internal("volumeoff.png"));
        bombtexture=new Texture(Gdx.files.internal("bomb.png"));
        redtexture=new Texture(Gdx.files.internal("red.png"));

        terencetexture=new Texture(Gdx.files.internal("terence.png" ));
        pigtexture = new Texture(Gdx.files.internal( "pig1.png"));

        Texture backTexture=new Texture(Gdx.files.internal("backy.png"));
        Texture playTexture=new Texture (Gdx.files.internal("PLAY.png"));
        Texture settingsTexture=new Texture(Gdx.files.internal("settings.png"));

        back=new Button(backTexture,0 ,4,1,1,viewport );
         play=new Button(playTexture,3.05f,2 ,1.9f ,1.5f,viewport);
        settings=new Button(settingsTexture,7,0,0.8f,0.7f,viewport);
         vol= new Button(vontexture,7, settings.getY()+settings.getHeight()+0.1f,0.8f,0.7f,viewport);
        bomb=new Bomb(bombtexture, 1,3.7f,0.8f,0.8f );
        red=new Red( redtexture,4,1,0.8f,0.8f );
        terence = new Terence( terencetexture,6,2,0.8f,0.8f);

        pig1=new Pig( pigtexture,5,4,1f,  1f);
        pig2 =new Pig(pigtexture,3, 2,1f,1f);
        pig3=new Pig(pigtexture,1,1,1f, 1f);
    }

    @Override
    public void show() {
         if (isMusicPlaying&&!music.isPlaying()) {
            music.play();
        }
    }

    @Override
    public void render(float delta) {
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();
        spriteBatch.draw(bgtexture,0,0,8,5);

        bomb.update(delta);
        bomb.render(spriteBatch);
        red.render(spriteBatch);
        terence.update(delta);
        terence.render(spriteBatch);

        pig1.update(delta);
        pig1.render(spriteBatch);
        pig2.update(delta);
         pig2.render(spriteBatch);
        pig3.update(delta);
         pig3.render(spriteBatch);
        back.render(spriteBatch);
        play.render(spriteBatch);

        settings.render(spriteBatch);
        Texture cvt;
        if(isMusicPlaying) {
            cvt=vontexture;
        }
        else {
            cvt=vofftexture;
        }
        spriteBatch.draw(cvt,vol.getX(),vol.getY(),vol.getWidth(),vol.getHeight());
         spriteBatch.end();
        handle();
    }

    private void handle() {
        if(back.isTouched()) {
            music.stop();
            game.setScreen(game.homeScreen);
        }
        if(play.isTouched()) {
            if(game.levelOneScreen==null) {
                game.levelOneScreen=new level1(game);
            }
            if(state.level==1){
                game.setScreen(game.levelOneScreen);
            }
            if(game.leveltwoScreen==null) {
                game.leveltwoScreen=new level1(game);
            }
            if(state.level==2){
                game.setScreen(game.leveltwoScreen);
            }
            if(game.levelThreeScreen==null) {
                game.levelThreeScreen=new level1(game);
            }
            if(state.level==3){
                game.setScreen(game.levelThreeScreen);

            }
        }
        if(settings.isTouched()) {
            if(game.settingsscreen==null) {
                game.settingsscreen=new settingsscreen(game);
            }
            game.setScreen(game.settingsscreen);
        }
        if(vol.isTouched()) {
            toggle();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
            music.stop();
        }
    }

    private void toggle() {
        isMusicPlaying=!isMusicPlaying;
        if (isMusicPlaying) {
            music.play();
        }
        else {
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
        if(music.isPlaying()) {
            music.stop();
        }
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        if(bgtexture!=null) bgtexture.dispose();
        if(music!=null) music.dispose();
        back.dispose();
        play.dispose();
        settings.dispose();
        vol.dispose();

        if(vontexture!=null) vontexture.dispose();
        if(vofftexture!=null) vofftexture.dispose();

        if(bombtexture!=null) bombtexture.dispose();
        if(redtexture!=null) redtexture.dispose();
        if(terencetexture!=null) terencetexture.dispose();
        if(pigtexture!=null) pigtexture.dispose();
        bomb.dispose();

        red.dispose();
        terence.dispose();
        pig1.dispose();
        pig2.dispose();
        pig3.dispose();
    }
}

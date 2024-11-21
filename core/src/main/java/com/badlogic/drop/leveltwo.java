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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import dev.lyze.gdxUnBox2d.GameObject;

public class leveltwo implements Screen {
    private final Main game;
    private SpriteBatch spriteBatch;
    private FitViewport viewport;
    private Texture bgtexture;
    private Music levelmusic;
    private Red red;
    private Bomb bomb;
    private boolean showTrajectory = false;
    private Catapult catapult;
    private Pig1 pig1;
    private Pig2 pig2;
    private Texture pigtwo1;
    private Texture pigtwo2;
    private Texture pigtwo3;
    private Texture damage;
    private Texture birdtexture, bird2texture, pigtexture, catapulttexture, pausebuttontexture;
    private Texture traj1;
    private Rectangle pausedim;
    private stone Stone1;
    private stone Stone2;
    private Texture stone1one, stone1two;
    private Texture stone2one, stone2two;
    private Texture iceone;
    private Texture icetwo;
    private ice Ice;
    public static int playtimes;


    private boolean isPaused = false;
    private Texture pausebgtexture;
    private Button replaybutton, crossbutton, settingsbutton, winbutton, losebutton;
    private boolean hasAdded = false;

    private boolean isDragging = false;
    private Vector2 dragStartPos = new Vector2();
    private Vector2 velocity = new Vector2();
    private float launchSpeed = 0f;
    private float launchAngle = 0f;
    private Bird current; // Track which bird is currently active

    public leveltwo(Main game) {
        this.game = game;
        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(8, 5);  // Or use a different viewport if needed

        viewport = new FitViewport(8, 5);

        bgtexture = new Texture(Gdx.files.internal("angyy.jpg"));
        stone1one = new Texture(Gdx.files.internal("stone1.png"));
        stone2one = new Texture(Gdx.files.internal("stone1.png"));
        stone1two = new Texture(Gdx.files.internal("stone2.png"));
        stone2two = new Texture(Gdx.files.internal("stone2.png"));
        levelmusic = Gdx.audio.newMusic(Gdx.files.internal("angry_birds_theme.mp3"));
        levelmusic.setLooping(true);
        levelmusic.setVolume(0.1f);

        birdtexture = new Texture(Gdx.files.internal("red.png"));
        bird2texture = new Texture(Gdx.files.internal("bomb.png"));
        pigtexture = new Texture(Gdx.files.internal("pig1.png"));
        catapulttexture = new Texture(Gdx.files.internal("catapult.png"));
        damage = new Texture(Gdx.files.internal("dabba11.png"));
        pausebuttontexture = new Texture(Gdx.files.internal("pausee.png"));
        pausedim = new Rectangle(0.01f, 4.2f, 2.2f, 2.2f);
        traj1 = new Texture(Gdx.files.internal("traj.png"));
        red = new Red(birdtexture, 1f, 0.72f, 0.3f, 0.3f);
        bomb = new Bomb(bird2texture, 1.3f, 0.72f, 0.3f, 0.3f);
        current = red; // Set the default bird to be the red bird
        catapult = new Catapult(catapulttexture, 0.65f, 0.7f);
        pigtwo1 = new Texture(Gdx.files.internal("pig21.png"));
        pigtwo2 = new Texture(Gdx.files.internal("pig22.png"));
        pigtwo3 = new Texture(Gdx.files.internal("pig23.png"));
        iceone= new Texture(Gdx.files.internal("ice1.png"));
        icetwo= new Texture(Gdx.files.internal("ice2.png"));
        Stone1= new stone(stone1one, 3.9f, .68f, 1f, 1f);
        iceone= new Texture(Gdx.files.internal("ice1.png"));
        Ice=new ice(iceone, 5.8f,.68f, .3f, .3f);
        pig2= new Pig2(pigtwo1, 5.3f, .68f, 0.3f, 0.3f);
        pig1 = new Pig1(pigtexture, 6.3f, .68f, 0.3f, 0.3f);
        Stone2= new stone(stone2one, 6.8f, .68f, 1f, 1f);
        Stone1.health=100;
        Stone2.health=150f;
        Ice.health=30f;
        pig1.health = 20;
        pig2.health = 20;
        playtimes=0;

        pausebgtexture = new Texture(Gdx.files.internal("pp.png"));
        Texture replaytexture = new Texture(Gdx.files.internal("replay.png"));
        Texture crosstexture = new Texture(Gdx.files.internal("crossbutton.png"));
        Texture settingstexture = new Texture(Gdx.files.internal("settings.png"));
        Texture wintexture = new Texture(Gdx.files.internal("winbutton.png"));
        Texture losetexture = new Texture(Gdx.files.internal("losebutton.png"));

        replaybutton = new Button(replaytexture, .3f, .7f, 1, 1, viewport);
        crossbutton = new Button(crosstexture, .3f, 2.2f, 1, 1, viewport);
        settingsbutton = new Button(settingstexture, .3f, 3.7f, 1, 1, viewport);
        winbutton = new Button(wintexture, .14f, 3.9f, .5f, .3f, viewport);
        losebutton = new Button(losetexture, .14f, 3.6f, .52f, .3f, viewport);
    }

    @Override
    public void show() {
        levelmusic.play();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();

        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();
        spriteBatch.draw(bgtexture, 0, 0, 8, 5);


        if (!isPaused) {
            red.render(spriteBatch);
            bomb.render(spriteBatch);
            catapult.render(spriteBatch);
            if(pig2!=null){
                pig2.render(spriteBatch);
            }
            if(pig1!=null){
                pig1.render(spriteBatch);
            }
            if (Stone1 != null) {
                Stone1.render(spriteBatch);
            }
            if (Stone2 != null) {
                Stone2.render(spriteBatch);
            }
            if (Ice != null) {
                Ice.render(spriteBatch);
            }




            spriteBatch.draw(pausebuttontexture, pausedim.x, pausedim.y, 0.7f, 0.7f);

            if (isDragging) {
                Vector2 currentPos = new Vector2();
                currentPos.set(Gdx.input.getX(), Gdx.input.getY());
                viewport.unproject(currentPos);


                float dragDistance = dragStartPos.dst(currentPos);
                launchAngle = (float) Math.atan2(currentPos.y - dragStartPos.y, currentPos.x - dragStartPos.x);
                launchSpeed = dragDistance * 10;

                current.setPosition(currentPos.x, currentPos.y);

                float angleDegrees = (float) Math.toDegrees(launchAngle);
                catapult.setRotation(-angleDegrees / 3.7f);

                velocity.set(-launchSpeed * (float) Math.cos(launchAngle), -launchSpeed * (float) Math.sin(launchAngle));

                if (showTrajectory) {
                    drawTrajectory();
                }

                if (!Gdx.input.isTouched()) {
                    current.launch(velocity);
                    isDragging = false;
                    showTrajectory = false;
                    catapult.setRotation(0);
                }
            }

            if (red.isFlying||bomb.isFlying) {
                if(Ice != null && Ice.bounds.overlaps(current.dim)){
                    if(current==red){
                        red.reset();
                     }
                    else{
                        Music mm = Gdx.audio.newMusic(Gdx.files.internal("bombu.mp3"));
                        mm.play();
                        bomb.reset();
                    }

                    Ice.dispose();
                    Ice=null;
                }

                if (Stone1 != null && Stone1.bounds.overlaps(current.dim) && Stone1.health > 0f) {

                    if(current==red){
                        red.reset();
                    }
                    else{
                        Music mm = Gdx.audio.newMusic(Gdx.files.internal("bombu.mp3"));
                        mm.play();

                        bomb.reset();
                    }
                    Stone1.health -= 10*(velocity.x+velocity.y);
                    Stone1.texture= stone1two;
                    Music m = Gdx.audio.newMusic(Gdx.files.internal("wooddamage.mp3"));
                    m.play();
                }

                if (Stone1 != null && Stone1.health <= 0f) {
                    Stone1.dispose();
                    Stone1=null;
                }

                if (Stone2 != null && Stone2.bounds.overlaps(current.dim) && Stone2.health > 0f) {
                    if(current==red){
                        red.reset();
                    }
                    else{
                        Music mm = Gdx.audio.newMusic(Gdx.files.internal("bombu.mp3"));
                        mm.play();

                        bomb.reset();
                    }
                    Stone2.health -= 10*(velocity.x+velocity.y);
                    Stone2.texture= stone2two;
                    Music m = Gdx.audio.newMusic(Gdx.files.internal("wooddamage.mp3"));
                    m.play();
                }

                if (Stone2 != null && Stone2.health <= 0f) {

                    Stone2.dispose();
                    Stone2=null;
                }

                if (pig1!=null && pig1.dim.overlaps(current.dim)) {
                    if(current==red){
                        red.reset();
                     }
                    else{
                        Music mm = Gdx.audio.newMusic(Gdx.files.internal("bombu.mp3"));
                        mm.play();
                        bomb.reset();
                     }

                    System.out.println("Collision with pig detected!");
                    pig1.health= pig1.health- 3*(velocity.x+velocity.y);
                    pig1.texture.dispose();
                    pig1.texture = new Texture(Gdx.files.internal("pig1damage1.png"));
                    Music mm = Gdx.audio.newMusic(Gdx.files.internal("pig1dam.mp3"));
                    mm.play();

                }

                if (pig2!=null && pig2.dim.overlaps(current.dim)) {
                    if(current==red){
                        red.reset();
                    }
                    else{
                        Music mm = Gdx.audio.newMusic(Gdx.files.internal("bombu.mp3"));
                        mm.play();
                        bomb.reset();
                    }
                    System.out.println("Collision with pig 2 detected!");
                    pig2.health= pig2.health-2*(velocity.x+velocity.y);
                    Music mm = Gdx.audio.newMusic(Gdx.files.internal("pig1dam.mp3"));
                    mm.play();
                }

                if(pig2!=null&&pig2.health<=20f){
                    pig2.texture.dispose();
                    pig2.texture = new Texture(Gdx.files.internal("pig22.png"));

                }

                if(pig2!=null&&pig2.health<=10f){
                    pig2.texture.dispose();
                    pig2.texture = new Texture(Gdx.files.internal("pig23.png"));
                }
                if(pig2!=null&&pig2.health<=0f){
                    pig2.dispose();
                    pig2=null;
                }
                if(pig1!=null&&pig1.health<=0f){
                    pig1.dispose();
                    pig1=null;
                }

                System.out.println(playtimes);

                if (pig1 ==null && pig2==null) {
                    if(current==red){
                        red.reset();
                    }
                    else{
                        bomb.reset();
                    }

//                    pig1.texture.dispose();
//                    pig1.texture = new Texture(Gdx.files.internal("pig1destroyed1.png"));
                    levelmusic.stop();
                    state.level2flag=true;
                    state.level=3;
                    state.level2score=(6-(playtimes))*21f;
                    System.out.println("Level 2 score "+state.level2score);
                    game.setScreen(game.winscreen);
                    return;
                }

                if(playtimes>=5){
                    levelmusic.stop();
                    game.setScreen(game.losecreen);
                    state.level2score=0;
                    red.reset();
                    bomb.reset();
                    playtimes=0;
                    pigtexture = new Texture(Gdx.files.internal("pig1.png"));
                    bgtexture = new Texture(Gdx.files.internal("angyy.jpg"));
                    stone1one = new Texture(Gdx.files.internal("stone1.png"));
                    stone2one = new Texture(Gdx.files.internal("stone1.png"));
                    stone1two = new Texture(Gdx.files.internal("stone2.png"));
                    stone2two = new Texture(Gdx.files.internal("stone2.png"));
                    pigtwo1 = new Texture(Gdx.files.internal("pig21.png"));
                    pigtwo2 = new Texture(Gdx.files.internal("pig22.png"));
                    pigtwo3 = new Texture(Gdx.files.internal("pig23.png"));
                    iceone= new Texture(Gdx.files.internal("ice1.png"));
                    icetwo= new Texture(Gdx.files.internal("ice2.png"));


                    Stone1= new stone(stone1one, 3.9f, .68f, 1f, 1f);
                    Ice=new ice(iceone, 5.8f,.68f, .3f, .3f);
                    pig2= new Pig2(pigtwo1, 5.3f, .68f, 0.3f, 0.3f);
                    pig1 = new Pig1(pigtexture, 6.3f, .68f, 0.3f, 0.3f);
                    Stone2= new stone(stone2one, 6.8f, .68f, 1f, 1f);
                    Stone1.health=100;
                    Stone2.health=150f;
                    Ice.health=30f;
                    pig1.health = 20f;
                    pig2.health = 30f;


                }





            }
        } else {
            red.render(spriteBatch);
            bomb.render(spriteBatch);
            catapult.render(spriteBatch);

            if(pig2!=null){
                pig2.render(spriteBatch);
            }
            if (Stone2 != null) {
                Stone2.render(spriteBatch);
            }
            if (Stone1 != null) {
                Stone1.render(spriteBatch);
            }
            if (Ice != null) {
                Ice.render(spriteBatch);
            }

            if(pig1!=null){
                pig1.render(spriteBatch);
            }
            spriteBatch.draw(pausebgtexture, 0, 0, 1.6f, 5);
            replaybutton.render(spriteBatch);
            crossbutton.render(spriteBatch);
            settingsbutton.render(spriteBatch);
        }
        spriteBatch.end();
        handle();
    }



    private void handle() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
            levelmusic.stop();
        }

        if (Gdx.input.isTouched()) {
            Vector2 posn = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(posn);

            if(red.dim.contains(posn)){

                red.setPosition(catapult.getX(), 1.7f);
                current=red;
            }
            else if(bomb.dim.contains(posn)){
                bomb.setPosition(catapult.getX(), 1.7f);
                bomb.texture= new Texture(Gdx.files.internal("bomb1.png"));
                current=bomb;
            }


            if (current.dim.contains(posn) && !isDragging && !isPaused && current.getY() == 1.7f) {
                dragStartPos.set(posn);
                isDragging = true;
                showTrajectory = true;
            }

            if (!isPaused) {
                if (pausedim.contains(posn)) {
                    isPaused = true;
                    levelmusic.pause();
                }

            } else {
                if (crossbutton.isTouched()) {
                    levelmusic.stop();
                    game.setScreen(game.homeScreen);
                }
                else if (replaybutton.isTouched()) {
                    game.leveltwoScreen = new leveltwo (game);
                    game.setScreen(game.leveltwoScreen);
                }
                else if (settingsbutton.isTouched()) {
                    if (game.settingsscreen == null) {
                        game.settingsscreen = new settingsscreen(game);
                    }
                    game.setScreen(game.settingsscreen);
                }
                else if(Gdx.input.justTouched()){
                    isPaused = false;
                }
            }
        }

        if (isDragging) {
            Vector2 currentPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(currentPos);
            float dragDistance = dragStartPos.dst(currentPos);
            launchAngle = (float) Math.atan2(currentPos.y - dragStartPos.y, currentPos.x - dragStartPos.x);
            launchSpeed = dragDistance * 15;
            if(current==red){
                red.setPosition(currentPos.x, currentPos.y);
            }
            else{
                bomb.setPosition(currentPos.x, currentPos.y);
            }

            float angleDegrees = (float) Math.toDegrees(launchAngle);
            catapult.setRotation(-angleDegrees/3.7f);

            if (!Gdx.input.isTouched()) {
                Vector2 launchVelocity = new Vector2(
                    -launchSpeed * (float) Math.cos(launchAngle),
                    -launchSpeed * (float) Math.sin(launchAngle)
                );
                if(current==red){
                    red.launch(launchVelocity);
                }
                else{
                    bomb.launch(launchVelocity);
                }
                isDragging = false;
                showTrajectory = false;

                catapult.setRotation(0);
            }
        }
        if(current==red){
            red.update(Gdx.graphics.getDeltaTime());
        }
        else{
            bomb.update(Gdx.graphics.getDeltaTime());
        }

    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void hide() {}

    @Override
    public void pause() {
        isPaused = true;
    }

    @Override
    public void resume() {
        isPaused = false;
    }
    private void drawTrajectory() {
        float timeStep = 0.1f;
        float totalTime = 1.5f;
        Vector2 currentPos = new Vector2(current.getX(), current.getY());

        for (float t = 0; t < totalTime; t += timeStep) {
            float x = currentPos.x + velocity.x * t;
            float y = currentPos.y + velocity.y * t + 0.5f * -9.8f * t * t;

            spriteBatch.draw(traj1, x, y, 0.15f, 0.15f);
        }
    }


    @Override
    public void dispose() {
        bgtexture.dispose();
        birdtexture.dispose();
        bird2texture.dispose();
        pigtexture.dispose();
        catapulttexture.dispose();
        damage.dispose();
        pausebuttontexture.dispose();
        traj1.dispose();
        pigtwo1.dispose();
        pigtwo2.dispose();
        pigtwo3.dispose();
        stone1one.dispose();
        stone1two.dispose();
        stone2one.dispose();
        stone2two.dispose();
        iceone.dispose();
        icetwo.dispose();
        pigtwo1.dispose();
        pigtwo2.dispose();
        pigtwo3.dispose();

        // Dispose of any other objects that require cleanup
        red.dispose();
        bomb.dispose();
        pig1.dispose();
        pig2.dispose();
        Stone1.dispose();
        Stone2.dispose();
        Ice.dispose();

        // Dispose of any sounds/music
        levelmusic.dispose();

        // Dispose of the buttons
        replaybutton.dispose();
        crossbutton.dispose();
        settingsbutton.dispose();
        winbutton.dispose();
        losebutton.dispose();

        // Dispose of the SpriteBatch
        spriteBatch.dispose();
    }
}

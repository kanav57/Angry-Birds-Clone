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

public class level1 implements Screen {
    private final Main game;
    private SpriteBatch spriteBatch;
    private FitViewport viewport;
    private Texture bgtexture;
    private Music levelmusic;
    private Red red;
    private float timer = 0; // Timer variable
    private float duration = 5f; // Duration for the timer (5 seconds)
    private boolean showTrajectory = false;
    private Catapult catapult;
    private Pig1 pig1;
    private wood woodstructure1;
    private Texture damage;
    private Texture birdtexture, pigtexture, catapulttexture, woodtexture, pausebuttontexture;
    private Texture traj1;
    private Rectangle pausedim;

    private boolean isPaused = false;
    private Texture pausebgtexture;
    private Button replaybutton, crossbutton, settingsbutton, winbutton, losebutton;

    private boolean isDragging = false;
    private Vector2 dragStartPos = new Vector2();
    private Vector2 velocity = new Vector2();
    private float launchSpeed = 0f;
    private float launchAngle = 0f;
    public static int playtimes=0;

    public level1(Main game) {
        this.game = game;
        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(8, 5);
        bgtexture = new Texture(Gdx.files.internal("angyy.jpg"));

        levelmusic = Gdx.audio.newMusic(Gdx.files.internal("angry_birds_theme.mp3"));
        levelmusic.setLooping(true);
        levelmusic.setVolume(0.1f);

        birdtexture = new Texture(Gdx.files.internal("red.png"));
        pigtexture = new Texture(Gdx.files.internal("pig1.png"));
        catapulttexture = new Texture(Gdx.files.internal("catapult.png"));
        woodtexture = new Texture(Gdx.files.internal("dabba1.png"));
        damage= new Texture(Gdx.files.internal("dabba11.png"));
        pausebuttontexture = new Texture(Gdx.files.internal("pausee.png"));
        pausedim = new Rectangle(0.01f, 4.2f, 2.2f, 2.2f);
        traj1=new Texture(Gdx.files.internal("traj.png"));
        red = new Red(birdtexture, 1f, 0.72f, 0.3f, 0.3f);
        catapult = new Catapult(catapulttexture, 0.65f, 0.7f);
        woodstructure1 = new wood(woodtexture, 6f, 0.68f, .5f, .5f);
        pig1 = new Pig1(pigtexture, 6.12f, 1.15f, 0.3f, 0.3f);
        pig1.health=20f;
        woodstructure1.health=20f;

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
            catapult.render(spriteBatch);

            if (woodstructure1 != null) {
                woodstructure1.render(spriteBatch);
            }

            pig1.render(spriteBatch);
            float deltaTime = Gdx.graphics.getDeltaTime();
            timer += deltaTime;



            spriteBatch.draw(pausebuttontexture, pausedim.x, pausedim.y, 0.7f, 0.7f);

            if (isDragging) {
                Vector2 currentPos = new Vector2();
                currentPos.set(Gdx.input.getX(), Gdx.input.getY());
                viewport.unproject(currentPos);

                float dragDistance = dragStartPos.dst(currentPos);
                launchAngle = (float) Math.atan2(currentPos.y - dragStartPos.y, currentPos.x - dragStartPos.x);
                launchSpeed = dragDistance * 10;

                red.setPosition(currentPos.x, currentPos.y);

                float angleDegrees = (float) Math.toDegrees(launchAngle);
                catapult.setRotation(-angleDegrees / 3.7f);

                velocity.set(-launchSpeed * (float) Math.cos(launchAngle), -launchSpeed * (float) Math.sin(launchAngle));

                if (showTrajectory) {
                    drawTrajectory();
                }

                if (!Gdx.input.isTouched()) {
                    red.launch(velocity);
                    isDragging = false;
                    showTrajectory = false;
                    catapult.setRotation(0);
                }
            }

            if (red.isFlying) {
                System.out.println(pig1.health);
                if (woodstructure1 != null && woodstructure1.bounds.overlaps(red.dim) && woodstructure1.health > 0f) {
                    red.reset();
                    System.out.println("Collision with wood detected!");
                    woodstructure1.health -= velocity.x+velocity.y;
                    woodstructure1.texture.dispose();
                    woodstructure1.texture = new Texture(Gdx.files.internal("dabba11.png"));

                    Music m = Gdx.audio.newMusic(Gdx.files.internal("wooddamage.mp3"));
                    m.play();
                }

                if (woodstructure1 != null && woodstructure1.health <= 0f) {
                    float destroyedX = woodstructure1.getX();
                    float destroyedY = woodstructure1.getY();

                    woodstructure1.dispose();
                    woodstructure1 = null;

                    pig1.dim.x = destroyedX;
                    pig1.dim.y = destroyedY;

                    pig1.health -= velocity.x+velocity.y;
                    pig1.texture.dispose();
                    pig1.texture = new Texture(Gdx.files.internal("pig1damage1.png"));
                    Music mm = Gdx.audio.newMusic(Gdx.files.internal("pig1dam.mp3"));
                    mm.play();

                }

                if (pig1.dim.overlaps(red.dim)) {
                    red.reset();

                    System.out.println("Collision with pig detected!");
                        pig1.health= pig1.health-velocity.x+velocity.y;
                        pig1.texture.dispose();
                        pig1.texture = new Texture(Gdx.files.internal("pig1damage1.png"));
                    Music mm = Gdx.audio.newMusic(Gdx.files.internal("pig1dam.mp3"));
                    mm.play();

                }

                if (pig1 !=null && pig1.health <= 0f) {
                    red.reset();
                    pig1.texture.dispose();
                    pig1.texture = new Texture(Gdx.files.internal("pig1destroyed1.png"));
                    levelmusic.stop();
                    state.level1flag=true;
                    state.level=2;
                    state.level1score=(6-playtimes)*21f;
                    game.setScreen(game.winscreen);


                    System.out.println("Level 1 score "+state.level1score);
                    return;
                }

                if(playtimes>=5){
                    levelmusic.stop();
                    game.setScreen(game.losecreen);
                    state.level1score=0;
                    red.reset();
                    playtimes=0;
                    birdtexture = new Texture(Gdx.files.internal("red.png"));
                    pigtexture = new Texture(Gdx.files.internal("pig1.png"));
                    catapulttexture = new Texture(Gdx.files.internal("catapult.png"));
                    woodtexture = new Texture(Gdx.files.internal("dabba1.png"));
                    damage= new Texture(Gdx.files.internal("dabba11.png"));
                    woodstructure1 = new wood(woodtexture, 6f, 0.68f, .5f, .5f);
                    pig1 = new Pig1(pigtexture, 6.12f, 1.15f, 0.3f, 0.3f);
                    pig1.health=20f;
                    woodstructure1.health=20f;
                }


            }
        } else {
            red.render(spriteBatch);
            catapult.render(spriteBatch);

            if (woodstructure1 != null) {
                woodstructure1.render(spriteBatch);
            }

            pig1.render(spriteBatch);
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

        if (Gdx.input.justTouched()) {
            Vector2 posn = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            red.setPosition(catapult.getX(), 1.7f);

            viewport.unproject(posn);
            if (red.dim.contains(posn) && !isDragging && !isPaused && red.getY() == 1.7f) {
                int x=0;
                while(x==0){
                    Music mm = Gdx.audio.newMusic(Gdx.files.internal("select.mp3"));
                    mm.play();
                    x++;
                }

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
                    game.levelOneScreen = new level1(game);
                    game.setScreen(game.levelOneScreen);
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

            red.setPosition(currentPos.x, currentPos.y);

            float angleDegrees = (float) Math.toDegrees(launchAngle);
            catapult.setRotation(-angleDegrees/3.7f);

            if (!Gdx.input.isTouched()) {
                Vector2 launchVelocity = new Vector2(
                    -launchSpeed * (float) Math.cos(launchAngle),
                    -launchSpeed * (float) Math.sin(launchAngle)
                );
                red.launch(launchVelocity);
                isDragging = false;
                showTrajectory = false;

                catapult.setRotation(0);
            }
        }

        red.update(Gdx.graphics.getDeltaTime());

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
        if (levelmusic.isPlaying()) {
            levelmusic.stop();
        }
    }
    private void drawTrajectory() {
        float timeStep = 0.1f;
        float totalTime = 1.5f;
        Vector2 currentPos = new Vector2(red.getX(), red.getY());

        for (float t = 0; t < totalTime; t += timeStep) {
            float x = currentPos.x + velocity.x * t;
            float y = currentPos.y + velocity.y * t + 0.5f * -9.8f * t * t;

            spriteBatch.draw(traj1, x, y, 0.15f, 0.15f);
        }
    }



    @Override
    public void dispose() {
        spriteBatch.dispose();
        bgtexture.dispose();
        if (levelmusic != null) levelmusic.dispose();

        birdtexture.dispose();
        pigtexture.dispose();
        catapulttexture.dispose();
        woodtexture.dispose();
        pausebuttontexture.dispose();

        pausebgtexture.dispose();
        red.dispose();
        pig1.dispose();

        woodstructure1.dispose();
        replaybutton.dispose();
        crossbutton.dispose();
        settingsbutton.dispose();
        winbutton.dispose();
        losebutton.dispose();
    }
}

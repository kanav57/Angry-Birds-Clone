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

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.ArrayList;

public class level3 implements Screen {
    public  final Main game;
    public static SpriteBatch spriteBatch;
    private FitViewport viewport;
    private Texture bgtexture;
    private Music levelmusic;
    private Matilda matilda;
    private Chuck chuck;
    private boolean showTrajectory = false;
    private Catapult catapult;
    private Pig3 pig1;
    private Texture pigtwo1;
    private Texture pigtwo2;
    private Texture pigtwo3;
    private Texture damage;
    private Texture birdtexture, bird2texture, pigtexture, catapulttexture, pausebuttontexture;
    private Texture traj1;
    private Rectangle pausedim;
    private stone Stone1;
    private stone Stone2;
    private stone Stone3;
    private Texture stone1one, stone1two;
    private Texture stone2one, stone2two;
    private Texture stone3one, stone3two;
    public static int matildaplaytimes=0;
    public static int chuckplaytimes=0;
    public Pillar pillar;
    private Texture pillartexture;
    public static wood tnt;
    private boolean isPaused = false;
    private Texture pausebgtexture;
    private Button replaybutton, crossbutton, settingsbutton, winbutton, losebutton;
    private Texture tnttexture;
    private boolean isDragging = false;
    private Vector2 dragStartPos = new Vector2();
    private Vector2 velocity = new Vector2();
    private float launchSpeed = 0f;
    private float launchAngle = 0f;
    private Bird current;
    public Egg egg1;
    public Egg egg2;
    private Texture white;
    private Texture golden;
    public static int playtimes=0;

    public level3(Main game) {
        this.game = game;
        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(8, 5);
        viewport = new FitViewport(8, 5);
        bgtexture = new Texture(Gdx.files.internal("angyy.jpg"));
        pillartexture = new Texture(Gdx.files.internal("pillar.png"));
        tnttexture= new Texture(Gdx.files.internal("tnt.png"));
        stone1one = new Texture(Gdx.files.internal("stone1.png"));
        stone2one = new Texture(Gdx.files.internal("stone1.png"));
        stone3one= new Texture(Gdx.files.internal("stone1.png"));
        stone1two = new Texture(Gdx.files.internal("stone2.png"));
        stone2two = new Texture(Gdx.files.internal("stone2.png"));
        stone3two = new Texture(Gdx.files.internal("stone2.png"));
        levelmusic = Gdx.audio.newMusic(Gdx.files.internal("angry_birds_theme.mp3"));
        levelmusic.setLooping(true);
        levelmusic.setVolume(0.1f);
        birdtexture = new Texture(Gdx.files.internal("matilda1.png"));
        bird2texture = new Texture(Gdx.files.internal("chuck1.png"));
        pigtexture = new Texture(Gdx.files.internal("pig31.png"));
        catapulttexture = new Texture(Gdx.files.internal("catapult.png"));
        damage = new Texture(Gdx.files.internal("dabba11.png"));
        pausebuttontexture = new Texture(Gdx.files.internal("pausee.png"));
        pausedim = new Rectangle(0.01f, 4.2f, 2.2f, 2.2f);
        traj1 = new Texture(Gdx.files.internal("traj.png"));
        matilda = new Matilda(birdtexture, 1f, 0.72f, 0.3f, 0.3f);
        chuck = new Chuck(bird2texture, 1.3f, 0.72f, 0.3f, 0.3f);
        current = matilda;
        catapult = new Catapult(catapulttexture, 0.65f, 0.7f);
        pillar= new Pillar(pillartexture, 5f, .68f, .5f, 2.2f);
        tnt=new wood(tnttexture, 6.9f, .68f, .4f, .4f);
        Stone1= new stone(stone1one, 6f, .68f, .5f, .5f);
        pig1 = new Pig3(pigtexture, 6.12f, .78f, 0.3f, 0.3f);
        Stone2= new stone(stone2one, 6f, 1.18f, .5f, .5f);
        Stone3=new stone(stone3one, 5f, 2.78f, .5f, .5f);
        Stone1.health=100;
        pillar.health=10f;
        tnt.health=0.1f;
        Stone2.health=100f;
        Stone3.health=100f;
        pig1.health = 40;

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
        spriteBatch = new SpriteBatch(); // Create a new SpriteBatch

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();

        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);

        System.out.println("Spritebatch.begin()");
        spriteBatch.begin();
        spriteBatch.draw(bgtexture, 0, 0, 8, 5);


        if (!isPaused) {
            matilda.render(spriteBatch);
            chuck.render(spriteBatch);
            catapult.render(spriteBatch);
            if (Stone1 != null) {
                Stone1.render(spriteBatch);
            }
            if (Stone2 != null) {
                Stone2.render(spriteBatch);
            }
            if(Stone3!=null){
                Stone3.render(spriteBatch);
            }
            if(pillar!=null){
                pillar.render(spriteBatch);
            }
            if(tnt!=null){
                tnt.render(spriteBatch);
            }

            System.out.println(pig1.health);

            if (pig1 !=null && pig1.health <= 0f) {
                if(current==matilda){
                    matilda.reset();
                }
                else{
                    chuck.reset();
                }

                pig1.texture.dispose();
                pig1.texture = new Texture(Gdx.files.internal("pig1destroyed1.png"));
                levelmusic.stop();
                state.level3flag=true;
                state.level=4;
                state.level3score=(6-(playtimes))*21f;
                game.setScreen(game.winscreen);
                System.out.println("Level 3 score "+state.level2score);
            }

            if(pig1!=null){
                pig1.render(spriteBatch);
            }
            if (matilda.droppedEgg != null) {
                matilda.droppedEgg.render(spriteBatch);
            }
            spriteBatch.draw(pausebuttontexture, pausedim.x, pausedim.y, 0.7f, 0.7f);

            if (isDragging) {
                Vector2 currentPos = new Vector2();
                currentPos.set(Gdx.input.getX(), Gdx.input.getY());
                viewport.unproject(currentPos);


                float dragDistance = dragStartPos.dst(currentPos);
                launchAngle = (float) Math.atan2(currentPos.y - dragStartPos.y, currentPos.x - dragStartPos.x);
                if(current==matilda){
                    matilda.setPosition(currentPos.x, currentPos.y);
                    launchSpeed = dragDistance * 10;
                }
                else{
                    chuck.setPosition(currentPos.x, currentPos.y);
                    launchSpeed = dragDistance * 30;

                }

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

            if(playtimes>=5 && (matilda.droppedEgg == null || !matilda.droppedEgg.isFalling)){
                levelmusic.stop();
                state.level2score=0;
                chuck.reset();
                matilda.reset();
                playtimes=0;
                pigtexture = new Texture(Gdx.files.internal("pig31.png"));
                spriteBatch = new SpriteBatch();
                viewport = new FitViewport(8, 5);
                viewport = new FitViewport(8, 5);
                bgtexture = new Texture(Gdx.files.internal("angyy.jpg"));
                pillartexture = new Texture(Gdx.files.internal("pillar.png"));
                tnttexture= new Texture(Gdx.files.internal("tnt.png"));
                stone1one = new Texture(Gdx.files.internal("stone1.png"));
                stone2one = new Texture(Gdx.files.internal("stone1.png"));
                stone3one= new Texture(Gdx.files.internal("stone1.png"));
                stone1two = new Texture(Gdx.files.internal("stone2.png"));
                stone2two = new Texture(Gdx.files.internal("stone2.png"));
                stone3two = new Texture(Gdx.files.internal("stone2.png"));
                levelmusic = Gdx.audio.newMusic(Gdx.files.internal("angry_birds_theme.mp3"));
                levelmusic.setLooping(true);
                levelmusic.setVolume(0.1f);
                birdtexture = new Texture(Gdx.files.internal("matilda1.png"));
                bird2texture = new Texture(Gdx.files.internal("chuck1.png"));
                pigtexture = new Texture(Gdx.files.internal("pig1.png"));
                catapulttexture = new Texture(Gdx.files.internal("catapult.png"));
                damage = new Texture(Gdx.files.internal("dabba11.png"));
                pausebuttontexture = new Texture(Gdx.files.internal("pausee.png"));
                pausedim = new Rectangle(0.01f, 4.2f, 2.2f, 2.2f);
                traj1 = new Texture(Gdx.files.internal("traj.png"));
                matilda = new Matilda(birdtexture, 1f, 0.72f, 0.3f, 0.3f);
                chuck = new Chuck(bird2texture, 1.3f, 0.72f, 0.3f, 0.3f);
                current = matilda;
                catapult = new Catapult(catapulttexture, 0.65f, 0.7f);
                pillar= new Pillar(pillartexture, 5f, .68f, .5f, 2.2f);
                tnt=new wood(tnttexture, 6.9f, .68f, .4f, .4f);
                Stone1= new stone(stone1one, 6f, .68f, .5f, .5f);
                pig1 = new Pig3(pigtexture, 6.12f, .78f, 0.3f, 0.3f);
                Stone2= new stone(stone2one, 6f, 1.18f, .5f, .5f);
                Stone3=new stone(stone3one, 5f, 2.78f, .5f, .5f);
                Stone1.health=100;
                pillar.health=10f;
                tnt.health=0.1f;
                Stone2.health=100f;
                Stone3.health=100f;
                pig1.health = 40;
                game.setScreen(game.losecreen);
            }


            if (matilda.isFlying||chuck.isFlying) {


                if(pillar != null && pillar.bounds.overlaps(current.dim)){
                    if(current==matilda){
                        matilda.reset();
                    }
                    else{
                        chuck.reset();
                    }
                    if(Stone3!=null){
                        Stone3.bounds.y=pillar.bounds.y;
                    }
                    pillar.dispose();
                    pillar=null;
                    Music mm = Gdx.audio.newMusic(Gdx.files.internal("bombu.mp3"));
                    mm.play();

                }

                if(Stone3!=null && Stone3.bounds.overlaps(current.dim)){
                    if(current==matilda){
                        matilda.reset();
                    }
                    else{
                        chuck.reset();
                    }
                    Stone3.dispose();
                    Stone3=null;
                    Music mm = Gdx.audio.newMusic(Gdx.files.internal("bombu.mp3"));
                    mm.play();

                }

                if(tnt!=null && tnt.bounds.overlaps(current.dim)){
                    if(current==matilda){
                        matilda.reset();
                    }
                    else{
                        chuck.reset();
                    }
                    tnt.health=0f;

                }

                if(tnt!=null && tnt.health<=0f){
                    tnt.dispose();
                    tnt=null;
                    Music mm = Gdx.audio.newMusic(Gdx.files.internal("bombu.mp3"));
                    mm.play();
                    mm.play();
                    mm.play();
                    if(pig1!=null){
                        pig1.health=0f;
                    }
                    if(Stone3!=null){
                        Stone3.dispose();
                        Stone3=null;

                    }
                    if(Stone1!=null){
                        Stone1.health=0f;
                    }
                    if(Stone2!=null){
                        Stone2.health=0f;
                    }
                    if(pillar!=null){
                        pillar.dispose();
                        pillar=null;
                    }
                }


                if (Stone1 != null && Stone1.bounds.overlaps(current.dim) && Stone1.health > 0f) {

                    if(current==matilda){
                        matilda.reset();
                    }
                    else{
                        chuck.reset();
                    }
                    Stone1.health -= 10*(velocity.x+velocity.y);
                    Stone1.texture= stone1two;
                    Music m = Gdx.audio.newMusic(Gdx.files.internal("wooddamage.mp3"));
                    m.play();

                }

                if (Stone1 != null && Stone1.health <= 0f) {

                    if(Stone2!=null){
                        Stone2.health=Stone2.health-10f;
                        Stone2.bounds.y=Stone1.bounds.y;
                    }

                    Stone1.dispose();
                    Stone1=null;

                }

                if (Stone2 != null && Stone2.bounds.overlaps(current.dim) && Stone2.health > 0f) {
                    if(current==matilda){
                        matilda.reset();
                    }
                    else{
                        chuck.reset();
                    }
                    Stone2.health -= 10*(velocity.x+velocity.y);
                    Stone2.texture.dispose();
                    Stone2.texture= stone2two;
                    Music m = Gdx.audio.newMusic(Gdx.files.internal("wooddamage.mp3"));
                    m.play();
                }

                if (Stone2 != null && Stone2.health <= 0f) {

                    Stone2.dispose();
                    Stone2=null;
                }

                if(Stone1!=null && Stone1.health<=50f){
                    Stone1.texture.dispose();
                    Stone1.texture= new Texture(Gdx.files.internal("stone3.mp3"));
                }

                if(Stone2!=null && Stone2.health<=50f){
                    Stone1.texture.dispose();
                    Stone1.texture= new Texture(Gdx.files.internal("stone2.mp3"));
                }

                if (pig1.dim.overlaps(current.dim)) {
                    if(current==matilda){
                        matilda.reset();
                    }
                    else{
                        Music mm = Gdx.audio.newMusic(Gdx.files.internal("chucku.mp3"));
                        mm.play();
                        chuck.reset();
                    }

                    System.out.println("Collision with pig detected!");
                    pig1.health= pig1.health- 3*(velocity.x+velocity.y);
                    pig1.texture.dispose();
                    pig1.texture = new Texture(Gdx.files.internal("pig33.png"));
                    Music mm = Gdx.audio.newMusic(Gdx.files.internal("pig1dam.mp3"));
                    mm.play();

                }

                if(pig1!=null && pig1.health<=0){
                    if(current==matilda){
                        matilda.reset();
                    }
                    else{
                        chuck.reset();
                    }

                    pig1.texture.dispose();
                    pig1.texture = new Texture(Gdx.files.internal("pig33.png"));
                    levelmusic.stop();
                    game.setScreen(game.winscreen);
                    state.level3flag=true;
                    state.level=4;
                    state.level3score=(6-(playtimes))*21f;
                    System.out.println("Level 3 score "+state.level3score);
                }



            }
        } else {
            matilda.render(spriteBatch);
            chuck.render(spriteBatch);
            catapult.render(spriteBatch);
            pig1.render(spriteBatch);
            if (Stone1 != null) {
                Stone1.render(spriteBatch);
            }
            if (Stone2 != null) {
                Stone2.render(spriteBatch);
            }
            if(Stone3!=null){
                Stone3.render(spriteBatch);
            }
            if(pillar!=null){
                pillar.render(spriteBatch);
            }
            if(tnt!=null){
                tnt.render(spriteBatch);
            }
            System.out.println(pig1.health);
            spriteBatch.draw(pausebgtexture, 0, 0, 1.6f, 5);
            replaybutton.render(spriteBatch);
            crossbutton.render(spriteBatch);
            settingsbutton.render(spriteBatch);


            pig1.render(spriteBatch);
            spriteBatch.draw(pausebuttontexture, pausedim.x, pausedim.y, 0.7f, 0.7f);

            if (isDragging) {
                Vector2 currentPos = new Vector2();
                currentPos.set(Gdx.input.getX(), Gdx.input.getY());
                viewport.unproject(currentPos);


                float dragDistance = dragStartPos.dst(currentPos);
                launchAngle = (float) Math.atan2(currentPos.y - dragStartPos.y, currentPos.x - dragStartPos.x);
                if(current==matilda){
                    matilda.setPosition(currentPos.x, currentPos.y);
                    launchSpeed = dragDistance * 10;
                }
                else{
                    chuck.setPosition(currentPos.x, currentPos.y);
                    launchSpeed = dragDistance * 30;

                }

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




            else{
                matilda.render(spriteBatch);
                chuck.render(spriteBatch);
                catapult.render(spriteBatch);

                if (Stone2 != null) {
                    Stone2.render(spriteBatch);
                }
                if (Stone1 != null) {
                    Stone1.render(spriteBatch);
                }
                if (pillar != null) {
                    pillar.render(spriteBatch);
                }
                if(Stone3!=null){
                    Stone3.render(spriteBatch);

                }

                pig1.render(spriteBatch);
                spriteBatch.draw(pausebgtexture, 0, 0, 1.6f, 5);
                replaybutton.render(spriteBatch);
                crossbutton.render(spriteBatch);
                settingsbutton.render(spriteBatch);

            }
        }

        handle();
        System.out.println("Spritebatch.end()");
        spriteBatch.end();

    }



    private void handle() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
            levelmusic.stop();
        }

        if (Gdx.input.isTouched()) {
            Vector2 posn = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(posn);

            if(matilda.dim.contains(posn)){

                matilda.setPosition(catapult.getX(), 1.7f);
                current=matilda;
            }
            else if(chuck.dim.contains(posn)){
                chuck.setPosition(catapult.getX(), 1.7f);
                chuck.texture= new Texture(Gdx.files.internal("chuck1.png"));
                current=chuck;
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
                if (winbutton.isTouched()) {
                    levelmusic.stop();
                    game.setScreen(game.winscreen);
                }
                if (losebutton.isTouched()) {
                    levelmusic.stop();
                    game.setScreen(game.losecreen);
                }
            } else {
                if (crossbutton.isTouched()) {
                    levelmusic.stop();
                    game.setScreen(game.homeScreen);
                }
                else if (replaybutton.isTouched()) {
                    game.levelOneScreen = new level3 (game);
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
            if(current==matilda){
                matilda.setPosition(currentPos.x, currentPos.y);
                launchSpeed = dragDistance * 10;
            }
            else{
                chuck.setPosition(currentPos.x, currentPos.y);
                launchSpeed = dragDistance * 30;

            }

            float angleDegrees = (float) Math.toDegrees(launchAngle);
            catapult.setRotation(-angleDegrees/3.7f);

            if (!Gdx.input.isTouched()) {
                Vector2 launchVelocity = new Vector2(
                    -launchSpeed * (float) Math.cos(launchAngle),
                    -launchSpeed * (float) Math.sin(launchAngle)
                );
                if(current==matilda){
                    matilda.launch(launchVelocity);
                }
                else{
                    chuck.launch(launchVelocity);
                }
                isDragging = false;
                showTrajectory = false;

                catapult.setRotation(0);
            }


        }
        if(current==matilda){
            matilda.update(Gdx.graphics.getDeltaTime());
        }
        else{
            chuck.update(Gdx.graphics.getDeltaTime());
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
        spriteBatch.dispose();
        levelmusic.dispose();
        bgtexture.dispose();
        birdtexture.dispose();
        bird2texture.dispose();
        pigtexture.dispose();
        catapulttexture.dispose();
        damage.dispose();
        pausebuttontexture.dispose();

    }
}

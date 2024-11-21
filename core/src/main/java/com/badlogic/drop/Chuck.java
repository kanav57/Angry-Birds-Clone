package com.badlogic.drop;

import com.badlogic.drop.Bird;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Chuck extends Bird {
    private Vector2 velocity; // Current velocity of the bird
    public boolean isFlying;
    public static final float GRAVITY = -9.8f; // Gravity constant
    public float newX;
    public float newY;
    public float explosionRadius;


    public Chuck(Texture texture, float x, float y, float width, float height) {
        super(texture, x, y, width, height);
        this.velocity = new Vector2(0, 0);
        this.isFlying = false;
        this.explosionRadius=width/2+height/2;
    }

    public void launch(Vector2 velocity) {
        this.velocity.set(velocity);
        this.isFlying = true;
    }

    public void update(float deltaTime) {
        if (isFlying) {
            this.newX = getX() + velocity.x * deltaTime;
            velocity.y -= 9.8f * deltaTime;
            this.newY = getY() + velocity.y * deltaTime + 0.5f * GRAVITY * deltaTime * deltaTime;
            setPosition(newX, newY);
            if (newY <= 0.73f || newY >= 5f || newX <= 0 || newX >= 8f) {
                reset();
            }
        }
    }



    public void reset() {
        if(!state.level3flag&&state.level1flag&&state.level2flag){
            level3.chuckplaytimes++;
        }
        isFlying = false;
        velocity.set(0, 0);
        this.newX = .65f;
        this.newY = 1.7f;
        setPosition(1.6f, .72f);
        this.texture= new Texture(Gdx.files.internal("chuck1.png"));
        Music selectSound = Gdx.audio.newMusic(Gdx.files.internal("select.mp3"));
        selectSound.play();
        level3.playtimes++;

    }
    public float getX() {
        return this.newX;
    }
}


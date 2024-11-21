package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Red extends Bird {
    private Vector2 velocity; // Current velocity of the bird
    public boolean isFlying;
    public static final float GRAVITY = -9.8f; // Gravity constant
    public float newX;
    public float newY;
    public static boolean hasAdded;


    public Red(Texture texture, float x, float y, float width, float height) {
        super(texture, x, y, width, height);
        this.velocity = new Vector2(0, 0); // Initialize velocity
        this.isFlying = false;
    }

    public void launch(Vector2 velocity) {
        this.velocity.set(velocity);
        this.isFlying = true;
    }

    public void update(float deltaTime) {
        if (isFlying) {
            this.newX = getX() + velocity.x * deltaTime;
            velocity.y -= 9.8f * deltaTime;
            this.newY = getY() + velocity.y * deltaTime + 0.5f * GRAVITY * deltaTime * deltaTime;  // 0.5 * g * t^2 for gravity

            setPosition(newX, newY);
            if (newY <= 0.73f || newY >= 5f || newX <= 0 || newX >= 8f) {
                reset();
            }
        }
    }

    public void reset() {
        if (!state.level2flag && state.level1flag) {
            leveltwo.playtimes++;
        }
        if(!state.level1flag){
            level1.playtimes++;
        }

        isFlying = false;
        velocity.set(0, 0);
        this.newX = .65f;
        this.newY = 1.7f;
        setPosition(1, .72f);
        Music selectSound = Gdx.audio.newMusic(Gdx.files.internal("select.mp3"));
        selectSound.play();

    }
    public float getX() {
        return this.newX;
    }



}

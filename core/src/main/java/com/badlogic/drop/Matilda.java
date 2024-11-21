package com.badlogic.drop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;



public class Matilda extends Bird {
    private Vector2 velocity; // Current velocity of the bird
    public boolean isFlying;
    public static final float GRAVITY = -9.8f; // Gravity constant
    public float newX;
    public float newY;
    public float explosionRadius;


    public Texture egg1Texture; // Texture for white egg
    public Egg droppedEgg;      // The egg Matilda drops

    public Matilda(Texture texture, float x, float y, float width, float height) {
        super(texture, x, y, width, height);
        this.velocity = new Vector2(0, 0); // Initialize velocity
        this.isFlying = false;
        this.explosionRadius = width / 2 + height / 2;

        egg1Texture = new Texture(Gdx.files.internal("egg.png"));

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

            if (velocity.y > 0 && velocity.y - 9.8f * deltaTime <= 0) {
                this.texture.dispose();
                this.texture = new Texture(Gdx.files.internal("matilda2.png"));
                dropEgg(newX, newY);
            }

            setPosition(newX, newY);

            if (newY <= 0.73f || newY >= 5f || newX <= 0 || newX >= 16f) {
                reset();
            }
        }

        if (droppedEgg != null) {
            droppedEgg.update(deltaTime, 5f); // Update egg position and status
        }
    }


    public void dropEgg(float x, float y) {
        if (droppedEgg == null) { // Ensure only one egg is dropped
            Texture chosenTexture =egg1Texture;
            droppedEgg = new Egg(chosenTexture, x ,y ,.2f,.2f);
        }
    }

    public void reset() {
        isFlying = false;
        velocity.set(0, 0);
        this.newX = .65f;
        this.newY = 1.7f;
        setPosition(1f, .72f);
        this.texture = new Texture(Gdx.files.internal("matilda1.png"));
        Music selectSound = Gdx.audio.newMusic(Gdx.files.internal("select.mp3"));
        selectSound.play();
        level3.playtimes++;
        droppedEgg = null;
    }

    public float getX() {
        return this.newX;
    }
}

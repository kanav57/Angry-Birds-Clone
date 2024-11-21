package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Egg implements Structure {
    public Rectangle bounds;
    public Texture texture;
    private float fallSpeed = 1.5f; // Controls how fast the egg falls
    public boolean isFalling = true;

    public Egg(Texture texture, float x, float y, float width, float height) {
        this.texture = texture;
        this.bounds = new Rectangle(x, y, width, height);
    }

    public void update(float deltaTime, float fallSpeed) {
        // If the egg is still falling, update its position
        if (isFalling) {
            bounds.y -= fallSpeed * deltaTime; // Make it fall gradually


            if (bounds.y <= 0.73f) {
                bounds.y = 0.73f; // Make sure it doesn't fall below the threshold
                isFalling = false;
            }


            if(level3.tnt!=null && level3.tnt.bounds.contains(bounds.x, bounds.y)) {
                isFalling = false;
                level3.tnt.health=0f;
            }




        }
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

    @Override
    public void render(SpriteBatch batch) {
        if (isFalling) {
            batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);
        }

    }

    @Override
    public float getX() {
        return bounds.x;
    }

    @Override
    public float getY() {
        return bounds.y;
    }

    @Override
    public float getWidth() {
        return bounds.width;
    }

    @Override
    public float getHeight() {
        return bounds.height;
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}

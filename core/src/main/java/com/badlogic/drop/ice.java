package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class ice implements Structure {

    public Texture texture;
    public Rectangle bounds;
    public float health;

    public ice(Texture texture, float x, float y, float width, float height) {
        this.texture = texture;
        this.bounds = new Rectangle(x, y, width, height);
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);
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

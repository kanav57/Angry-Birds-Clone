package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;  // Add this import for Rectangle

public class Catapult {
    protected Texture texture;
    protected Rectangle dim;
    private float rotation; // Rotation angle in degrees

    public Catapult(Texture texture, float x, float y) {
        this.texture = texture;
        this.dim = new Rectangle(x, y, 0.35f, 1.1f);
        this.rotation = 0; // Default tilt
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, dim.x, dim.y, dim.width / 2, 0, dim.width, dim.height, 1, 1, rotation, 0, 0, texture.getWidth(), texture.getHeight(), false, false);
    }

    public void setTilt(float angle) {
        this.rotation = angle;
    }

    public void resetTilt() {
        this.rotation = 0; // Reset to default position
    }
    public void setRotation(float angle) {
        this.rotation = angle;
    }


    public float getX() {
        return dim.x;
    }

    public float getY() {
        return dim.y;
    }

    public float getWidth() {
        return dim.width;
    }

    public float getHeight() {
        return dim.height;
    }
}

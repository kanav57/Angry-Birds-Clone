package com.badlogic.drop;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public interface Structure {

    // Method to get the bounds of the structure
    Rectangle getBounds();

    // Method to render the structure
    void render(SpriteBatch batch);

    // Methods to get position and dimensions
    float getX();
    float getY();
    float getWidth();
    float getHeight();

    // Method to dispose of resources
    void dispose();
}

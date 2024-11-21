package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bird {
    protected Texture texture;
    public Rectangle dim;
    private Vector2 velocity;

    public Bird(Texture texture, float x, float y, float width, float height) {
        this.texture = texture;
        this.dim = new Rectangle(x, y, width, height);
        this.velocity = new Vector2(0, 0);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, dim.x, dim.y, dim.width, dim.height);
    }

    public void update(float delta) {
        dim.x += velocity.x * delta;
        dim.y += velocity.y * delta;

        velocity.y -= 9.8f * delta;  // Adjust gravity strength as needed

        if (dim.x < 0 || dim.x + dim.width > 8) {
            velocity.x = -velocity.x;  // Reverse direction on X axis if out of bounds
        }
        if (dim.y < 0 || dim.y + dim.height > 5 || dim.y + dim.height < 1) {
            velocity.y = -velocity.y;  // Reverse direction on Y axis if out of bounds
        }
    }

    public void launch(Vector2 velocity) {
        this.velocity.set(velocity);  // Set the bird's velocity to the given value
    }

    // Method to set the bird's position
    public void setPosition(float x, float y) {
        dim.x = x;
        dim.y = y;
    }

    // Getter methods for position and dimensions
    public float getX() {
        return dim.x;
    }

    public float getY() {
        return dim.y;
    }

    public float getHeight() {
        return dim.height;
    }

    // Dispose method to release resources
    public void dispose() {
        texture.dispose();
    }
}

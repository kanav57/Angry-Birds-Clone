package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Pig {
    protected Texture texture;
    public Rectangle dim;
    private float vx;
    private float vy;
    public float health;

    public Pig(Texture texture,float x,float y,float width,float height) {
        this.texture=texture;
        this.dim=new Rectangle(x,y,width,height);

        this.vx=1.5f;
        this.vy=1.5f;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, dim.x, dim.y, dim.width, dim.height);
    }


    public void update(float delta) {
         dim.x+=vx*delta;
        dim.y+=vy*delta;

         if (dim.x<0||dim.x+dim.width>8) {
            vx=-vx;
        }
        if (dim.y<0||dim.y+dim.height>5) {
            vy=-vy;
        }

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

     public void dispose() {
        texture.dispose();
    }
}

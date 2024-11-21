package com.badlogic.drop;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Button {
    private Texture texture;
    private Rectangle dim;

    private Viewport viewport;
    public Button(Texture texture,float x,float y,float width,float height,Viewport viewport) {
        this.texture=texture;
        this.viewport=viewport;
        dim=new Rectangle(x,y,width,height);
    }
    public void render(SpriteBatch batch) {
        batch.draw(texture,dim.x,dim.y,dim.width,dim.height);
    }

    public boolean isTouched() {
        if (Gdx.input.isTouched()) {
            float X = Gdx.input.getX();
            float Y = Gdx.input.getY();
            Vector3 posn = new Vector3(X, Y, 0);
            viewport.unproject(posn);
              return dim.contains(posn.x,posn.y);
        }
        return false;
    }
     public void dispose() {
        texture.dispose();
    }
    public float getX() {
        return dim.x;
    }
    public float getY() {
        return dim.y;
    }

    public float getHeight() {
        return dim.height;
    }
    public float getWidth() {
        return dim.width;
    }
}

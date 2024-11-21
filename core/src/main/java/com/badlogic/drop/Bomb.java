package com.badlogic.drop;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Bomb extends Bird{
    private Vector2 velocity; // Current velocity of the bird
    public boolean isFlying;
    public static final float GRAVITY = -9.8f; // Gravity constant
    public float newX;
    public float newY;
    public boolean exploded = false;
    public float explosionRadius;
    public static boolean hasAdded;



    public Bomb(Texture texture, float x, float y, float width, float height) {
        super(texture, x, y, width, height);
        this.velocity = new Vector2(0, 0); // Initialize velocity
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
            if(newX>=2f||newY>=2f){
                this.texture= new Texture(Gdx.files.internal("bomb2.png"));
            }
            if(newX>=3.5f||newY>=3.5f){
                this.texture= new Texture(Gdx.files.internal("bomb3.png"));
            }
            setPosition(newX, newY);
            if (newY <= 0.73f || newY >= 5f || newX <= 0 || newX >= 8f) {
                reset();
            }
        }
    }

    public void explode() {
        exploded = true;


    }



    public void reset() {
        if (!state.level2flag && state.level1flag) {
            leveltwo.playtimes++;
        }
        isFlying = false;
        velocity.set(0, 0);
        this.newX = .65f;
        this.newY = 1.7f;
        setPosition(1.3f, .72f);
        this.texture= new Texture(Gdx.files.internal("bomb.png"));
        Music selectSound = Gdx.audio.newMusic(Gdx.files.internal("select.mp3"));
        selectSound.play();

    }
    public float getX() {
        return this.newX;
    }



    }


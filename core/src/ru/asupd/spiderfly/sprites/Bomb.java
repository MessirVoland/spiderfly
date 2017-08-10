package ru.asupd.spiderfly.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Asup.D on 12.12.2016.
 */

public class Bomb {
    private Texture texture_bomb;
    private Vector3 velosity,position;
    private Rectangle bound_bomb;
    private boolean detonation;
    private Animation bombAnimation;
    private static final int MOVEMENT = 100;
    private static final int GRAVITY=-15;
    private int number;
    private float currentTime;
    public Bomb(float x,float y)
    {
        position = new Vector3(x,y,0);
        velosity = new Vector3(0,0,0);
        currentTime=0;
        detonation=false;
        texture_bomb = new Texture("bomb_anim2.png");
        bombAnimation = new Animation(new TextureRegion(texture_bomb),4,1.5f);
        bound_bomb = new Rectangle(x,y,texture_bomb.getWidth()/4,texture_bomb.getHeight());
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getBomb() {
        return bombAnimation.getFrames();
    }

    public boolean isDetonation() {
        return detonation;
    }

    public void bounced()
    {
        velosity.x=0-velosity.x;
        velosity.y=0-velosity.y;
    }

    public void update (float dt)
    {
        currentTime+=dt;
        if (currentTime>=1.5f)
        {
            detonation=true;

        }
        bombAnimation.update(dt);
        velosity.add(0,GRAVITY,0);
        velosity.scl(dt);
        position.add(0,velosity.y,0);
        if (position.y<0){
            position.y=0;
        }
        velosity.scl(1/dt);
        bound_bomb.setPosition(position.x,position.y);
    }

    public Rectangle getBound_bomb() {
        return bound_bomb;
    }

    public void dispose()
    {

        texture_bomb.dispose();
        System.out.println("The Bomb has been disposed()");
    }
}

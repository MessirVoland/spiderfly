package ru.asupd.spiderfly.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Asup.D on 23.12.2016.
 */

public class Snow extends Creature {
    private Texture texture_snow;
    private Vector3 velosity,position;
    private Rectangle bound_snow;
   // private Animation bombAnimation;
    //private static final int MOVEMENT = 100;
   // private static final int GRAVITY=-15;
   // private int number;
   // private float currentTime;
    public Snow(int x,int y) {
        position = new Vector3(x,y,0);
        velosity = new Vector3(0,0,0);
        texture_snow = new Texture("snow.png");
        bound_snow = new Rectangle(x+5,y+5,texture_snow.getWidth()-5,texture_snow.getHeight()-5);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture_snow() {

        return texture_snow;
    }

    public boolean collision (Rectangle player)
    {
        return player.overlaps(bound_snow);
    }

    @Override
    public void update(float dt) {
        velosity.scl(dt);
        position.add(velosity.x,velosity.y,0);
        velosity.scl(1/dt);
        bound_snow.setPosition(position.x,position.y);
    }

    @Override
    public void dispose() {

    }
}

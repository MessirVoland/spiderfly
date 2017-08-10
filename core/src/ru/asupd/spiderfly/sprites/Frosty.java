package ru.asupd.spiderfly.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Asup.D on 23.12.2016.
 */

public class Frosty extends Creature {

    private Texture texture_frosty;
    private Vector3 velosity,position;
    private Rectangle bound_frosty;
    private boolean detonation;
    private Animation frostyAnimation;
    private float currentTime;
    public Frosty(int x,int y){
        position = new Vector3(x,y,0);
        texture_frosty = new Texture("frosty.png");
        frostyAnimation = new Animation(new TextureRegion(texture_frosty),70,7.0f);
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getFrosty() {
        return frostyAnimation.getFrames();
    }

    @Override

    public void update(float dt) {
        frostyAnimation.update(dt);
    }

    @Override
    public void dispose() {

    }
}

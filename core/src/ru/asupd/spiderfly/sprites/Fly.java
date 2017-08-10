package ru.asupd.spiderfly.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Asup.D on 23.12.2016.
 */

public class Fly extends Creature {
    private Vector3 velosity,position;
    private Texture texture_fly;
    private Texture texture_fly_snow;
    private Rectangle bound_fly;
    private Boolean snow,snowed;
    private int speed_modificator;

    public Vector3 getPosition() {
        return position;
    }

    public void setSnow(Boolean snow) {
        this.snow = snow;
    }

    public Texture getTexture_fly() {
        if (snow)
        {
            return texture_fly_snow;
        }
        else {
            return texture_fly;
        }
    }

    public Vector3 getVelosity() {
        return velosity;
    }

    public Rectangle getBound_fly() {

        return bound_fly;
    }

    public Fly(float x, float y, float coin_x, float coin_y){
        position = new Vector3(x,y,0);
        double a = Math.sqrt((coin_x*coin_x)+(coin_y*coin_y));
        float speed;
        speed_modificator=300;
        speed = (float) a/speed_modificator;//пиздец жесткач))
        snow =false;
        snowed=true;
        velosity = new Vector3(((coin_x-x)/speed),((coin_y-y)/speed),0);//2 это 2 секунды до монеты
        texture_fly = new Texture("Bee-icon.png");
        texture_fly_snow= new Texture("Bee-icon-snow.png");

        if (snow){
            bound_fly = new Rectangle(x+10,y,texture_fly_snow.getWidth()-20,texture_fly_snow.getHeight());
        }
        else {
            bound_fly = new Rectangle(x + 10, y, texture_fly.getWidth() - 20, texture_fly.getHeight());
        }
    }
    @Override
    public void update(float dt) {
        velosity.scl(dt);

        if ((snow)&&(snowed)){
            snowed=false;
            velosity.x=velosity.x/2;
            velosity.y=velosity.y/2;
        }

            position.add(velosity.x, velosity.y, 0);

        velosity.scl(1/dt);
        bound_fly.setPosition(position.x,position.y);
    }

    @Override
    public void dispose() {

    }
}

package ru.asupd.spiderfly.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

/**
 * Created by Asup.D on 13.12.2016.
 */

public class Bee {
    private Texture bee;
    private Vector3 velosity,position;
    private Rectangle bound_bee;
    private Random random;
    private Boolean moveable;

    public Bee (int x,int y,float spider_x,float spider_y){
        random = new Random();
        position = new Vector3(x,random.nextInt(300),0);
       // velosity = new Vector3(random.nextInt(200)+100,random.nextInt(150)-75,0);
        int ter;
        ter= random.nextInt(2)+2;
        velosity = new Vector3((spider_x-x)/ter,(spider_y-y)/ter,0);
        bee = new Texture("bee2.png");
        moveable=true;
        bound_bee = new Rectangle(x+10,y+10,bee.getWidth()-10,bee.getHeight()-10);
    }
    public void move(){
        moveable = true;
    }
    public void stop(){
        moveable = false;
    }
    public Texture getBee() {
        return bee;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Rectangle getBound_bee() {
        return bound_bee;
    }

    public void update(float dt)
    {
        if (moveable) {
            velosity.scl(dt);
            position.add(velosity.x, velosity.y, 0);
            velosity.scl(1 / dt);
            bound_bee.setPosition(position.x, position.y);
        }

    }

    public Vector3 getVelosity() {
        return velosity;
    }

    public void dispose()
    {
        bee.dispose();
    }
}

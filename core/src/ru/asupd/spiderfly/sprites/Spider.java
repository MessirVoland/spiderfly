package ru.asupd.spiderfly.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Asup.D on 09.12.2016.
 */

public class Spider {
    private Texture spider;
    private Texture spider_snow;
    private Vector3 velosity,position;
    private Rectangle bound_spider;
    private static final int MOVEMENT = 100;
    private static final int GRAVITY=-15;
    private Boolean snow;
    private float currentTime;


    public void setSnow(Boolean snow) {
        currentTime=0;
        this.snow = snow;
    }

    public Spider(int x, int y)
    {
        position = new Vector3(x,y,0);
        velosity = new Vector3(0,0,0);
        currentTime=0;
        spider = new Texture("spider-2-icon.png");
        spider_snow = new Texture("spider-2-icon-snow.png");
        snow = false;

        bound_spider = new Rectangle(x,y,spider.getWidth(),spider.getHeight());
    }

    public Vector3 getPosition() {
        return position;
    }

    public Rectangle getBound_spider() {
        return bound_spider;
    }

    public Texture getSpider() {
        if (snow){
            return spider_snow;

        }else {
            return spider;
        }
    }
    public void trycatchfly()
    {
        System.out.println("catch");
        velosity.y=250;
    }
    public void  move_acc(float acc)
    {
        if (snow){
            velosity.x = acc * 15;
        }else {
            velosity.x = acc * 30;
        }
    }
    public boolean collision (Rectangle player)
    {
        return player.overlaps(bound_spider);
    }
    public void move_left()
    {
        System.out.println("move left");
       // position.x-=10;
        velosity.x=-200;
    }
    public void move_right()
    {
        System.out.println("move right");
       // position.x+=10;
        velosity.x=200;
    }
    public void update(float dt)
    {
     //   velosity.add(0,GRAVITY,0);

        if (snow){
            currentTime+=dt;
            if (currentTime >7.0f){
                snow=false;
            }
        }
        velosity.add(0,0,0);
        velosity.scl(dt);
       // position.add(MOVEMENT*dt,velosity.y,0);
      //  position.add(0,velosity.y,0);
        position.add(velosity.x,0,0);
        velosity.scl(1/dt);

        if (position.x<=0){
            //velosity.x=200;
            position.x=0;
        }
        if (position.x>=736){
            //velosity.x=-200;
            position.x=736;
        }
        bound_spider.setPosition(position.x,position.y);
    }
}

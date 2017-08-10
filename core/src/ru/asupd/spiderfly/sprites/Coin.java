package ru.asupd.spiderfly.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Asup.D on 13.12.2016.
 */

public class Coin {
    private Texture coin;
    private Vector3 velosity,position;
    private Rectangle bound_coin;

    public Vector3 getPosition() {
        return position;
    }

    public void setVelosity(Vector3 velosity) {
        this.velosity = velosity;
    }

    public Texture getCoin() {

        return coin;

    }

    public Coin(int x, int y)
    {
        position = new Vector3(x,y,0);
        velosity = new Vector3(0,0,0);
        coin = new Texture("Bitcoin-64x64.png");
        bound_coin = new Rectangle(x+5,y+5,coin.getWidth()-5,coin.getHeight()-5);
    }
    public boolean collision (Rectangle player)
    {
        return player.overlaps(bound_coin);
    }
    public void update(float dt)
    {
        velosity.scl(dt);
        position.add(velosity.x,velosity.y,0);
        velosity.scl(1/dt);
        bound_coin.setPosition(position.x,position.y);
    }
    public void dispose()
    {
        coin.dispose();
    }

}


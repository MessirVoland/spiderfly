package ru.asupd.spiderfly.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Asup.D on 10.04.2017.
 */

public class Green_leaf extends Creature {
    private Texture texture_green_leaf;

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture_green_leaf() {

        return texture_green_leaf;
    }

    private Vector3 velosity,position;
    private Rectangle bound_green_leaf;
    public Green_leaf(int x,int y) {
        position = new Vector3(x,y,0);
        texture_green_leaf = new Texture("leaf.png");
        bound_green_leaf=new Rectangle(x,y,texture_green_leaf.getWidth(),texture_green_leaf.getHeight());
    }
    public boolean collision (Rectangle player)
    {
        return player.overlaps(bound_green_leaf);
    }
    @Override
    public void update(float dt) {

    }

    @Override
    public void dispose() {

    }
}

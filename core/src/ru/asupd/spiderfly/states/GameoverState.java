package ru.asupd.spiderfly.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import ru.asupd.spiderfly.workers.Json_worker;


/**
 * Created by Asup.D on 13.12.2016.
 */

public class GameoverState extends State implements InputProcessor {
    private BitmapFont font;
    private Texture texture;
    private Texture sender;
    private int clicker;
    private Json_worker jsoner;
    private boolean result_wiew;
    float result_time;
    String table="Loading results...";
    String send_rec="Click button to send your result: ";
    Boolean send_log;
    public GameoverState(GameStateManager gsm) {
        super(gsm);
        Gdx.input.setInputProcessor(this);
        font = new BitmapFont();
        texture =new Texture("gameover.jpg");
        sender =new Texture("send.jpg");
        result_wiew=true;
        send_rec +=GameState.score;
        result_time=0.0f;
        clicker =0;
        send_log=true;

    }
    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            clicker += 1;
            if (clicker >= 4) {
                gsm.set(new GameState(gsm));
            }
        }
    }

    @Override
    public void update(float dt) {
        //handleInput();
        result_time+=dt;
        if ((result_time>0.1f)&&(result_wiew)){
            result_wiew=false;
            jsoner = new Json_worker();
            table = jsoner.execute_json(jsoner.getResultJson());
        }
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        camera.setToOrtho(false,800,480);
        sb.begin();
        sb.draw(texture,0,0);
        font.draw(sb,"click some times to restart: " +clicker,600,480);
        font.draw(sb,table,0,480);
        font.draw(sb,send_rec,0,125);
        sb.draw(sender,0,0);
        sb.end();
    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if ((screenX>200)&&(screenY>100)) {
            clicker += 1;
            if (clicker >= 4) {
                gsm.set(new GameState(gsm));
            }
        }
        else
        {
            //send_result;

            if (send_log) {
                send_log=false;
                send_rec = "Your record was SENT";
                jsoner.package_json("Cтрока");
            }
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}

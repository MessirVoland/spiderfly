package ru.asupd.spiderfly.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by Asup.D on 09.12.2016.
 */

public class MenuState extends State implements InputProcessor {
    Stage stage;
    private BitmapFont font;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setInputProcessor(this);
        stage = new Stage(new ScreenViewport());
        System.out.println("Menu started");
        font = new BitmapFont();
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()){
            System.out.println(" menu justToched");
            //gsm.set(new GameState(gsm));
            //gsm.set(new GameoverState(gsm));


        }

    }

    @Override
    public void update(float dt) {
      //  handleInput();
      /*  float accelX = Gdx.input.getAccelerometerX();
        float accelY = Gdx.input.getAccelerometerY();
        float accelZ = Gdx.input.getAccelerometerZ();
        System.out.println(accelX+" X");
        System.out.println(accelY+" Y");
        System.out.println(accelZ+" Z");*/

    }

    @Override
    public void render(SpriteBatch sb) {
        camera.setToOrtho(false,800,480);
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        font.draw(sb,"Touch the screen to start game",200,100);
        sb.end();

    }

    @Override
    public void dispose() {
        stage.dispose();
        System.out.println("Menu disposed");

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
        System.out.println("Menu mouse Toched");
        gsm.set(new GameState(gsm));
        //gsm.set(new GameoverState(gsm));

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

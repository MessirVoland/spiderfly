package ru.asupd.spiderfly;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import ru.asupd.spiderfly.states.GameState;
import ru.asupd.spiderfly.states.GameStateManager;
import ru.asupd.spiderfly.states.MenuState;

public class spiderfly  extends ApplicationAdapter  {
	Texture img;
	private GameStateManager gsm;
	private SpriteBatch batch;
	public static final int HEIGHT=480;
	public static final int WIDTH=800;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("spider-2-icon.png");
		gsm = new GameStateManager();
		/*boolean available = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);
		if (available)
		{
			System.out.println("Accelerometer ON");
		} else
		{
			System.out.println("Accelerometer OFF");
		}*/

		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//batch.begin();
		//batch.draw(img, 0, 0);
		//batch.end();
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		super.dispose();
	}
}

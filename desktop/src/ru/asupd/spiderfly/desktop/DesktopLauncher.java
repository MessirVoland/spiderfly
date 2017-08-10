package ru.asupd.spiderfly.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.asupd.spiderfly.spiderfly;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=spiderfly.WIDTH;
		config.height=spiderfly.HEIGHT;
		new LwjglApplication(new spiderfly(), config);
	}
}

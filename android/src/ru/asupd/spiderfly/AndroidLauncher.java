package ru.asupd.spiderfly;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import ru.asupd.spiderfly.spiderfly;

public class AndroidLauncher extends AndroidApplication {
	//public static int HEIGHT=480;
	//public static int WIDTH=800;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new spiderfly(), config);
	}
}

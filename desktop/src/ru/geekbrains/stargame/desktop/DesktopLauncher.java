package ru.geekbrains.stargame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import ru.geekbrains.stargame.Star2DGame;
import ru.geekbrains.stargame.StarGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "IvanezStarGame";
//		config.width = 480;
//		config.height = 320;
		new LwjglApplication(new Star2DGame(), config);
	}
}

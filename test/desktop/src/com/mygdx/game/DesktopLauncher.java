package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("MP3");
		config.setWindowedMode(1760, 1080);
		new Lwjgl3Application(new gdxGame(), config);
	}
	public String toString(){
		return "OVERRIDEN";
	}
	public boolean equals(Object obj){
		return true;
	}
}

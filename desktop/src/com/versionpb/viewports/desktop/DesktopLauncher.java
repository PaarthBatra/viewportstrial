package com.versionpb.viewports.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.versionpb.viewports.GameInfo;
import com.versionpb.viewports.Viewportstrial;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int)GameInfo.Desktop_GAME_WIDTH;
		config.height = (int)GameInfo.Desktop_GAME_HEIGHT;
		new LwjglApplication(new Viewportstrial(), config);
	}
}

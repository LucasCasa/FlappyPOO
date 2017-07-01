package desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import music.MenuMusic;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Flappy.WIDTH;
		config.height = Flappy.HEIGHT;
		config.title = Flappy.TITLE;
		config.resizable = false;
		config.foregroundFPS = 60;
		config.backgroundFPS = 60;

		config.addIcon("icon128.png", Files.FileType.Local);
		config.addIcon("icon32.png", Files.FileType.Local);
		config.addIcon("icon16.png", Files.FileType.Local);
		new LwjglApplication(new Flappy(), config);
		MenuMusic m = MenuMusic.getInstance();
		m.play(1);
		/*try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
	}
}

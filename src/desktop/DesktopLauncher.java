package desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import component.worldComponent.Types;
import world.music.Music;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Flappy.WIDTH;
		config.height = Flappy.HEIGHT;
		config.title = Flappy.TITLE;
		new LwjglApplication(new Flappy(), config);
		
		Music m = new Music(Types.MAIN_THEME);
		m.play(0.5f);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		m.fadeOut();

	}
}

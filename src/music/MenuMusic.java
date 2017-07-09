package music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import component.worldComponent.Types;

public class MenuMusic extends Music{
	private static MenuMusic instance = new MenuMusic();
	private MenuMusic() {
		s = Gdx.audio.newSound(Gdx.files.internal(Types.MAIN_THEME));
	}

	public static MenuMusic getInstance() {
		return instance;
	}
}

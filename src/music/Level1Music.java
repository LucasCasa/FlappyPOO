package music;

import com.badlogic.gdx.audio.Sound;

import component.worldComponent.Types;

public class Level1Music extends Music {

	private static Level1Music instance = null;

	private Level1Music() {
		s = Types.LEVEL1_THEME;
	}

	public static Level1Music getInstance() {
		if (instance == null) {
			instance = new Level1Music();
		}
		return instance;
	}
}

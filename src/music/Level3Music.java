package music;

import com.badlogic.gdx.audio.Sound;

import component.worldComponent.Types;

public class Level3Music extends Music {
	
	private static Level3Music instance = new Level3Music();

	private Level3Music(){
		s = Types.LEVEL3_THEME;
	}
	
	public static Level3Music getInstance(){
		return instance;
	}
}

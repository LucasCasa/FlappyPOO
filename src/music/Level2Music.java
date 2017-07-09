package music;


import component.worldComponent.Types;

public class Level2Music extends Music {
	
	private static Level2Music instance = new Level2Music();

	private Level2Music(){
		s = Types.LEVEL2_THEME;
	}
	
	public static Level2Music getInstance(){
		return instance;
	}
}

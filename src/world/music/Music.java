package world.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Music {
	
	public static void playSound(String path, Float volume ){
		Sound s = Gdx.audio.newSound(Gdx.files.internal(path));
		s.play(volume);
	}

}

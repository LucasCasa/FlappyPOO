package world.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Music {

	public Sound s;
	public long id;
	public float volume;
	private static float MAX_VOLUME = 0.5f;

	public Music(String path) {
		if (this.volume > MAX_VOLUME) {
			this.volume = MAX_VOLUME;
		}
		s = Gdx.audio.newSound(Gdx.files.internal(path));
		
	}
		
	public void play(float volume){
		id = s.play(volume);
		this.volume = volume;
		System.out.println(id);
	}
	
	public static void stopMusic(Sound s) {
		s.stop();
	}

	public void reduceVolume(float volume){
		s.setVolume(id, volume);
	}
	
	public void fadeOut() {
		float vol_fade = volume;
		System.out.println(vol_fade);
		for (float i = volume; i >= 0; i -= 0.0000009) {
			System.out.println(i);
			reduceVolume(i);
			if (i <= 0)
				s.stop();
		}
	}

}

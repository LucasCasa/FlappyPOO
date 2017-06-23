package world.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import component.worldComponent.Types;

public class MenuMusic implements Music{
	private static MenuMusic instance = new MenuMusic();
	private Sound s;
	private long id;
	private float volume;
	private static float MAX_VOLUME = 0.5f;

	private MenuMusic() {
		s = Gdx.audio.newSound(Gdx.files.internal(Types.MAIN_THEME));
	}

	public static MenuMusic getInstance() {
		return instance;
	}

	public void play(float volume){
		id = s.play(volume);
		this.volume = volume;
		System.out.println(id);
	}
	
	public void stop() {
		s.stop();
	}

	public void reduceVolume(float volume){
		s.setVolume(id, volume);
	}
	
	public void fadeOut() {
		float vol_fade = volume;
		System.out.println(vol_fade);
		for (float i = volume; i >= 0; i -= 0.01) {
			System.out.println(i);
			reduceVolume(i);
			if (i <= 0)
				s.stop();
			try {
				Thread.sleep(30);
			}catch (Exception e){
				e.printStackTrace();
			}

		}
	}

}

package world.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import component.worldComponent.Types;

public class Level2Music implements Music {
	
	private static Level2Music instance = new Level2Music();
	private Sound s;
	private long id = -1;
	private float volume;
	private boolean playing = false;
	private static float MAX_VOLUME = 0.5f;

	private Level2Music(){
		s = Types.LEVEL2_THEME;
	}
	
	public static Level2Music getInstance(){
		return instance;
	}
	@Override
	public void fadeOut() {
		playing = false;
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

	@Override
	public void reduceVolume(float v) {
		this.volume = v;
		s.setVolume(id, v);
	}

	@Override
	public void play(float v) {
		if(id == -1){
			id = s.loop(v);
			volume = v;
		}
		playing = true;
	}

	@Override
	public void stop() {
		playing = false;
		s.stop();
	}

    public boolean isPlaying() {
        return playing;
    }
}

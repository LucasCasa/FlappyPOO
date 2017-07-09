package music;

import com.badlogic.gdx.audio.Sound;

import component.worldComponent.Types;

public class Level1Music implements Music {

	private static Level1Music instance = null;
	private Sound s;
	private long id = -1;
	private float volume;
	private boolean playing = false;
	private static float MAX_VOLUME = 0.5f;
	
	private Level1Music() {
		s = Types.LEVEL1_THEME;
	}

	public static Level1Music getInstance() {
		if (instance == null) {
			instance = new Level1Music();
		}
		return instance;
	}

	@Override
	public void fadeOut() {

		float vol_fade = volume;
		playing = false;
		for (float i = volume; i >= 0; i -= 0.01) {
			reduceVolume(i);
			if (i <= 0) {
				s.stop();

			}
			try {
				Thread.sleep(30);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		id = -1;
	}

	@Override
	public void reduceVolume(float v) {
		this.volume = v;
		s.setVolume(id, v);
	}

	@Override
	public void play(float v) {
		System.out.println("PLAY");
		if (id == -1) {
			System.out.println("HICE PLAY");
			id = s.loop(v);
			volume = v;
		}else {
			System.out.println("YA TENIA PLAY");
		}
		playing = true;
	}

	@Override
	public void stop() {
		playing = false;
		s.stop();
		id=-1;
	}

    public boolean isPlaying() {
        return playing;
    }

    public void setPitch(float p){
		s.setPitch(id,p);
	}
}

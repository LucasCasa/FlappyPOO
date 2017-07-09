package music;

import com.badlogic.gdx.audio.Sound;

import component.worldComponent.Types;

public class Level3Music implements Music {
	
	private static Level3Music instance = new Level3Music();
	private Sound s;
	private long id = -1;
	private float volume;
	private boolean playing = false;
	private static float MAX_VOLUME = 0.5f;

	private Level3Music(){
		s = Types.LEVEL3_THEME;
	}
	
	public static Level3Music getInstance(){
		return instance;
	}
	@Override
	public void fadeOut() {
		playing = false;
		float vol_fade = volume;
		for (float i = volume; i >= 0; i -= 0.01) {
			reduceVolume(i);
			if (i <= 0) {
				s.stop();
				id = -1;
			}
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
		id=-1;
		s.stop();
	}

    public boolean isPlaying() {
        return playing;
    }


	public void setPitch(float p){
		s.setPitch(id,p);
	}
}

package music;

import com.badlogic.gdx.audio.Sound;

/**
 * Created by Lucas on 23/06/2017.
 */
public abstract class Music {
    protected Sound s;
    private long id;
    private float volume;
    boolean playing = false;

    public void play(float volume){
        id = s.loop(volume);
        this.volume = volume;
        playing = true;
    }
    public void stop() {
        s.stop();
        id=-1;
        playing = false;
    }

    public void reduceVolume(float volume){
        s.setVolume(id, volume);
        this.volume = volume;
    }

    public void fadeOut() {
        float vol_fade = volume;
        playing = false;
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

    public boolean isPlaying() {
        return playing;
    }


    public void setPitch(float p){
        s.setPitch(id,p);
    }
}

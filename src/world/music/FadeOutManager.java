package world.music;

/**
 * Created by Lucas on 23/06/2017.
 */
public class FadeOutManager implements Runnable {
    Music m;
    public FadeOutManager(Music m){
        this.m = m;
    }
    @Override
    public void run() {
        m.fadeOut();
    }
}

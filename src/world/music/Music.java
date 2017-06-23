package world.music;

/**
 * Created by Lucas on 23/06/2017.
 */
public interface Music {

    void fadeOut();
    void reduceVolume(float v);
    void play(float v);
    void stop();
}

package world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import component.bird.BirdType;
import component.worldComponent.Types;
import music.Mode2Music;

/**
 * Created by Lucas on 01/07/2017.
 */
public class CountdownWorldManager extends WorldManager {
    private float time = 60;
    /**
     * Crea al mundo y sus componentes. Crea de forma aleatoria las posiciones de los corazones.
     * Consulta a los settings del mundo cuantos corazones debe poner y cuantas bombas debe poner.
     *
     */
    public CountdownWorldManager(OrthographicCamera cam, String p1Name, String p2Name, BirdType p1Bird, BirdType p2Bird) {
        super(cam, p1Name, p2Name, p1Bird, p2Bird);

    }

    public void update(float dt, float pos, float width) {
        time-=dt;
        super.update(dt,pos,width);

    }
    @Override
    protected boolean hasEnded() {
        return time <= 0;
    }

    @Override
    protected void endGame() {
        int rScore = bRight.getScore();
        int lScore = bLeft.getScore();
        if(rScore > lScore){
            Types.PLAYER2_VOICE[Integer.parseInt(Types.MESSAGES.getString("id"))].play(Types.masterVolume);
            contPlay = false;
        }else if(lScore > rScore){
            Types.PLAYER1_VOICE[Integer.parseInt(Types.MESSAGES.getString("id"))].play(Types.masterVolume);
            contPlay = false;
        }else {
            Types.DRAW[Integer.parseInt(Types.MESSAGES.getString("id"))].play(Types.masterVolume);
            contPlay = false;
        }
        Mode2Music.getInstance().stop();
    }

    float getTime() {
        return time;
    }
}

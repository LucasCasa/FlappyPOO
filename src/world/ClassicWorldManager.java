package world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import component.bird.BirdType;
import component.worldComponent.Types;
import music.*;
import scoreFiles.Output;

/**
 * Created by Lucas on 01/07/2017.
 */
public class ClassicWorldManager extends WorldManager{

    /**
     * Crea al mundo y sus componentes. Crea de forma aleatoria las posiciones de los corazones.
     * Consulta a los settings del mundo cuantos corazones debe poner y cuantas bombas debe poner.
     *
     * @param cam
     * @param p1Name
     * @param p2Name
     * @param p1Bird
     * @param p2Bird
     */
    public ClassicWorldManager(OrthographicCamera cam, String p1Name, String p2Name, BirdType p1Bird, BirdType p2Bird) {
        super(cam, p1Name, p2Name, p1Bird, p2Bird);
        loadBombs();
        loadLives();
        loadTubes();
    }


    public void update(float dt, float pos, float width) {
        updateTubes(pos,width);
        super.update(dt,pos,width);

    }


    @Override
    protected boolean hasEnded() {
        return getBLeft().getLife() <= 0 || getBRight().getLife() <= 0;
    }

    @Override
    protected void endGame() {
        level++;
        if(bRight.getScore() <= 0){
            pLeftScore++;
            if(pLeftScore == 2){
                Output.getInstance().write(getWinner(bLeft, bRight));
                Types.PLAYER1_VOICE.play(Types.masterVolume);
                Level2Music.getInstance().stop();
                Level3Music.getInstance().stop();
                MenuMusic.getInstance().play(Types.masterVolume);
                contPlay = false;
                return;
            }
        }else{
            pRightScore++;
            if(pRightScore == 2){
                Output.getInstance().write(getWinner(bLeft, bRight));
                Level2Music.getInstance().stop();
                Level3Music.getInstance().stop();
                MenuMusic.getInstance().play(Types.masterVolume);
                contPlay = false;
                Types.PLAYER2_VOICE.play(Types.masterVolume);
                return;
            }
        }
        switch(level){
            case 2:
                (new Thread(new FadeOutManager(Level1Music.getInstance()))).start();
                Level2Music.getInstance().play(Types.masterVolume);
                Types.LEVEL2_VOICE.play(Types.masterVolume);
                break;
            case 3:
                (new Thread(new FadeOutManager(Level2Music.getInstance()))).start();
                Level3Music.getInstance().play(Types.masterVolume);
                Types.LEVEL3_VOICE.play(Types.masterVolume);
                break;
        }
        loadLevel();
        bRight.reset();
        bLeft.reset();
    }
}

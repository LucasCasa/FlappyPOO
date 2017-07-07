package world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import component.worldComponent.Types;

/**
 * Created by Lucas on 01/07/2017.
 */
public class CountdownWorldManagerView extends WorldManagerView{
    boolean play = false;
    public CountdownWorldManagerView(WorldManager world) {
        super(world);
    }

    @Override
    public void render(SpriteBatch sb, float pos, float width) {
        super.render(sb, pos, width);
        t1.drawCoins(sb, pos, w.getBRight().getScore(), 200);
        t1.drawCoins(sb, pos, w.getBLeft().getScore(), -200);
        int time = (int)(((CountdownWorldManager)w).getTime()) + 1;
        t1.drawTime(sb,pos,time);
        if(time == 5 && !play){
            Types.COUNTDOWN[Integer.parseInt(Types.MESSAGES.getString("id"))].play(Types.masterVolume);
            play = true;
        }
    }
}

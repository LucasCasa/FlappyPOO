package world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Lucas on 01/07/2017.
 */
public class CountdownWorldManagerView extends WorldManagerView{
    public CountdownWorldManagerView(WorldManager world) {
        super(world);
    }

    @Override
    public void render(SpriteBatch sb, float pos, float width) {
        super.render(sb, pos, width);
        t1.drawCoins(sb, pos, w.getBRight().getScore(), 200);
        t1.drawCoins(sb, pos, w.getBLeft().getScore(), -200);
        t1.drawTime(sb,pos,(int)(((CountdownWorldManager)w).getTime()) + 1);
    }
}

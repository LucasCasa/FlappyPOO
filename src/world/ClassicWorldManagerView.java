package world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Lucas on 01/07/2017.
 */
public class ClassicWorldManagerView extends WorldManagerView {
    public ClassicWorldManagerView(WorldManager world) {
        super(world);
    }

    @Override
    public void render(SpriteBatch sb, float pos, float width) {
        super.render(sb, pos, width);
        t1.drawScore(sb,pos,w.getBLeft().getName(),w.getLeftScore(),w.getBRight().getName(),w.getRightScore());

        t1.drawLife(sb, pos, w.getBLeft().getLife(), -280);
        t1.drawMana(sb, pos, w.getBLeft().getScore(), -280);

        t1.drawLife(sb, pos, w.getBRight().getLife(), 200);
        t1.drawMana(sb, pos, w.getBRight().getScore(), 200);
    }
}

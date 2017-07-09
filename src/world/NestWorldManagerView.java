package world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import component.bird.*;
import component.bullet.Bullet;
import component.bullet.BulletView;
import component.worldComponent.Types;

/**
 * Created by Lucas on 08/07/2017.
 */
public class NestWorldManagerView extends WorldManagerView {
    BulletView bv;
    public NestWorldManagerView(WorldManager world) {
        super(world);
        bv = new BulletView(Types.ENEMY_BULLET);
        bird2View = selectBirdSkin(w.getBRight());
        birdView = selectBirdSkin(w.getBLeft());
        bv1 = bulletSkin(w.getBLeft());
        bv2 = bulletSkin(w.getBRight());
    }

    private BirdView selectBirdSkin(Bird b) {
        BirdView birdView = null;
        if (b instanceof BirdLeft) {
            if (b instanceof RedBird) {
                birdView = new BirdView(Types.RED_BIRD,b.getName());
            } else if (b instanceof GreenBird) {
                birdView = new BirdView(Types.GREEN_BIRD,b.getName());
            } else if (b instanceof SilverBird) {
                birdView = new BirdView(Types.SILVER_BIRD,b.getName());
            } else {
                birdView = new BirdView(Types.BLUE_BIRD,b.getName());
            }
        }
        return birdView;
    }

    private BulletView bulletSkin(Bird b) {
        BulletView bv = null;
            if (b instanceof RedBird) {
                bv = new BulletView(Types.FIRE_BULLET);
            } else if (b instanceof GreenBird) {
                bv = new BulletView(Types.LASER_BULLET);
            } else if (b instanceof SilverBird) {
                bv = new BulletView(Types.SILVER_BULLET);
            } else {
                bv = new BulletView(Types.CANNON_BULLET);
            }
            return bv;
    }

    @Override
    public void render(SpriteBatch sb, float pos, float width) {
        super.render(sb, pos, width);
        for(Bullet b : ((NestWorldManager)w).bullets){
            bv.draw(sb,b);
        }
        t1.drawLife(sb,pos,((NestWorldManager)w).lives,-200);
        t1.drawTime(sb,pos,(int)((NestWorldManager)w).time);
        //t1.drawKills(sb,pos,((NestWorldManager)w).p1Kills,-300);
        //t1.drawKills(sb,pos,((NestWorldManager)w).p2Kills,200);
    }
}

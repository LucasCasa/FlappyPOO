package component.bullet;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Lucas on 08/07/2017.
 */
public class EnemyBullet extends BulletRight {

    private static final int W = 32;
    private static final int H = 31;

    /**
     * Instantiates a new bullet.
     *
     * @param x     posicion en x
     * @param y     posicion en y
     * @param speed velocidad con la que sale disparada la bala
     */
    public EnemyBullet(float x, float y, float speed) {
        super(x, y, speed);
        super.bounds = new Rectangle(super.position.x, super.position.y, W, H);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        super.bounds.setPosition(position.x, position.y);
    }



}

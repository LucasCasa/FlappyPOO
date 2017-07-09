package component.bullet;

import com.badlogic.gdx.math.Rectangle;
import component.worldComponent.Types;

public class FireBullet extends Bullet {

	private static final int W = 53;
	private static final int H = 35;
	
	public FireBullet(float x, float y) {
		super(x, y, Types.FIRE_BULLET_SPEED);
		super.bounds = new Rectangle(super.position.x, super.position.y, W, H);
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
		super.bounds.setPosition(position.x, position.y);
	}
	

}

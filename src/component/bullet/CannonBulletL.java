package component.bullet;

import com.badlogic.gdx.math.Rectangle;

import component.worldComponent.Types;

public class CannonBulletL extends BulletLeft{

	private static final int W = 32;
	private static final int H = 21;

	public CannonBulletL(float x, float y) {
		super(x, y, Types.CANNON_BULLET_SPEED);
		bounds = new Rectangle(position.x, position.y, W, H);
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
		super.bounds.setPosition(position.x, position.y);
	}

}

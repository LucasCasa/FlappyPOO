package component.bullet;

import com.badlogic.gdx.math.Rectangle;

import component.worldComponent.Types;

public class FireBulletR extends BulletRight {

	private static final int W = 38;
	private static final int H = 10;
	
	public FireBulletR(float x, float y) {
		super(x, y, Types.FIRE_BULLET_SPEED*-1);
		super.bounds = new Rectangle(super.position.x, super.position.y, W, H);
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
		super.bounds.setPosition(position.x, position.y);
	}
	

}

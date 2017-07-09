package component.bullet;

import com.badlogic.gdx.math.Rectangle;

import component.worldComponent.Types;

public class LaserBulletR extends BulletRight {

	private static final int W = 26;
	private static final int H = 17;
	
	public LaserBulletR(float x, float y) {
		super(x, y, Types.LASER_BULLET_SPEED*-1);
		bounds = new Rectangle(position.x, position.y, W, H);
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
		super.bounds.setPosition(position.x, position.y);
	}


}

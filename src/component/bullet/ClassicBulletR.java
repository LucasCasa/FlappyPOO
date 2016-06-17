package component.bullet;

import com.badlogic.gdx.math.Rectangle;

import component.worldComponent.Types;

public class ClassicBulletR extends BulletRight {

	private static final int W = 32;
	private static final int H = 21;

	public ClassicBulletR(float x, float y) {
		super(x, y, Types.CLASSIC_BULLET_SPEED * -1);
		bounds = new Rectangle(position.x, position.y, W, H);
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
		super.bounds.setPosition(position.x, position.y);
	}

}

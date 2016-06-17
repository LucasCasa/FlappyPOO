package component.bullet;

import com.badlogic.gdx.math.Rectangle;

import component.worldComponent.Types;

public class SilverBulletL extends BulletLeft {

	private static final int W = 50;
	private static final int H = 9;
	
	public SilverBulletL(float x, float y) {
		super(x, y, Types.SILVER_BULLET_SPEED);
		bounds = new Rectangle(position.x, position.y, W, H);
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
		super.bounds.setPosition(position.x, position.y);
	}

}

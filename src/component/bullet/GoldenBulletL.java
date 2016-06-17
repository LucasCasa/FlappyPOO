package component.bullet;

import com.badlogic.gdx.math.Rectangle;

import component.worldComponent.Types;

public class GoldenBulletL extends BulletLeft {

	private static final int W = 50;
	private static final int H = 8;
	
	public GoldenBulletL(float x, float y) {
		super(x, y, Types.GOLDEN_BULLET_SPEED);
		bounds = new Rectangle(position.x, position.y, W, H);
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
		super.bounds.setPosition(position.x, position.y);
	}


}

package component.bullet;

import com.badlogic.gdx.math.Rectangle;

public class SilverBullet extends Bullet {

	private static final int W = 50;
	private static final int H = 9;

	public SilverBullet(float x, float y) {
		super(x, y, 550f);
		bounds = new Rectangle(position.x, position.y, W, H);
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
		super.bounds.setPosition(position.x, position.y);
	}

}

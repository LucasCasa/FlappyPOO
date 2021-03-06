package component.bullet;

import com.badlogic.gdx.math.Rectangle;

public class CannonBullet extends Bullet {

	private static final int W = 32;
	private static final int H = 21;
	
	public CannonBullet(float x, float y) {
		super(x, y, 400f);
		bounds = new Rectangle(position.x, position.y, W, H);
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
		super.bounds.setPosition(position.x, position.y);
	}

}

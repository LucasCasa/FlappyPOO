package component.bullet;

import com.badlogic.gdx.math.Rectangle;

public class ClassicBullet extends Bullet {

	private static final int W = 32;
	private static final int H = 21;
	private static final int SPEED = 400;

	public ClassicBullet(float x, float y, int direction) {
		super(x, y, SPEED * Math.signum(direction));
		bounds = new Rectangle(position.x, position.y, W, H);
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
		super.bounds.setPosition(position.x, position.y);
	}

}

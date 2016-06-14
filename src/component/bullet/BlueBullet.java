package component.bullet;

import com.badlogic.gdx.math.Rectangle;

public class BlueBullet extends Bullet {

	private static final int W = 38;
	private static final int H = 10;
	
	public BlueBullet(float x, float y) {
		super(x, y, 250f);
		super.bounds = new Rectangle(super.position.x, super.position.y, W, H);
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
		super.bounds.setPosition(position.x, position.y);
	}
	

}

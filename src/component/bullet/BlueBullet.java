package component.bullet;

import com.badlogic.gdx.math.Rectangle;

public class BlueBullet extends Bullet {

	private static final int W = 38;
	private static final int H = 10;
	private static final int SPEED = 250;
	
	public BlueBullet(float x, float y, int direction) {
		super(x, y, SPEED * Math.signum(direction)); 
		super.bounds = new Rectangle(super.position.x, super.position.y, W, H);
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
		super.bounds.setPosition(position.x, position.y);
	}
	

}

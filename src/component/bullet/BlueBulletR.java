package component.bullet;

import com.badlogic.gdx.math.Rectangle;

import component.worldComponent.Types;

public class BlueBulletR extends BulletRight {

	private static final int W = 38;
	private static final int H = 10;
	
	public BlueBulletR(float x, float y) {
		super(x, y, Types.BLUE_BULLET_SPEED*-1); 
		super.bounds = new Rectangle(super.position.x, super.position.y, W, H);
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
		super.bounds.setPosition(position.x, position.y);
	}
	

}

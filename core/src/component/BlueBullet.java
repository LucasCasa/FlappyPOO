package component;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;

public class BlueBullet extends Bullet {

	private static final int W = 38;
	private static final int H = 10;
	
	public BlueBullet(float x, float y) {
		super(x, y, 250f);
		super.bounds = new Rectangle(super.position.x, super.position.y, W, H);
	}

	@Override
	public boolean onScreen(OrthographicCamera cam) {
		return cam.position.x - (cam.viewportWidth / 2) > getPosition().x + W;
	}
	
	

}

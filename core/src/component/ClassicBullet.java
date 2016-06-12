package component;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;

public class ClassicBullet extends Bullet {

	private static final int W = 32;
	private static final int H = 21;
	
	public ClassicBullet(float x, float y) {
		super(x, y, 400f);
		bounds = new Rectangle(position.x, position.y, W, H);
	}

	@Override
	public boolean onScreen(OrthographicCamera cam) {
		return cam.position.x - (cam.viewportWidth / 2) > getPosition().x + W;
	}

}

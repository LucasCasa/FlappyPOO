package component;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;

public class GoldenBullet extends Bullet {

	private static final int W = 50;
	private static final int H = 8;
	
	public GoldenBullet(float x, float y) {
		super(x, y, 450f);
		bounds = new Rectangle(position.x, position.y, W, H);
	}

	@Override
	public boolean onScreen(OrthographicCamera cam) {
		return cam.position.x - (cam.viewportWidth / 2) > getPosition().x + W;
	}

}

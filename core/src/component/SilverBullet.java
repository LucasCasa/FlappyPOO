package component;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;

public class SilverBullet extends Bullet {

	private static final int W = 50;
	private static final int H = 9;

	public SilverBullet(float x, float y) {
		super(x, y, 550f);
		bounds = new Rectangle(position.x, position.y, W, H);
	}

	@Override
	public boolean onScreen(OrthographicCamera cam) {
		System.out.println("CAM: " + cam.position.x);
		System.out.println("VIEWPORT: "+cam.viewportWidth);
		System.out.println("BULLETPOS: " + getPosition().x);
		double v1 = cam.position.x - (cam.viewportWidth / 1);
		System.out.println("cam.position.x - (cam.viewportWidth): " + v1 );
		System.out.println("CAM: " + cam.position.x);
		System.out.println("*************");
		return cam.position.x - (cam.viewportWidth / 1) > getPosition().x;
	}

}

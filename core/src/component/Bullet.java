package component;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Bullet implements Crashable {

	protected Vector2 position;
	private float speed = 350f; // default speed
	protected Rectangle bounds;

	public Bullet(float x, float y, float speed) {
		this.position = new Vector2(x, y);
		this.speed = speed;
	}


	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public boolean crash(Bird b) {
		boolean crashes = b.getBounds().overlaps(bounds);
		if (crashes) {
			b.lifeReducer();
		}
		return crashes;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void reverse() {
		this.speed = -1 * speed;
	}

	public void update(float dt) {
		position.add(speed * dt, 0);
	}

	public float getSpeed() {
		return speed;
	}
	
	public abstract boolean onScreen(OrthographicCamera cam);
		//return cam.position.x - (cam.viewportWidth / 2) > getPostop().x + TubeView.WIDTH;
		
	

}

package component.bullet;

import com.badlogic.gdx.math.Vector2;

import component.bird.Bird;
import component.worldComponent.SimpleFObject;

public abstract class Bullet extends SimpleFObject{

	private float speed = 350f; // default speed

	public Bullet(float x, float y, float speed) {
		super(x, y);
		this.speed = speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public boolean crash(Bird b) {
		boolean crashes = b.getBounds().overlaps(this.bounds);
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

}

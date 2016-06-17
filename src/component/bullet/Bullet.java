package component.bullet;

import com.badlogic.gdx.math.Vector2;

import component.bird.Bird;
import component.worldComponent.SimpleFObject;

// TODO: Auto-generated Javadoc
/**
 * The Class Bullet.
 */
public abstract class Bullet extends SimpleFObject{

	/** The speed. */
	private float speed = 350f; // default speed

	/**
	 * Instantiates a new bullet.
	 *
	 * @param x posicion en x
	 * @param y posicion en y
	 * @param speed velocidad con la que sale disparada la bala
	 */
	public Bullet(float x, float y, float speed) {
		super(x, y);
		this.speed = speed;
	}

	/**
	 * Cambia la velocidad de la bala en caso de ser necesario
	 *
	 * @param speed the new speed
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	/**
	 * Crash.
	 *
	 * @param b the b
	 * @return true, if successful
	 */
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

	/**
	 * Actualiza la posicion de la bala respecto al tiempo
	 *
	 * @param dt the dt
	 */
	public void update(float dt) {
		position.add(speed * dt, 0);
	}

	/**
	 * Devuelve la velocidad de la bala
	 *
	 * @return the speed
	 */
	public float getSpeed() {
		return speed;
	}

}

package component.bird;

import com.badlogic.gdx.math.Rectangle;

import component.bullet.Bullet;
import component.bullet.LaserBulletL;

public class GreenBird extends BirdLeft {

	public GreenBird(int ID, int x, int y) {
		super(ID, x, y);
		bounds = new Rectangle(super.position.x, super.position.y, Bird.WIDTH, Bird.HEIGHT);
	}

	@Override
	public void shoot() {
		if(canShoot()) {
			Bullet b = new LaserBulletL(position.x, position.y);
			bullets.add(b);
		}
	}


}

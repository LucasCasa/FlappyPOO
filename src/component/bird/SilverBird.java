package component.bird;

import com.badlogic.gdx.math.Rectangle;

import component.bullet.Bullet;
import component.bullet.CannonBulletL;
import component.bullet.SilverBulletL;

public class SilverBird extends BirdLeft {

	public SilverBird(int ID, int x, int y) {
		super(ID, x, y);
		bounds = new Rectangle(super.position.x, super.position.y, Bird.WIDTH, Bird.HEIGHT);
	}

	@Override
	public void shoot() {
		if(canShoot()) {
			Bullet b = new SilverBulletL(position.x, position.y);
			bullets.add(b);
		}
	}

}

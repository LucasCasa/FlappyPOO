package component.bird;

import com.badlogic.gdx.math.Rectangle;

import component.bullet.Bullet;
import component.bullet.FireBulletL;
import component.bullet.SilverBulletL;

public class RedBird extends BirdLeft {

	public RedBird(int ID, int x, int y) {
		super(ID, x, y);
		bounds = new Rectangle(super.position.x, super.position.y, Bird.WIDTH, Bird.HEIGHT);
	}

	@Override
	public void usePower() {

	}

	@Override
	public void shoot() {
		if(canShoot()) {
			Bullet b = new FireBulletL(position.x, position.y);
			bullets.add(b);
		}
	}


}

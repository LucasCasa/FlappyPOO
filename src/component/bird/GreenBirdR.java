package component.bird;

import com.badlogic.gdx.math.Rectangle;

import component.bullet.Bullet;
import component.bullet.LaserBulletR;

public class GreenBirdR extends BirdRight {

	public GreenBirdR(int ID, int x, int y) {
		super(ID, x, y);
		bounds = new Rectangle(super.position.x, super.position.y, Bird.WIDTH, Bird.HEIGHT);
	}

	@Override
	public void shoot() {
		Bullet b = new LaserBulletR(position.x, position.y);
		bullets.add(b);				
	}


}

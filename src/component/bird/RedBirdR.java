package component.bird;

import com.badlogic.gdx.math.Rectangle;

import component.bullet.Bullet;
import component.bullet.SilverBulletR;

public class RedBirdR extends BirdRight {

	public RedBirdR(int ID, int x, int y) {
		super(ID, x, y);
		bounds = new Rectangle(super.position.x, super.position.y, Bird.WIDTH, Bird.HEIGHT);
	}

	@Override
	public void shoot() {
		Bullet b = new SilverBulletR(position.x, position.y);
		bullets.add(b);		
	}


}

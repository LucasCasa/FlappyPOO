package component.bird;

import com.badlogic.gdx.math.Rectangle;

import component.bullet.Bullet;
import component.bullet.SilverBullet;

public class RedBird extends Bird {

	public RedBird(int ID, int x, int y) {
		super(ID, x, y);
		bounds = new Rectangle(super.position.x, super.position.y, Bird.WIDTH, Bird.HEIGHT);
	}

	@Override
	public void shoot() {
		Bullet b = new SilverBullet(position.x, position.y);
		if (ID % 2 != 0)
			b.reverse();
		bullets.add(b);		
	}

	@Override
	public void apply(int type, Bird b) {
		// TODO Auto-generated method stub
		
	}

}
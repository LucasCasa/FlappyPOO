package component.bird;

import com.badlogic.gdx.math.Rectangle;

import component.bullet.BlueBulletR;
import component.bullet.Bullet;

public class BlueBirdR extends BirdRight{
	
	public BlueBirdR(int ID, int x, int y) {
		super(ID, x, y);
		bounds = new Rectangle(super.position.x, super.position.y, Bird.WIDTH, Bird.HEIGHT);
	}

	@Override
	public void shoot() {
		Bullet b = new BlueBulletR(position.x, position.y);
		bullets.add(b);	
	}



}

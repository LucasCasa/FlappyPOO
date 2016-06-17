package component.bird;

import com.badlogic.gdx.math.Rectangle;

import component.bullet.BlueBulletL;
import component.bullet.Bullet;

public class BlueBird extends BirdLeft{
	
	public BlueBird(int ID, int x, int y) {
		super(ID, x, y);
		bounds = new Rectangle(super.position.x, super.position.y, Bird.WIDTH, Bird.HEIGHT);
	}

	@Override
	public void shoot() {
		Bullet b = new BlueBulletL(position.x, position.y);
		bullets.add(b);	
	}



}

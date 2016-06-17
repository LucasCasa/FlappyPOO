package component.bird;

import com.badlogic.gdx.math.Rectangle;

import component.bullet.BlueBullet;
import component.bullet.Bullet;

public class BlueBird extends Bird{
	
	public BlueBird(int ID, int x, int y) {
		super(ID, x, y);
		bounds = new Rectangle(super.position.x, super.position.y, Bird.WIDTH, Bird.HEIGHT);
	}

	@Override
	public void shoot() {
		int direction = 1;
		if(!getTeam())
			direction = direction * -1;
		Bullet b = new BlueBullet(position.x, position.y, direction);
		bullets.add(b);	
	}



}

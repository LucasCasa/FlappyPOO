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
		int direction = 1;
		if(!getTeam())
			direction = direction * -1;
		Bullet b = new SilverBullet(position.x, position.y,direction);
		bullets.add(b);		
	}


}

package component.bird;

import com.badlogic.gdx.math.Rectangle;

import component.bullet.Bullet;
import component.bullet.GoldenBullet;

public class GreenBird extends Bird {

	public GreenBird(int ID, int x, int y) {
		super(ID, x, y);
		bounds = new Rectangle(super.position.x, super.position.y, Bird.WIDTH, Bird.HEIGHT);
	}

	@Override
	public void shoot() {
		int direction = 1;
		if(!getTeam())
			direction = direction * -1;
		Bullet b = new GoldenBullet(position.x, position.y,direction);
		bullets.add(b);				
	}


}

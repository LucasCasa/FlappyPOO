package component.bird;

import com.badlogic.gdx.math.Rectangle;

import component.bullet.Bullet;
import component.bullet.ClassicBullet;

public class ClassicBird extends Bird {

	public ClassicBird(int ID, int x, int y) {
		super(ID, x, y);
		bounds = new Rectangle(super.position.x, super.position.y, Bird.WIDTH, Bird.HEIGHT);
	}

	@Override
	public void shoot() {
		int direction = 1;
		if(!getTeam())
			direction = direction * -1;
		Bullet b = new ClassicBullet(position.x, position.y,direction);
		bullets.add(b);			
	}

}

package component.bird;

import com.badlogic.gdx.math.Rectangle;

import component.bullet.Bullet;
import component.bullet.ClassicBulletR;

public class ClassicBirdR extends BirdRight {

	public ClassicBirdR(int ID, int x, int y) {
		super(ID, x, y);
		System.out.println("creo de el clasico de la derecha");
		bounds = new Rectangle(super.position.x, super.position.y, Bird.WIDTH, Bird.HEIGHT);
	}

	@Override
	public void shoot() {
		Bullet b = new ClassicBulletR(position.x, position.y);
		bullets.add(b);			
	}

}

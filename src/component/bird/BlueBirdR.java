package component.bird;

import com.badlogic.gdx.math.Rectangle;

import component.bullet.CannonBulletR;
import component.bullet.FireBulletR;
import component.bullet.Bullet;

public class BlueBirdR extends BirdRight{
	
	public BlueBirdR(int ID, int x, int y) {
		super(ID, x, y);
		bounds = new Rectangle(super.position.x, super.position.y, Bird.WIDTH, Bird.HEIGHT);
	}

	@Override
	public void usePower() {
		if(score >= 5) {
			rival.freeze();
			score-=5;
		}
	}

	@Override
	public void shoot() {
		if(canShoot()) {
			Bullet b = new CannonBulletR(position.x, position.y);
			bullets.add(b);
		}
	}



}

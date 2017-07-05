package component.bird;

import com.badlogic.gdx.math.Rectangle;

import component.bullet.CannonBulletL;
import component.bullet.FireBulletL;
import component.bullet.Bullet;

public class BlueBird extends BirdLeft{
	
	public BlueBird(int ID, int x, int y) {
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
			Bullet b = new CannonBulletL(position.x, position.y);
			bullets.add(b);
		}
	}



}

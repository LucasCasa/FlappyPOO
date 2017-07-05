package component.bird;

import com.badlogic.gdx.math.Rectangle;

import component.bullet.Bullet;
import component.bullet.CannonBulletL;
import component.bullet.SilverBulletL;
import component.worldComponent.Timer;

public class SilverBird extends BirdLeft {
	Timer bulletTime = new Timer(200);
	int bulletCount = 0;
	public SilverBird(int ID, int x, int y) {
		super(ID, x, y);
		bounds = new Rectangle(super.position.x, super.position.y, Bird.WIDTH, Bird.HEIGHT);
	}

	@Override
	public void usePower() {
		if(score <=5) {
			Bullet b = new SilverBulletL(position.x, position.y);
			bullets.add(b);
			bulletTime.reset();
			bulletCount = 1;
			score-=5;
		}
	}

	public void update(float dt){
		super.update(dt);
		if(bulletTime.isCounting()){
			bulletTime.update();
			if(!bulletTime.isCounting()){
				if(bulletCount < 3){
					Bullet b = new SilverBulletL(position.x, position.y);
					bullets.add(b);
					bulletTime.reset();
					bulletCount++;
				}
			}
		}
	}
	@Override
	public void shoot() {
		if(canShoot()) {
			Bullet b = new SilverBulletL(position.x, position.y);
			bullets.add(b);
		}
	}

}

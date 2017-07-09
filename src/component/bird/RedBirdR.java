package component.bird;

import com.badlogic.gdx.math.Rectangle;

import component.bullet.Bullet;
import component.bullet.FireBulletR;
import component.worldComponent.Timer;

public class RedBirdR extends BirdRight {
	Timer shieldTimer = new Timer(3000);
	public RedBirdR(int ID, int x, int y) {
		super(ID, x, y);
		bounds = new Rectangle(super.position.x, super.position.y, Bird.WIDTH, Bird.HEIGHT);
	}
	@Override
	public void usePower() {
		if(score>=5){
			shieldTimer.reset();
			shield = true;
			score-=5;
		}
	}

	@Override
	public void update(float dt){
		super.update(dt);
		if(shieldTimer.isCounting()){
			shieldTimer.update();
			if(!shieldTimer.isCounting()){
				shield = false;
			}
		}
	}

	@Override
	public boolean shoot() {
		if(canShoot()) {
			Bullet b = new FireBulletR(position.x, position.y);
			bullets.add(b);
			return true;
		}
		return false;
	}


}

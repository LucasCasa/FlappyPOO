package component.bird;

import com.badlogic.gdx.math.Rectangle;

import component.bullet.Bullet;
import component.bullet.LaserBulletR;
import component.worldComponent.Timer;

public class GreenBirdR extends BirdRight {
	Timer shrinkTimer = new Timer(4000);
	public GreenBirdR(int ID, int x, int y) {
		super(ID, x, y);
		bounds = new Rectangle(super.position.x, super.position.y, Bird.WIDTH, Bird.HEIGHT);
	}

	@Override
	public void usePower() {
		if(score >=5) {
			bounds.setWidth(15);
			bounds.setHeight((int)(15.0*Bird.HEIGHT/Bird.WIDTH));
			score-=5;
			width =15;
			height=(int)(15.0*Bird.HEIGHT/Bird.WIDTH);
			shrinkTimer.reset();
		}
	}

	@Override
	public void update(float dt){
		super.update(dt);
		if(shrinkTimer.isCounting()){
			shrinkTimer.update();
			if(!shrinkTimer.isCounting()){
				width = Bird.WIDTH;
				height = Bird.HEIGHT;
				bounds.setSize(Bird.WIDTH,Bird.HEIGHT);
			}
		}
	}

	@Override
	public boolean shoot() {
		if(canShoot()) {
			Bullet b = new LaserBulletR(position.x, position.y);
			bullets.add(b);
			return true;
		}
		return false;
	}


}

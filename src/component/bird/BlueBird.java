package component.bird;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;

import component.PowerManager;
import component.bullet.BlueBullet;
import component.bullet.Bullet;

public class BlueBird extends Bird{
	
	public BlueBird(int ID, int x, int y) {
		super(ID, x, y);
		bounds = new Rectangle(super.position.x, super.position.y, Bird.WIDTH, Bird.HEIGHT);
	}

	@Override
	public void shoot() {
		Bullet b = new BlueBullet(position.x, position.y);
		if (ID % 2 != 0)
			b.reverse();
		bullets.add(b);	
	}

	@Override
	public void apply(int type, Bird b) {
		//if(availablePowers.contains(type)
			if(type == Input.Keys.E){
				PowerManager.getInstance().poder1(b);
			}else if(type == Input.Keys.R){
				PowerManager.getInstance().poder2(b);
			}else if(type == Input.Keys.T){
				PowerManager.getInstance().poder3(b);
			}
	}

}

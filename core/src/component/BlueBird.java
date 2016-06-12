package component;

import com.badlogic.gdx.Input;

public class BlueBird extends Bird{

	public BlueBird(int ID, int x, int y) {
		super(ID, x, y);
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

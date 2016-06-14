package component;

import component.bird.Bird;

public class Power1 implements Powerable {

	public void apply(Bird b) {
		b.setGrav(b.getGrav() * -1);
		b.setJump(b.getJump() * -1);
	}

}

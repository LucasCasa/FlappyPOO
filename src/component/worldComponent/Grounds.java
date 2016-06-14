package component.worldComponent;

import com.badlogic.gdx.math.Rectangle;

import component.bird.Bird;

public class Grounds extends CompoundFObject{

	public static final int GROUND_Y_OFFSET = -50;
	public static final int WIDTH = 672;
	public static final int HEIGHT = 112;

	public Grounds(float pos, float width) {
		simple1 = new Ground(pos - width / 2 - 100, GROUND_Y_OFFSET);
		simple2 = new Ground((pos - width / 2) + WIDTH - 100, GROUND_Y_OFFSET);
		simple1.bounds = new Rectangle(simple1.position.x, simple1.position.y, WIDTH, HEIGHT);
		simple2.bounds = new Rectangle(simple2.position.x, simple2.position.y, WIDTH, HEIGHT);
	}

	public void update(float pos, float width) {
		if(pos - width / 2 > simple1.position.x + WIDTH)
			simple1.position.add(WIDTH * 2, 0);
		if(pos - width / 2 > simple2.position.x + WIDTH)
			simple2.position.add(WIDTH * 2, 0);
	}
	
	public Ground getTop(){
		return (Ground) simple1;
	}
	
	public Ground getBottom(){
		return (Ground) simple2;
	}

	public boolean crash(Bird b) {
		boolean crashes = b.getPosition().y <= HEIGHT + GROUND_Y_OFFSET;
		if (crashes) {
			b.lifeReducer();
		}
		return crashes;
	}

}

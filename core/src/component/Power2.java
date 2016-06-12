package component;

public class Power2 implements Powerable{

	public void apply(Bird b) {
		b.setGrav(b.getGrav() * -2);
		b.setJump(b.getJump() * -2);
		
	}

}

package component;

public class GreenBird extends Bird {

	public GreenBird(int ID, int x, int y) {
		super(ID, x, y);
	}

	@Override
	public void shoot() {
		Bullet b = new GoldenBullet(position.x, position.y);
		if (ID % 2 != 0)
			b.reverse();
		bullets.add(b);				
	}

	@Override
	public void apply(int type, Bird b) {
		// TODO Auto-generated method stub
		
	}

}

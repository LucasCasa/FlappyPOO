package component.bird;

public abstract class BirdRight extends Bird {

	public BirdRight(int ID, int x, int y) {
		super(ID, x, y);
	}

	@Override
	public abstract void shoot();

}

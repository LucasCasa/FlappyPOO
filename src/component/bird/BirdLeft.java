package component.bird;

public abstract class BirdLeft extends Bird {

	public BirdLeft(int ID, int x, int y) {
		super(ID, x, y);
	}

	@Override
	public abstract boolean shoot();

}

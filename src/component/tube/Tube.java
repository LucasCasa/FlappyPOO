package component.tube;

import component.worldComponent.SimpleFObject;

public class Tube extends SimpleFObject{

	public static final int WIDTH = 52;
	public static final int HEIGHT = 320;
	public boolean metal;
	public Tube(float x, float y,boolean metal) {
		super(x, y);
		this.metal = metal;
	}

}

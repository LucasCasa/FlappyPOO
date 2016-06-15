package component.worldComponent;

import com.badlogic.gdx.math.Rectangle;

import component.WorldSettings;

public class Bomb extends SimpleFObject{

	public static final int W = 23;
	public static final int H = 32;
	boolean hasExploited = false;
	public Bomb(float x, float y) {
		super(x, y);
		bounds = new Rectangle(x, y, W, H);
	}
	
	public void exploit(){
		if(!hasExploited){
			WorldSettings.getInstance().reduceGap();
			hasExploited = true;
		}
	}

}

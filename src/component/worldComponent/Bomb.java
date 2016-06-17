package component.worldComponent;

import com.badlogic.gdx.math.Rectangle;

import component.WorldSettings;

// TODO: Auto-generated Javadoc
/**
 * The Class Bomb.
 */
public class Bomb extends SimpleFObject{

	/** Width. */
	public static final int W = 23;
	
	/** Height. */
	public static final int H = 32;
	
	/** Si la bomba ya exploto anteriormente. */
	boolean hasExploited = false;
	
	/**
	 * Instantiates a new bomb.
	 *
	 * @param x posicion en x
	 * @param y posicion en y
	 */
	public Bomb(float x, float y) {
		super(x, y);
		bounds = new Rectangle(x, y, W, H);
	}
	
	/**
	 * La bomba explota. Si no fue explotada anteriormente, reduce el tamaño de los
	 * gaps del mundo
	 */
	
	public void exploit(){
		if(!hasExploited){
			WorldSettings.getInstance().reduceGap();
			hasExploited = true;
		}
	}

}

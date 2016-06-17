package component.worldComponent;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * SimpleFObject.
 * Es una clase que representa un objeto del mundo simple, o sea, que no es un objeto 
 * compuesto por mas de uno.
 * 
 * Ejemplo: Pajaro, Life, Tube
 */
public abstract class SimpleFObject extends FlappyObject implements FlappyCrashable {

	/** La posicion del objeto */
	protected Vector2 position;
	
	protected Rectangle bounds;
	
	/**
	 * Instantiates a new simple f object.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public SimpleFObject(float x, float y) {
		position = new Vector2(x, y);		
	}

	@Override
	public boolean crash(SimpleFObject obj) {
		return bounds.overlaps(obj.bounds);
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public void setPosition(float x, float y){
		position.set(x, y);
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	

}

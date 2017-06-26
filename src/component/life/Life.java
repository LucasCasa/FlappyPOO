package component.life;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import component.bird.Bird;
import component.worldComponent.SimpleFObject;
import desktop.Flappy;

/**
 * No es la clase que se encarga de administrar las vidas de los jugadores.
 * Life es el objeto que aparece en el juego con forma de CORAZON. 
 * Lo unico que hace es incrementar la vida en 1
 */

public class Life extends SimpleFObject{

	/** Frecuencia de aparicion de corazones. */
	private static final int FREQUENCY = 500;
	
	private static final int Y_VARIATION = 3;
	
	private int touched = 0;
	
	/** The Constant WIDTH. */
	public static final int WIDTH = 48;
	
	/** The Constant HEIGHT. */
	public static final int HEIGHT = 48;

	/**
	 * Instantiates a new life.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Life(float x, float y) {
		super(x,y);
		bounds = new Rectangle(x, y, WIDTH, HEIGHT);
	}

	public Vector2 getPosition() {
		return position;
	}

	
	public void update() {
		float posx = (float) (Math.random() * FREQUENCY + Flappy.WIDTH);
		float posy = (float) (Math.random() * Y_VARIATION);
		position.add(posx, posy);
		bounds.setPosition( bounds.x + posx, bounds.y + posy);
	}

	
	/**
	 * Crash.
	 *
	 * @param b the b
	 * @return true, if successful
	 */
	public boolean crash(Bird b) {
		boolean crashes = b.getBounds().overlaps(bounds);
		
		if(crashes && !b.getLifeSecure()){
			b.increaseLife();
			touched++;
			b.setLifeSecure(true);
		}
		return crashes;	
	}
	
	public void touched(){
		touched++;
	}
	
	public int getTouched() {
		return touched;
	}
	

}

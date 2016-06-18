package component.worldComponent;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import start.Flappy;

/*
 * No es la clase que se encarga de administrar las vidas de los jugadores.
 * Life es el objeto que aparece en el juego con forma de CORAZON. 
 * Lo unico que hace es incrementar la vida en 1
 */

public class Life extends SimpleFObject{

	private static final int FREQUENCY = 50000;
	private static final int Y_VARIATION = 3;
	public static final int WIDTH = 48;
	public static final int HEIGHT = 48;

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

	/*
	public boolean crash(Bird b) {
		boolean crashes = b.getBounds().overlaps(bounds);
		
		if(crashes && !b.getLifeSecure()){
			b.increaseLife();
			b.setLifeSecure(true);
		}
		return crashes;	
	}
	*/

}

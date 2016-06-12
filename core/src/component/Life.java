package component;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import ar.edu.itba.Flappy;

/*
 * No es la clase que se encarga de administrar las vidas de los jugadores.
 * Life es el objeto que aparece en el juego con forma de CORAZON. 
 * Lo unico que hace es incrementar la vida en 1
 */

public class Life implements Crashable {

	private Vector2 position;
	private Rectangle bounds;

	private static final int FREQUENCY = 50000;
	private static final int Y_VARIATION = 3;

	public Life(float x, float y) {
		position = new Vector2(x, y);
		bounds = new Rectangle(x, y, LifeView.WIDTH, LifeView.HEIGHT);
	}

	public boolean crash(Rectangle player) {
		return player.overlaps(bounds);
	}

	public Vector2 getPosition() {
		return position;
	}

	public void updatePosition() {
		float posx = (float) (Math.random() * FREQUENCY + Flappy.WIDTH);
		float posy = (float) (Math.random() * Y_VARIATION);
		position.add(posx, posy);
		bounds.setPosition( bounds.x + posx, bounds.y + posy);
	}

	public boolean crash(Bird b) {
		boolean crashes = b.getBounds().overlaps(bounds);
		
		if(crashes && !b.getLifeSecure()){
			b.increaseLife();
			b.setLifeSecure(true);
			updatePosition();
		}
		return crashes;	
	}

}

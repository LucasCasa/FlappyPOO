package component.worldComponent;

import java.util.Random;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import component.WorldSettings;
import component.bird.Bird;

/**
 * Tubes esta compuesta por dos partes.
 * Parte superior del tubo (del tipo Tube)
 * Parte inferior del tubo (del tipo Tube)
 * Ambos son SimpleFObject.
 * 
 */
public class Tubes extends CompoundFObject {

	/**
	 * Random se utiliza a la hora de crear y setear las posiciones de las aperturas de los tubos
	 */
	private Random rand;
	private WorldSettings wp;

	/**
	 * Crea un Tubes. Este consiste en una parte superior y otra inferior
	 * Ambas partes son SimpleFObjects.
	 *
	 * @param x the x
	 */
	public Tubes(float x) {
		rand = new Random();
		wp = WorldSettings.getInstance();
		simple1 = new Tube(x, rand.nextInt() + wp.getGap() + WorldSettings.LOWEST_OPENING);
		simple2 = new Tube(x, simple1.position.y - wp.getGap() - Tube.HEIGHT);

		simple1.bounds = new Rectangle(simple1.position.x, simple1.position.y, Tube.WIDTH, Tube.HEIGHT);
		simple2.bounds = new Rectangle(simple2.position.x, simple2.position.y, Tube.WIDTH, Tube.HEIGHT);

	}

	/**
	 * Obtiene la posicion (x,y) de la parte superior del Tubes
	 *
	 */
	public Vector2 getTubeTopPos() {
		return simple1.position;
	}

	/**
	 * Obtiene la posicion (x,y) de la parte superior del Tubes
	 */
	public Vector2 getTubeBottomPos() {
		return simple2.position;
	}

	/**
	 * Reposiciona el Tubes respecto a X.
	 * Es un metodo privado sera llamada luego por otro metodo privado.
	 * El usuario no debe saber reposicionar los tubos.
	 *
	 * @param x the x
	 */
	private void reposition(float x) {
		simple1.position.set(x, rand.nextInt(wp.getFluctuation()) + wp.getGap() + WorldSettings.LOWEST_OPENING);
		simple2.position.set(x, simple1.position.y - wp.getGap() - Tube.HEIGHT);
		simple1.bounds.setPosition(simple1.position.x, simple1.position.y);
		simple2.bounds.setPosition(simple2.position.x, simple2.position.y);
	}


	/**
	 * Reposiciona los tubos cuando esta salen de la pantalla.
	 */
	public void repositionByInterface() {
		this.reposition(this.getTubeTopPos().x + ((Tube.WIDTH + wp.getTubeSpacing()) * wp.getTubeCount()));
	}

	/**
	 * Dice si el Tubes esta visualizable
	 *
	 * @param pos the pos
	 * @param width the width
	 * @return true, if successful
	 */
	public boolean onScreen(float pos, float width) {
		return pos - width / 2 > getTubeTopPos().x + Tube.WIDTH;
	}

	/**
	 * Crash.
	 *
	 * @param b the b
	 * @return true, if successful
	 */
	public boolean crash(Bird b) {
		boolean crashes = b.getBounds().overlaps(simple1.bounds) || b.getBounds().overlaps(simple2.bounds);
		if (crashes) {
			b.lifeReducer();
		}
		return crashes;
	}

	/**
	 * Gets the top.
	 *
	 * @return the top
	 */
	public Tube getTop() {
		return (Tube) super.simple1;
	}

	/**
	 * Gets the bottom.
	 *
	 * @return the bottom
	 */
	public Tube getBottom() {
		return (Tube) super.simple2;
	}

}

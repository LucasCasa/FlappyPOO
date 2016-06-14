package component.worldComponent;

import java.util.Random;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import component.WorldSettings;
import component.bird.Bird;

public class Tubes extends CompoundFObject {

	public static final int WIDTH = 52;
	public static final int HEIGHT = 320;

	private Random rand;
	private WorldSettings wp;

	public Tubes(float x) {
		rand = new Random();
		wp = WorldSettings.getInstance();
		simple1 = new Tube(x, rand.nextInt() + wp.getGap() + WorldSettings.LOWEST_OPENING);
		simple2 = new Tube(x, simple1.position.y - wp.getGap() - HEIGHT);

		simple1.bounds = new Rectangle(simple1.position.x, simple1.position.y, WIDTH, HEIGHT);
		simple2.bounds = new Rectangle(simple2.position.x, simple2.position.y, WIDTH, HEIGHT);

	}

	public Vector2 getTubeTopPos() {
		return simple1.position;
	}

	public Vector2 getTubeBottomPos() {
		return simple2.position;
	}

	public void reposition(float x) {
		simple1.position.set(x, rand.nextInt(wp.getFluctuation()) + wp.getGap() + WorldSettings.LOWEST_OPENING);
		simple2.position.set(x, simple1.position.y - wp.getGap() - HEIGHT);
		simple1.bounds.setPosition(simple1.position.x, simple1.position.y);
		simple2.bounds.setPosition(simple2.position.x, simple2.position.y);
	}

	// Se llama a este método cada vez que el tubo se "escapa" de la vision del
	// usuario y se reposiciona
	public void repositionByInterface() {
		this.reposition(this.getTubeTopPos().x + ((WIDTH + wp.getTubeSpacing()) * wp.getTubeCount()));
	}

	public boolean onScreen(float pos, float width) {
		return pos - width / 2 > getTubeTopPos().x + WIDTH;
	}

	public boolean crash(Bird b) {
		boolean crashes = b.getBounds().overlaps(simple1.bounds) || b.getBounds().overlaps(simple2.bounds);
		if (crashes) {
			b.lifeReducer();
		}
		return crashes;
	}

	public Tube getTop() {
		return (Tube) super.simple1;
	}

	public Tube getBottom() {
		return (Tube) super.simple2;
	}

}

package component;

import java.util.Random;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Tube implements Crashable {

	/*
	public static final int TUBE_SPACING = 140; //espaciado entre uno y otro MIN=50 
	public static final int TUBE_COUNT = 6; //MIN = 6 es para 800x600 con TubeSpacing = 140 
	private static final int FLUCTUATION = 120; 
	private static final int TUBE_GAP = 100; //apertura
	private static final int LOWEST_OPENING = 120; //este es fijo
	*/
	
	private Vector2 postop, posbottom;
	private Rectangle boundsTop, boundsBot;
	private Random rand;
	private WorldSettings wp;
	
	public Tube(float x) {
		rand = new Random();
		wp = WorldSettings.getInstance();
		postop = new Vector2(x, rand.nextInt() + wp.getGap() + WorldSettings.LOWEST_OPENING);
		posbottom = new Vector2(x, postop.y - wp.getGap() - TubeView.HEIGHT);

		boundsTop = new Rectangle(postop.x, postop.y, TubeView.WIDTH, TubeView.HEIGHT);
		boundsBot = new Rectangle(posbottom.x, posbottom.y, TubeView.WIDTH, TubeView.HEIGHT);

	}

	public Vector2 getPostop() {
		return postop;
	}

	public Vector2 getPosbottom() {
		return posbottom;
	}

	public void reposition(float x) {
		postop.set(x, rand.nextInt(wp.getFluctuation()) + wp.getGap() + WorldSettings.LOWEST_OPENING);
		posbottom.set(x, postop.y - wp.getGap() - TubeView.HEIGHT); // bottom.getHeight()
		boundsTop.setPosition(postop.x, postop.y);
		boundsBot.setPosition(posbottom.x, posbottom.y);
	}

	/*
	 * Se llama a este m�todo cada vez que el tubo se "escapa" de la vision del
	 * usuario y se reposiciona
	 */
	public void repositionByInterface() {
		this.reposition(this.getPostop().x + ((TubeView.WIDTH + wp.getTubeSpacing()) * wp.getTubeCount()));
	}
	
	public boolean onScreen(OrthographicCamera cam){
		return cam.position.x - (cam.viewportWidth / 2) > getPostop().x + TubeView.WIDTH;
	}

	public boolean crash(Bird b) {
		boolean crashes = b.getBounds().overlaps(boundsTop) || b.getBounds().overlaps(boundsBot);
		if (crashes) {
			b.lifeReducer();
		}
		return crashes;
	}
	
	@Override
	public String toString() {
		return "HOLALLALALALLA";
	}



}

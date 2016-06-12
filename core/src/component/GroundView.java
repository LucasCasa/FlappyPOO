package component;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GroundView {

	private Texture ground;

	public static final int GROUND_Y_OFFSET = -50;
	public static final int WIDTH = 672;
	public static final int HEIGHT = 112;

	public GroundView() {
		ground = new Texture(Types.GROUND);
	}

	public Texture getTextureBird() {
		return ground;
	}

	public void setTextureBird(Texture bird) {
		this.ground = bird;
	}

	public void dispose() {
		ground.dispose();
	}
	
	public int getHeight() {
		return ground.getHeight();
	}
	
	public int getWidth() {
		return ground.getWidth();
	}

	public void draw(SpriteBatch sb, Ground g) {
		sb.draw(ground, g.getGroundPos1().x, g.getGroundPos1().y);
		sb.draw(ground, g.getGroundPos2().x, g.getGroundPos2().y);
	}

}

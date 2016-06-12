package component;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BirdView {

	private Texture b;

	/*
	 * Este es el ancho y el alto visual respectivamente del bird. No se hizo un
	 * bird.getHeight() porque bird no puede ser estático y sin embargo, el
	 * width y el height si deben serlo porque siempre vale eso.
	 */
	public static final int WIDTH = 34;
	public static final int HEIGHT = 24;

	public BirdView(String birdPath) {
		b = new Texture(birdPath);
	}

	public Texture getTextureBird() {
		return b;
	}

	public void setTextureBird(Texture bird) {
		this.b = bird;
	}

	public void dispose() {
		b.dispose();
	}

	public void draw(SpriteBatch sb, Bird b) {

		if (b.getAuraState() == true) {
			Texture aura = new Texture("aura.png");
			sb.draw(aura, b.getPosition().x - HEIGHT / 1.5f, b.getPosition().y - WIDTH / 2);
		}
		sb.draw(this.b, b.getPosition().x, b.getPosition().y);

	}

}

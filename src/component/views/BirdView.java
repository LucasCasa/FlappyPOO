package component.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import component.bird.Bird;

public class BirdView {
	
	public static final int VISUAL_WIDTH = 34;
	public static final int VISUAL_HEIGHT = 24;

	private Texture b;
	private boolean hasSound = false;

	/*
	 * Este es el ancho y el alto visual respectivamente del bird. No se hizo un
	 * bird.getHeight() porque bird no puede ser estático y sin embargo, el
	 * width y el height si deben serlo porque siempre vale eso.
	 */
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

		if (b.getAuraState()) {
			Texture aura = new Texture("aura.png");
			sb.draw(aura, b.getPosition().x - VISUAL_HEIGHT / 1.5f, b.getPosition().y - VISUAL_WIDTH / 2);
			if (!hasSound) {
				Sound s = Gdx.audio.newSound(Gdx.files.internal("crash.mp3"));
				s.play(1f);
				hasSound = true;
			}

		} else {
			hasSound = false;
		}
		sb.draw(this.b, b.getPosition().x, b.getPosition().y);

	}

}

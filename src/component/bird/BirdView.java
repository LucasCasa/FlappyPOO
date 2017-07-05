package component.bird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import component.worldComponent.SimpleFObjectView;
import component.worldComponent.Types;

public class BirdView extends SimpleFObjectView{
	
	public static final int VISUAL_WIDTH = 34;
	public static final int VISUAL_HEIGHT = 24;
	private String name;
	private boolean hasSound = false;

	/*
	 * Este es el ancho y el alto visual respectivamente del bird. No se hizo un
	 * bird.getHeight() porque bird no puede ser estático y sin embargo, el
	 * width y el height si deben serlo porque siempre vale eso.
	 */
	public BirdView(String birdPath,String name) {
		super(birdPath);
	}

	public void draw(SpriteBatch sb, Bird b) {

		if (b.getAuraState()) {
			Texture aura = new Texture("aura.png");
			float xdif = (aura.getWidth() - b.getWidth()) / 2;
			float ydif = (aura.getHeight() - b.getHeight()) / 2;
			sb.draw(aura, b.getPosition().x - xdif, b.getPosition().y - ydif);
			if (!hasSound) {
				/*
				Sound s = Gdx.audio.newSound(Gdx.files.internal(Types.CRASH));
				s.play(1f);
				*/
				hasSound = true;
			}

		} else {
			hasSound = false;
		}
		sb.draw(super.getTexture(), b.getPosition().x, b.getPosition().y,b.getWidth(),b.getHeight());
		if(b.shield){
			float xdif = (Types.SHIELD.getWidth() - b.getWidth()) / 2;
			float ydif = (Types.SHIELD.getHeight() - b.getHeight()) / 2;
			sb.draw(Types.SHIELD, b.getPosition().x - xdif, b.getPosition().y - ydif);
		}

		if(b.isFrozen()){
			float xdif = (Types.FREEZE.getWidth() - b.getWidth()) / 2;
			float ydif = (Types.FREEZE.getHeight() - b.getHeight()) / 2;
			sb.draw(Types.FREEZE, b.getPosition().x - xdif, b.getPosition().y - ydif);
		}

	}

}

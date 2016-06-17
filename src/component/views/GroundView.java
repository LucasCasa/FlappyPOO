package component.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import component.Types;
import component.worldComponent.Grounds;

public class GroundView {

	private Texture ground;

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

	public void draw(SpriteBatch sb, Grounds g) {
		sb.draw(ground, g.getGround1().getPosition().x, g.getGround1().getPosition().y);
		sb.draw(ground, g.getGround2().getPosition().x, g.getGround2().getPosition().y);
	}

}

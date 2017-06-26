package component.ground;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import component.worldComponent.SimpleFObjectView;

public class GroundView extends SimpleFObjectView{

	public GroundView(String t) {
		super(t);
	}

	public void draw(SpriteBatch sb, Grounds g) {
		sb.draw(super.getTexture(), g.getGround1().getPosition().x, g.getGround1().getPosition().y);
		sb.draw(super.getTexture(), g.getGround2().getPosition().x, g.getGround2().getPosition().y);
	}

}

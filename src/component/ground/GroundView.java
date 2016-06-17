package component.ground;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import component.worldComponent.SimpleFObjectView;
import component.worldComponent.Types;

public class GroundView extends SimpleFObjectView{

	public GroundView() {
		super(Types.GROUND);
	}

	public void draw(SpriteBatch sb, Grounds g) {
		sb.draw(super.getTexture(), g.getGround1().getPosition().x, g.getGround1().getPosition().y);
		sb.draw(super.getTexture(), g.getGround2().getPosition().x, g.getGround2().getPosition().y);
	}

}

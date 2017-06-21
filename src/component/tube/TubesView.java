package component.tube;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import component.worldComponent.SimpleFObjectView;

public abstract class TubesView {
	
	SimpleFObjectView top, bottom;

	public Texture getTopTexture() {
		return top.getTexture();
	}
	
	public Texture getBottomTexture() {
		return bottom.getTexture();
	}
	
	public void dispose(){
		top.dispose();
		bottom.dispose();
	}
	
	public void draw(SpriteBatch sb, Tubes tube){
		sb.draw(getTopTexture(),tube.getTubeTopPos().x, tube.getTubeTopPos().y);
		sb.draw(getBottomTexture(), tube.getTubeBottomPos().x, tube.getTubeBottomPos().y);
	}
	

}

package component.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import component.Types;
import component.worldComponent.Tubes;
import start.Flappy;

public class TubeView {
	
	private Texture top, bottom;

	public TubeView() {
		top = new Texture(Types.TOP_TUBE);
		bottom = new Texture(Types.BOTTOM_TUBE);
	}
	
	public Texture getTop() {
		return top;
	}
	
	public Texture getBottom() {
		return bottom;
	}
	
	public void dispose(){
		top.dispose();
		bottom.dispose();
	}
	
	public void draw(SpriteBatch sb, Tubes tube){
		sb.draw(top, tube.getTubeTopPos().x, tube.getTubeTopPos().y);
		sb.draw(bottom, tube.getTubeBottomPos().x, tube.getTubeBottomPos().y);
		//sb.draw(bottom, tube.getTubeTopPos().x, Flappy.HEIGHT / 2);
	}
	

}

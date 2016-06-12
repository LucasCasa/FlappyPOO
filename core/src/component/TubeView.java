package component;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TubeView {
	
	private Texture top, bottom;

	public static final int WIDTH = 52;
	public static final int HEIGHT = 320;

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
	
	public void draw(SpriteBatch sb, Tube tube){
		sb.draw(top, tube.getPostop().x, tube.getPostop().y);
		sb.draw(bottom, tube.getPosbottom().x, tube.getPosbottom().y);
	}
	

}

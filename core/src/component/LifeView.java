package component;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LifeView {
	
	private Texture l;
	
	public static final int WIDTH = 48;
	public static final int HEIGHT = 48;
	
	public LifeView() {
		this.l = new Texture("heart.png");
	}
	
	public Texture getLifeTexture(){
		return l;
	}
	
	public void draw(SpriteBatch sb, Life life) {
		sb.draw(l, life.getPosition().x, life.getPosition().y);
	}
	
	public void dispose() {
		l.dispose();
	}
	
	
}

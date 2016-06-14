package component.worldComponent;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LifeView {
	
	private Texture l;
	
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

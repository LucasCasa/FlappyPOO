package component.worldComponent;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public abstract class SimpleFObjectView implements Drawable{

	protected Texture t;
	
	public SimpleFObjectView(String path) {
		t = new Texture(path);
	}
	
	public Texture getTexture() {
		return t;
	}
	
	public void setTexture(Texture t){
		this.t = t;
	}
	
	public void dispose(){
		t.dispose();
	}
	
	public void draw(SpriteBatch sb, SimpleFObject b) {
		sb.draw(t, b.getPosition().x, b.getPosition().y);
	}

	
	
}

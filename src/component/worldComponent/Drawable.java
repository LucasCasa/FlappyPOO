package component.worldComponent;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Drawable {
	
	public void dispose();
	
	public void draw(SpriteBatch sb, SimpleFObject b);

}

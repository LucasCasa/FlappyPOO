package component.worldComponent;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class SimpleFObject extends FlappyObject implements FlappyCrashable {

	protected Vector2 position;
	protected Rectangle bounds;
	
	public SimpleFObject(float x, float y) {
		position = new Vector2(x, y);		
	}

	@Override
	public boolean crash(SimpleFObject obj) {
		return bounds.overlaps(obj.bounds);
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public void setPosition(float x, float y){
		position.set(x, y);
	}
	

}

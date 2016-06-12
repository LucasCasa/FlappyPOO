package component;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class Ground implements Crashable{

	private Vector2 groundPos1, groundPos2;
	
	
	public Ground(OrthographicCamera cam) {
		groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2 - 100, GroundView.GROUND_Y_OFFSET);
		groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + GroundView.WIDTH - 100, GroundView.GROUND_Y_OFFSET);
	}
	
	public Vector2 getGroundPos1() {
		return groundPos1;
	}
	
	public Vector2 getGroundPos2() {
		return groundPos2;
	}
	
	public void updateGround(OrthographicCamera cam) {
		if (cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + GroundView.WIDTH)
			groundPos1.add(GroundView.WIDTH * 2, 0);
		if (cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + GroundView.WIDTH)
			groundPos2.add(GroundView.WIDTH * 2, 0);
	}

	@Override
	public boolean crash(Bird b) {
		boolean crashes = b.getPosition().y <= GroundView.HEIGHT + GroundView.GROUND_Y_OFFSET;
		if(crashes){
			b.lifeReducer();
		}
		return crashes;
	}
	
	
	
	
	
}

package component;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;

import component.bird.Bird;
import component.bird.ClassicBird;
import component.bird.RedBird;
import component.bullet.Bullet;
import component.worldComponent.Grounds;
import component.worldComponent.Life;
import component.worldComponent.Tubes;

public class WorldManager {

	private static final int X_CAM_OFFSET = 250;

	private Bird bLeft;
	private Bird bRight;
	private Grounds g;
	private Array<Tubes> tubes;
	private List<Life> lifes;

	private OrthographicCamera cam;

	public WorldManager(OrthographicCamera cam) {
		bLeft = new RedBird(0, 100, 200);
		bRight = new ClassicBird(1, 500, 200);
		this.cam = cam;

		WorldSettings.getInstance().setDefaultPreferences();

		lifes = new ArrayList<Life>();

		for (int i = 1; i <= WorldSettings.getInstance().getLife(); i++) {
			int ranX = (int) Math.floor(Math.random() * (WorldSettings.MIN_RAN_X - (WorldSettings.MAX_RAN_X + 1)) + (WorldSettings.MAX_RAN_X + 1));
			int ranY = (int) Math.floor(Math.random() * (WorldSettings.MIN_RAN_Y - (WorldSettings.MAX_RAN_Y + 1))+ (WorldSettings.MAX_RAN_Y + 1));
			lifes.add(new Life(ranX * i, ranY));
		}

		tubes = new Array<Tubes>();

		g = new Grounds(cam.position.x, cam.viewportWidth);

		for (int i = 1; i <= WorldSettings.getInstance().getTubeCount(); i++) {
			tubes.add(new Tubes(i * (WorldSettings.getInstance().getTubeSpacing() + Tubes.WIDTH)));
		}
	}

	public void update(float dt) {

		g.update(cam.position.x, cam.viewportWidth);

		bLeft.update(dt);
		bRight.update(dt);

		cam.position.x = bLeft.getPosition().x + X_CAM_OFFSET;

		for (int i = 0; i < tubes.size; i++) {
			Tubes tube = tubes.get(i);

			if (tube.onScreen(cam.position.x,cam.viewportWidth))
				tube.repositionByInterface();
			
			bLeft.crash(tube);
			bRight.crash(tube);
			
		}

		for (Life l : lifes) {
			bLeft.crash(l);
			bRight.crash(l);
		}

		bLeft.crash(g);
		bRight.crash(g);
		
		bLeft.updateTimers();
		bRight.updateTimers();

		for (Bullet b : bLeft.getBullets()) {
			b.update(dt);
			bRight.crash(b);
		}

		for (Bullet b : bRight.getBullets()) {
			b.update(dt);
			bLeft.crash(b);
		}

		cam.update();

	}

	public Bird getBLeft() {
		return bLeft;
	}

	public Bird getBRight() {
		return bRight;
	}

	public Grounds getG() {
		return g;
	}

	public Array<Tubes> getTubes() {
		return tubes;
	}

	public OrthographicCamera getCam() {
		return cam;
	}

	public List<Life> getLifes() {
		return lifes;
	}

}

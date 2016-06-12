package component;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;

public class WorldComponent {

	private static final int X_CAM_OFFSET = 250;

	private Bird bird;
	private Bird bird2;
	private Ground g;
	private Array<Tube> tubes;
	List<Life> lifes;
	private OrthographicCamera cam;

	public WorldComponent(OrthographicCamera cam) {
		bird = new BlueBird(0, 100, 200);
		bird2 = new ClassicBird(1, 500, 200);
		this.cam = cam;

		WorldSettings.getInstance().setDefaultPreferences();
		//WorldPreferences.getInstance().setHardPreferences();

		lifes = new ArrayList<Life>();
		/*
		 * Creo las vidas en posiciones aleatorias
		 */
		for (int i = 1; i <= WorldSettings.getInstance().getLife(); i++) {
			int ranX = (int) Math.floor(Math.random() * (WorldSettings.MIN_RAN_X - (WorldSettings.MAX_RAN_X + 1))
					+ (WorldSettings.MAX_RAN_X + 1));
			int ranY = (int) Math.floor(Math.random() * (WorldSettings.MIN_RAN_Y - (WorldSettings.MAX_RAN_Y + 1))
					+ (WorldSettings.MAX_RAN_Y + 1));
			lifes.add(new Life(ranX * i, ranY));
		}

		tubes = new Array<Tube>();

		g = new Ground(cam);

		for (int i = 1; i <= WorldSettings.getInstance().getTubeCount(); i++) {
			tubes.add(new Tube(i * (WorldSettings.getInstance().getTubeSpacing() + TubeView.WIDTH)));
		}
	}

	public void update(float dt) {

		g.updateGround(cam);

		bird.update(dt);
		bird2.update(dt);

		cam.position.x = bird.getPosition().x + X_CAM_OFFSET;

		for (int i = 0; i < tubes.size; i++) {
			Tube tube = tubes.get(i);

			if (tube.onScreen(cam))
				tube.repositionByInterface();

			tube.crash(bird);
			tube.crash(bird2);
		}

		for (Life l : lifes) {
			l.crash(bird);
			l.crash(bird2);
		}

		g.crash(bird);
		g.crash(bird2);

		bird.updateTimers();
		bird2.updateTimers();

		for (Bullet b : bird.getBullets()) {
			b.update(dt);
			b.crash(bird2);
		}

		for (Bullet b : bird2.getBullets()) {
			b.update(dt);
			b.crash(bird);
		}

		cam.update();

	}

	public Bird getBird() {
		return bird;
	}

	public Bird getBird2() {
		return bird2;
	}

	public Ground getG() {
		return g;
	}

	public Array<Tube> getTubes() {
		return tubes;
	}

	public OrthographicCamera getCam() {
		return cam;
	}

	public List<Life> getLifes() {
		return lifes;
	}

}

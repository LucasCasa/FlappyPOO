package component;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;

import component.bird.Bird;
import component.bird.BirdType;
import component.bird.BlueBird;
import component.bird.ClassicBird;
import component.bird.GreenBird;
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

	public WorldManager(OrthographicCamera cam, String p1Name, String p2Name, BirdType p1Bird, BirdType p2Bird) {
		
		createBirds(p1Name, p2Name, p1Bird, p2Bird);
		
		//bLeft = new RedBird(0, 100, 200);
		//bRight = new ClassicBird(1, 500, 200);
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
	
	public void createBirds(String p1Name, String p2Name, BirdType p1Bird, BirdType p2Bird) {
		switch(p1Bird) {
			case RED: {
				bLeft = new RedBird(0, 100, 200);
				break;
			}
			case GREEN: {
				bLeft = new GreenBird(0, 100, 200);
				break;
			}
			case CLASSIC: {
				bLeft = new ClassicBird(0, 100, 200);
				break;
			}
			case BLUE: {
				bLeft = new BlueBird(0, 100, 200);
				break;
			}
		}

		switch(p2Bird) {
			case RED: {
				bRight = new RedBird(1, 500, 200);
				break;
			}
			case GREEN: {
				bRight = new GreenBird(1, 500, 200);
				break;
			}
			case CLASSIC: {
				bRight = new ClassicBird(1, 500, 200);
				break;
			}
			case BLUE: {
				bRight = new BlueBird(1, 500, 200);
				break;
			}
		}

		bLeft.setName(p1Name);
		bRight.setName(p2Name);
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

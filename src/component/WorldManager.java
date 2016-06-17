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
import component.worldComponent.Bomb;
import component.worldComponent.Grounds;
import component.worldComponent.Life;
import component.worldComponent.Tubes;
import scoreFiles.Output;

public class WorldManager {

	private static final int X_CAM_OFFSET = 250;

	private Bird bLeft;
	private Bird bRight;
	private Grounds g;
	private Array<Tubes> tubes;
	private List<Life> lifes;
	private List<Bomb> bombs;

	private Boolean continues;
	private OrthographicCamera cam;

	public WorldManager(OrthographicCamera cam, String p1Name, String p2Name, BirdType p1Bird, BirdType p2Bird) {
		// habria que crear las variables para pasarselas al endgame
		createBirds(p1Name, p2Name, p1Bird, p2Bird);

		this.cam = cam;
		continues = true;
		lifes = new ArrayList<>();
		bombs = new ArrayList<>();

		for (int i = 1; i <= WorldSettings.getInstance().getLife(); i++) {
			lifes.add(new Life(randomX() * i, randomY()));
		}

		for (int i = 1; i < WorldSettings.getInstance().getBombs(); i++) {
			bombs.add(new Bomb(randomX() * i, randomY()));
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

			if (tube.onScreen(cam.position.x, cam.viewportWidth))
				tube.repositionByInterface();

		}

		updateBirdOnGame(bLeft, bRight, dt);
		updateBirdOnGame(bRight, bLeft, dt);

		if (bLeft.getLife() == 0 || bRight.getLife() == 0) {

			Output.getInstance().write(getWinner(bLeft, bRight));
			continues = false;
		}

		cam.update();

	}

	private void updateBirdOnGame(Bird me, Bird enemy, float dt) {

		for (int i = 0; i < tubes.size; i++) {
			Tubes tube = tubes.get(i);
			me.crash(tube);
		}

		for (Life l : lifes) {
			me.crash(l);
		}

		me.crash(g);
		me.updateTimers();

		for (Bullet b : me.getBullets()) {
			b.update(dt);
			enemy.crash(b);
		}

		for (Bomb b : bombs) {
			if (me.crash(b))
				b.exploit();
		}
	}

	public void createBirds(String p1Name, String p2Name, BirdType p1Bird, BirdType p2Bird) {
		switch (p1Bird) {
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

		switch (p2Bird) {
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

	public Boolean getContinues() {
		return continues;
	}

	public void setContinues(Boolean continues) {
		this.continues = continues;
	}

	public List<Life> getLifes() {
		return lifes;
	}

	public List<Bomb> getBombs() {
		return bombs;
	}

	private int randomX() {
		return (int) Math.floor(Math.random() * (WorldSettings.MIN_RAN_X - (WorldSettings.MAX_RAN_X + 1))
				+ (WorldSettings.MAX_RAN_X + 1));
	}

	private int randomY() {
		return (int) Math.floor(Math.random() * (WorldSettings.MIN_RAN_Y - (WorldSettings.MAX_RAN_Y + 1))
				+ (WorldSettings.MAX_RAN_Y + 1));
	}

	private Bird getWinner(Bird l, Bird r) {
		if (r.getScore() != 0)
			return r;
		else
			return l;
	}

}

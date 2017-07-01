package state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import component.bird.BirdType;
import component.worldComponent.GameMode;
import component.worldComponent.Types;
import desktop.Flappy;
import world.*;

public class PlayState extends State {

	private static final int X_CAM_OFFSET = 250;
	public static final float ZOOM_CAMARA = 1.2f;
	private long leftId;
	private long rightId;

	private WorldManager world;
	private WorldManagerView worldView;

	public PlayState(GameStateManager gsm, String p1Name, String p2Name, BirdType birdType, BirdType birdType2, GameMode gm) {
		super(gsm);
		cam.setToOrtho(false, Flappy.WIDTH / ZOOM_CAMARA, Flappy.HEIGHT / ZOOM_CAMARA);
		switch (gm){
			case CLASSIC:
				this.world = new ClassicWorldManager(cam, p1Name, p2Name, birdType, birdType2);
				this.worldView = new ClassicWorldManagerView(world);
				break;
			case COUNTDOWN:
				this.world = new CountdownWorldManager(cam, p1Name, p2Name, birdType, birdType2);
				this.worldView = new CountdownWorldManagerView(world);
				break;
		}


	}

	@Override
	protected void handleInput() {
		if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
			world.getBLeft().jump();
			Types.JUMP.play(Types.masterVolume,1,-1);
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			world.getBRight().jump();
			Types.JUMP.play(Types.masterVolume,1,1);
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
			worldView.playShootSound(world.getBLeft(),true);
			world.getBLeft().shoot();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
			worldView.playShootSound(world.getBRight(),false);
			world.getBRight().shoot();
		}

		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}

	}

	@Override
	public void update(float dt) {
		handleInput();
		cam.position.x = world.getBLeft().getPosition().x + X_CAM_OFFSET;
		world.update(dt, cam.position.x, cam.viewportWidth);
		if (!(world.getContinues())) {
			gsm.set(new EndGame(gsm));
		}
		cam.update();
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		worldView.render(sb, cam.position.x, cam.viewportWidth);
		sb.end();
	}

	@Override
	public void dispose() {
		worldView.dispose();
	}

}

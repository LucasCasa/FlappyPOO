package state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import component.WorldManager;
import component.WorldSettings;
import component.bird.BirdType;
import component.views.WorldManagerView;
import start.Flappy;

public class PlayState extends State {

	private static final int X_CAM_OFFSET = 250;
	public static final float ZOOM_CAMARA = 1.2f;

	private WorldManager world;
	private WorldManagerView worldView;
	private Sound s;

	public PlayState(GameStateManager gsm, String p1Name, String p2Name, BirdType birdType, BirdType birdType2) {
		super(gsm);
		System.out.println("************INICIO DE NUEVA PARTIDA************");
		System.out.println("GAAAAP: " + WorldSettings.getInstance().getGap());
		cam.setToOrtho(false, Flappy.WIDTH / ZOOM_CAMARA, Flappy.HEIGHT / ZOOM_CAMARA);
		System.out.println("CANTIDAD DE VIDAS DISPONIBLES: " + WorldSettings.getInstance().getLife());
		this.world = new WorldManager(cam, p1Name, p2Name, birdType, birdType2);
		this.worldView = new WorldManagerView(world);
		s = Gdx.audio.newSound(Gdx.files.internal("jump2.wav"));

	}

	@Override
	protected void handleInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			world.getBLeft().jump();
		}

		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			world.getBRight().jump();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
			s.play(1f);
			world.getBLeft().shoot();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
			s.play(1f);
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

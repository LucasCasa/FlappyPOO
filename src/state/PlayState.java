package state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import component.WorldManager;
import component.views.WorldManagerView;

public class PlayState extends State {

	private WorldManager world;
	private WorldManagerView worldView;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		System.out.println("************INICIO DE NUEVA PARTIDA************");

		this.world = new WorldManager(cam);
		this.worldView = new WorldManagerView(world);

	}

	@Override
	protected void handleInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.W))
			world.getBLeft().jump();

		if (Gdx.input.isKeyPressed(Input.Keys.UP))
			world.getBRight().jump();

		if (Gdx.input.isKeyJustPressed(Input.Keys.D))
			world.getBLeft().shoot();

		if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT))
			world.getBRight().shoot();

		// Deberia cambiarle el nombre a apply
		if (Gdx.input.isKeyJustPressed(Input.Keys.E))
			world.getBLeft().apply(Input.Keys.E, world.getBRight());

		if (Gdx.input.isKeyJustPressed(Input.Keys.R))
			world.getBLeft().apply(Input.Keys.R, world.getBRight());

		if (Gdx.input.isKeyJustPressed(Input.Keys.T))
			world.getBLeft().apply(Input.Keys.T, world.getBRight());

		if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {

		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.N)) {

		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.B)) {

		}

		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}

	}

	@Override
	public void update(float dt) {
		handleInput();
		world.update(dt);
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(world.getCam().combined);
		sb.begin();
		worldView.render(sb);
		sb.end();
	}

	@Override
	public void dispose() {
		worldView.dispose();
	}

}

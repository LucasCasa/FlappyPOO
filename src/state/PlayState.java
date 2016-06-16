package state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import component.WorldManager;
import component.bird.BirdType;
import component.views.WorldManagerView;

public class PlayState extends State {

	private WorldManager world;
	private WorldManagerView worldView;
	private Sound s;
	private String p1;
	private String p2;
	private BirdType b1;
	private BirdType b2;

	public PlayState(GameStateManager gsm, String p1Name, String p2Name, BirdType birdType, BirdType  birdType2) {
		super(gsm);
		System.out.println("************INICIO DE NUEVA PARTIDA************");
		
		this.p1=p1Name;
		this.p2=p2Name;
		this.b1=birdType;
		this.b2=birdType2;
		this.world = new WorldManager(cam, p1Name, p2Name, birdType, birdType2);
		this.worldView = new WorldManagerView(world);
		s = Gdx.audio.newSound(Gdx.files.internal("jump2.wav"));

	}


	@Override
	protected void handleInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.W)){
			world.getBLeft().jump();
		}

		if (Gdx.input.isKeyPressed(Input.Keys.UP)){
			world.getBRight().jump();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.D)){
			s.play(1f);
			world.getBLeft().shoot();
		}
			
		if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
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
		world.update(dt);
		if(!(world.getContinues())){
			gsm.set(new EndGame(gsm, p1, p2, b1, b2));
		}
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

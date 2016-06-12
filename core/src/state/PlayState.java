package state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import component.WorldComponent;
import component.WorldComponentView;

public class PlayState extends State {

	private WorldComponent world;
	private WorldComponentView worldView;


	public PlayState(GameStateManager gsm) {
		super(gsm);
		System.out.println("************INICIO DE NUEVA PARTIDA************");

		this.world = new WorldComponent(cam);
		this.worldView = new WorldComponentView(world);

	}

	@Override
	protected void handleInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.W))
			world.getBird().jump();

		if (Gdx.input.isKeyPressed(Input.Keys.UP))
			world.getBird2().jump();

		if (Gdx.input.isKeyJustPressed(Input.Keys.D))
			world.getBird().shoot();
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT))
			world.getBird2().shoot();
		
		//Deberia cambiarle el nombre a apply
		if(Gdx.input.isKeyJustPressed(Input.Keys.E))
			world.getBird().apply(Input.Keys.E, world.getBird2());
			
		if(Gdx.input.isKeyJustPressed(Input.Keys.R))
			world.getBird().apply(Input.Keys.R, world.getBird2());
			
		if(Gdx.input.isKeyJustPressed(Input.Keys.T))
			world.getBird().apply(Input.Keys.T, world.getBird2());
			
		if(Gdx.input.isKeyJustPressed(Input.Keys.M))
			
			
		if(Gdx.input.isKeyJustPressed(Input.Keys.N))
				
			
		if(Gdx.input.isKeyJustPressed(Input.Keys.B))

			
		
			if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
			Gdx.app.exit();
		
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

package state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import start.Flappy;

public class EndGame extends State {

	private Texture background;
	private Texture playBtn;

	public EndGame(GameStateManager gsm) {
		super(gsm);
		//cam.setToOrtho(false, Flappy.WIDTH / ZOOM_CAMARA, Flappy.HEIGHT / ZOOM_CAMARA);
		background = new Texture("bg2.png");
		playBtn = new Texture("playBtn.png");

	}

	@Override
	public void handleInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_1))
		//if (Gdx.input.justTouched())
			gsm.set(new PlayState(gsm));
		
	}

	@Override
	public void update(float dt) {
		handleInput();
	}

	@Override
	public void render(SpriteBatch sb) {
		
		sb.begin();
		sb.draw(background, cam.position.x - (cam.viewportWidth / 2), 0);
		sb.draw(playBtn, (Flappy.WIDTH / 2) - (playBtn.getWidth() / 2), (Flappy.HEIGHT / 2) - (playBtn.getWidth() / 2));
		sb.end();
	}

	public void dispose() {
		background.dispose();
		playBtn.dispose();
	}

}

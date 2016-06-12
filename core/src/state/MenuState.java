package state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ar.edu.itba.Flappy;
import component.Types;

public class MenuState extends State {

	private Texture logo;
	private Texture background;
	private Texture playBtn;

	public MenuState(GameStateManager gsm) {
		super(gsm);
		//cam.setToOrtho(false, Flappy.WIDTH / ZOOM_CAMARA, Flappy.HEIGHT / ZOOM_CAMARA);
		logo = new Texture(Types.LOGO);
		background = new Texture(Types.BACKGROUND);
		playBtn = new Texture(Types.PLAYBTN);

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
		sb.draw(background, 0, 0, Flappy.WIDTH, Flappy.HEIGHT);
		sb.draw(logo, (Flappy.WIDTH / 2) - (logo.getWidth() / 2), (Flappy.HEIGHT / 2) - (logo.getWidth() / 2));
		sb.draw(playBtn, (Flappy.WIDTH / 2) - (playBtn.getWidth() / 2), (Flappy.HEIGHT / 2) - (playBtn.getWidth() / 2));
		sb.end();
	}

	public void dispose() {
		background.dispose();
		playBtn.dispose();
	}

}

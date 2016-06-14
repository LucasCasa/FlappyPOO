package state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import component.views.EndComponentView;
import component.worldComponent.EndComponent;
import start.Flappy;

public class EndGame extends State {

	private EndComponent end;
	private EndComponentView endView;

	public EndGame(GameStateManager gsm) {
		super(gsm);
		//cam.setToOrtho(false, Flappy.WIDTH / ZOOM_CAMARA, Flappy.HEIGHT / ZOOM_CAMARA);
		end= new EndComponent(gsm);
		endView= new EndComponentView(end);

	}

	@Override
	public void handleInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_1))
			end.Select(Options.MAIN);
	
	
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_2))
			end.Select(Options.PLAY_AGAIN);
	
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_3))
			end.Select(Options.EXIT);
		
	}

	@Override
	public void update(float dt) {
		handleInput();
		end.update();
	}

	@Override
	public void render(SpriteBatch sb) {
		endView.render(sb);
	}

	public void dispose() {
		endView.dispose();
	}

}

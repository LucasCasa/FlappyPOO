package state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import component.bird.BirdType;
import component.views.EndComponentView;
import component.worldComponent.EndComponent;

public class EndGame extends State {

	private EndComponent end;
	private EndComponentView endView;

	public EndGame(GameStateManager gsm) {
		super(gsm);
		//cam.setToOrtho(false, Flappy.WIDTH / ZOOM_CAMARA, Flappy.HEIGHT / ZOOM_CAMARA);
		end= new EndComponent(cam, gsm);
		endView= new EndComponentView(end);

	}

	@Override
	public void handleInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.A))
			end.Select(Options.PLAY_AGAIN);
	
		if (Gdx.input.isKeyPressed(Input.Keys.B))
			end.Select(Options.TO_MAIN_MENU);
	
		if (Gdx.input.isKeyPressed(Input.Keys.C))
			end.Select(Options.EXIT);
		
	}

	@Override
	public void update(float dt) {
		if(end.currentSelect() == Options.MAIN)
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

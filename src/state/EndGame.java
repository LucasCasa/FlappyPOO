package state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import menu.EndManager;
import menu.EndView;

public class EndGame extends State {

	private EndManager end;
	private EndView endView;

	public EndGame(GameStateManager gsm) {
		super(gsm);
		end = new EndManager(cam, gsm);
		endView = new EndView(end);

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
		if (end.currentSelect() == Options.MAIN)
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

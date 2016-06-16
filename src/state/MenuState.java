package state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import component.views.MenuMangerView;
import component.worldComponent.MenuManager;

public class MenuState extends State {

	private MenuManager menu;
	private MenuMangerView menuView;

	public MenuState(GameStateManager gsm) {
		super(gsm);
		this.menu = new MenuManager();
		this.menuView = new MenuMangerView(menu);
	}

	@Override
	public void handleInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)/* && menu.currentSelect() != Options.SELECT_PLAYERS*/){
				menu.Select(Options.SELECT_PLAYERS);
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_2))
			menu.Select(Options.HIGHSCORES);
		
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_3))
			menu.Select(Options.SELECT_DIFICULTY);
		
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
//			if(menu.currentSelect() == Options.MAIN)
//				Gdx.app.exit();
//			else
				menu.Select(Options.MAIN);
		}
	}
	
	public void keyDown(int keycode) {
		if (menu.currentSelect() == Options.SELECT_PLAYERS)
			menu.keyDown(keycode);
	}
	
	@Override
	public void update(float dt) {
		if (menu.currentSelect() == Options.MAIN){
			handleInput();
	}
		menu.update();
		if(menu.selectDone()) {
			gsm.set(new PlayState(gsm, menu.getP1Name(), menu.getP2Name(), menu.getP1Bird(), menu.getP2Bird()));
		}
		
	}

	@Override
	public void render(SpriteBatch sb) {
		menuView.render(sb);
	}

	public void dispose() {
		menuView.dispose();
	}

}

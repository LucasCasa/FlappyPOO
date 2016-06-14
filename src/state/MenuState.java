package state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import component.views.MenuComponentView;
import component.worldComponent.MenuComponent;

public class MenuState extends State {

	private MenuComponent menu;
	private MenuComponentView menuView;

	public MenuState(GameStateManager gsm) {
		super(gsm);
		this.menu = new MenuComponent();
		this.menuView = new MenuComponentView(menu);
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
		handleInput();
		menu.update();
	}

	@Override
	public void render(SpriteBatch sb) {
		menuView.render(sb);
	}

	public void dispose() {
		menuView.dispose();
	}

}

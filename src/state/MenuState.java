package state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import component.worldComponent.Types;
import menu.MenuManager;
import menu.MenuMangerView;
import world.music.FadeOutManager;
import world.music.MenuMusic;

public class MenuState extends State {

	private MenuManager menu;
	private MenuMangerView menuView;

	public MenuState(GameStateManager gsm) {
		super(gsm);
		this.menu = new MenuManager();
		this.menuView = new MenuMangerView(menu);
	}
	
	public MenuState(GameStateManager gsm, Boolean replay){
		super(gsm);
		this.menu=new MenuManager();
		this.menuView = new MenuMangerView(menu);
		if(replay){
			menu.Select(Options.SELECT_PLAYERS);
		}
	}

	@Override
	public void handleInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_1))
				menu.Select(Options.SELECT_PLAYERS);
		
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_2))
			menu.Select(Options.HIGHSCORES);
		
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_3))
			menu.Select(Options.SELECT_DIFICULTY);
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
			Gdx.app.exit();
	}
	
	public void keyDown(int keycode) {
		if(Math.random() > 0.5)
			Types.typing1.play(0.5f);
		else
			Types.typing2.play(0.5f);

		if (menu.currentSelect() == Options.SELECT_PLAYERS)
			menu.keyDown(keycode);
	}
	
	@Override
	public void update(float dt) {
		if (menu.currentSelect() == Options.MAIN) {
			handleInput();
		}
		
		if (menu.currentSelect() == Options.SELECT_DIFICULTY) {
			if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
				menu.Select(Options.MAIN);
		}

		if (menu.currentSelect() == Options.SELECT_PLAYERS) {
			if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
				menu.resetPlayers();
				menu.Select(Options.MAIN);
			}
		}

		if (menu.currentSelect() == Options.HIGHSCORES) {
			if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
				menu.Select(Options.MAIN);
			}
		}

		
		menu.update();
		
		if(menu.selectDone()) {
			(new Thread(new FadeOutManager(MenuMusic.getInstance()))).start();
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

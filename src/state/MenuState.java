package state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import component.worldComponent.GameMode;
import component.worldComponent.Types;
import menu.MenuManager;
import menu.MenuMangerView;
import music.*;

import java.util.Locale;
import java.util.ResourceBundle;

public class MenuState extends State {

	private MenuManager menu;
	private MenuMangerView menuView;
	private GameMode gameMode;

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
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_1) || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_1)){
			menu.Select(Options.SELECT_PLAYERS);
			gameMode = GameMode.CLASSIC;
		}else if (Gdx.input.isKeyPressed(Input.Keys.NUM_2) || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_2)) {
			menu.Select(Options.SELECT_PLAYERS);
			gameMode = GameMode.COUNTDOWN;
		}else if (Gdx.input.isKeyPressed(Input.Keys.NUM_3) || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_3)) {
			menu.Select(Options.SELECT_PLAYERS);
			gameMode = GameMode.COOPERATIVE;
		}else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_4) || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_4)) {
			menu.Select(Options.SELECT_DIFICULTY);
		}else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_5) || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_5)) {
			menu.Select(Options.HELP1);
		}else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_6) || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_6)) {
			Types.MESSAGES = ResourceBundle.getBundle("text", Locale.ENGLISH);
		}else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_7) || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_7)) {
			Types.MESSAGES = ResourceBundle.getBundle("text", Locale.forLanguageTag("ES"));
		}else if(Gdx.input.isKeyJustPressed(Input.Keys.TAB)){
			switch (menu.currentSelect()){
				case HELP1: menu.Select(Options.HELP2); break;
				case HELP2: menu.Select(Options.HELP3); break;
				case HELP3: menu.Select(Options.MAIN); break;
			}
		}else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
			Gdx.app.exit();
		}

	}
	
	public void keyDown(int keycode) {
		if(Math.random() > 0.5)
			Types.typing1.play(Types.masterVolume);
		else
			Types.typing2.play(Types.masterVolume);

		if (menu.currentSelect() == Options.SELECT_PLAYERS)
			menu.keyDown(keycode);
	}
	
	@Override
	public void update(float dt) {
		switch (menu.currentSelect()){
			case HELP1:
			case HELP2:
			case HELP3:
			case MAIN:
				handleInput();
				break;
			case SELECT_PLAYERS:
				if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
					menu.resetPlayers();
			case SELECT_DIFICULTY:
			case HIGHSCORES:
				if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
					menu.Select(Options.MAIN);
				break;
			default:
				break;

		}
		menu.update();
		
		if(menu.selectDone()) {
			(new Thread(new FadeOutManager(MenuMusic.getInstance()))).start();
			System.out.println("PONGO PLAY LVL1");
			if(gameMode == GameMode.CLASSIC){
				Types.LEVEL1_VOICE[Integer.parseInt(Types.MESSAGES.getString("id"))].play(Types.masterVolume);
				Level1Music.getInstance().play(Types.masterVolume);
			}else if(gameMode == GameMode.COUNTDOWN){
				Types.START[Integer.parseInt(Types.MESSAGES.getString("id"))].play(Types.masterVolume);
				Mode2Music.getInstance().play(Types.masterVolume);
			}else if(gameMode == GameMode.COOPERATIVE){
				Types.START[Integer.parseInt(Types.MESSAGES.getString("id"))].play(Types.masterVolume);
				Mode3Music.getInstance().play(Types.masterVolume);
			}
			gsm.set(new PlayState(gsm, menu.getP1Name(), menu.getP2Name(), menu.getP1Bird(), menu.getP2Bird(),gameMode));
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

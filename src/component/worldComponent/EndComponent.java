package component.worldComponent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

import component.bird.BirdType;
import state.GameStateManager;
import state.MenuState;
import state.Options;
import state.PlayState;

public class EndComponent {
	
	private Options option;
	private GameStateManager gsm;
	private OrthographicCamera cam;
	
	public EndComponent(OrthographicCamera cam, GameStateManager gsm){
		
		this.cam=cam;
		this.option = Options.MAIN;
		this.gsm=gsm;
		
	}
	
	public void update() {
		switch(option) {
			case MAIN: {
				break;
			}
									
			case PLAY_AGAIN: {
				gsm.set(new MenuState(gsm, true)); // esta va directo a play
				break;
			}
			
			case TO_MAIN_MENU: {
				gsm.set(new MenuState(gsm));
				break;
			}
			
			case EXIT: {
				Gdx.app.exit();
			}						
		}
	}
	
	public OrthographicCamera getCam() {
		return cam;
	}

	public Options currentSelect() {
		return option;
	}
	
	public void Select(Options option) {
		this.option = option;
	}

	
}

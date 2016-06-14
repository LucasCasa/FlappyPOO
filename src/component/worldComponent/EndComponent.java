package component.worldComponent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;

import state.GameStateManager;
import state.MenuState;
import state.Options;
import state.PlayState;

public class EndComponent {
	
	private Options option;
	private GameStateManager gsm;
	
	public EndComponent(GameStateManager gsm){
		this.option = Options.MAIN;
		this.gsm=gsm;
	}
	
	public void update() {
		switch(option) {
			case MAIN: {
				gsm.set(new MenuState(gsm));
				break;
			}
									
			case PLAY_AGAIN: {
				// como recargo la partida anterior? podemos hacer dos constructores diferentes en PlayState
				gsm.set(new PlayState(gsm));
				break;
			}
			
			case EXIT: {
				Gdx.app.exit();
			}						
		}
	}
	
	public Options currentSelect() {
		return option;
	}
	
	public void Select(Options option) {
		this.option = option;
	}

	
}

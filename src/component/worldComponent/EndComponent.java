package component.worldComponent;

import com.badlogic.gdx.Gdx;

import component.bird.BirdType;
import state.GameStateManager;
import state.MenuState;
import state.Options;
import state.PlayState;

public class EndComponent {
	private Options option;
	private GameStateManager gsm;
	private String p1;
	private String p2;
	private BirdType b1;
	private BirdType b2;
	
	public EndComponent(GameStateManager gsm,  String p1Name, String p2Name, BirdType birdType, BirdType  birdType2){
		this.option = Options.MAIN;
		this.gsm=gsm;
		this.p1=p1Name;
		this.p2=p2Name;
		this.b1=birdType;
		this.b2=birdType2;
	}
	
	public void update() {
		switch(option) {
			case MAIN: {
				gsm.set(new MenuState(gsm));
				break;
			}
									
			case PLAY_AGAIN: {
				// como recargo la partida anterior? podemos hacer dos constructores diferentes en PlayState
				gsm.set(new PlayState(gsm, p1, p2, b1, b2));
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

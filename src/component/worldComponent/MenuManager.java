package component.worldComponent;


import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.Array;

import component.WorldSettings;
import component.bird.BirdType;
import state.GameStateManager;
import state.Options;
import state.PlayState;

public class MenuManager {
	
	private Options option;
	
	private String p1Name, p2Name;
	
	private BirdType p1Bird, p2Bird;
	
	private boolean writing, endSelect;
	
	private int currentPlayer;
	
	private List<Tubes> tubes;
	
	private String name;
	
	public MenuManager(){
		option = Options.MAIN;
		currentPlayer = 1;
		name = "";
		writing = true;
		tubes = new ArrayList<Tubes>();
		for (int i = 1; i <= WorldSettings.getInstance().getTubeCount(); i++) {
			//tubes.add(new Tubes(i * (WorldSettings.getInstance().getTubeSpacing() + Tubes.WIDTH)));
			tubes.add(new Tubes(i * 5));
		}
	}
	
	public void update() {
		switch(option) {
			case MAIN: {
				break;
			}
									
			case SELECT_PLAYERS: {
				if (!writing) {
					setPlayerBird();
				}
					
				break;
			}
			
			case HIGHSCORES: {
				
			}
			
			case SELECT_DIFICULTY: {
				gameSettings();
			}						
		}
	}
	
	public String getName() {
		return this.name;
	}

	public int getCurrentPlayerSelect() {
		return this.currentPlayer;
	}
		
	public void keyDown(int keycode) {
		if(writing)
			writeName(keycode);
	}
	
	public boolean selectDone() {
		return endSelect;
	}
	
	public boolean writingNames() {
		return writing;
	}
	
	private void writeName(int KeyCode){
	    String key = Input.Keys.toString(KeyCode);
	    //aca deberia usarse "MAXNAMELENGTH" como una constante
	    if(key.length() == 1 && name.length() < 25)
	    	this.name = name.concat(key);
	    else if(KeyCode == Keys.ENTER){
	    	if(currentPlayer == 1) {
	        p1Name = name;
	        name = "";
	        writing = false;
	    	} else {
	    		p2Name = name;
	    		writing = false;
	    	}
	        return;
	    }else if(KeyCode == Keys.DEL){
	        if(!(name.length()==0))
	            this.name = name.substring(0, name.length()-1);
	    }
	    return;
	}
	
	public void gameSettings() {
//		for (int i = 1; i <= WorldSettings.getInstance().getTubeCount(); i++) {
//			tubes.add(new Tubes(i * (WorldSettings.getInstance().getTubeSpacing() + Tubes.WIDTH)));
//		}
	}
	
	public List<Tubes> getSettingTubes() {
		return tubes;
	}
	
	public void setPlayerBird() {
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
			writing = true;
			if (currentPlayer == 1) {
				p1Bird = BirdType.GREEN;
				currentPlayer = 2;
			}
			else  {
				p2Bird = BirdType.GREEN;
				endSelect = true;
			}
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
			writing = true;
			if (currentPlayer == 1) {
				p1Bird = BirdType.RED;
				currentPlayer = 2;
			} else  {
				p2Bird = BirdType.RED;
				endSelect = true;
			}
		}

		if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {
			writing = true;
			if (currentPlayer == 1) {
				p1Bird = BirdType.CLASSIC;
				currentPlayer = 2;
			} else  {
				p2Bird = BirdType.CLASSIC;
				endSelect = true;
			}
		}

		if (Gdx.input.isKeyPressed(Input.Keys.NUM_4)) {
			writing = true;
			if (currentPlayer == 1) {
				p1Bird = BirdType.BLUE;
				currentPlayer = 2;
			} else {
				p2Bird = BirdType.BLUE;
				endSelect = true;
			}
				
		}

	}

	public Options currentSelect() {
		return option;
	}
	
	public void Select(Options option) {
		this.option = option;
	}
	
	public void handlePlayerSelect() {
		
	}
	
	public String getP1Name() {
		return p1Name;
	}
	
	public String getP2Name() {
		return p2Name;
	}

	public BirdType getP1Bird() {
		return p1Bird;
	}
	
	public BirdType getP2Bird() {
		return p2Bird;
	}	
}

package component.worldComponent;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;

import state.GameStateManager;
import state.Options;
import state.PlayState;

public class MenuManager {
	
	private Options option;
	
	private String p1Name, p2Name;
	
	private int p1Bird, p2Bird;
	
	private boolean writing, endSelect;
	
	private int currentPlayer;
	
	private String name;
	
	public MenuManager(){
		option = Options.MAIN;
		currentPlayer = 1;
		name = "";
		writing = true;
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
				if (endSelect) {
					GameStateManager.getInstance().set(new PlayState(GameStateManager.getInstance()));
				}
					
				break;
			}
			
			case HIGHSCORES: {
				
			}
			
			case SELECT_DIFICULTY: {
				
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
	
	public void setPlayerBird() {
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
			writing = true;
			if (currentPlayer == 1) {
				p1Bird = 1;
				currentPlayer = 2;
			}
			else  {
				p2Bird = 1;
				endSelect = true;
			}
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
			writing = true;
			if (currentPlayer == 1) {
				p1Bird = 2;
				currentPlayer = 2;
			} else  {
				p2Bird = 2;
				endSelect = true;
			}
		}

		if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {
			writing = true;
			if (currentPlayer == 1) {
				p1Bird = 3;
				currentPlayer = 2;
			} else  {
				p2Bird = 3;
				endSelect = true;
			}
		}

		if (Gdx.input.isKeyPressed(Input.Keys.NUM_4)) {
			writing = true;
			if (currentPlayer == 1) {
				p1Bird = 4;
				currentPlayer = 2;
			} else {
				p2Bird = 4;
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
	
	public String getP1() {
		return p1Name;
	}
	
	public String getP2() {
		return p2Name;
	}

	
}

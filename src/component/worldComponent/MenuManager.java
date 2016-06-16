package component.worldComponent;


import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.Array;

import component.WorldSettings;
import component.bird.BirdType;
import start.Flappy;
import state.GameStateManager;
import state.MenuState;
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
	
	private int setting;
	
	public MenuManager(){
		option = Options.MAIN;
		currentPlayer = 1;
		name = "";
		writing = true;
		tubes = new ArrayList<Tubes>();
		setting = 1;
//		for (int i = 1; i <= WorldSettings.getInstance().getTubeCount(); i++) {
//			//tubes.add(new Tubes(i * (WorldSettings.getInstance().getTubeSpacing() + Tubes.WIDTH)));
//			tubes.add(new Tubes(i * (WorldSettings.getInstance().getTubeSpacing() + Tubes.WIDTH)));
//		}
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
		WorldSettings ws = WorldSettings.getInstance();
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			if (setting == 1)
				setting = 5;
			else
				setting -= 1;
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
			if (setting == 5)
				setting = 1;
			else
				setting += 1;		
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
			if(setting == 5)
				ws.setDefaultPreferences();
		}

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			switch (setting) {
				case 1: {
					if(ws.getGap() > ws.getMinGap())
					ws.setGap(ws.getGap() - 1);
					break;
				}
				case 2: {
					if(ws.getFluctuation() > ws.getMinFluctuation())
					ws.setFluctuation(ws.getFluctuation() - 1);
					break;
				}
				case 3: {
					if(ws.getLife() > 0)
						ws.setLife(ws.getLife() - 1);					
					break;
				}
				case 4: {
					if(ws.getBombs() > 0)
						ws.setBombs(ws.getBombs() - 1);
					break;
				}
			}
		}

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			switch (setting) {
				case 1: {
					if(ws.getGap() < ws.getMaxGap())
					ws.setGap(ws.getGap() + 1);
					break;
				}
				case 2: {
					if(ws.getFluctuation() < ws.getMaxFluctuation())
					ws.setFluctuation(ws.getFluctuation() + 1);
					break;
				}
				case 3: {
					//if(ws.getLife() < ws.getMaxLifes())
					ws.setLife(ws.getLife() + 1);					
					break;
				}
				case 4: {
					ws.setBombs(ws.getBombs() + 1);
					break;
				}
			}
		}

		
	}
	
	public int getSettingPos() {
		return setting;
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
	
	public void resetPlayers() {
		p1Name = "";
		p2Name = "";
		currentPlayer = 1;
		endSelect = false;
		writing = true;
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

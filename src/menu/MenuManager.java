package menu;


import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;

import component.bird.BirdType;
import component.tube.Tubes;
import state.Options;
import world.WorldSettings;

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
	}
	
	public void update() {
		switch(option) {
			case SELECT_PLAYERS:
				if (!writing)
					setPlayerBird();
				break;
			case SELECT_DIFICULTY: gameSettings(); break;
		default:
			break;						
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
	
	/**
	 * Escribe en pantalla lo que recibe por teclado. 
	 * Si presiona ENTER, deja de escribir.
	 * 
	 * @param KeyCode
	 */
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
				p1Bird = BirdType.SILVER;
				currentPlayer = 2;
			}
			else  {
				p2Bird = BirdType.SILVER;
				endSelect = true;
			}
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
			writing = true;
			if (currentPlayer == 1) {
				p1Bird = BirdType.GREEN;
				currentPlayer = 2;
			} else  {
				p2Bird = BirdType.GREEN;
				endSelect = true;
			}
		}

		if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {
			writing = true;
			if (currentPlayer == 1) {
				p1Bird = BirdType.BLUE;
				currentPlayer = 2;
			} else  {
				p2Bird = BirdType.BLUE;
				endSelect = true;
			}
		}

		if (Gdx.input.isKeyPressed(Input.Keys.NUM_4)) {
			writing = true;
			if (currentPlayer == 1) {
				p1Bird = BirdType.RED;
				currentPlayer = 2;
			} else {
				p2Bird = BirdType.RED;
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

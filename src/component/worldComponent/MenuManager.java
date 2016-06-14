package component.worldComponent;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;

import state.Options;

public class MenuComponent {
	
	private Options option;
	
	private String p1, p2;
	
	private int currentPlayer;
	
	private String name;
	
	public MenuComponent(){
		option = Options.MAIN;
		currentPlayer = 1;
		name = "";
	}
	
	public void update() {
		switch(option) {
			case MAIN: {
				break;
			}
									
			case SELECT_PLAYERS: {
				//esto puede cambiar
//				if(currentPlayer == 1) {
//
//					//writeName(KeyboardManager.getKeyDown());
//					Scanner scan = new Scanner(System.in);
//					String s = scan.next();
//					p1.setName(p1.getName() + s);
//
//						
//				} else {
//
//				}
//

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
		writeName(keycode);
	}
	
	private void writeName(int KeyCode){
	    String key = Input.Keys.toString(KeyCode);
	    //aca deberia usarse "MAXNAMELENGTH" como una constante
	    if(key.length() == 1 && name.length() < 25)
	    	this.name = name.concat(key);
	    else if(KeyCode == Keys.ENTER){
	    	if(currentPlayer == 1) {
	        p1 = name;
	        name = "";
	        currentPlayer = 2;
	    	} else {
	    		currentPlayer = 0;
	    		p2 = name;
	    	}
	        return;
	    }else if(KeyCode == Keys.DEL){
	        if(!(name.length()==0))
	            this.name = name.substring(0, name.length()-1);
	    }
	    return;
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
		return p1;
	}
	
	public String getP2() {
		return p2;
	}

	
}

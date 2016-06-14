package component.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import component.Types;
import component.worldComponent.MenuComponent;
import start.Flappy;


public class MenuComponentView {
	
	private Texture logo;
	private Texture background;
	private Texture playBtn;
	//esto tiene que ir a la clase assets en los "FONTS"
	public BitmapFont FONT;
	
	private MenuComponent menu;
	
	public MenuComponentView(MenuComponent menu) {
		this.menu = menu;
		
		FONT = new BitmapFont(Gdx.files.internal("arcade.fnt"));
		
		logo = new Texture(Types.LOGO);
		background = new Texture(Types.BACKGROUND);
		playBtn = new Texture(Types.PLAYBTN);
	}
	
	public void render(SpriteBatch sb) {
		sb.begin();
		switch(menu.currentSelect()) {
			case MAIN: {
				drawMainMenu(sb);
				break;
			}
									
			case SELECT_PLAYERS: {
				if(menu.getCurrentPlayerSelect() == 1) {
					drawPlayerSelect(sb, 1);
				}	else {
					drawPlayerSelect(sb, 2);
				}
			}
			
			case HIGHSCORES: {
				
			}
			
			case SELECT_DIFICULTY: {
				
			}
								
		}

		sb.end();
	}

	public void dispose() {
		background.dispose();
		playBtn.dispose();
		FONT.dispose();
	}
	
	public void drawMainMenu(SpriteBatch sb) {
		sb.draw(background, 0, 0, Flappy.WIDTH, Flappy.HEIGHT);
		sb.draw(logo, (Flappy.WIDTH / 2) - (logo.getWidth() / 2), (Flappy.HEIGHT) - (logo.getWidth() / 2));
		FONT.draw(sb, "1 - Play FlappyPOO", Flappy.WIDTH*1/4, Flappy.HEIGHT*2/3);
		FONT.draw(sb, "2 - View Highscores", Flappy.WIDTH*1/4, Flappy.HEIGHT*4/7);
		FONT.draw(sb, "3 - Set Dificulty", Flappy.WIDTH*1/4, Flappy.HEIGHT*10/21);
	}
	
	public void drawPlayerSelect(SpriteBatch sb, int player) {
		sb.draw(background, 0, 0, Flappy.WIDTH, Flappy.HEIGHT);
		
		if(player == 1) {
			FONT.draw(sb, "Choose a name for Player 1", Flappy.WIDTH*1/9, Flappy.HEIGHT*15/16);
		}
		else {
			FONT.draw(sb, "Choose a name for Player 2", Flappy.WIDTH*1/9, Flappy.HEIGHT*15/16);
		}
		
		FONT.draw(sb, menu.getName(), Flappy.WIDTH*1/8, Flappy.HEIGHT*15/18);

	}

}

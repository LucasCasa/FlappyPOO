package component.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import component.Types;
import component.worldComponent.MenuManager;
import start.Flappy;


public class MenuMangerView {
	
	private Texture logo;
	private Texture background;
	private Texture playBtn;
	
	private Texture birdRed, birdGreen, birdClassic, birdBlue;
	
	private Texture bullet1, bullet2, bullet3, bullet4;
	//esto tiene que ir a la clase assets en los "FONTS"
	public BitmapFont FONT;
	
	private MenuManager menu;
	
	public MenuMangerView(MenuManager menu) {
		this.menu = menu;
		
		FONT = new BitmapFont(Gdx.files.internal("arcade.fnt"));
		
		logo = new Texture(Types.LOGO);
		background = new Texture(Types.BACKGROUND);
		playBtn = new Texture(Types.PLAYBTN);
		
		birdRed = new Texture(Types.RED_BIRD);
		birdGreen = new Texture(Types.GREEN_BIRD);
		birdClassic = new Texture(Types.CLASSIC_BIRD);
		birdBlue = new Texture(Types.BLUE_BIRD);
		
		bullet1 = new Texture(Types.SILVER_BULLET_INV);
		bullet2 = new Texture(Types.GOLDEN_BULLET_INV);
		bullet3 = new Texture(Types.CLASSIC_BULLET_INV);
		bullet4 = new Texture(Types.BLUE_BULLET_INV);
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
				
				break;
			}			
			case SELECT_DIFICULTY: {
				
				break;
			}
								
		}

		sb.end();
	}

	public void dispose() {
		background.dispose();
		playBtn.dispose();
		FONT.dispose();
		birdGreen.dispose();
		birdRed.dispose();
		birdClassic.dispose();
		birdBlue.dispose();
		bullet1.dispose();
		bullet2.dispose();
		bullet3.dispose();
		bullet4.dispose();
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

		if (menu.writingNames()) {
			if (player == 1) {
				FONT.draw(sb, "Choose a name for Player 1", Flappy.WIDTH*1/9, Flappy.HEIGHT*15/16);
			}
			else {
				FONT.draw(sb, "Choose a name for Player 2", Flappy.WIDTH*1/9, Flappy.HEIGHT*15/16);
			}
			FONT.draw(sb, menu.getName(), Flappy.WIDTH*1/8, Flappy.HEIGHT*15/18);
		} else {
			if (player == 1) {
				FONT.draw(sb, "Select a bird for Player 1", Flappy.WIDTH*1/9, Flappy.HEIGHT*15/16);
			} else {
				FONT.draw(sb, "Select a bird for Player 2", Flappy.WIDTH*1/9, Flappy.HEIGHT*15/16);				
			}
			
			//GOLD
			FONT.draw(sb, "1 - GOLD   550", Flappy.WIDTH*1/8, Flappy.HEIGHT*21/25);
			sb.draw(birdGreen, Flappy.WIDTH*1/8, Flappy.HEIGHT*18/25);
			sb.draw(bullet2, Flappy.WIDTH*1/4, Flappy.HEIGHT*18/25);
			sb.draw(bullet2, Flappy.WIDTH*1/3, Flappy.HEIGHT*18/25);
			sb.draw(bullet2, Flappy.WIDTH*5/12, Flappy.HEIGHT*18/25);

			//SILVER
			FONT.draw(sb, "2 - SILVER   450", Flappy.WIDTH*1/8, Flappy.HEIGHT*249/400);
			sb.draw(birdRed, Flappy.WIDTH*1/8, Flappy.HEIGHT*201/400);
			sb.draw(bullet1, Flappy.WIDTH*1/4, Flappy.HEIGHT*201/400);
			sb.draw(bullet1, Flappy.WIDTH*1/3, Flappy.HEIGHT*201/400);
			sb.draw(bullet1, Flappy.WIDTH*5/12, Flappy.HEIGHT*201/400);

			//CLASSIC
			FONT.draw(sb, "3 - CLASSIC   400", Flappy.WIDTH*1/8, Flappy.HEIGHT*81/190);
			sb.draw(birdClassic, Flappy.WIDTH*1/8, Flappy.HEIGHT*29/100);
			sb.draw(bullet3, Flappy.WIDTH*1/4, Flappy.HEIGHT*29/100);
			sb.draw(bullet3, Flappy.WIDTH*1/3, Flappy.HEIGHT*29/100);
			sb.draw(bullet3, Flappy.WIDTH*5/12, Flappy.HEIGHT*29/100);

			//BLUE
			FONT.draw(sb, "4 - BLUE   250", Flappy.WIDTH*1/8, Flappy.HEIGHT*77/400);
			sb.draw(birdBlue, Flappy.WIDTH*1/8, Flappy.HEIGHT*29/400);
			sb.draw(bullet4, Flappy.WIDTH*1/4, Flappy.HEIGHT*29/400);
			sb.draw(bullet4, Flappy.WIDTH*1/3, Flappy.HEIGHT*29/400);
			sb.draw(bullet4, Flappy.WIDTH*5/12, Flappy.HEIGHT*29/400);						
		}
	}

}

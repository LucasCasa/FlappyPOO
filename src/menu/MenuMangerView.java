package menu;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import component.worldComponent.Types;
import desktop.Flappy;
import scoreFiles.Input;
import world.WorldSettings;


public class MenuMangerView {
	
	private Texture logo;
	private Texture background;

	private Texture birdRed, birdGreen, birdClassic, birdBlue;
	
	private Texture bullet1, bullet2, bullet3, bullet4;

	public BitmapFont text;
	
	private MenuManager menu;
	
	public MenuMangerView(MenuManager menu) {
		this.menu = menu;
		
		text = new BitmapFont(Gdx.files.internal("arcade.fnt"));
		
		logo = new Texture(Types.LOGO);
		background = new Texture(Types.BACKGROUND[0]);
		
		birdRed = new Texture(Types.RED_BIRD);
		birdGreen = new Texture(Types.GREEN_BIRD);
		birdClassic = new Texture(Types.CLASSIC_BIRD);
		birdBlue = new Texture(Types.BLUE_BIRD);
		
		bullet1 = new Texture(Types.SILVER_BULLET);
		bullet2 = new Texture(Types.GOLDEN_BULLET);
		bullet3 = new Texture(Types.CLASSIC_BULLET);
		bullet4 = new Texture(Types.BLUE_BULLET);
		
	}

	public void render(SpriteBatch sb) {
		sb.begin();		
		sb.draw(background, 0, 0, Flappy.WIDTH, Flappy.HEIGHT);

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
				break;
			}		
			case HIGHSCORES: {
				drawHighscore(sb);
				break;
			}			
			case SELECT_DIFICULTY: {
					drawSettings(sb);
				break;
			}
		default:
			break;
								
		}

		sb.end();
	}

	private void drawHighscore(SpriteBatch sb) {

		text.draw(sb, Input.getInstance().getTopHighscore(0), Flappy.WIDTH*1/4, Flappy.HEIGHT*2/3);
		text.draw(sb,Input.getInstance().getTopHighscore(1), Flappy.WIDTH*1/4, Flappy.HEIGHT*4/7);
		text.draw(sb, Input.getInstance().getTopHighscore(2), Flappy.WIDTH*1/4, Flappy.HEIGHT*10/21);
		text.draw(sb, Input.getInstance().getTopHighscore(3), Flappy.WIDTH*1/4, Flappy.HEIGHT*10/26);
		text.draw(sb, Input.getInstance().getTopHighscore(4), Flappy.WIDTH*1/4, Flappy.HEIGHT*10/33);
	}

	public void dispose() {
		background.dispose();
		text.dispose();
		birdGreen.dispose();
		birdRed.dispose();
		birdClassic.dispose();
		birdBlue.dispose();
		bullet1.dispose();
		bullet2.dispose();
		bullet3.dispose();
		bullet4.dispose();
	}
	
	public void drawSettings(SpriteBatch sb) {
		text.draw(sb, "Settings", Flappy.WIDTH*1/4, Flappy.HEIGHT*9/10);
		WorldSettings ws = WorldSettings.getInstance();
		
		text.getData().setScale(0.7f);
		switch (menu.getSettingPos()) {
			case 1: {
				text.draw(sb, ">", Flappy.WIDTH*1/13, Flappy.HEIGHT*9/12);
				break;
			}
			case 2: {
				text.draw(sb, ">", Flappy.WIDTH*1/13, Flappy.HEIGHT*10/15);				
				break;
			}
			case 3: {
				text.draw(sb, ">", Flappy.WIDTH*1/13, Flappy.HEIGHT*9/16);
				break;
			}
			case 4: {
				text.draw(sb, ">", Flappy.WIDTH*1/13, Flappy.HEIGHT*9/19);
				break;
			}
			case 5: {
				text.draw(sb, ">", Flappy.WIDTH*1/13, Flappy.HEIGHT*1/8);
				break;
			}

		}
		text.draw(sb, "TUBES GAP: " + ws.getGap(), Flappy.WIDTH*1/10, Flappy.HEIGHT*9/12);
		text.draw(sb, "TUBES HOLE FLUCTIATION: " + ws.getFluctuation(), Flappy.WIDTH*1/10, Flappy.HEIGHT*10/15);
		text.draw(sb, "MAX REACHABLE LIVES: " + ws.getLife(), Flappy.WIDTH*1/10, Flappy.HEIGHT*9/16);
		text.draw(sb, "MAX REACHABLE BOMBS: "+ ws.getBombs(), Flappy.WIDTH*1/10, Flappy.HEIGHT*9/19);
		text.getData().setScale(1);
		
		text.draw(sb, "SET DEFAULT SETTINGS", Flappy.WIDTH*1/10, Flappy.HEIGHT*1/8);
		
		
	}
	
	public void drawMainMenu(SpriteBatch sb) {
		sb.draw(logo, (Flappy.WIDTH / 2) - (logo.getWidth() / 2), (Flappy.HEIGHT) - (logo.getWidth() / 2));
		text.draw(sb, "1 - Play FlappyPOO", Flappy.WIDTH*1/4, Flappy.HEIGHT*2/3);
		text.draw(sb, "2 - View Highscores", Flappy.WIDTH*1/4, Flappy.HEIGHT*4/7);
		text.draw(sb, "3 - Set Dificulty", Flappy.WIDTH*1/4, Flappy.HEIGHT*10/21);
	}
	
	public void drawPlayerSelect(SpriteBatch sb, int player) {
		if (menu.writingNames()) {
			if (player == 1) {
				text.draw(sb, "Choose a name for Player 1", Flappy.WIDTH*1/9, Flappy.HEIGHT*15/16);
			}
			else {
				text.draw(sb, "Choose a name for Player 2", Flappy.WIDTH*1/9, Flappy.HEIGHT*15/16);
			}
			text.draw(sb, menu.getName(), Flappy.WIDTH*1/8, Flappy.HEIGHT*15/18);
		} else {
			if (player == 1) {
				text.draw(sb, "Select a bird for Player 1", Flappy.WIDTH*1/9, Flappy.HEIGHT*15/16);
			} else {
				text.draw(sb, "Select a bird for Player 2", Flappy.WIDTH*1/9, Flappy.HEIGHT*15/16);				
			}
			
			//GOLD
			text.draw(sb, "1 - GOLD   550", Flappy.WIDTH*1/8, Flappy.HEIGHT*21/25);
			sb.draw(birdGreen, Flappy.WIDTH*1/8, Flappy.HEIGHT*18/25);
			sb.draw(bullet2, Flappy.WIDTH*1/4, Flappy.HEIGHT*18/25);
			sb.draw(bullet2, Flappy.WIDTH*1/3, Flappy.HEIGHT*18/25);
			sb.draw(bullet2, Flappy.WIDTH*5/12, Flappy.HEIGHT*18/25);

			//SILVER
			text.draw(sb, "2 - SILVER   450", Flappy.WIDTH*1/8, Flappy.HEIGHT*249/400);
			sb.draw(birdRed, Flappy.WIDTH*1/8, Flappy.HEIGHT*201/400);
			sb.draw(bullet1, Flappy.WIDTH*1/4, Flappy.HEIGHT*201/400);
			sb.draw(bullet1, Flappy.WIDTH*1/3, Flappy.HEIGHT*201/400);
			sb.draw(bullet1, Flappy.WIDTH*5/12, Flappy.HEIGHT*201/400);

			//CLASSIC
			text.draw(sb, "3 - CLASSIC   400", Flappy.WIDTH*1/8, Flappy.HEIGHT*81/190);
			sb.draw(birdClassic, Flappy.WIDTH*1/8, Flappy.HEIGHT*29/100);
			sb.draw(bullet3, Flappy.WIDTH*1/4, Flappy.HEIGHT*29/100);
			sb.draw(bullet3, Flappy.WIDTH*1/3, Flappy.HEIGHT*29/100);
			sb.draw(bullet3, Flappy.WIDTH*5/12, Flappy.HEIGHT*29/100);

			//BLUE
			text.draw(sb, "4 - BLUE   250", Flappy.WIDTH*1/8, Flappy.HEIGHT*77/400);
			sb.draw(birdBlue, Flappy.WIDTH*1/8, Flappy.HEIGHT*29/400);
			sb.draw(bullet4, Flappy.WIDTH*1/4, Flappy.HEIGHT*29/400);
			sb.draw(bullet4, Flappy.WIDTH*1/3, Flappy.HEIGHT*29/400);
			sb.draw(bullet4, Flappy.WIDTH*5/12, Flappy.HEIGHT*29/400);						
		}
	}

}

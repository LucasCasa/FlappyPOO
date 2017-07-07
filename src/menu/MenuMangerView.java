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

	private Texture birdRed, birdGreen, birdSilver, birdBlue;
	
	private Texture bullet1, bullet2, bullet3, bullet4;

	private BitmapFont text;
	
	private MenuManager menu;
	private Texture[][] help = new Texture[2][];
	public MenuMangerView(MenuManager menu) {
		this.menu = menu;
		
		text = new BitmapFont(Gdx.files.internal("shadow.fnt"));
		
		logo = new Texture(Types.LOGO);
		background = new Texture("bgMenu.png");
		
		birdRed = new Texture(Types.RED_BIRD);
		birdGreen = new Texture(Types.GREEN_BIRD);
		birdSilver = new Texture(Types.SILVER_BIRD);
		birdBlue = new Texture(Types.BLUE_BIRD);

		bullet1 = new Texture(Types.SILVER_BULLET);
		bullet2 = new Texture(Types.LASER_BULLET);
		bullet3 = new Texture(Types.CANNON_BULLET);
		bullet4 = new Texture(Types.FIRE_BULLET);
		help[0] = new Texture[]{new Texture("help1en.png"),new Texture("help2en.png"),new Texture("help3en.png")};
		help[1] = new Texture[]{new Texture("help1es.png"),new Texture("help2es.png"),new Texture("help3es.png")};
	}

	public void render(SpriteBatch sb) {
		sb.begin();		
		sb.draw(background, 0, 0, Flappy.WIDTH, Flappy.HEIGHT);

		switch(menu.currentSelect()) {
			case MAIN:	drawMainMenu(sb);break;
			case SELECT_PLAYERS:
				if(menu.getCurrentPlayerSelect() == 1)
					drawPlayerSelect(sb, 1);
				else
					drawPlayerSelect(sb, 2);
				break;
			case HIGHSCORES: 		drawHighscore(sb);		break;
			case SELECT_DIFICULTY: 	drawSettings(sb);		break;
			case HELP1: 			drawHelp(sb,0);		break;
			case HELP2: 			drawHelp(sb,1);		break;
			case HELP3: 			drawHelp(sb,2);		break;
			default:
				break;
		}
		sb.end();
	}

	private void drawHighscore(SpriteBatch sb) {

		text.draw(sb, Input.getInstance().getTopHighscore(0), Flappy.WIDTH /4, Flappy.HEIGHT*2/3);
		text.draw(sb,Input.getInstance().getTopHighscore(1), Flappy.WIDTH /4, Flappy.HEIGHT*4/7);
		text.draw(sb, Input.getInstance().getTopHighscore(2), Flappy.WIDTH /4, Flappy.HEIGHT*10/21);
		text.draw(sb, Input.getInstance().getTopHighscore(3), Flappy.WIDTH /4, Flappy.HEIGHT*10/26);
		text.draw(sb, Input.getInstance().getTopHighscore(4), Flappy.WIDTH /4, Flappy.HEIGHT*10/33);
	}

	public void dispose() {
		background.dispose();
		text.dispose();
		birdGreen.dispose();
		birdRed.dispose();
		birdSilver.dispose();
		birdBlue.dispose();
		bullet1.dispose();
		bullet2.dispose();
		bullet3.dispose();
		bullet4.dispose();
	}
	
	private void drawSettings(SpriteBatch sb) {
		text.draw(sb, Types.MESSAGES.getString("settings"), Flappy.WIDTH /4, Flappy.HEIGHT*9/10);
		WorldSettings ws = WorldSettings.getInstance();
		
		text.getData().setScale(0.7f);
		switch (menu.getSettingPos()) {
			case 1: {
				text.draw(sb, ">", Flappy.WIDTH /13, Flappy.HEIGHT*9/12);
				break;
			}
			case 2: {
				text.draw(sb, ">", Flappy.WIDTH /13, Flappy.HEIGHT*10/15);
				break;
			}
			case 3: {
				text.draw(sb, ">", Flappy.WIDTH /13, Flappy.HEIGHT*9/16);
				break;
			}
			case 4: {
				text.draw(sb, ">", Flappy.WIDTH /13, Flappy.HEIGHT*9/19);
				break;
			}
			case 5: {
				text.draw(sb, ">", Flappy.WIDTH /13, Flappy.HEIGHT /8);
				break;
			}

		}
		text.draw(sb, Types.MESSAGES.getString("tubeGap") + ws.getGap(), Flappy.WIDTH /10, Flappy.HEIGHT*9/12);
		text.draw(sb, Types.MESSAGES.getString("tubeHole")+ ws.getFluctuation(), Flappy.WIDTH /10, Flappy.HEIGHT*10/15);
		text.draw(sb, Types.MESSAGES.getString("maxLives")+ ws.getLife(), Flappy.WIDTH /10, Flappy.HEIGHT*9/16);
		text.draw(sb, Types.MESSAGES.getString("maxBombs")+ ws.getBombs(), Flappy.WIDTH /10, Flappy.HEIGHT*9/19);
		text.getData().setScale(1);
		
		text.draw(sb, Types.MESSAGES.getString("defaultSettings"), Flappy.WIDTH /10, Flappy.HEIGHT /8);
		
		
	}
	
	private void drawMainMenu(SpriteBatch sb) {
		sb.draw(logo, (Flappy.WIDTH / 2) - (logo.getWidth() / 2), (Flappy.HEIGHT) - (logo.getWidth() / 2));
		text.draw(sb, "1 - " + Types.MESSAGES.getString("playClassic"), Flappy.WIDTH/4, Flappy.HEIGHT*13/20);
		text.draw(sb, "2 - " + Types.MESSAGES.getString("playCountdown") , Flappy.WIDTH/4, Flappy.HEIGHT*11/20);
		text.draw(sb, "3 - " + Types.MESSAGES.getString("playNest") , Flappy.WIDTH/4, Flappy.HEIGHT*9/20);
		text.draw(sb, "4 - " + Types.MESSAGES.getString("settings"), Flappy.WIDTH/4, Flappy.HEIGHT*7/20);
		text.draw(sb, "5 - " + Types.MESSAGES.getString("help"), Flappy.WIDTH/4, Flappy.HEIGHT*5/20);
		text.draw(sb, Types.MESSAGES.getString("volume").replaceFirst("0",String.valueOf((int)(Types.masterVolume*100))), 0,0 + text.getLineHeight());
	}
	
	private void drawPlayerSelect(SpriteBatch sb, int player) {
		if (menu.writingNames()) {
			if (player == 1) {
				text.draw(sb, Types.MESSAGES.getString("chooseName1"), Flappy.WIDTH/9, Flappy.HEIGHT*15/16);
			}
			else {
				text.draw(sb, Types.MESSAGES.getString("chooseName2"), Flappy.WIDTH/9, Flappy.HEIGHT*15/16);
			}
			text.draw(sb, menu.getName(), Flappy.WIDTH/8, Flappy.HEIGHT*15/18);
		} else {
			if (player == 1) {
				text.draw(sb, Types.MESSAGES.getString("chooseBird1"), Flappy.WIDTH/9, Flappy.HEIGHT*15/16);
			} else {
				text.draw(sb, Types.MESSAGES.getString("chooseBird2"), Flappy.WIDTH/9, Flappy.HEIGHT*15/16);
			}
			
			//GOLD
			text.draw(sb, "1 - "+ Types.MESSAGES.getString("silver")+"  " + Types.SILVER_BULLET_SPEED, Flappy.WIDTH/8, Flappy.HEIGHT*21/25);
			sb.draw(birdSilver, Flappy.WIDTH /8, Flappy.HEIGHT*18/25);
			sb.draw(bullet1, Flappy.WIDTH /4, Flappy.HEIGHT*18/25);
			sb.draw(bullet1, Flappy.WIDTH /3, Flappy.HEIGHT*18/25);
			sb.draw(bullet1, Flappy.WIDTH*5/12, Flappy.HEIGHT*18/25);

			//SILVER
			text.draw(sb, "2 - "+ Types.MESSAGES.getString("green")+"   "+ Types.LASER_BULLET_SPEED, Flappy.WIDTH /8, Flappy.HEIGHT*249/400);
			sb.draw(birdGreen, Flappy.WIDTH /8, Flappy.HEIGHT*201/400);
			sb.draw(bullet2, Flappy.WIDTH /4, Flappy.HEIGHT*201/400);
			sb.draw(bullet2, Flappy.WIDTH /3, Flappy.HEIGHT*201/400);
			sb.draw(bullet2, Flappy.WIDTH*5/12, Flappy.HEIGHT*201/400);

			//SILVER
			text.draw(sb, "3 - "+ Types.MESSAGES.getString("blue")+"    "+Types.CANNON_BULLET_SPEED, Flappy.WIDTH /8, Flappy.HEIGHT*81/190);
			sb.draw(birdBlue, Flappy.WIDTH /8, Flappy.HEIGHT*29/100);
			sb.draw(bullet3, Flappy.WIDTH /4, Flappy.HEIGHT*29/100);
			sb.draw(bullet3, Flappy.WIDTH /3, Flappy.HEIGHT*29/100);
			sb.draw(bullet3, Flappy.WIDTH*5/12, Flappy.HEIGHT*29/100);

			//BLUE
			text.draw(sb, "4 - "+ Types.MESSAGES.getString("red")+"     " + Types.FIRE_BULLET_SPEED, Flappy.WIDTH /8, Flappy.HEIGHT*77/400);
			sb.draw(birdRed, Flappy.WIDTH /8, Flappy.HEIGHT*29/400);
			sb.draw(bullet4, Flappy.WIDTH /4, Flappy.HEIGHT*29/400);
			sb.draw(bullet4, Flappy.WIDTH /3, Flappy.HEIGHT*29/400);
			sb.draw(bullet4, Flappy.WIDTH*5/12, Flappy.HEIGHT*29/400);						
		}
	}
	private void drawHelp(SpriteBatch sb, int i){
		sb.draw(help[Integer.parseInt(Types.MESSAGES.getString("id"))][i],0,0);
	}

}

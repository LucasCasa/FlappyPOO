package menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import component.worldComponent.Types;
import desktop.Flappy;

public class EndView {
	
	public static final float ZOOM_CAMARA = 1f;
	private Texture gameOver;
	private Texture background;
	//esto tiene que ir a la clase assets en los "FONTS"
	public BitmapFont FONT;
	
	private EndManager end;
	
	public EndView(EndManager end) {
		this.end = end;
		
		end.getCam().setToOrtho(false, Flappy.WIDTH / ZOOM_CAMARA , Flappy.HEIGHT / ZOOM_CAMARA);
		FONT = new BitmapFont(Gdx.files.internal("arcade.fnt"));
		gameOver = new Texture(Types.GAME_OVER);
		background = Types.BACKGROUND[0];
	}
	
	public void render(SpriteBatch sb) {
		end.getCam().update();
		sb.setProjectionMatrix(end.getCam().combined);
		sb.begin();
		drawEndMenu(sb);
		sb.end();
	}


	public void dispose() {
		background.dispose();
		gameOver.dispose();
		FONT.dispose();
	}
	
	public void drawEndMenu(SpriteBatch sb) {
		sb.draw(background, 0, 0, Flappy.WIDTH, Flappy.HEIGHT);
		sb.draw(gameOver, (Flappy.WIDTH / 2) - (gameOver.getWidth() / 2), (Flappy.HEIGHT) - (gameOver.getWidth() / 2));
		FONT.draw(sb, "A - " + Types.MESSAGES.getString("playAgain"), Flappy.WIDTH/4, Flappy.HEIGHT*2/3);
		FONT.draw(sb, "B - " + Types.MESSAGES.getString("mainMenu"), Flappy.WIDTH/4, Flappy.HEIGHT*4/7);
		FONT.draw(sb, "C - " + Types.MESSAGES.getString("exit"), Flappy.WIDTH/4, Flappy.HEIGHT*10/21);
	}

}

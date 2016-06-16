package component.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import component.Types;
import component.worldComponent.EndComponent;
import start.Flappy;

public class EndComponentView {
	
	public static final float ZOOM_CAMARA = 1f;
	private Texture gameOver;
	private Texture background;
	//esto tiene que ir a la clase assets en los "FONTS"
	public BitmapFont FONT;
	
	private EndComponent end;
	
	public EndComponentView(EndComponent end) {
		this.end = end;
		
		end.getCam().setToOrtho(false, Flappy.WIDTH / ZOOM_CAMARA , Flappy.HEIGHT / ZOOM_CAMARA);
		FONT = new BitmapFont(Gdx.files.internal("arcade.fnt"));
		gameOver = new Texture(Types.GAME_OVER);
		background = new Texture(Types.BACKGROUND);
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
		FONT.draw(sb, "1 - Main Menu", Flappy.WIDTH*1/4, Flappy.HEIGHT*2/3);
		FONT.draw(sb, "2 - Play Again", Flappy.WIDTH*1/4, Flappy.HEIGHT*4/7);
		FONT.draw(sb, "3 - Exit Game", Flappy.WIDTH*1/4, Flappy.HEIGHT*10/21);
	}

}

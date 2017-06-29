package menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TableView {

	private BitmapFont text;
	private BitmapFont textSmall;
	private Texture tCoinds;
	private Texture tLifes;

	public TableView() {
		text = new BitmapFont(Gdx.files.internal("arcade.fnt"));
		textSmall = new BitmapFont(Gdx.files.internal("little.fnt"));
		tCoinds = new Texture("coins.png");
		tLifes = new Texture("heart.png");
	}

	public void draw(SpriteBatch sb, float pos, int s, int l, int offset) {
		sb.draw(tCoinds, pos - 50 + offset, 450); //100
		text.draw(sb, "" + s, pos + offset, 475); //125
		sb.draw(tLifes, pos + 40 + offset, 445); //95
		text.draw(sb, "" + l, pos + 100 + offset, 475); //125
	}
	public void drawScore(SpriteBatch sb,float pos, String nameL, int leftScore, String nameR, int rightScore) {
		textSmall.draw(sb,nameL,pos - 12*(nameL.length()+1) - 25,480);
		textSmall.draw(sb,leftScore + " - " + rightScore,pos - 25,480);
		textSmall.draw(sb,nameR,pos + 12*6 - 25,480);
	}

	
	public void dispose(){
		tCoinds.dispose();
		tLifes.dispose();
		text.dispose();
	}

}

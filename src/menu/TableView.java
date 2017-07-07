package menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import component.worldComponent.Types;

public class TableView {

	private BitmapFont text;
	private BitmapFont textSmall;
	private Texture tCoinds;
	private Texture tLifes;
	private Texture tMana;
	public TableView() {
		text = new BitmapFont(Gdx.files.internal("shadow.fnt"));
		textSmall = new BitmapFont(Gdx.files.internal("little.fnt"));
		tCoinds = new Texture("coins.png");
		tLifes = new Texture("heart.png");
		tMana = new Texture("mana.png");
	}

	public void drawCoins(SpriteBatch sb, float pos, int s, int offset) {
		sb.draw(tCoinds, pos - 50 + offset, 450); //100
		text.draw(sb, "" + s, pos + offset, 475); //125
	}

	public void drawMana(SpriteBatch sb, float pos, int s, int offset) {
		sb.draw(tMana, pos - 50 + offset, 450); //100
		text.draw(sb, "" + s, pos + offset, 475); //125
	}


	public void drawLife(SpriteBatch sb, float pos, int l, int offset) {
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

    public void drawAmmo(SpriteBatch sb, float pos, int ammo, int ammo1) {
		sb.draw(Types.AMMO_BACK,pos - 300,10);
		sb.draw(Types.AMMO_BACK,pos + 140,10);
		sb.draw(Types.AMMO_BAR,pos - 300 +5, 10 +5,(int)((ammo/3000.0)*Types.AMMO_BAR.getWidth()),Types.AMMO_BAR.getHeight());
		sb.draw(Types.AMMO_BAR,pos + 140 +5, 10 +5,(int)((ammo1/3000.0)*Types.AMMO_BAR.getWidth()),Types.AMMO_BAR.getHeight());
		textSmall.draw(sb,Types.MESSAGES.getString("ammo"),pos - 305,10 + 38);
		textSmall.draw(sb,Types.MESSAGES.getString("ammo"),pos + 145,10 + 38);
    }

	public void drawTime(SpriteBatch sb, float pos, int time) {
		text.draw(sb,String.valueOf(time),pos-20,475);
	}
}

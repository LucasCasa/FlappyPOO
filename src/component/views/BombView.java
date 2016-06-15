package component.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import component.worldComponent.Bomb;

public class BombView {
	
	private Texture t;
	
	public BombView() {
		t = new Texture("bomb.png");
	}
	
	public Texture getTexture() {
		return t;
	}
	
	public void dispose(){
		t.dispose();
	}
	
	public void draw(SpriteBatch sb, Bomb b) {
		sb.draw(t, b.getPosition().x, b.getPosition().y);
	}

}

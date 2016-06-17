package component.life;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import component.worldComponent.SimpleFObjectView;
import component.worldComponent.Types;

public class LifeView extends SimpleFObjectView{
	
	public LifeView() {
		super(Types.HEART);
	}
	
	public void draw(SpriteBatch sb, Life l) {

		if(l.getTouched()<=2){
			sb.draw(super.getTexture(), l.getPosition().x, l.getPosition().y);
		}else{
			Texture black = new Texture(Types.BLACK_HEART);
			sb.draw(black, l.getPosition().x, l.getPosition().y);
		}
		
	}
	
	
	
}

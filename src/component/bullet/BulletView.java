package component.bullet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BulletView {
	
	private Texture bullet;
	
	public static final int WIDTH = 32;
	public static final int HEIGHT = 32;
	
	public BulletView(String path) {
		bullet = new Texture(path);
	}
	
	public Texture getBulletView() {
		return bullet;
	}
	
	public void setBulletView(Texture bullet) {
		this.bullet = bullet;
	}
	
	public void dispose(){
		bullet.dispose();
	}
	
	public void draw(SpriteBatch sb, Bullet b){
		sb.draw(bullet, b.getPosition().x, b.getPosition().y);
	}
	
}

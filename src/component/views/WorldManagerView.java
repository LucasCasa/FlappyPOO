package component.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import component.Types;
import component.WorldManager;
import component.bird.Bird;
import component.bird.ClassicBird;
import component.bird.GreenBird;
import component.bird.RedBird;
import component.bullet.Bullet;
import component.worldComponent.Bomb;
import component.worldComponent.Life;
import component.worldComponent.Tubes;
import start.Flappy;

public class WorldManagerView {

	public static final float ZOOM_CAMARA = 1.2f;
	private BirdView birdView;
	private BirdView bird2View;
	private Texture bg;
	private GroundView gv;
	private TubeView tubeView;
	private BulletView bv1; //For player 1
	private BulletView bv2; //For player 2
	private LifeView lw;
	private BombView bw;
	private TableView t1;

	WorldManager w;

	public WorldManagerView(WorldManager world) {
		lw = new LifeView();
		t1 = new TableView();
		bw = new BombView();
		
		birdSkinSelector(world);

		world.getCam().setToOrtho(false, Flappy.WIDTH / ZOOM_CAMARA, Flappy.HEIGHT / ZOOM_CAMARA);
		bg = new Texture(Types.BACKGROUND);
		tubeView = new TubeView();

		bulletSkinSelector(world);

		gv = new GroundView();
		w = world;
	}

	public BirdView getBirdView() {
		return birdView;
	}

	public void render(SpriteBatch sb) {

		sb.draw(bg, w.getCam().position.x - (w.getCam().viewportWidth / 2), 0);

		birdView.draw(sb, w.getBLeft());
		bird2View.draw(sb, w.getBRight());

		for (Tubes tube : w.getTubes()) {
			tubeView.draw(sb, tube);
		}
		
		for (Life l : w.getLifes()){
			lw.draw(sb, l);
		}

		for (Bullet b : w.getBLeft().getBullets()) {
			bv1.draw(sb, b);
		}

		for (Bullet b : w.getBRight().getBullets()) {
			bv2.draw(sb, b);
		}
		
		gv.draw(sb, w.getG());
		
		for (Bomb b : w.getBombs()){
			bw.draw(sb, b);
		}
		
		t1.draw(sb, w.getCam().position.x, w.getBLeft().getScore(), w.getBLeft().getLife(),-250);
		t1.draw(sb, w.getCam().position.x, w.getBRight().getScore(), w.getBRight().getLife(),150);
		

	}

	public void dispose() {
		bg.dispose();
		gv.dispose();
		bv1.dispose();
		bv2.dispose();
		lw.dispose();
		t1.dispose();
		bw.dispose();
		birdView.dispose();
		bird2View.dispose();
		tubeView.dispose();
	}

	private void bulletSkinSelector(WorldManager w) {
		bulletSkin(w.getBLeft());
		bulletSkin(w.getBRight());
	}

	private void birdSkinSelector(WorldManager w) {
		birdSkin(w.getBLeft());
		birdSkin(w.getBRight());
	}

	private void bulletSkin(Bird b) {
		if (b.getID() % 2 == 0) {
			if (b instanceof RedBird) {
				bv1 = new BulletView(Types.SILVER_BULLET);
			} else if (b instanceof GreenBird) {
				bv1 = new BulletView(Types.GOLDEN_BULLET);
			} else if (b instanceof ClassicBird) {
				bv1 = new BulletView(Types.CLASSIC_BULLET);
			} else {
				bv1 = new BulletView(Types.BLUE_BULLET);
			}
		} else {
			if (b instanceof RedBird) {
				bv2 = new BulletView(Types.SILVER_BULLET_INV);
			} else if (b instanceof GreenBird) {
				bv2 = new BulletView(Types.GOLDEN_BULLET_INV);
			} else if (b instanceof ClassicBird) {
				bv2 = new BulletView(Types.CLASSIC_BULLET_INV);
			} else {
				bv2 = new BulletView(Types.BLUE_BULLET_INV);
			}
		}
	}

	private void birdSkin(Bird b) {
		if (b.getID() % 2 == 0) {
			if (b instanceof RedBird) {
				birdView = new BirdView(Types.RED_BIRD);
			} else if (b instanceof GreenBird) {
				birdView = new BirdView(Types.GREEN_BIRD);
			} else if (b instanceof ClassicBird) {
				birdView = new BirdView(Types.CLASSIC_BIRD);
			} else {
				birdView = new BirdView(Types.BLUE_BIRD);
			}
		} else {
			if (b instanceof RedBird) {
				bird2View = new BirdView(Types.RED_BIRD_INV);
			} else if (b instanceof GreenBird) {
				bird2View = new BirdView(Types.GREEN_BIRD_INV);
			} else if (b instanceof ClassicBird) {
				bird2View = new BirdView(Types.CLASSIC_BIRD_INV);
			} else {
				bird2View = new BirdView(Types.BLUE_BIRD_INV);
			}
		}
	}

}

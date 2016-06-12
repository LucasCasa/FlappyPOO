package component;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ar.edu.itba.Flappy;

public class WorldComponentView {

	public static final float ZOOM_CAMARA = 1.2f;
	private BirdView birdView;
	private BirdView bird2View;
	private Texture bg;
	private GroundView gv;
	private TubeView tubeView;
	private BulletView bv1;
	private BulletView bv2;
	private LifeView lw;

	WorldComponent w;

	public WorldComponentView(WorldComponent world) {
		lw = new LifeView();

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

		birdView.draw(sb, w.getBird());
		bird2View.draw(sb, w.getBird2());

		for (Tube tube : w.getTubes()) {
			tubeView.draw(sb, tube);
		}
		
		for (Life l : w.getLifes()){
			lw.draw(sb, l);
		}

		for (Bullet b : w.getBird().getBullets()) {
			bv1.draw(sb, b);
		}

		for (Bullet b : w.getBird2().getBullets()) {
			bv2.draw(sb, b);
		}

		gv.draw(sb, w.getG());
	}

	public void dispose() {
		bg.dispose();
		gv.dispose();
		bv1.dispose();
		bv2.dispose();
		lw.dispose();
		birdView.dispose();
		bird2View.dispose();
		tubeView.dispose();
	}

	private void bulletSkinSelector(WorldComponent w) {
		bulletSkin(w.getBird());
		bulletSkin(w.getBird2());
	}

	private void birdSkinSelector(WorldComponent w) {
		birdSkin(w.getBird());
		birdSkin(w.getBird2());
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

package world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import component.bird.*;
import component.bird.SilverBirdR;
import component.bomb.Bomb;
import component.bomb.BombView;
import component.bullet.Bullet;
import component.bullet.BulletView;
import component.ground.GroundView;
import component.life.Life;
import component.life.LifeView;
import component.tube.TubesView;
import component.tube.MetalTubesView;
import component.tube.NormalTubes;
import component.tube.NormalTubesView;
import component.tube.Tubes;
import component.worldComponent.Types;
import menu.TableView;

public abstract class WorldManagerView {

	BirdView birdView;
	BirdView bird2View;
	protected Texture bg;
	private GroundView gv;
	
	private TubesView normalTubeView;
	private TubesView metalTubeView;
	
	BulletView bv1; // For player 1
	BulletView bv2; // For player 2
	private LifeView lw;
	private BombView bw;
	protected TableView t1;
	private final BitmapFont fontSmall = new BitmapFont(Gdx.files.internal("little.fnt"));

	private int lastLevel = 1;

	WorldManager w;

	public WorldManagerView(WorldManager world) {
		lw = new LifeView();
		t1 = new TableView();
		bw = new BombView();

		birdSkinSelector(world);

		bg = new Texture(Types.BACKGROUND[0]);
		
		normalTubeView = new NormalTubesView();
		metalTubeView = new MetalTubesView();
		
		bulletSkinSelector(world);

		gv = new GroundView(Types.GROUNDS[0]);
		w = world;
	}

	public BirdView getBirdView() {
		return birdView;
	}

	public void render(SpriteBatch sb, float pos, float width) {
		checkChangeOnLevel();
		sb.draw(bg, pos - (width / 2), 0);
		birdView.draw(sb, w.getBLeft());
		bird2View.draw(sb, w.getBRight());

		for (Tubes tube : w.getTubes()) {
			if(tube instanceof NormalTubes){
				normalTubeView.draw(sb, tube);
			}else{
				metalTubeView.draw(sb, tube);
			}
		}

		for (Life l : w.getLives()) {
			lw.draw(sb, l);
		}

		for (Bullet b : w.getBLeft().getBullets()) {
			bv1.draw(sb, b);
		}

		for (Bullet b : w.getBRight().getBullets()) {
			bv2.draw(sb, b);
		}

		gv.draw(sb, w.getG());

		for (Bomb b : w.getBombs()) {
			bw.draw(sb, b);
		}
		t1.drawAmmo(sb,pos,w.getBLeft().getAmmo(),w.getBRight().getAmmo());
	}

	private void checkChangeOnLevel() {
		if(w.getLevel() != lastLevel){
			lastLevel = w.getLevel();
			bg = new Texture(Types.BACKGROUND[lastLevel-1]);
			gv = new GroundView(Types.GROUNDS[lastLevel-1]);
		}
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
		normalTubeView.dispose();
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

		if (b instanceof BirdLeft) {
			if (b instanceof RedBird) {
				bv1 = new BulletView(Types.FIRE_BULLET);
			} else if (b instanceof GreenBird) {
				bv1 = new BulletView(Types.LASER_BULLET);
			} else if (b instanceof SilverBird) {
				bv1 = new BulletView(Types.SILVER_BULLET);
			} else {
				bv1 = new BulletView(Types.CANNON_BULLET);
			}
		}

		if (b instanceof BirdRight) {
			if (b instanceof RedBirdR) {
				bv2 = new BulletView(Types.FIRE_BULLET_INV);
			} else if (b instanceof GreenBirdR) {
				bv2 = new BulletView(Types.LASER_BULLET_INV);
			} else if (b instanceof SilverBirdR) {
				bv2 = new BulletView(Types.SILVER_BULLET_INV);
			} else if (b instanceof BlueBirdR) {
				bv2 = new BulletView(Types.CANNON_BULLET_INV);
			}
		}

	}

	private void birdSkin(Bird b) {

		if (b instanceof BirdLeft) {
			if (b instanceof RedBird) {
				birdView = new BirdView(Types.RED_BIRD,b.getName());
			} else if (b instanceof GreenBird) {
				birdView = new BirdView(Types.GREEN_BIRD,b.getName());
			} else if (b instanceof SilverBird) {
				birdView = new BirdView(Types.SILVER_BIRD,b.getName());
			} else {
				birdView = new BirdView(Types.BLUE_BIRD,b.getName());
			}
		}

		if (b instanceof BirdRight) {
			if (b instanceof RedBirdR) {
				bird2View = new BirdView(Types.RED_BIRD_INV,b.getName());
			} else if (b instanceof GreenBirdR) {
				bird2View = new BirdView(Types.GREEN_BIRD_INV,b.getName());
			} else if (b instanceof SilverBirdR) {
				bird2View = new BirdView(Types.SILVER_BIRD_INV,b.getName());
			} else if (b instanceof BlueBirdR) {
				bird2View = new BirdView(Types.BLUE_BIRD_INV,b.getName());
			}
		}

	}

	public void playShootSound(Bird b,boolean left) {
		if (b instanceof RedBird || b instanceof RedBirdR ) {
			Types.FIRE_SOUND.play(Types.masterVolume, 1, (left)?-1:1);
		} else if (b instanceof GreenBird || b instanceof GreenBirdR) {
			Types.LASER_SOUND.play(Types.masterVolume, 1, (left)?-1:1);
		} else if (b instanceof SilverBird || b instanceof SilverBirdR) {
			Types.BULLET_SOUND.play(Types.masterVolume, 1, (left)?-1:1);
		} else if (b instanceof BlueBird  || b instanceof BlueBirdR ) {
			Types.CANNON_SOUND.play(Types.masterVolume, 1, (left)?-1:1);
		}
	}
	public void playPowerSound(Bird b,boolean left){
		if (b instanceof RedBird || b instanceof RedBirdR ) {
			Types.SHIELD_SOUND.play(Types.masterVolume, 1, (left)?-1:1);
		} else if (b instanceof GreenBird || b instanceof GreenBirdR) {
			Types.SHRINK_SOUND.play(Types.masterVolume, 1, (left)?-1:1);
		} else if (b instanceof SilverBird || b instanceof SilverBirdR) {
			//
		} else if (b instanceof BlueBird  || b instanceof BlueBirdR ) {
			Types.FREEZE_SOUND.play(Types.masterVolume, 1, (left)?-1:1);
		}
	}

	public void playCantPowerSound(boolean left) {
		Types.CANT_POWER_SOUND.play(Types.masterVolume,1,(left)?-1:1);
	}

	public void playCantShootSound(boolean left) {
		Types.CANT_SHOOT_SOUND.play(Types.masterVolume,1,(left)?-1:1);
	}
}

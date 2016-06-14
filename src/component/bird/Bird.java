package component.bird;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import component.WorldSettings;
import component.bullet.Bullet;
import component.worldComponent.SimpleFObject;
import component.worldComponent.Timer;
import files.Output;
import component.worldComponent.CompoundFObject;
import component.worldComponent.Life;
import component.worldComponent.Shootable;

public abstract class Bird extends SimpleFObject implements Shootable {

	public static final int WIDTH = 34;
	public static final int HEIGHT = 24;
	
	protected int ID = 0;

	protected Vector2 velocity;

	protected List<Bullet> bullets;
	
	private int lifes = 3;
	private int score = 0;
	private float deltaTime = 0;
	
	private int grav = -15;
	private int movement = 100;
	private int jump = 250;
	
	Timer aura = new Timer(3000);
	Timer life = new Timer(1000);
	Timer power = new Timer(2000);
	
	public Bird(int ID, int x, int y) {
		super(x,y);
		velocity = new Vector2(0, 0);
		bullets = new ArrayList<Bullet>();
		this.ID = ID;
	}

	public void update(float dt) {
		if (position.y > 0) {
			velocity.add(0, grav);
		}
		velocity.scl(dt);
		position.add(movement * dt, velocity.y);
		if (position.y < 0) {
			position.y = 0;
		}

		scoreManager(dt);

		velocity.scl(1 / dt);
		bounds.setPosition(position.x, position.y);
	}

	public Vector2 getPosition() {
		return position;
	}

	public void jump() {
		velocity.y = jump;
	}
	
	public void setJump(int jump) {
		this.jump = jump;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	@Override
	public abstract void shoot();

	public List<Bullet> getBullets() {
		return bullets;
	}

	private void reduceLife() {
		lifes--;
		System.out.println("Se reduce la vida de " + getID() + " - VIDAS ACTUALES: " + getLife());
		if (getLife() == 0) {
			Output.getInstance().write(this);
			Gdx.app.exit();
		}
	}

	public void increaseLife() {
		lifes++;
	}

	public int getLife() {
		return lifes;
	}

	public int getID() {
		return ID;
	}

	public boolean getLifeSecure() {
		return life.getSecure();
	}

	public boolean getAuraState() {
		return aura.getSecure();
	}

	public int getScore() {
		return score;
	}

	public void addScore(float dt) {
		deltaTime += dt;
		if (deltaTime >= 1) {
			System.out.println("BIRD " + getID() + " SCORES: " + getScore());
			score++;
			deltaTime = 0;
		}
	}

	public void resetScore() {
		score = 0;
	}
	
	private void scoreManager(float dt){
		addScore(dt);
		if (score % WorldSettings.SCORE_POWERS == 0 && score != 0) {
			if (power.getSecure()) {
				System.out.println("AGREGO UN PODER A " + ID);
				power.setSecure(true);
			}
		}
		power.updateSecure();
	}

	public void lifeReducer() {
		if (!aura.getSecure()) {
			reduceLife();
			aura.setSecure(true);
			resetScore();
		}
	}

	public void updateTimers() {
		life.updateSecure();
		aura.updateSecure();
	}
	
	public void setGrav(int grav) {
		this.grav = grav;
	}
	
	public int getGrav() {
		return grav;
	}
	
	public void setMovement(int movement) {
		this.movement = movement;
	}
	
	public int getMovement() {
		return movement;
	}
	
	public int getJump() {
		return jump;
	}
	
	public abstract void apply(int type, Bird b);
	
	@Override
	public boolean crash(SimpleFObject obj) {
		boolean crashes = super.crash(obj);
		if(obj instanceof Life){
			if(crashes && !life.getSecure()){
				System.out.println("***INCREMENTA LA VIDA DE " + this.ID + "***");
				increaseLife();
				life.setSecure(true);
			}
		}else{
			if(crashes)
				lifeReducer();
		}
		return crashes;
	}
	
	public boolean crash(CompoundFObject obj){
		boolean crashes1, crashes2;
		crashes1 = crash(obj.getSimple1());
		crashes2 = crash(obj.getSimple2());
		return crashes1 || crashes2;
	}
	

}

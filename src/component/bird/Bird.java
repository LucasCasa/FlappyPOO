package component.bird;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import component.bullet.Bullet;
import component.worldComponent.SimpleFObject;
import component.worldComponent.Timer;
import component.worldComponent.CompoundFObject;
import component.worldComponent.Life;
import component.worldComponent.Shootable;

public abstract class Bird extends SimpleFObject implements Shootable {

	public static final int WIDTH = 34;
	public static final int HEIGHT = 24;
	private static final int PROM_POSITION = 300;

	protected int ID = 0;

	protected Vector2 velocity;

	protected List<Bullet> bullets;

	private int lifes = 3;
	private int score = 0;
	private float deltaTime = 0;
	private String name = "";
	private boolean team = false; // team me dice si es un pajaro de la derecha
									// si es falso o si es verdadero de la
									// izquierda

	private final int grav = -15;
	private int movement = 100;
	private int jump = 250;

	Timer aura = new Timer(3000);
	Timer life = new Timer(1000);

	public Bird(int ID, int x, int y) {
		super(x, y);
		if (x <= PROM_POSITION)
			team = true;
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

		addScore(dt);

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

	public abstract void shoot();

	public List<Bullet> getBullets() {
		return bullets;
	}

	private void reduceLife() {
		lifes--;
		System.out.println("Se reduce la vida de " + getID() + " - VIDAS ACTUALES: " + getLife());
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
	
	public boolean getTeam(){
		return team;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public boolean crash(SimpleFObject obj) {
		boolean crashes = super.crash(obj);
		if (obj instanceof Life) {
			if (crashes && !life.getSecure()) {
				System.out.println("***INCREMENTA LA VIDA DE " + this.ID + "***");
				increaseLife();
				life.setSecure(true);
			}
		} else {
			if (crashes) {
				System.out.println("CHOCO");
				lifeReducer();
			}
		}
		return crashes;
	}

	public boolean crash(CompoundFObject obj) {
		boolean crashes1, crashes2;
		crashes1 = crash(obj.getSimple1());
		crashes2 = crash(obj.getSimple2());
		return crashes1 || crashes2;
	}
	
	

}

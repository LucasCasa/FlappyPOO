package component;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Bird implements Shootable {

	protected int ID = 0;

	protected Rectangle bounds;
	protected Vector2 position;
	protected Vector2 velocity;

	protected List<Bullet> bullets;
	
	private int life = 3;
	private int score = 0;
	private float scoreAux = 0;
	
	private int grav = -15;
	private int movement = 100;
	private int jump = 250;

	
	/*
	 * Todo esto de los secure los tengo que pasar a una clase y manejarlo desde ahi!
	 * ahora esta todo tirado en bird y eso esta MUY mal
	 */
	private boolean auraSecure = false;
	private long timeInMilisOfAura = 0;
	public static final int MAX_AURA_TIME = 3000; // 3 segundos

	private boolean lifeSecure = false;
	private long timeInMilisOfLife = 0;
	public static final int MAX_LIFE_TIME = 1000;

	private boolean powerSecure = false;
	private long timeOfPower = 0;
	public static final int MAX_POWER_TIME = 2000;
	/*
	 * TODO ESTO QUE PASO ESTA PARA REFACTORIZAR!!! 
	 */

	public Bird(int ID, int x, int y) {
		position = new Vector2(x, y);
		velocity = new Vector2(0, 0);
		bullets = new ArrayList<Bullet>();
		bounds = new Rectangle(x, y, BirdView.WIDTH, BirdView.HEIGHT);
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

	public void reduceLife() {
		life--;
		// tengo que limpiar la lista de poderes extraordinarios que gana el pajaro
		System.out.println("Se reduce la vida de " + getID() + " - VIDAS ACTUALES: " + getLife());
		if (getLife() == 0) {
			Gdx.app.exit();
		}
	}

	public void increaseLife() {
		life++;
	}

	public int getLife() {
		return life;
	}

	public int getID() {
		return ID;
	}

	/*
	 * Aura es un metodo que se llama cuando choca con un tubo y pierde una
	 * vida. Aura sirve para que el jugador, una vez que pierde dicha vida, se
	 * reintegre con mas facilidad al juego, evitando por una X cantidad de
	 * segundos que se choque contra algo (tubo o bala), excepto el piso
	 */
	public void setAura(boolean state) {
		auraSecure = state;
		timeInMilisOfAura = 0;
		if (state)
			timeInMilisOfAura = System.currentTimeMillis();
	}
	
	public void pushPower(Powerable p, Integer key){

	}

	public void setPowerSecure(boolean state) {
		powerSecure = state;
		timeOfPower = 0;
		if (state)
			timeOfPower = System.currentTimeMillis();
	}

	public void setLifeSecure(boolean state) {
		lifeSecure = state;
		timeInMilisOfLife = 0;
		if (state)
			timeInMilisOfLife = System.currentTimeMillis();
	}

	public boolean getLifeSecure() {
		return lifeSecure;
	}

	public boolean getAuraState() {
		return auraSecure;
	}

	public long getTimeInMilisOfAura() {
		return timeInMilisOfAura;
	}

	public int getScore() {
		return score;
	}

	public void addScore(float dt) {
		scoreAux += dt;
		if (scoreAux >= 1) {
			score++;
			scoreAux = 0;
		}
	}

	public void resetScore() {
		score = 0;
	}
	
	private void scoreManager(float dt){
		addScore(dt);
		if (score % WorldSettings.SCORE_POWERS == 0 && score != 0) {
			if (powerSecure != true) {
				System.out.println("AGREGO UN PODER A " + ID);
				setPowerSecure(true);
			}
		}
		updatePowerSecure();
	}

	private void updateLifeTime() {
		if (lifeSecure) {
			if (System.currentTimeMillis() - timeInMilisOfLife >= MAX_LIFE_TIME) {
				setLifeSecure(false);
			}
		}
	}
	
	private void updatePowerSecure(){
		if (System.currentTimeMillis() - timeOfPower >= MAX_POWER_TIME) {
			setPowerSecure(false);
		}
	}

	public void lifeReducer() {
		if (!auraSecure) {
			reduceLife();
			setAura(true);
			resetScore();
			System.out.println("ID:" + getID() + " // cambie de estado: " + getAuraState());
		}
	}

	private void updateAura() {
		if (getAuraState()) {
			if (System.currentTimeMillis() - getTimeInMilisOfAura() >= Bird.MAX_AURA_TIME) {
				setAura(false);
			}
		}
	}

	public void updateTimers() {
		updateLifeTime();
		updateAura();
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
	

}

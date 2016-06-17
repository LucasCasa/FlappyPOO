package component.bird;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import component.bullet.Bullet;
import component.worldComponent.SimpleFObject;
import component.worldComponent.Timer;
import component.worldComponent.Bomb;
import component.worldComponent.CompoundFObject;
import component.worldComponent.Life;
import component.worldComponent.Shootable;

public abstract class Bird extends SimpleFObject implements Shootable {

	public static final int WIDTH = 34;
	public static final int HEIGHT = 24;
	private static final int PROM_POSITION = 300;
	private static final int WEIGTH = -15;
	private static final int MOVEMENT = 100;
	protected int ID = 0;
	
	protected Vector2 velocity;
	protected List<Bullet> bullets;
	
	private int lifes = 3;
	private int score = 0;
	private float scoreTimeAux = 0;
	private String name = "";
	private boolean team = false;
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


	/**
	 * Metodo que se llama para actualizar la posicion y otras cosas relativas al pájaro, como la velocidad.
	 * La velocidad del salto y consecuentemente de la caida del mismo, esta dada por una función matemática 
	 * con Vectores (scale) y el dt.
	 */
	public void update(float dt) {
		if (position.y > 0) {
			velocity.add(0, WEIGTH);
		}
		velocity.scl(dt);
		position.add(MOVEMENT * dt, velocity.y);

		if (position.y < 0) {
			position.y = 0;
		}

		addScore(dt);

		velocity.scl(1 / dt);
		bounds.setPosition(position.x, position.y);
	}

	/**
	 * Metodo que se utiliza en el momento cuando el jugador elige que tipo de pajaro quiere en el menu
	 * Al final, en el momento del guardado de la partida, preguntamos cual es su nombre para guardarlo dentro
	 * de una tabla con highscores
	 */
	public void setName(String name) {
		if (name.equals("") || name.equals(" ")) {
			this.name = "ANONIMO";
		} else {
			this.name = name;
		}
	}

	/**
	 * Metodo para chocarse con un objeto simple dentro del mundo
	 * Todo lo que choco reduce vida y resetea el score,
	 * a excepción de los corazones que ganan
	 */
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
			
			if (crashes) 
				lifeReducer();
			
			if(obj instanceof Bomb){
				Bomb b = (Bomb) obj;
				b.exploit();
			}
			
		}
		return crashes;
	}

	/**
	 * Metodo para chocarse con algun objeto que sea del tipo
	 * CompoundFObject. Un ejemplo de esto puede ser el piso 
	 * o mismo los tubos, que estan compuestos por una parte superior
	 * y por otra inferior.
	 */
	public boolean crash(CompoundFObject obj) {
		boolean crashes1, crashes2;
		crashes1 = crash(obj.getSimple1());
		crashes2 = crash(obj.getSimple2());
		return crashes1 || crashes2;
	}

	/**
	 * Reduce la vida en el caso que no tenga el aura activado.
	 * Si el aura (proteccion al pájaro) esta desactivado, reduce la vida en uno 
	 * y activa el aura hasta un cierto tiempo definido.
	 */
	public void lifeReducer() {
		if (!aura.getSecure()) {
			reduceLife();
			aura.setSecure(true);
			resetScore();
		}
	}

	/**
	 * Update timers.
	 */
	public void updateTimers() {
		life.updateSecure();
		aura.updateSecure();
	}

	/**
	 * Agrega un punto al pájaro. Los puntos se calculan en base al tiempo que el pájaro estuvo
	 * en el aire sin chocarse con ningun objeto, a excepción de los corazones que ganan vidas.
	 * En el caso de chocarse con algo, el score se reduce a 0 y vuelve a comenzar.
	 */
	public void addScore(float dt) {
		scoreTimeAux += dt;
		if (scoreTimeAux >= 1) {
			System.out.println("BIRD " + getID() + " SCORES: " + getScore());
			score++;
			scoreTimeAux = 0;
		}
	}

	/**
	 * El pajaro salta con la velocidad default o con la velocidad que uno setea cuando hace un setJump
	 */
	public void jump() {
		velocity.y = jump;
	}

	public Vector2 getPosition() {
		return position;
	}

	/**
	 * Es el salto que pega el pajaro. Pasandole un jump muy alto, el pajaro salta mucho mas alto
	 * 
	 */
	public void setJump(int jump) {
		this.jump = jump;
	}

	/**
	 * Representa el Bounds. Significa cuanto espacio ocupara en el mundo dicho pájaro.
	 * Bounds se utiliza en los casos que un pájaro colisione con otra cosa que tenga un Bound
	 * en el mundo
	 *
	 * @return Rectangle
	 */
	public Rectangle getBounds() {
		return bounds;
	}

	public abstract void shoot();

	/**
	 * Gets the bullets shot by this Bird.
	 *
	 * @return the bullets
	 */
	public List<Bullet> getBullets() {
		return bullets;
	}

	/**
	 * Reduce life.
	 */
	private void reduceLife() {
		lifes--;
		System.out.println("Se reduce la vida de " + getID() + " - VIDAS ACTUALES: " + getLife());
	}

	/**
	 * Increase life.
	 */
	public void increaseLife() {
		lifes++;
	}

	/**
	 * Gets the life.
	 *
	 * @return the life
	 */
	public int getLife() {
		return lifes;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Gets the life secure.
	 *
	 * @return the life secure
	 */
	public boolean getLifeSecure() {
		return life.getSecure();
	}

	/**
	 * Gets the aura state.
	 *
	 * @return the aura state
	 */
	public boolean getAuraState() {
		return aura.getSecure();
	}

	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Reset score.
	 */
	public void resetScore() {
		score = 0;
	}

	/**
	 * Gets the jump.
	 *
	 * @return the jump
	 */
	public int getJump() {
		return jump;
	}

	/**
	 * Gets the team .
	 *
	 * @return true if the bird belongs to the LeftTeam, or false to the
	 *         RightTeam
	 */
	public boolean getTeam() {
		return team;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

}

package component.bird;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import component.bomb.Bomb;
import component.bullet.Bullet;
import component.ground.Ground;
import component.life.Life;
import component.tube.Tube;
import component.worldComponent.CompoundFObject;
import component.worldComponent.Shootable;
import component.worldComponent.SimpleFObject;
import component.worldComponent.Timer;
import component.worldComponent.Types;
import desktop.Flappy;

public abstract class Bird extends SimpleFObject implements Shootable {

	public static final int WIDTH = 34;
	public static final int HEIGHT = 24;
	public static final int STARTING_LIVES = 5;
	private static final int PROM_POSITION = 300;
	private static final int WEIGTH = -15;
	private static final int MOVEMENT = 100;
	protected int ID = 0;

	protected Vector2 velocity;
	protected List<Bullet> bullets;

	private long lastUpdate = System.currentTimeMillis();
	private int lives = STARTING_LIVES;
	protected int score = 0;
	private float scoreTimeAux = 0;
	private String name = "";
	private boolean team = false;
	private int jump = 350;
	public int ammo = 3000;
	boolean frozen = false;
	Timer aura = new Timer(3000);
	Timer life = new Timer(1000);
	Timer frozenTime = new Timer(4000);

	protected Bird rival;
	protected boolean shield = false;

	protected float width = Bird.WIDTH;
	protected float height = Bird.HEIGHT;
	public Bird(int ID, int x, int y) {
		super(x, y);
		if (x <= PROM_POSITION)
			team = true;
		velocity = new Vector2(0, 0);
		bullets = new ArrayList<>();
		this.ID = ID;
	}

	public void reset(){
		velocity.set(0,0);
		position.y = Flappy.HEIGHT/2;
		bullets = new ArrayList<>();
		lives = STARTING_LIVES;
	}
	/**
	 * Metodo que se llama para actualizar la posicion y otras cosas relativas
	 * al p�jaro, como la velocidad. La velocidad del salto y consecuentemente
	 * de la caida del mismo, esta dada por una funci�n matem�tica con
	 * Vectores (scale) y el dt.
	 */
	public void update(float dt) {
		if(isFrozen()) {
			position.add(MOVEMENT*dt,0);
			addScore(dt);
			bounds.setPosition(position.x, position.y);
			frozenTime.update();
			if(!frozenTime.isCounting()){
				frozen = false;
			}
		}else {
			if (position.y > 480) {
				velocity.set(0, 0);
			}
			if (position.y > 62) { // 112 - offset de ground
				velocity.add(0, WEIGTH);
			}
			velocity.scl(dt);
			position.add(MOVEMENT * dt, ((position.y > 480 && velocity.y > 0) || (position.y < 62 && velocity.y < 0)) ? 0 : velocity.y);

			if (position.y < 0) {
				position.y = 0;
			}

			addScore(dt);

			velocity.scl(1 / dt);
			bounds.setPosition(position.x, position.y);
		}
	}

	/**
	 * Metodo que se utiliza en el momento cuando el jugador elige que tipo de
	 * pajaro quiere en el menu Al final, en el momento del guardado de la
	 * partida, preguntamos cual es su nombre para guardarlo dentro de una tabla
	 * con highscores
	 */
	public void setName(String name) {
		if (name.equals("") || name.equals(" ")) {
			this.name = "NN";
		} else {
			this.name = name;
		}
	}

	/**
	 * Metodo para chocarse con un objeto simple dentro del mundo Todo lo que
	 * choco reduce vida y resetea el score, a excepci�n de los corazones que
	 * ganan
	 */
	@Override
	public boolean crash(SimpleFObject obj) {
		boolean crashes = super.crash(obj);

		if (obj instanceof Life) {
			if (crashes && !life.isCounting()) {
				Life l = (Life) obj;
				l.touched();
				increaseLife();
				life.reset();
				if (this instanceof RedBird || this instanceof BlueBird || this instanceof SilverBird || this instanceof GreenBird) {
					Types.LIFE_SOUND.play(Types.masterVolume,1,-1);
				}else{
					Types.LIFE_SOUND.play(Types.masterVolume,1,1);
				}
			}
		} else {
			if (crashes) {
				if (obj instanceof Bomb) {
					if (!aura.isCounting() && !shield) {
						if (this instanceof RedBird || this instanceof BlueBird || this instanceof SilverBird || this instanceof GreenBird) {
							Types.BOMB_EXPLOSION_SOUND.play(Types.masterVolume,1,-1);
						}else{
							Types.BOMB_EXPLOSION_SOUND.play(Types.masterVolume,1,1);
						}
					}
					Bomb b = (Bomb) obj;
					b.exploit();
				} else if (obj instanceof Bullet) {
					if (!aura.isCounting() && !shield) {
						if (this instanceof RedBird || this instanceof BlueBird || this instanceof SilverBird || this instanceof GreenBird) {
							Types.BIRD_SOUNDS[(int) (Math.random() * 3)].play(Types.masterVolume, 1, -1);
						} else {
							Types.BIRD_SOUNDS[(int) (Math.random() * 3)].play(Types.masterVolume, 1, 1);
						}
					}
				} else if (obj instanceof Tube && ((Tube) obj).metal) {
					if (!aura.isCounting() && !shield) {
						if (this instanceof RedBird || this instanceof BlueBird || this instanceof SilverBird || this instanceof GreenBird) {
							Types.CRASH_METAL_SOUND.play(Types.masterVolume, 1, -1);
						} else {
							Types.CRASH_METAL_SOUND.play(Types.masterVolume, 1, 1);
						}
					}
				} else if (obj instanceof Tube && !((Tube) obj).metal) {
					if (!aura.isCounting() && !shield) {
						if (this instanceof RedBird || this instanceof BlueBird || this instanceof SilverBird || this instanceof GreenBird) {
							Types.CRASH_WOOD_SOUND.play(Types.masterVolume, 1, -1);
						} else {
							Types.CRASH_WOOD_SOUND.play(Types.masterVolume, 1, 1);
						}
					}
				} else if (obj instanceof Ground) {
					if (!aura.isCounting() && !shield) {
						if (this instanceof RedBird || this instanceof BlueBird || this instanceof SilverBird || this instanceof GreenBird) {
							Types.CRASH_GRASS_SOUND.play(Types.masterVolume, 1, -1);
						} else {
							Types.CRASH_GRASS_SOUND.play(Types.masterVolume, 1, 1);
						}
					}
				}
				lifeReducer();
			}
		}
		return crashes;
	}

	/**
	 * Metodo para chocarse con algun objeto que sea del tipo CompoundFObject.
	 * Un ejemplo de esto puede ser el piso o mismo los tubos, que estan
	 * compuestos por una parte superior y por otra inferior.
	 */
	public boolean crash(CompoundFObject obj) {
		boolean crashes1, crashes2;
		crashes1 = crash(obj.getSimple1());
		crashes2 = crash(obj.getSimple2());
		return crashes1 || crashes2;
	}

	/**
	 * Reduce la vida en el caso que no tenga el aura activado. Si el aura
	 * (proteccion al p�jaro) esta desactivado, reduce la vida en uno y activa
	 * el aura hasta un cierto tiempo definido.
	 */
	public void lifeReducer() {
		if (!aura.isCounting() && !shield) {
			reduceLife();
			aura.reset();
			resetScore();
		}
	}

	/**
	 * Update timers.
	 */
	public void updateTimers() {
		life.update();
		aura.update();
		ammo+= lastUpdate - System.currentTimeMillis();
		if(ammo < 0){
			ammo = 0;
		}
		lastUpdate = System.currentTimeMillis();
	}

	/**
	 * Agrega un punto al p�jaro. Los puntos se calculan en base al tiempo que
	 * el p�jaro estuvo en el aire sin chocarse con ningun objeto, a
	 * excepci�n de los corazones que ganan vidas. En el caso de chocarse con
	 * algo, el score se reduce a 0 y vuelve a comenzar.
	 */
	public void addScore(float dt) {
		scoreTimeAux += dt;
		if (scoreTimeAux >= 1) {
			score++;
			scoreTimeAux = 0;
		}
	}

	public boolean canShoot(){
		if(ammo > 2000){
			return false;
		}else{
			ammo += 1000;
			return true;
		}
	}

	public abstract void usePower();
	/**
	 * El pajaro salta con la velocidad default o con la velocidad que uno setea
	 * cuando hace un setJump
	 */
	public void jump() {
		velocity.y = jump;
	}

	public Vector2 getPosition() {
		return position;
	}

	/**
	 * Es el salto que pega el pajaro. Pasandole un jump muy alto, el pajaro
	 * salta mucho mas alto
	 * 
	 */
	public void setJump(int jump) {
		this.jump = jump;
	}

	/**
	 * Representa el Bounds. Significa cuanto espacio ocupara en el mundo dicho
	 * p�jaro. Bounds se utiliza en los casos que un p�jaro colisione con
	 * otra cosa que tenga un Bound en el mundo
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
		lives--;
	}

	/**
	 * Increase life.
	 */
	public void increaseLife() {
		lives++;
	}

	/**
	 * Gets the life.
	 *
	 * @return the life
	 */
	public int getLife() {
		return lives;
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
		return life.isCounting();
	}

	/**
	 * Gets the aura state.
	 *
	 * @return the aura state
	 */
	public boolean getAuraState() {
		return aura.isCounting();
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

	public void setLifeSecure(Boolean b) {
		if(b)
			life.reset();
	}

	public void addLife(int lives) {
		this.lives += lives;
	}

	public int getAmmo() {
		return ammo;
	}
	public void setRival(Bird b){
		rival = b;
	}
	public void freeze(){
		frozen = true;
		frozenTime.reset();
	}

    public float getHeight() {
        return height;
    }

	public float getWidth() {
		return width;
	}

	public boolean isFrozen() {
		return frozen;
	}
}

package world;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;

import component.bird.*;
import component.bird.SilverBirdR;
import component.bomb.Bomb;
import component.bullet.Bullet;
import component.ground.Grounds;
import component.life.Life;
import component.tube.MetalTubes;
import component.tube.NormalTubes;
import component.tube.Tube;
import component.tube.Tubes;

/**
 * WorldManager es la clase que se encargar de actualizar, chequear y modificar el 
 * estado de los pajaros durante el juego.Como es "la l�gica central" del juego,
 * esta contiene los elementos escenciales, como los tubos, corazones, piso y jugadores.
 * Este juego esta dise�ado especialmente para dos jugadores. Si incrementan el numero de 
 * jugadores, la jugabilidad del mismo ser�a casi imposible. 
 * Claramente el juego se puede escalar a mas jugadores, pero habr�a que hacer un minimo cambio
 */

public abstract class WorldManager {

	protected BirdLeft bLeft;
	protected BirdRight bRight;
	protected Grounds g;
	protected int level = 1;
	FPSLogger fps = new FPSLogger();
	protected Array<Tubes> tubes;
	protected List<Life> lives;
	protected List<Bomb> bombs;
	protected int pLeftScore = 0;
	protected int pRightScore = 0;
	protected Boolean contPlay;
	
	/**
	 * Crea al mundo y sus componentes. Crea de forma aleatoria las posiciones de los corazones.
	 * Consulta a los settings del mundo cuantos corazones debe poner y cuantas bombas debe poner. 
	 */
	public WorldManager(OrthographicCamera cam, String p1Name, String p2Name, BirdType p1Bird, BirdType p2Bird) {
		createBirds(p1Name, p2Name, p1Bird, p2Bird);
		loadLevel();
		g = new Grounds(cam.position.x, cam.viewportWidth);
		tubes = new Array<>();
		lives = new ArrayList<>();
		bombs = new ArrayList<>();
	}
	public WorldManager(OrthographicCamera cam){
		g = new Grounds(cam.position.x, cam.viewportWidth);
		loadLevel();
		tubes = new Array<>();
		lives = new ArrayList<>();
		bombs = new ArrayList<>();
	}

	protected void loadLevel() {
		contPlay = true;
	}
	
	protected void loadTubes(){
		double rand;
		for (int i = 1; i <= WorldSettings.getInstance().getTubeCount(); i++) {
			rand = Math.random() * 10;
			int n = (int)rand % 2;
			if(n == 0){
				tubes.add(new MetalTubes(i * (WorldSettings.getInstance().getTubeSpacing() + Tube.WIDTH)));
			}else{
				tubes.add(new NormalTubes(i * (WorldSettings.getInstance().getTubeSpacing() + Tube.WIDTH)));
			}

		}
	}
	
	protected void loadLives(){
		for (int i = 1; i <= WorldSettings.getInstance().getLife(); i++) {
			lives.add(new Life(randomX() * i, randomY()));
		}

	}
	
	protected void loadBombs(){
		for (int i = 1; i < WorldSettings.getInstance().getBombs(); i++) {
			bombs.add(new Bomb(randomX() * i, randomY()));
		}
	}

	/**
	 * Actualiza al mundo. Los tubos se reposicionan si quedan fuera de la vista del usuario. 
	 * De esta manera, el usuario ve infinitos tubos, pero que en realidad solo hay muy pocos
	 * Actualiza y consulta si el pajaro se choco con algo.
	 */
	public void update(float dt, float pos, float width) {
		fps.log();
		g.update(pos, width);

		bLeft.update(dt);
		bRight.update(dt);

		updateBirdOnGame(bLeft, bRight, dt);
		updateBirdOnGame(bRight, bLeft, dt);

		if(hasEnded()){
			endGame();
		}

	}
	protected void updateTubes(float pos, float width){
		for (int i = 0; i < tubes.size; i++) {
			Tubes tube = tubes.get(i);

			if (tube.onScreen(pos, width))
				tube.repositionByInterface();

		}
	}

	protected abstract boolean hasEnded();

	protected abstract void endGame();

	/**
	 * Actualiza al pajaro y todas sus acciones. Consulta si se choco con algun objeto que 
	 * exista en el mundo
	 * 
	 * @param me Es el jugador en cuestion
	 * @param enemy Es el jugador contrincante. A este le afectan mis balas
	 * @param dt
	 */

	private void updateBirdOnGame(Bird me, Bird enemy, float dt) {

		for (int i = 0; i < tubes.size; i++) {
			Tubes tube = tubes.get(i);
			me.crash(tube);
		}

		for (Life l : lives){
			me.crash(l);
		}
			

		me.crash(g);
		me.updateTimers();

		for (Bullet b : me.getBullets()) {
			b.update(dt);
			enemy.crash(b);
		}

		for (Bomb b : bombs){
			me.crash(b);
		}


	}

	public void createBirds(String p1Name, String p2Name, BirdType p1Bird, BirdType p2Bird) {
		switch (p1Bird) {
		case RED: {
			bLeft = new RedBird(0, 100, 200);
			break;
		}
		case GREEN: {
			bLeft = new GreenBird(0, 100, 200);
			break;
		}
		case SILVER: {
			bLeft = new SilverBird(0, 100, 200);
			break;
		}
		case BLUE: {
			bLeft = new BlueBird(0, 100, 200);
			break;
		}
		}

		switch (p2Bird) {
		case RED:
			bRight = new RedBirdR(1, 500, 200);
			break;
		case GREEN:
			bRight = new GreenBirdR(1, 500, 200);
			break;
		case SILVER:
			bRight = new SilverBirdR(1, 500, 200);
			break;
		case BLUE:
			bRight = new BlueBirdR(1, 500, 200);
			break;
		}

		bLeft.setName(p1Name);
		bRight.setName(p2Name);
		bLeft.setRival(bRight);
		bRight.setRival(bLeft);
	}
	public Bird createBird(String name,BirdType bt,boolean left){
		int x,y,id;
		if(left){
			id = 0;
			x = 100;
			y = 200;
		}else{
			id = 1;
			x = 500;
			y = 200;
		}
		Bird b = null;
		switch (bt){
			case RED:
				b = new RedBird(id,x,y);
				b.setName(name);
				break;
			case BLUE:
				b = new BlueBird(id,x,y);
				b.setName(name);
				break;
			case GREEN:
				b = new GreenBird(id,x,y);
				b.setName(name);
				break;
			case SILVER:
				b = new SilverBird(id,x,y);
				b.setName(name);
				break;
		}
		return b;
	}
	public Bird getBLeft() {
		return bLeft;
	}

	public Bird getBRight() {
		return bRight;
	}

	public Grounds getG() {
		return g;
	}

	public Array<Tubes> getTubes() {
		return tubes;
	}


	public Boolean getContinues() {
		return contPlay;
	}

	public void setContinues(Boolean continues) {
		this.contPlay = continues;
	}

	public List<Life> getLives() {
		return lives;
	}

	public List<Bomb> getBombs() {
		return bombs;
	}

	private int randomX() {
		return (int) Math.floor(Math.random() * (WorldSettings.MIN_RAN_X - (WorldSettings.MAX_RAN_X + 1))
				+ (WorldSettings.MAX_RAN_X + 1));
	}

	private int randomY() {
		return (int) Math.floor(Math.random() * (WorldSettings.MIN_RAN_Y - (WorldSettings.MAX_RAN_Y + 1))
				+ (WorldSettings.MAX_RAN_Y + 1));
	}

	protected Bird getWinner(Bird l, Bird r) {
		if (r.getScore() != 0)
			return r;
		else
			return l;
	}

	public int getLevel() {
		return level;
	}

	public int getLeftScore() {
		return pLeftScore;
	}
	public int getRightScore() {
		return pRightScore;
	}
}

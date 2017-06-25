package world;

import java.util.ArrayList;
import java.util.List;

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
import component.worldComponent.Types;
import scoreFiles.Output;
import world.music.FadeOutManager;
import world.music.Level1Music;
import world.music.Level2Music;
import world.music.Level3Music;
import world.music.MenuMusic;

/*
 * WorldManager es la clase que se encargar de actualizar, chequear y modificar el 
 * estado de los pajaros durante el juego.Como es "la l�gica central" del juego,
 * esta contiene los elementos escenciales, como los tubos, corazones, piso y jugadores.
 * Este juego esta dise�ado especialmente para dos jugadores. Si incrementan el numero de 
 * jugadores, la jugabilidad del mismo ser�a casi imposible. 
 * Claramente el juego se puede escalar a mas jugadores, pero habr�a que hacer un minimo cambio
 */

public class WorldManager {

	private BirdLeft bLeft;
	private BirdRight bRight;
	private Grounds g;
	private int level = 1;
	
	private Array<Tubes> tubes;
	private List<Life> lifes;
	private List<Bomb> bombs;

	private Boolean contPlay;
	
	/**
	 * Crea al mundo y sus componentes. Crea de forma aleatoria las posiciones de los corazones.
	 * Consulta a los settings del mundo cuantos corazones debe poner y cuantas bombas debe poner. 
	 */
	public WorldManager(OrthographicCamera cam, String p1Name, String p2Name, BirdType p1Bird, BirdType p2Bird) {

		createBirds(p1Name, p2Name, p1Bird, p2Bird);

		contPlay = true;
		lifes = new ArrayList<>();
		bombs = new ArrayList<>();
		

		for (int i = 1; i <= WorldSettings.getInstance().getLife(); i++) {
			lifes.add(new Life(randomX() * i, randomY()));
		}

		for (int i = 1; i < WorldSettings.getInstance().getBombs(); i++) {
			bombs.add(new Bomb(randomX() * i, randomY()));
		}

		tubes = new Array<Tubes>();

		g = new Grounds(cam.position.x, cam.viewportWidth);

		double rand;
		for (int i = 1; i <= WorldSettings.getInstance().getTubeCount(); i++) {
			rand = Math.random() * 10;
			int n = (int)rand % 2;
			if(n == 0){
				System.out.println("CREO UNO DE METAL");
				tubes.add(new MetalTubes(i * (WorldSettings.getInstance().getTubeSpacing() + Tube.WIDTH)));
			}else{
				System.out.println("CREO UNO NORMAL");
				tubes.add(new NormalTubes(i * (WorldSettings.getInstance().getTubeSpacing() + Tube.WIDTH)));
			}
						
		}

	}
	
	/**
	 * Actualiza al mundo. Los tubos se reposicionan si quedan fuera de la vista del usuario. 
	 * De esta manera, el usuario ve infinitos tubos, pero que en realidad solo hay muy pocos
	 * Actualiza y consulta si el pajaro se choco con algo.
	 */
	public void update(float dt, float pos, float width) {

		g.update(pos, width);

		bLeft.update(dt);
		bRight.update(dt);

		for (int i = 0; i < tubes.size; i++) {
			Tubes tube = tubes.get(i);

			if (tube.onScreen(pos, width))
				tube.repositionByInterface();

		}
		
		updateBirdOnGame(bLeft, bRight, dt);
		updateBirdOnGame(bRight, bLeft, dt);

		if (bLeft.getLife() == 0 || bRight.getLife() == 0) {
			if(level == 3) {
				Output.getInstance().write(getWinner(bLeft, bRight));
				Types.GAMEOVER_VOICE.play(Types.masterVolume);
				contPlay = false;
			}else{
				bLeft.addLife(Bird.STARTING_LIVES);
				bRight.addLife(Bird.STARTING_LIVES);
				level++;
				switch(level){
				case 2:
					(new Thread(new FadeOutManager(Level1Music.getInstance()))).start();
					Level2Music.getInstance().play(Types.masterVolume);
					Types.LEVEL2_VOICE.play(Types.masterVolume);
					break;
				case 3:
					(new Thread(new FadeOutManager(Level2Music.getInstance()))).start();
					Level3Music.getInstance().play(Types.masterVolume);
					Types.LEVEL3_VOICE.play(Types.masterVolume);
					break;
				}
			}
		}

	}
	
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

		for (Life l : lifes){
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
		case RED: {
			bRight = new RedBirdR(1, 500, 200);
			break;
		}
		case GREEN: {
			bRight = new GreenBirdR(1, 500, 200);
			break;
		}
		case SILVER: {
			bRight = new SilverBirdR(1, 500, 200);
			break;
		}
		case BLUE: {
			bRight = new BlueBirdR(1, 500, 200);
			break;
		}
		}

		bLeft.setName(p1Name);
		bRight.setName(p2Name);
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

	public List<Life> getLifes() {
		return lifes;
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

	private Bird getWinner(Bird l, Bird r) {
		if (r.getScore() != 0)
			return r;
		else
			return l;
	}

	public int getLevel() {
		return level;
	}
}

package component;

/*
 * Es una clase que define cuales son los parametros del mundo, tal
 * como la apertura de los tubos, etc...
 * Gracias a esta clase, podemos setear infinitos tipos de dificultades
 * al juego
 */
public class WorldSettings {

	private int tubeSpacing = 140;
	private int tubeCount = 6;
	private int fluctuation = 1000;
	private int gap = 100;
	private int life = 15;
	
	public static final int MIN_RAN_X = 200; //para los tubos en X
	public static final int MAX_RAN_X = 1000; 
	
	public static final int MIN_RAN_Y = 0; //para los tubos en Y
	public static final int MAX_RAN_Y = 600; 
	
	/*
	 * Este numero me dice cada cuantos puntos, el pajarito va a sumar
	 * un poder al mundo
	 */
	public static final int SCORE_POWERS =10; 	
	
	public static final int LOWEST_OPENING = 120; // este es fijo
	private static WorldSettings wp;

	public static WorldSettings getInstance() {
		if (wp == null) {
			wp = new WorldSettings();
		}
		return wp;
	}

	public void setDefaultPreferences() {
		this.tubeSpacing = 140;
		this.tubeCount = 6;
		this.fluctuation = 120;
		this.gap = 100;
		this.life = 1500;
	}
	
	public void setHardPreferences() {
		this.tubeSpacing = 60;
		this.tubeCount = 10;
		this.fluctuation = 90;
		this.gap = 100;
		life = 0;
	}

	public boolean isInDefault() {
		return tubeSpacing == 140 && tubeCount == 6 && fluctuation == 120 && gap == 100 && life == 15;
	}

	public void setTubeSpacing(int n) {
		this.tubeSpacing = n;
	}

	public void setTubeCount(int n) {
		tubeCount = n;
	}
	
	public void setLife(int life) {
		this.life = life;
	}

	public void setFluctuation(int n) {
		fluctuation = n;
	}

	public void setGap(int n) {
		gap = n;
	}
	
	public int getLife() {
		return life;
	}

	public int getFluctuation() {
		return fluctuation;
	}

	public int getGap() {
		return gap;
	}

	public int getTubeCount() {
		return tubeCount;
	}

	public int getTubeSpacing() {
		return tubeSpacing;
	}

}

package component;

/*
 * Es una clase que define cuales son los parametros del mundo, tal
 * como la apertura de los tubos, etc...
 * Gracias a esta clase, podemos setear infinitos tipos de dificultades
 * al juego
 */
public class WorldSettings {

	private int tubeSpacing = 140;
	private int tubeCount = 15;
	private int fluctuation = 100;
	private int gap = 120;
	
	private int life = 15;
	private int bombs = 20;

	private static final int MAX_SPACING = 200;
	private static final int MIN_SPACING = 30;
	private static final int MAX_FLUCTUATION = 100;
	private static final int MIN_FLUCTUATION = 10;
	private static final int MAX_GAP = 200;
	private static final int MIN_GAP = 50;

	public static final int MIN_RAN_X = 200; // para los tubos en X
	public static final int MAX_RAN_X = 1000;
	public static final int MIN_RAN_Y = 0; // para los tubos en Y
	public static final int MAX_RAN_Y = 600;

	/*
	 * Este numero me dice cada cuantos puntos, el pajarito va a sumar un poder
	 * al mundo
	 */
	public static final int SCORE_POWERS = 10;

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
		this.gap = 120;
		this.life = 1500;
		this.bombs = 20;
	}

	public void setHardPreferences() {
		this.tubeSpacing = 60;
		this.tubeCount = 10;
		this.fluctuation = 90;
		this.gap = 100;
		life = 0;
		this.bombs = 130;
	}

	public void set(int ts, int fluc, int gap) {
		tubeSpacing = ts % MAX_SPACING + MIN_SPACING;
		fluc = fluctuation % MAX_FLUCTUATION + MIN_FLUCTUATION;
		this.gap = gap % MAX_GAP + MIN_GAP;
	}

	public void reduceGap() {
		if (gap > MIN_GAP)
			gap -= 10;
	}

	public boolean isInDefault() {
		return tubeSpacing == 140 && tubeCount == 6 && fluctuation == 120 && gap == 120 && life == 15;
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
	
	public void setBombs(int n) {
		bombs = n;
	}
	
	public int getMaxGap() {
		return MAX_GAP;
	}

	public int getMinGap() {
		return MIN_GAP;
	}

	public int getMaxFluctuation() {
		return MAX_FLUCTUATION;
	}
	
	public int getMinFluctuation() {
		return MIN_FLUCTUATION;
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
	
	public int getBombs() {
		return bombs;
	}

}

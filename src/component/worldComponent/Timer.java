package component.worldComponent;

public class Timer {

	private boolean counting = false;
	private long timerCount = 0;
	private int maxTime = 3000; // 3 segundos

	/*
	 * maxTime in miliseconds
	 */
	public Timer(int maxTime) {
		this.maxTime = maxTime;
	}

	public void update() {
		if (counting) {
			if (System.currentTimeMillis() - timerCount >= maxTime) {
				counting = false;
			}
		}
	}

	public void set(int time) {
		maxTime = time;
		counting = true;
		timerCount = 0;
		timerCount = System.currentTimeMillis();
	}
	
	public boolean isCounting(){
		return counting;
	}

	public long getYimeLeft(){
		return System.currentTimeMillis() - timerCount;
	}

	public void reset() {
		counting = true;
		timerCount = 0;
		timerCount = System.currentTimeMillis();
	}
}

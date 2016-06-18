package component.worldComponent;

public class Timer {

	private boolean secure = false;
	private long timerCount = 0;
	private int maxTime = 3000; // 3 segundos

	/*
	 * maxTime in miliseconds
	 */
	public Timer(int maxTime) {
		this.maxTime = maxTime;
	}

	public void updateSecure() {
		if (secure) {
			if (System.currentTimeMillis() - timerCount >= maxTime) {
				setSecure(false);
			}
		}
	}

	public void setSecure(boolean state) {
		secure = state;
		timerCount = 0;
		if (state)
			timerCount = System.currentTimeMillis();
	}
	
	public boolean getSecure(){
		return secure;
	}

}

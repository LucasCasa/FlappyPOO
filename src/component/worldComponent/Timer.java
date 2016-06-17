package component.worldComponent;

// TODO: Auto-generated Javadoc
/**
 * Clase Timer:
 * Utilizada para el aura y agarrar corazones
 */
public class Timer {

	/** Mientras secure sea falso, cualquier cosa me puede dañar
	 * sin embargo, si este esta encendido, no hay bomba, ni tubo que
	 * pueda molestar y descontar vidas
	 */
	private boolean secure = false;
	
	/** 
	 * El timerCount se acciona cuando me choco. 
	 * De esta manera, se tiene un registro de cuando sucedio
	 * el evento. 
	 */
	private long timerCount = 0;
	
	/**
	* Tiempo maximo por el cual el secure estara activo 
	*/
	private int maxTime = 3000; // 3 segundos

	public Timer(int maxTime) {
		this.maxTime = maxTime;
	}

	/**
	 * Actualiza el seguro. Si el seguro esta activado, consulta si ya se paso
	 * de tiempo. Si esto ocurre, vuelve todo a la normalidad y cualquier 
	 * otra cosa me puede dañar.
	 */
	public void updateSecure() {
		if (secure) {
			if (System.currentTimeMillis() - timerCount >= maxTime) {
				setSecure(false);
			}
		}
	}

	/**
	 * Si el estado es true, significa que me choque con algo y seteo el tiempo de choque
	 */
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

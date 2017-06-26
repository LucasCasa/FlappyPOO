package scoreFiles;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * GamePlayersInfo: Clase para guardar y levantar la informaci�n de partidas 
 * anteriores. Usada �nicamente por Input y Output.
 * Implementa Comparable porque estos luegos se ordenaran en un Set por los scores
 *
 */

public class GamePlayersInfo implements Comparable<GamePlayersInfo>{

	private String name;
	private int score;
	
	/** date representa la fecha de la partida jugada */
	private String date;

	public GamePlayersInfo(String n, int s) {
		this((new SimpleDateFormat("dd/MM/yyyy")).format(new Date()), n, s);
	}

	public GamePlayersInfo(String d, String n, int s) {
		this.name = n;
		this.score = s;
		this.date = d;
	}
	
	@Override
	public String toString() {
		return toScreen();
	}
	
	public String toScreen(){
		return date + " " + name + " " + score + "\n";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		if(!(obj instanceof GamePlayersInfo))
			return false;
		else{
			GamePlayersInfo g = (GamePlayersInfo) obj;
			return g.date.equals(date);
		}
	}
	
	@Override
	public int hashCode() {
		return date.hashCode();
	}

	@Override
	public int compareTo(GamePlayersInfo o) {
		if(getScore()>o.getScore()){
			return -1;
		}else if(getScore()<o.getScore()){
			return 1;
		}else{
			return getName().compareTo(o.getName());
		}
		
		
	}

	public int getScore() {
		return this.score;
	}

	public String getName() {
		return this.name;
	}

}

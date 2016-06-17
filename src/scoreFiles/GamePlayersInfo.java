package scoreFiles;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GamePlayersInfo {

	private String name;
	private int score;
	private String d;

	public GamePlayersInfo(String n, int s) {
		this((new SimpleDateFormat("dd/MM/yyyy")).format(new Date()), n, s);
	}

	public GamePlayersInfo(String d, String n, int s) {
		this.name = n;
		this.score = s;
		this.d = d;
	}
	
	@Override
	public String toString() {
		return d + System.lineSeparator() + name + System.lineSeparator() + score;
	}

}

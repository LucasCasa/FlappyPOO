package scoreFiles;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import component.bird.Bird;

public class Output {

	public static String FILE = "output.txt";
	private static Output instance = null;

	public static Output getInstance() {
		if (instance == null)
			instance = new Output();
		return instance;
	}

	public void write(Bird b) {

		try {
			FileWriter fw = new FileWriter(FILE, true);
			BufferedWriter bfw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bfw);
			GamePlayersInfo g = new GamePlayersInfo(b.getName(), b.getScore());
			out.write(g.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

package scoreFiles;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Input {

	private static Input instance = null;
	private static Set<GamePlayersInfo> set = null;
	public static Input getInstance() {
		if(instance==null){
			instance = new Input();
		}
		
		if(set==null)
			getHighscoreFromFile();

		return instance;
	}
	
	
	
	private static Set<GamePlayersInfo> getHighscoreFromFile() {

		try {

			set = new HashSet<>();
			File file = new File("output.txt");

			try {
				Scanner read = new Scanner(file);

				System.out.println("Reading...");
				while (read.hasNextLine()) {
					String date = read.nextLine();
					String name = read.nextLine();
					int score = Integer.parseInt(read.nextLine());
					GamePlayersInfo g = new GamePlayersInfo(date, name, score);
					set.add(g);
				}

				read.close();

			} catch (Exception e) {
				System.out.println("Oops... Creo que tuvimos un problema");
			}

		} catch (Exception e) {
			System.out.println("Error opening or finding file");
		}

		return set;

	}

	public String getTopHighscore(int index) {
		
		if(index>=set.size()){
			return "NO AVAILABLE PLAY YET";
		}
		
		List<GamePlayersInfo> nameList = new ArrayList<GamePlayersInfo>();	
		nameList.addAll(set);

		return nameList.get(index).toScreen();
	}

}

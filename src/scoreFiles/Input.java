package scoreFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Input {

	private static Input instance = null;
	private static Set<GamePlayersInfo> set = null;

	public static Input getInstance() {
		if (instance == null) {
			instance = new Input();
		}

		if (set == null) {
			set = new TreeSet<GamePlayersInfo>(new GamePlayersComp());
			readNormal();
		}

		return instance;
	}
	
	// comparator utilizado para el treeset
	public static class GamePlayersComp implements Comparator<GamePlayersInfo>{

		@Override
		public int compare(GamePlayersInfo i1, GamePlayersInfo i2) {
			if(i1.getScore()>i2.getScore()){
				return -1;
			}else if(i1.getScore()<i2.getScore()){
				return 1;
			}else{
				return i1.getName().compareTo(i2.getName());
			}
			
		}
	}
		
	
	

	public static void readNormal() {
		try (BufferedReader br = new BufferedReader(new FileReader("output.txt"))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				String[] data = sCurrentLine.split("\\s+");
				String date = data[0];
				String name = data[1];
				int score = Integer.parseInt(data[2]);
				GamePlayersInfo g = new GamePlayersInfo(date, name, score);
				System.out.println(g.toScreen());
				set.add(g);
				System.out.println(set.size());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getTopHighscore(int index) {

		if (index >= set.size()) {
			return "NO AVAILABLE PLAY YET";
		}

		List<GamePlayersInfo> nameList = new ArrayList<GamePlayersInfo>();
		nameList.addAll(set);

		return nameList.get(index).toScreen();
	}

}

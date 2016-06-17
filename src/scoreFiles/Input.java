package scoreFiles;

import java.io.File;
import java.util.Scanner;
import java.util.Set;

public class Input {

	public static void read(Set<GamePlayersInfo> set) {

		try {

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

	}

}

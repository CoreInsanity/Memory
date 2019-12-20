import java.util.ArrayList;
import java.util.Scanner;

/**
 * Maxim Van den Eede
 * 13/12/2019.
 */
public class Menu {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        var playedGames = new ArrayList<Models.Game>();
        Helpers.Scoreboard.printScoreBoard();

        while(true) {
            var gameData = new Models.Game();
            var memory = new Memory(gameData);

            System.out.printf("Geef je naam in: ");
            gameData.getPlayer().setName(input.nextLine());
            System.out.printf("Hoe oud ben je %s? ", gameData.getPlayer().getName());
            gameData.getPlayer().setAge(input.nextInt());

            gameData.setGameTime(60);
            gameData.setScore(420);
            //var gameRes = memory.start();
            Helpers.Scoreboard.addGameData(gameData);
        }
    }
}

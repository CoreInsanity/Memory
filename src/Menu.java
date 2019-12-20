import Helpers.Scoreboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Maxim Van den Eede
 * 13/12/2019.
 */
public class Menu {
    private static String ScoreboardPath = "scoreboard.json";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while(true) {
            var gameData = new Models.Game();
            var memory = new Memory(gameData);

            System.out.printf("Geef je naam in: ");
            gameData.getPlayer().setName(input.nextLine());
            System.out.printf("Hoe oud ben je %s? ", gameData.getPlayer().getName());
            gameData.getPlayer().setAge(input.nextInt());

            var gameRes = memory.start();
            Helpers.Scoreboard.addGameData(gameRes, ScoreboardPath);

            printScoreBoard();

            input.nextLine();
        }
    }
    private static void printScoreBoard(){
        Helpers.Playfield.clearConsole();

        var games = Helpers.Scoreboard.readScoreBoard(ScoreboardPath);

        String formatEx = "| %-10s | %3d | %5d | %4d |%n";
        System.out.format("+------------+-----+-------+------+%n");
        System.out.format("| Name       | Age | Score | Time |%n");
        System.out.format("+------------+-----+-------+------+%n");
        for (var game: games){
            System.out.format(formatEx, game.getPlayer().getName(), game.getPlayer().getAge(), game.getScore(), game.getGameTime());
        }
        System.out.format("+------------+-----+-------+------+%n");
        System.out.println();
        System.out.println("-----------------------------------------------------");
    }
}

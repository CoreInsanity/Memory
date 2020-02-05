import models.Game;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Lars De Loenen
 * 13/12/2019.
 */
public class Memory {
    private Game GameData;
    private boolean[][] CardStatuses = new boolean[4][4];
    private int[][] CardValues;

    public Memory(models.Game gameData) {
        GameData = gameData;
        CardValues = helpers.Playfield.fill();
    }

    public models.Game start() {
        Scanner input = new Scanner(System.in);
        LocalDateTime startTime = LocalDateTime.now();

        int noDownCards = 0;
        for (var row : CardValues) // Dynamically read playfield size
            for (var card : row)
                noDownCards++;

        while (noDownCards > 0) {
            try {
                System.out.println(generateBoard());

                System.out.println("-----------------------------------");
                System.out.printf("Locatie 1: ");
                String g1 = input.next();
                int g1x = Integer.parseInt(g1.substring(0, 1)) - 1;
                int g1y = Integer.parseInt(g1.substring(1, 2)) - 1;
                System.out.printf("De kaart op deze positie is: %d %n", CardValues[g1x][g1y]);

                System.out.printf("Locatie 2: ");
                String g2 = input.next();
                int g2x = Integer.parseInt(g2.substring(0, 1)) - 1;
                int g2y = Integer.parseInt(g2.substring(1, 2)) - 1;
                System.out.printf("De kaart op deze positie is: %d %n", CardValues[g2x][g2y]);

                if (CardValues[g1x][g1y] == CardValues[g2x][g2y]) {
                    CardStatuses[g1x][g1y] = true;
                    CardStatuses[g2x][g2y] = true;
                    noDownCards -= 2;
                    GameData.adjustScore(15);
                } else GameData.adjustScore(-5);

                System.out.printf("----------------------------------- %n %nDruk op een toets om verder te gaan");
                input.nextLine();
                input.nextLine();
            } catch (Exception ex) {
                continue; //User probably entered a wrong number
            }
        }

        var gameTime = Duration.between(startTime, LocalDateTime.now());
        GameData.setGameTime((int)gameTime.getSeconds());

        return GameData;
    }

    private String generateBoard() {
        var boardBuilder = new StringBuilder();

        helpers.Playfield.clearConsole();

        boardBuilder.append("     1 2 3 4 \n"); // PRINT X AXIS
        boardBuilder.append("---+---------\n"); // PRINT X AXIS
        for (int x = 0; x < 4; x++) {
            boardBuilder.append(" " + (x + 1) + " | "); // PRINT Y AXIS
            for (int y = 0; y < 4; y++) {
                if (CardStatuses[x][y]) boardBuilder.append(CardValues[x][y] + " ");
                else boardBuilder.append("* ");
            }
            boardBuilder.append("\n");
        }
        boardBuilder.append("\n");

        return boardBuilder.toString();
    }
}

import Models.Game;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Lars De Loenen
 * 13/12/2019.
 */
public class Memory {
    private Models.Game GameData;
    private boolean[][] upDown = new boolean[4][4];
    private int[][] cards;

    public Memory(Models.Game gameData){
        GameData = gameData;
        cards = Helpers.Playfield.Fill();
    }

    public Models.Game start() {
        Scanner input = new Scanner(System.in);
        int noDownCards = 16;

        LocalDateTime timeEen = LocalDateTime.now();
        while (noDownCards > 0) {
            displayBoard();
            System.out.println("Enter co-oridinate 1");
            String g1 = input.next();
            int g1x = Integer.parseInt(g1.substring(0, 1)) - 1;
            int g1y = Integer.parseInt(g1.substring(1, 2)) - 1;
            System.out.println(cards[g1x][g1y]);

            System.out.println("Enter co-oridinate 2");
            String g2 = input.next();
            int g2x = Integer.parseInt(g2.substring(0, 1)) - 1;
            int g2y = Integer.parseInt(g2.substring(1, 2)) - 1;
            System.out.println(cards[g2x][g2y]);
            if (cards[g1x][g1y] == cards[g2x][g2y]) {
                System.out.println("You found a match");
                upDown[g1x][g1y] = true;
                upDown[g2x][g2y] = true;
                noDownCards -= 2;
                GameData.setScore(10);

            } else {
                GameData.setScore(-5);
            }
        }

        displayBoard();
        System.out.println("Je hebt gewonnen!");

        var gameTime = Duration.between(timeEen, LocalDateTime.now());
        GameData.setGameTime(gameTime.getSeconds());

        return GameData;
    }

    private void displayBoard(){
        System.out.println("     1 2 3 4 ");
        System.out.println("---+---------");
        for (int i = 0; i < 4; i++) {
            System.out.print(" " + (i + 1) + " | ");
            for (int a = 0; a < 4; a++) {
                if (upDown[i][a]) {
                    System.out.print(cards[i][a]);
                    System.out.print(" ");
                }
                else
                    System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

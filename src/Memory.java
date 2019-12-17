import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Lars De Loenen
 * 13/12/2019.
 */
public class Memory {
    public static void spelen(boolean[][] upDown, int[][] cards) {
        Scanner keyboard = new Scanner(System.in);
        int noDownCards = 16;
        Bord.Vul();

        System.out.print("Geef je naam in: ");
        String naam = keyboard.nextLine();
        Speler speler = new Speler(naam);

        LocalDateTime timeEen = LocalDateTime.now();
        while (noDownCards > 0) {
            Bord.displayBoard(upDown, cards);
            System.out.println("Enter co-oridinate 1");
            String g1 = keyboard.next();
            int g1x = Integer.parseInt(g1.substring(0, 1)) - 1;
            int g1y = Integer.parseInt(g1.substring(1, 2)) - 1;
            System.out.println(cards[g1x][g1y]);

            System.out.println("Enter co-oridinate 2");
            String g2 = keyboard.next();
            int g2x = Integer.parseInt(g2.substring(0, 1)) - 1;
            int g2y = Integer.parseInt(g2.substring(1, 2)) - 1;
            System.out.println(cards[g2x][g2y]);
            if (cards[g1x][g1y] == cards[g2x][g2y]) {
                System.out.println("You found a match");
                upDown[g1x][g1y] = true;
                upDown[g2x][g2y] = true;
                noDownCards -= 2;
                speler.setScore(10);

            } else {
                speler.setScore(-5);
            }
        }

        LocalDateTime timeTwee = LocalDateTime.now();
        Bord.displayBoard(upDown, cards);
        System.out.println("You win");
        //Tijdsduur spel berekenen
        Duration tijdsduur = Duration.between(timeEen, timeTwee);
        speler.SetTijd(tijdsduur.getSeconds());
        System.out.println(tijdsduur.getSeconds());

        Scoreboard scoreboard = new Scoreboard();
        scoreboard.voegStatsToe(speler.toString());
        System.out.println(scoreboard);
    }
}

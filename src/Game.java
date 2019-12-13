import Models.Player;

import java.util.Random;

/**
 * Maxim Van den Eede
 * 13/12/2019.
 */
public class Game {
    public Player speler;
    public Game(Player newPlayer){
        speler = newPlayer;
        ();
    }
    public void StartGame(){

    }

    //print the board
    public static void setup() {
        for (int i = 0; i < 4; i++) {
            for (int a = 0; a < 4; a++) {
                upDown[i][a]=false;
            }
        }
        cards = randomizer(); //Shuffle cards
    }


    //print the board
    public static void displayBoard(boolean[][] upDown, int[][] cards) {

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

    public static int[][] randomizer() {
        int num[] = {1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8};
        int cards[][] = new int[4][4];
        Random random = new Random();
        int temp, t;
        for (int j = 0; j <= 20; j++) {
            for (int x = 0; x < 16; x++) { //Randomize the card order
                t = random.nextInt(1000) % 15;
                temp = num[x];
                num[x] = num[t];
                num[t] = temp;

            }
            t = 0;
            for (int r = 0; r < 4; r++) // Cards receive Numbers
            {
                for (int s = 0; s < 4; s++) {
                    cards[r][s] = num[t];
                    t = t + 1;
                }
            }

        }
        return cards;
    }
}

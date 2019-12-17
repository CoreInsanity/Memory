import java.util.Random;

/**
 * Lars De Loenen
 * 13/12/2019.
 */
public class Bord {

    public static int[][] Vul() {
        int nummer[] = {1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8};
        int cards[][] = new int[4][4];
        Random ran = new Random();
        int tmp, i;
        for (int s = 0; s <= 20; s++) {
            for (int x = 0; x < 16; x++) //randomize the card placements
            {
                i = ran.nextInt(100000) % 15;
                tmp = nummer[x];
                nummer[x] = nummer[i];
                nummer[i] = tmp;
            }
        }
        i = 0;

        for (int r = 0; r < 4; r++) // put values in cards here
        {
            for (int c = 0; c < 4; c++) {
                cards[r][c] = nummer[i];
                i = i + 1;
            }
        }
        return cards;

    }
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

}

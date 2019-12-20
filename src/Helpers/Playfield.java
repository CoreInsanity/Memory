package Helpers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Lars De Loenen
 * 13/12/2019.
 */
public class Playfield {
    public static int[][] fill() {
        var shuffledNums = Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8});
        Collections.shuffle(shuffledNums);
        int positions[][] = new int[4][4];
        int posCounter = 0;
        for (int r = 0; r < 4; r++)
            for (int c = 0; c < 4; c++) {
                positions[r][c] = shuffledNums.get(posCounter);
                posCounter++;
            }
        return positions;
    }

    public static void clearConsole() {
        for (int i = 0; i < 50; i++) System.out.println();
        System.out.println("-----------------------------------------------------");
        System.out.println();
    }
}

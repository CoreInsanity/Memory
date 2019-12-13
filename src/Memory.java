import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import Models.Player;

public class Memory  {

    static int[][] cards = new int[4][4];
    static boolean upDown[][] = new boolean[4][4];

    static HashMap[][] playField = new HashMap[4][4];

    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.printf("Hi, please enter your name: ");
        var name
        var game = new Game(new Player());
        game.StartGame();
    }
}
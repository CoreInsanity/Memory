import Models.Player;
import java.util.Random;
import java.util.Scanner;

/**
 * Maxim Van den Eede
 * 13/12/2019.
 */
public class Game {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Memory.spelen(new boolean[4][4], Bord.Vul());
        System.out.println("Geef je naam in: ");
        Speler speler = new Speler(keyboard.nextLine());
    }
}

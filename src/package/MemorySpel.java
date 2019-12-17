import java.util.Scanner;

/**
 * Lars De Loenen
 * 13/12/2019.
 */
public class MemorySpel {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Memory.spelen(new boolean[4][4], Bord.Vul());
        System.out.println("Geef je naam in: ");
        Speler speler = new Speler(keyboard.nextLine());
    }
}

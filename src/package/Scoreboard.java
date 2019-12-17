import java.io.PrintStream;
import java.util.*;

/**
 * Lars De Loenen
 * 13/12/2019.
 */
public class Scoreboard{
    Set<String> scoreboard = new LinkedHashSet<>();
    // TODO: 16/12/2019 versturen van score naar file + sorteren en ook ophalen
    public void voegStatsToe(String stat) {
        scoreboard.add(stat);
    }

    @Override
    public String toString() {


        for (Iterator<String> iterator = scoreboard.iterator(); iterator.hasNext(); ) {
            String next = iterator.next();
            return next+"\n";
        }
        return " ";
    }
}

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Lars De Loenen
 * 13/12/2019.
 */
public class Speler {
    private String naam;
    private int score;
    private Long speeltijd;

    public Speler(String naam) {
        this.naam = naam;
    }

    public String getNaam() {
        return naam;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score += score;
    }

    public void SetTijd(Long seconden){
        this.speeltijd = seconden;
    }



    @Override
    public String toString(){
        return String.format("%s %5ds %5d",naam,speeltijd,score);
    }
}

import java.util.*;

/**
 * Lars De Loenen
 * 13/12/2019.
 */
public class Scoreboard{
//    public static void addGameData(Models.Game){}
    public static void printScoreBoard(){
        var games = new Models.Game[4];
        games[0] = new Models.Game();
        games[0].setScore(10);
        games[0].setGameTime(120);
        games[0].getPlayer().setAge(69);
        games[0].getPlayer().setName("peter");

        games[1] = new Models.Game();
        games[1].setScore(666);
        games[1].setGameTime(420);
        games[1].getPlayer().setAge(69);
        games[1].getPlayer().setName("swakke");

        System.out.println("==========================================");
        System.out.printf("--Naam,Leeftijd------------ Tijd -- Score\n");
        for(int i = 0 ;i<games.length;i++){
            System.out.println(String.format("%d:%-8s %-3d ------------ %-4d -- %d\n",i+1,games[i].getPlayer().getName(),games[i].getPlayer().getAge(),games[i].getScore(),games[i].getGameTime()));
            }
    }
}

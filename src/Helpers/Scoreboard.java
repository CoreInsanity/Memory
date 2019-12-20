package Helpers;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

/**
 * Lars De Loenen
 * 13/12/2019.
 */
public class Scoreboard{
    public static void addGameData(Models.Game newGame){
        var file = new File("scoreboard.txt");
        try{
            if(!file.exists()) file.createNewFile();

            var fw = new FileWriter(file);
            fw.append(newGame.getPlayer().getName() + "\r\n");
            fw.append(String.valueOf(newGame.getPlayer().getAge()) + "\r\n");
            fw.append(String.valueOf(newGame.getGameTime()) + "\r\n");
            fw.append(String.valueOf(newGame.getScore())+ "\r\n");
            fw.append("--NEWGAME-- \r\n");
            fw.flush();
            fw.close();
        }catch (Exception ex) {
            //TODO: handle this
        }
    }

    public static void readScoreBoard(){
        var games = new Models.Game[4];
        games[0].setScore(10);
        games[0].setGameTime(120);
        games[0].getPlayer().setAge(69);
        games[0].getPlayer().setName("peter");
    }
}

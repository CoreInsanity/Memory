package Helpers;
import Models.Game;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.*;

/**
 * Lars De Loenen
 * 13/12/2019.
 */
public class Scoreboard {
    private static String ScoreboardPath = "scoreboard.json";

    public static void addGameData(Models.Game newGame) {
        var file = new File(ScoreboardPath);

        try {
            if (!file.exists()) file.createNewFile();
            Files.write(file.toPath(), modelToText(newGame).getBytes());
        } catch (Exception ex) {
            //TODO: handle this
            System.out.println(ex.getMessage());
        }
    }

    private static String modelToText(Models.Game newGame) {
        Gson jsonBuilder = new Gson();
        var currentRecords = new ArrayList<>(textToModel()); //Create a copy of the resulting list since arrays.aslist does not allow structural modification (weird java stuff)
        currentRecords.add(newGame); //Add the new game

        return jsonBuilder.toJson(currentRecords);
    }

    private static List<Models.Game> textToModel(){
        String rawText;
        var file = new File(ScoreboardPath);
        Gson jsonBuilder = new Gson();

        try {
            if (!file.exists()) throw new FileNotFoundException();
            rawText = new String (Files.readAllBytes(file.toPath()));
        } catch (Exception ex) {
            //TODO: handle this, ScoreboardPath probably doesnt exist
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        }
        if(rawText.isEmpty() || rawText.isBlank()) return new ArrayList<Game>();
        return Arrays.asList(jsonBuilder.fromJson(rawText, Models.Game[].class)); //Manually convert to list because gson can only handle arrays
    }

    public static void printScoreBoard() {
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
        System.out.println("--Naam,Leeftijd------------ Tijd -- Score");
        for (int i = 0; i < games.length; i++) {
            //System.out.println(String.format("%d:%-8s %-3d ------------ %-4d -- %d\n",i+1,games[i].getPlayer().getName(),games[i].getPlayer().getAge(),games[i].getScore(),games[i].getGameTime()));
        }
    }
}

package helpers;
import models.Game;
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
    public static void addGameData(models.Game newGame, String scoreboardPath) {
        var file = new File(scoreboardPath);

        try {
            if (!file.exists()) file.createNewFile();
            Files.write(file.toPath(), modelToText(newGame, scoreboardPath).getBytes());
        } catch (Exception ex) {
            //TODO: handle this
            System.out.println(ex.getMessage());
        }
    }

    public static List<models.Game> readScoreBoard(String scoreboardPath){
        String rawText = null;
        var file = new File(scoreboardPath);
        Gson jsonBuilder = new Gson();

        try {
            if (!file.exists()) throw new FileNotFoundException();
            rawText = new String (Files.readAllBytes(file.toPath()));
        } catch (Exception ex) {
            //TODO: handle this, ScoreboardPath probably doesnt exist
            System.out.println(ex.getMessage());
        }
        if(rawText.isEmpty() || rawText.isBlank()) return new ArrayList<Game>();
        return Arrays.asList(jsonBuilder.fromJson(rawText, models.Game[].class)); //Manually convert to list because gson can only handle arrays
    }

    private static String modelToText(models.Game newGame, String scoreboardPath) {
        Gson jsonBuilder = new Gson();

        //Current procedure is to read the current scoreboard.json file, add the new game model and overwrite the original file with the result
        var currentRecords = new ArrayList<>(readScoreBoard(scoreboardPath)); //Create a copy of the resulting list since arrays.aslist does not allow structural modification (weird java stuff)
        currentRecords.add(newGame); //Add the new game

        return jsonBuilder.toJson(currentRecords);
    }

}

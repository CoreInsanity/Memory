package helpers;
import models.Game;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * Lars De Loenen
 * 13/12/2019.
 */
public class Scoreboard {
    public static void addGameData(models.Game newGame, String scoreboardPath) throws Exception {
        var scoreboardFile = new File(scoreboardPath);
        if(!scoreboardFile.exists())scoreboardFile.createNewFile();

        var oldJson = new String(Files.readAllBytes(scoreboardFile.toPath()));
        var gameJson = helpers.Json.modelToJson(newGame);

        var scoreboardWriter = new FileWriter(scoreboardFile.getPath(), false);
        scoreboardWriter.write(helpers.Json.mergeJson(oldJson, gameJson));
        scoreboardWriter.flush();
        scoreboardWriter.close();
    }

    public static List<Game> readScoreBoard(String scoreboardPath) throws Exception {
        var scoreboardFile = new File(scoreboardPath);
        if(!scoreboardFile.exists())scoreboardFile.createNewFile();

        return helpers.Json.jsonToModels(new String(Files.readAllBytes(scoreboardFile.toPath())));
    }

    public static boolean clearScoreBoard(String scoreboardPath) {
        var file = new File(scoreboardPath);
        return file.delete();
    }
}

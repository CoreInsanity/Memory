package helpers;

import models.Game;

import java.text.SimpleDateFormat;
import java.util.*;

public class Json {
    private static String dateFormat = "dd/MM/yyyy_HH:mm:ss";

    public static String modelToJson(models.Game newGame) throws Exception {
        var jsonBuilder = new StringBuilder();
        jsonBuilder.append("{\n");
        jsonBuilder.append("\"GameTime\":" + newGame.getGameTime());
        jsonBuilder.append(",\n\"Clicks\":" + newGame.getClickAmount());
        jsonBuilder.append(",\n\"GameDate\":\"" + new SimpleDateFormat(dateFormat).format(new Date()));
        jsonBuilder.append("\",\n\"Player\":{");
        jsonBuilder.append("\n\"Name\":\"" + newGame.getPlayer().getName());
        jsonBuilder.append("\",\n\"Age\":" + newGame.getPlayer().getAge());
        jsonBuilder.append("\n}");
        jsonBuilder.append("\n},");
        return jsonBuilder.toString();
    }
    public static List<Game> jsonToModels(String json) throws Exception{
        List<Game> models = new ArrayList<Game>();
        var currentGame = new Game();

        for (var line:json.split("\\r?\\n")) {
            var cleanLine = line.replace("\"", ""); //Remove all double quotes
            cleanLine = cleanLine.replaceAll("\\s",""); //Make sure there's no blank spaces

            if(cleanLine.startsWith("{"))
                currentGame = new Game();
            if(cleanLine.startsWith("},"))
                models.add(currentGame);
            if(cleanLine.startsWith("GameTime:"))
                currentGame.setGameTime(Integer.parseInt(cleanLine.replace("GameTime:", "").replace(",", ""))); //Remove object name and comma
            if(cleanLine.startsWith("GameDate:"))
                currentGame.setGameDate(new SimpleDateFormat(dateFormat).parse(cleanLine.replace("GameDate:", "").replace(",", ""))); //Remove object name and comma
            if(cleanLine.startsWith("Clicks:"))
                currentGame.setClickAmount(Integer.parseInt(cleanLine.replace("Clicks:", "").replace(",", ""))); //Remove object name and comma
            if(cleanLine.startsWith("Name:"))
                currentGame.getPlayer().setName(cleanLine.replace("Name:", "").replace(",", "")); //Remove object name and comma
            if(cleanLine.startsWith("Age:"))
                currentGame.getPlayer().setAge(Integer.parseInt(cleanLine.replace("Age:", "").replace(",", ""))); //Remove object name and comma
        }
        models.sort(Comparator.comparing(Game::getGameTime));
        models.sort(Comparator.comparing(Game::getClickAmount));
        return models;
    }
    public static String mergeJson(String oldJson, String newJson) throws Exception{
        var merge = new StringBuilder();
        merge.append("[\n");

        if(!oldJson.isEmpty() && !oldJson.isBlank())
            for (var line:oldJson.split("\\r?\\n"))
                if(!line.contains("[") && !line.contains("]")) merge.append(line + "\n");

        merge.append(newJson);
        merge.append("\n]");
        return merge.toString();
    }
}

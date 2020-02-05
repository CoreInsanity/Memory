package helpers;

import models.Game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Json {
    public static String modelToJson(models.Game newGame) {
        var jsonBuilder = new StringBuilder();
        jsonBuilder.append("{\n");
        jsonBuilder.append("\"GameTime\":" + newGame.getGameTime());
        jsonBuilder.append(",\n\"Score\":" + newGame.getScore());
        jsonBuilder.append(",\n\"Player\":{");
        jsonBuilder.append("\n\"Name\":\"" + newGame.getPlayer().getName());
        jsonBuilder.append("\",\n\"Age\":" + newGame.getPlayer().getAge());
        jsonBuilder.append("\n}");
        jsonBuilder.append("\n},");
        return jsonBuilder.toString();
    }
    public static List<Game> jsonToModels(String json){
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
            if(cleanLine.startsWith("Score:"))
                currentGame.setScore(Integer.parseInt(cleanLine.replace("Score:", "").replace(",", ""))); //Remove object name and comma
            if(cleanLine.startsWith("Name:"))
                currentGame.getPlayer().setName(cleanLine.replace("Name:", "").replace(",", "")); //Remove object name and comma
            if(cleanLine.startsWith("Age:"))
                currentGame.getPlayer().setAge(Integer.parseInt(cleanLine.replace("Age:", "").replace(",", ""))); //Remove object name and comma
        }
        return models;
    }
    public static String mergeJson(String oldJson, String newJson)
    {
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

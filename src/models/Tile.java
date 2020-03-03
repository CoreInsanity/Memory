package models;

import com.google.gson.internal.$Gson$Preconditions;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.MemoryScreen.MemoryScreenPresenter;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Lars De Loenen
 * 19/02/2020.
 */
public class Tile {
    private static Integer lastClickedHash;
    private static boolean secondClick;
    private Timer timer = new Timer();
    private int i = 90;
    private TimerTask task;
    private String string;
    private String seconds;
    private String minutes;

    public static void onClick(ImageView viewer, Image botImage, Image topImage) {
        viewer.setImage(botImage);
        if (lastClickedHash == null) {
            lastClickedHash = botImage.hashCode();
            secondClick = true;
        } else if (secondClick && lastClickedHash.equals(botImage.hashCode())) {
            System.out.println("You found a match cyka");
            lastClickedHash = null;
            secondClick = false;
        } else if (secondClick) {
            System.out.println("njet blyat");

            viewer.setImage(topImage);
            secondClick = false;
            lastClickedHash = null;
        } else secondClick = true;

        lastClickedHash = botImage.hashCode();
    }
    public void startTimer(){
        System.out.println("Hoer");
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                i--;
                if (i == 0){
                    cancel();
                }
                int Minutes = i/60;
                int Seconds = i%60;
                minutes = (Minutes < 10? "0":"") + Minutes;
                seconds = (Seconds < 10? "0":"") + Seconds;
                string = "Time:" + minutes + ":"+ seconds;

                System.out.println(string);
            }
        },1000,1000);
    }

    public String getString(){
        return string;
    }
}

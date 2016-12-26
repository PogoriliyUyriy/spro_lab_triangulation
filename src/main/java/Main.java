import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;

/**
 * Created by Pogorilyi Yurii on 20.12.2016.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Start");
        System.out.println("Avaialble proc = " + Runtime.getRuntime().availableProcessors());
        int FIGURES_COUNT = 3265;
        File file = new File(Constants.RESOURCES_PATH + Constants.INPUT_FILE_NAME);
        JsonManager.generateParams(1221, FIGURES_COUNT);
        Long start = new Date().getTime();
        System.out.println("Start time = " + new Date().getTime() );

        double square = SquareCalculator.calculateSquare(
                SquareCalculator.readFiguresData()
        );
        System.out.println("Square = " + square);

        System.out.println("End time = " + new Date().getTime());
        Long end = new Date().getTime();
        System.out.println("total time = " + (end - start) );
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

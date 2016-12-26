import entities.Figure;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Pogorilyi Yurii on 20.12.2016.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Start");
        new File(Constants.RESOURCES_PATH + Constants.INPUT_FILE_NAME);
        int FIGURES_COUNT = 20;
        ArrayList<Figure> inputFigures = SquareCalculator.readFiguresData();
        FileIOManager.generateParams(10, FIGURES_COUNT);

        double square = SquareCalculator.calculateSquare(inputFigures);
        System.out.println("square = " + square);
        double squareL = SquareCalculator.calculateSquareLambda(inputFigures);
        System.out.println("squareL = " + squareL);
        double squareLP = SquareCalculator.calculateSquareLambdaPar(inputFigures);
        System.out.println("squareLP = " + squareLP);
//        try {
//            System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}

import entities.Figure;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Pogorilyi Yurii on 20.12.2016.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Start");
        new File(Constants.RESOURCES_PATH + Constants.OUTPUT_FILE_NAME);

        String inputParams = FileIOManager.readFile(Constants.RESOURCES_PATH + Constants.INPUT_PARAMS_FILE_NAME);
        String[] params = new String[4];
        int i = 0;
        for(String s: inputParams.split("[A-Z]|;|/n")){
            if (s.matches("=[0-9]+")){
                params[i] = s.substring(1);
                i++;
            }
        }

        int EXPERIMENTS_COUNT = new Integer(params[0]);
        int STEP = new Integer(params[1]);
        int FIGURES_COUNT = new Integer(params[2]);
        int VERTEX_COUNT = new Integer(params[3]);

        FileIOManager.writeString("exp_num;sequantial;parallel");

        for (int expNum = 1; expNum < EXPERIMENTS_COUNT; expNum++) {
            FileIOManager.generateParams(VERTEX_COUNT, FIGURES_COUNT, STEP, expNum);
            FileIOManager.writeString("\n" + expNum);
            ArrayList<Figure> inputFigures = SquareCalculator.readFiguresData();
            double squareL = SquareCalculator.calculateSquareLambda(inputFigures);
            System.out.println("squareL = " + squareL);
            double squareLP = SquareCalculator.calculateSquareLambdaParallel(inputFigures);
            System.out.println("squareLP = " + squareLP);
        }
//        FileIOManager.generateParams(VERTEX_COUNT, FIGURES_COUNT, STEP, expNumber);
    }
}

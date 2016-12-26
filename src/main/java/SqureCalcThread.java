import java.util.Date;

/**
 * Created by pogor on 12/26/2016.
 */
public class SqureCalcThread extends Thread {

    @Override
    public void run(){

        Long start = new Date().getTime();
        System.out.println("Start time = " + new Date().getTime());

        System.out.println("Helo from thread!");
                double square = SquareCalculator.calculateSquare(
                SquareCalculator.readFiguresData()
        );
        System.out.println("End time = " + new Date().getTime());
        Long end = new Date().getTime();
        System.out.println("total time = " + (end - start) );
        System.out.println("Square = " + square);
    }

}

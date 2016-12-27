import entities.Figure;
import entities.Triangle;

import java.util.*;

/**
 * Created by Pogorilyi Yurii on 21.12.2016.
 */
public class SquareCalculator {

    public static ArrayList<Figure> readFiguresData(){
        ArrayList<Figure> figures = new ArrayList<Figure>();

        HashMap<Long, Long> vertexFigureMap = FileIOManager.readParamsList();
        for (long vertexCount: vertexFigureMap.keySet()){
            for (int i = 0; i < vertexFigureMap.get(vertexCount); i++) {
                figures.add(new Figure(vertexCount));
            }
        }
        return figures;
    }

    public static double calculateSquare(List<Figure> figures){
        double square = 0;
        System.out.println("calculateSquare() started.");
        long start = System.nanoTime();

        for (Figure figure: figures){
            for(Triangle triangle: figure.triangulate()){
                square += triangle.getSquare();
            }
        }

        long end = System.nanoTime();
        long total = end - start;
        System.out.println("Total time = " + total);
        return square;
    }

    public static double calculateSquareLambda(List<Figure> figures){
        System.out.println("calculateSquareLambda() started!");
        long start = System.nanoTime();

        Triangle result =
                figures.stream()
                .flatMap(figure -> figure.triangulate().stream())
                .reduce(new Triangle(), (t1, t2)->{
                    t1.squareAcc += t2.getSquare();
                    return t1;
                });

        long end = System.nanoTime();
        long total = end - start;
        FileIOManager.writeString(";" + total);
        System.out.println("Total time = " + total);

        return result.squareAcc;
    }

    public static double calculateSquareLambdaParallel(List<Figure> figures){
        System.out.println("calculateSquareLambdaParallel() started!");
        long start = System.nanoTime();

        Triangle result =
                figures.stream()
                        .flatMap(figure -> figure.triangulate().parallelStream())
                        .reduce(new Triangle(), (t1, t2)->{
                            t1.squareAcc += t2.getSquare();
                            return t1;
                        });

        long end = System.nanoTime();
        long total = end - start;
        FileIOManager.writeString(";" + total);
        System.out.println("Total time = " + total);

        return result.squareAcc;
    }
}

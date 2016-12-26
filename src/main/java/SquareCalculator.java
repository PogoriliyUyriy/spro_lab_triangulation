import entities.Figure;
import entities.Triangle;

import java.util.*;

/**
 * Created by Pogorilyi Yurii on 21.12.2016.
 */
public class SquareCalculator {

//    public static List<entities.Triangle> triangulateFigure(entities.Figure figure){
//        ArrayList<entities.Triangle> triangles = new ArrayList<entities.Triangle>();
//        for (int i = 0; i < figure.points.size() - 2; i++) {
//            triangles.add(new entities.Triangle(
//                    figure.points.get(0),
//                    figure.points.get(i + 1),
//                    figure.points.get((i + 2))
//            ));
//        }
//        return triangles;
//    }

    public static ArrayList<Figure> readFiguresData(){
        ArrayList<Figure> figures = new ArrayList<Figure>();

        HashMap<Long, Long> vertexFigureMap = JsonManager.readParamsList();
        for (long vertexCount: vertexFigureMap.keySet()){
            for (int i = 0; i < vertexFigureMap.get(vertexCount); i++) {
                figures.add(new Figure(vertexCount));
            }
        }
        return figures;
    }

    public static double calculateSquare(List<Figure> figures){
        double square = 0;
        for (Figure figure: figures){
            for(Triangle triangle: figure.triangulate()){
                square += triangle.getSquare();
            }
        }
        return square;
    }
}

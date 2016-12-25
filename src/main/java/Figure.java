import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pogorilyi Yurii on 20.12.2016.
 */
public class Figure {

    public Figure(){}

    public Figure(long vertexCount){
        this.points = generatePolygon(vertexCount);
    }

    public List<Point> points;

    public List<Triangle> triangulate(){
        ArrayList<Triangle> triangles = new ArrayList<Triangle>();
        for (int i = 0; i < this.points.size() - 2; i++) {
            triangles.add(new Triangle(
                    this.points.get(0),
                    this.points.get(i + 1),
                    this.points.get((i + 2))
            ));
        }
        return triangles;
    }

    private  List<Point> generatePolygon(long peaksCount) {
        double radius = 0.9;
        ArrayList<Point> polygon = new ArrayList<Point>((int) peaksCount);

        double angle = 2 * Math.PI / peaksCount;

        Point start = new Point(0, radius);
        polygon.add(start);
        for (long i = 0; i < peaksCount - 1; i++) {
            double x = polygon.get((int) i).X;
            double y = polygon.get(((int) i)).Y;

            double next_x = x * Math.cos(angle) - y * Math.sin(angle);
            double next_y = x * Math.sin(angle) + y * Math.cos(angle);

            polygon.add(new Point(next_x, next_y));
        }
        return polygon;
    }
}

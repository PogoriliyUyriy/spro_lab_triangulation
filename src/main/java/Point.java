/**
 * Created by Pogorilyi Yurii on 20.12.2016.
 */
public class Point {

    public Point(double x, double y){
        this.X = x;
        this.Y = y;
    }

    public Double X;
    public Double Y;

    @Override
    public String toString() {
        return X.toString() + "; " + Y.toString();
    }
}

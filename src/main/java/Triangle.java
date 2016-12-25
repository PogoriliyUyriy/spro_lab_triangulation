/**
 * Created by pogor on 12/25/2016.
 */
public class Triangle extends Figure {
    Point A;
    Point B;
    Point C;

    double getEdgeA(){
        return Math.sqrt(Math.pow(B.X - C.X, 2) + Math.pow(B.Y - C.Y, 2));
    }
    double getEdgeB(){
        return Math.sqrt(Math.pow(C.X - A.X, 2) + Math.pow(C.Y - A.Y, 2));
    }
    double getEdgeC(){
        return Math.sqrt(Math.pow(A.X - B.X, 2) + Math.pow(A.Y - B.Y, 2));
    }

    Triangle(Point a, Point b, Point c){
        super();
        this.A = a;
        this.B = b;
        this.C = c;
    }

    double P(){ return (getEdgeA() + getEdgeB() + getEdgeC()) / 2; }

    public double getSquare(){
        return Math.sqrt(P() * (P() - getEdgeA()) * (P() - getEdgeA()) * (P() - getEdgeC()));
    }
}

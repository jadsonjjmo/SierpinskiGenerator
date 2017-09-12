/**
 * @author Jadson Oliveira
 */
public class Triangle2D {

    private Point2D pointA;
    private Point2D pointB;
    private Point2D pointC;


    public Triangle2D(Point2D a, Point2D b, Point2D c) {
        this.setPointA(a);
        this.setPointB(b);
        this.setPointC(c);
    }


    //Getters
    public Point2D getPointA() {
        return pointA;
    }

    public Point2D getPointB() {
        return pointB;
    }

    public Point2D getPointC() {
        return pointC;
    }

    //Setters

    public void setPointA(Point2D pointA) {
        this.pointA = pointA;
    }

    public void setPointB(Point2D pointB) {
        this.pointB = pointB;
    }

    public void setPointC(Point2D pointC) {
        this.pointC = pointC;
    }


    @Override
    public String toString() {
//        return ((this.getPointA().getX() + this.getPointB().getX()) / 2) + "\t" + ((this.getPointC().getY() + this.getPointC().getY()) / 2) + "\n";
        return this.getPointA() + "\n" + this.getPointB() + "\n" + this.getPointC() + "\n";
    }
}

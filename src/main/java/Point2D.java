import java.text.DecimalFormat;

/**
 * @author Jadson Oliveira
 */
public class Point2D {

    private DecimalFormat decimalFormat = new DecimalFormat("0.00000000");
    private double x;
    private double y;

    public Point2D(double x, double y){
        super();
        this.setX(x);
        this.setY(y);
    }
    public Point2D(){
        super();
    }

    //Getters

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    //Setters

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        String formattedDoubleX = decimalFormat.format(this.getX());
        String formattedDoubleY = decimalFormat.format(this.getY());
        formattedDoubleX = formattedDoubleX.replace(",", ".");
        formattedDoubleY = formattedDoubleY.replace(",", ".");

        return formattedDoubleX + " " + formattedDoubleY;
    }
}

import java.io.*;

/**
 * @author Jadson Oliveira
 */
public class Sierpinski {

    private static BufferedWriter bufferedWriter;

    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println("Please enter the following parameters: [OUTPUT FILE] [LEVELS]");
            System.exit(1);
        }

        final String pathOutput = args[0];
        final int levels = Integer.parseInt(args[1]);

        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathOutput)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        //Initial sierpinski triangle points
        Point2D pointA = new Point2D(0, 0);
        Point2D pointB = new Point2D(0.5, 1);
        Point2D pointC = new Point2D(1, 0);

        generateSierpinski(new Triangle2D(pointA, pointB, pointC), levels);


        try {
            bufferedWriter.flush();
        } catch (IOException e) {
            System.err.println("Failed to flush on file!");
            e.printStackTrace();
        }

        try {
            bufferedWriter.close();
        } catch (IOException e) {
            System.err.println("Failed to close the file!");
            e.printStackTrace();
        }

    }

    public static void generateSierpinski(final Triangle2D triangle2D, int level) {

        if (level > 0) {

            try {
                bufferedWriter.write(triangle2D.toString());
            } catch (IOException e) {
                System.err.println("Failed to write on file!");
                e.printStackTrace();
            }

            Point2D auxPointA = new Point2D();
            Point2D auxPointB = new Point2D();
            Point2D auxPointC = new Point2D();

            auxPointA.setX((triangle2D.getPointA().getX() + triangle2D.getPointB().getX()) / 2.0);
            auxPointA.setY((triangle2D.getPointA().getY() + triangle2D.getPointB().getY()) / 2.0);

            auxPointB.setX((triangle2D.getPointB().getX() + triangle2D.getPointC().getX()) / 2.0);
            auxPointB.setY((triangle2D.getPointB().getY() + triangle2D.getPointC().getY()) / 2.0);

            auxPointC.setX((triangle2D.getPointA().getX() + triangle2D.getPointC().getX()) / 2.0);
            auxPointC.setY((triangle2D.getPointA().getY() + triangle2D.getPointC().getY()) / 2.0);


            final Triangle2D rightTriangle2D = new Triangle2D(triangle2D.getPointA(), auxPointA, auxPointC);
            generateSierpinski(rightTriangle2D, level - 1);

            final Triangle2D topTriangle2D = new Triangle2D(auxPointA, triangle2D.getPointB(), auxPointB);
            generateSierpinski(topTriangle2D, level - 1);

            final Triangle2D leftTriangle2D = new Triangle2D(auxPointC, auxPointB, triangle2D.getPointC());
            generateSierpinski(leftTriangle2D, level - 1);

        }

    }

}

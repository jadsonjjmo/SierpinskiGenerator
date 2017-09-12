import java.io.*;
import java.util.Random;

/**
 * @author Jadson Oliveira
 */
public class Sierpinski {

    private static BufferedWriter bufferedWriter;

    public static void main(final String[] args) {

        if (args.length != 3) {
            System.err.println("Please enter the following parameters: [OUTPUT FILE] [LEVELS/POINTS] [IMPLEMENTATION]");
            System.exit(1);
        }

        final String pathOutput = args[0];
        final int levels = Integer.parseInt(args[1]);

        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathOutput)));
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        }


        //Initial sierpinski triangle points
        final Point2D pointA = new Point2D(0, 0);
        final Point2D pointB = new Point2D(0.5, 1);
        final Point2D pointC = new Point2D(1, 0);

        final Triangle2D mainTriangle = new Triangle2D(pointA, pointB, pointC);


        if (args[2].toLowerCase().equals("random")) {
            generateRandomSierpinski(mainTriangle, levels);
        } else if (args[2].toLowerCase().equals("fixed")) {
            generateSierpinski(mainTriangle, levels);
        } else {
            System.err.println("Invalid parameter! Please choose a \"random\" or a \"fixed\" implementation!");
            System.exit(1);
        }

        try {
            bufferedWriter.flush();
        } catch (final IOException e) {
            System.err.println("Failed to flush on file!");
            e.printStackTrace();
        }

        try {
            bufferedWriter.close();
        } catch (final IOException e) {
            System.err.println("Failed to close the file!");
            e.printStackTrace();
        }

    }


    /**
     * Write on file points of the sierpinski triangle and recursively get other triangles to be plotted
     *
     * @param triangle2D A Sierpinski triangle
     * @param level      The level of the triangle
     */
    private static void generateSierpinski(final Triangle2D triangle2D, final int level) {

        if (level >= 0) {

            try {
                bufferedWriter.write(triangle2D.toString());
            } catch (final IOException e) {
                System.err.println("Failed to write on file!");
                e.printStackTrace();
            }

            final Point2D auxPointA = new Point2D();
            final Point2D auxPointB = new Point2D();
            final Point2D auxPointC = new Point2D();

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


    /**
     * Write on file, points of the sierpinski triangle, using the caos game strategy
     *
     * @param triangle2D       An initial triangle (main sierpinski triangle)
     * @param quantityOfPoints The quantity of points to be plotted
     */
    private static void generateRandomSierpinski(final Triangle2D triangle2D, final int quantityOfPoints) {
        final Random random = new Random(13L);

        //Choose a random point, known as seed.
        Point2D seedPoint = new Point2D(random.nextDouble(), random.nextDouble());

        for (int i = 0; i < quantityOfPoints; i++) {

            //Choose a random vertex.
            final int randomVertex = random.nextInt(3);

            //Update the seedPoint based on the mid point between the vertex chosen and the seedPoint
            switch (randomVertex) {
                case 0:
                    seedPoint = midPoint(triangle2D.getPointA(), seedPoint);
                    break;
                case 1:
                    seedPoint = midPoint(triangle2D.getPointB(), seedPoint);
                    break;
                case 2:
                    seedPoint = midPoint(triangle2D.getPointC(), seedPoint);
                    break;
            }

            //Write on buffer the actual seedPoint.
            try {
                bufferedWriter.write(seedPoint.toString());
                bufferedWriter.newLine();
            } catch (final IOException e) {
                System.err.println("Failed to write on file!");
                e.printStackTrace();
            }

        }

    }


    private static Point2D midPoint(final Point2D pointA, final Point2D pointB) {
        return new Point2D((pointA.getX() + pointB.getX()) / 2, (pointA.getY() + pointB.getY()) / 2);
    }


}

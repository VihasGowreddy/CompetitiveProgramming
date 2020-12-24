import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static Point first = new Point(10001, 10001);
    public static void main(String args[]) throws Exception {
        
        InputStreamReader console = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(console);
        StringBuffer output = new StringBuffer();
        String readIn;

        while (Integer.parseInt((readIn = reader.readLine())) > 0) {

            int numPoints = Integer.parseInt(readIn);
            List<Point> points = new ArrayList<Point>();
            List<Point> newPoints = new ArrayList<Point>();
            Deque<Point> convexHull = new ArrayDeque<Point>();
            first = new Point(10001, 10001);
            for (int x = 0; x < numPoints; x++) {

                String []inputPoint = reader.readLine().split(" ");
                int xCoord = Integer.parseInt(inputPoint[0]);
                int yCoord = Integer.parseInt(inputPoint[1]);

                Point toAdd = new Point(xCoord, yCoord);
                points.add(toAdd);
                if (first.y > yCoord || (first.y == yCoord && first.x > xCoord))
                    first = toAdd;
            }

            if (points.size() < 2) {
                output.append(points.size() + "\n");
                for (int x = 0; x < points.size(); x++)
                    output.append(points.get(x).x + " " + points.get(x).y + "\n");
                continue;
            }

            Collections.sort(points);

            //output.append("Sorted Points\n");
            //for (int x = 0; x < points.size(); x++)
            //    output.append("X: " + points.get(x).x + "  Y: " + points.get(x).y + "  Valid: " + points.get(x).valid + "\n");

            newPoints.add(first);
            //CHECK THIS
            //REPLACE DISTANCE
            //REPLACE 
            for (int x = 1; x < points.size(); x++) {
                if (first.x != points.get(x).x || first.y != points.get(x).y) {
                    if (points.get(x).valid)
                        newPoints.add(points.get(x));
                }
            }    

            for (int i = 0; i < newPoints.size(); i++) {
                if (convexHull.size() > 1) {
                    while (convexHull.size() > 1 && ccw(stackSecondElement(convexHull), convexHull.peek(), newPoints.get(i)) <= 0)
                        convexHull.pop();
                }
                convexHull.push(newPoints.get(i));
            }

            output.append(convexHull.size() + "\n");
            Iterator outputIter = convexHull.descendingIterator();
            while (outputIter.hasNext()) {
                Point toPrint = (Point)outputIter.next();
                //if (outputIter.hasNext())
                output.append(toPrint.x + " " + toPrint.y + "\n");
            }
            
        }   
        System.out.print(output);
    }

    public static Point stackSecondElement(Deque<Point> listPoints) {
        Point temp = listPoints.pop();
        Point toReturn = listPoints.peek();
        listPoints.push(temp);
        return toReturn;
    }

    public static int ccw(Point p1, Point p2, Point p3) {
        return ((p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x));
    }
}

class Point implements Comparable<Point> {
    public int x;
    public int y;
    public boolean valid;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.valid = true;
    }

    @Override
    public int compareTo(Point point) {
        int orientation = Main.ccw(Main.first, this, point);
        if (orientation < 0)
            return 1;
        else if (orientation == 0) {
            int distanceThis = (this.x - Main.first.x) * (this.x - Main.first.x) + (this.y - Main.first.y) * (this.y - Main.first.y);
            int distanceCompareTo = (point.x - Main.first.x) * (point.x - Main.first.x) + (point.y - Main.first.y) * (point.y - Main.first.y);
            if (distanceThis >= distanceCompareTo) {
                point.valid = false;
                return 1;
            }
            else {
                this.valid = false;
                return -1;
            }
            //return 0;
        }
        else
            return -1;
    }
}
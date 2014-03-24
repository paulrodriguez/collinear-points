/*************************************************************************
 * Name: Paul Rodriguez
 * 
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/
import java.util.Arrays;
import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER;       // YOUR DEFINITION HERE

    private class SlopeOrder implements Comparator<Point>
    {
        public int compare(Point p1, Point p2)
        {
            //StdOut.println("inside comparator");
            double diff = slopeTo(p1) - slopeTo(p2);
            
            if (diff < 0.0) return -1;
            else if (diff > 0.0) return 1;
            else return 0;
        }
    }
    
    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
        
        SLOPE_ORDER = new SlopeOrder();
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        
        if (this.x == that.x)
        {
            if (this.y == that.y)
            {
                return Double.NEGATIVE_INFINITY;
            }
        }
        
        if (this.x == that.x)
            return Double.POSITIVE_INFINITY;
        
        if (this.y == that.y)
            return +0.0;
        
        return (double) (that.y - this.y)/(that.x - this.x);
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    //return negative number if less than, 0 if equal or positive number if greater than
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        if (this.y == that.y)
        {
            return this.x - that.x;
        }
        return this.y - that.y;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.02);
        In file = new In(args[0]);
        int N = file.readInt();
        Point[] points = new Point[N];
        
       
        for (int i = 0; i < N; i++)
        {
            points[i] = new Point(file.readInt(), file.readInt());
            
        }
        Arrays.sort(points);
        
         points[0].draw();
             StdDraw.show(0);
              points[N-5].draw();
             StdDraw.show(0);
             // points[N-4].draw();
             //StdDraw.show(0);
        points[N-1].draw();
             StdDraw.show(0);
        //Arrays.sort(points, points[2].SLOPE_ORDER);
        StdOut.println(Arrays.toString(points));
        //StdDraw.show(0);
    }
}

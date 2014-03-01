/**
 * Author:        Paul Rodriguez
 * Created:       2/27/2014
 * Last Updated:  2/27/2014
 * 
 */

import java.util.Arrays;
public class Brute
{
    
    private static boolean arePointsCollinear(Point[] points)
    {
        if (points.length == 2)
            return true;
        
        double desiredSlope = points[0].slopeTo(points[1]);
        
        for (int i = 2; i < points.length; i++)
        {
            //StdOut.println("desired slope: " + +desiredSlope + " other slope: " + points[0].slopeTo(points[i]));
            if (desiredSlope != points[0].slopeTo(points[i]))
            {
                return false;
            }
        }
       
        return true;
    }
    
    private static void printOutput(Point[] points)
    {
        Arrays.sort(points);
        for (int i = 0; i < points.length -1; i++)
        {
            StdOut.print(points[i].toString() + " -> ");
        }
       StdOut.println(points[points.length-1].toString());
      
       points[0].drawTo(points[points.length-1]);
    }
    
    public static void main(String[] args)
    {
        
        In file = new In(args[0]);
        int N = file.readInt();
        Point[] points = new Point[N];
        
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
       
        for (int i = 0; i < N; i++)
        {
            points[i] = new Point(file.readInt(), file.readInt());
             points[i].draw();
             StdDraw.show(0);
        }
        
        //first iterator
        for (int i = 0; i < N - 3; i++)
        {
           // points[i].draw();
            //collinear[0] = points[i];
            
            for (int j = i + 1; j < N - 2; j++)
            {
                //collinear[1] = points[j];
                
                for (int k = j + 1; k < N-1; k++)
                {
                    Point[] preCheck = new Point[3];
                    preCheck[0] = points[i];
                    preCheck[1] = points[j];
                    preCheck[2] = points[k];
                    if (!arePointsCollinear(preCheck))
                        continue;
                    //collinear[2] = points[k];
                    
                    for (int w = k + 1; w < N; w++)
                    {
                        Point[] collinear = new Point[4];
                        collinear[0] = points[i];
                        collinear[1] = points[j];
                        collinear[2] = points[k];
                        collinear[3] = points[w];
                        
                        //  do something if points are collinear
                        if (arePointsCollinear(collinear))
                        {
                            printOutput(collinear);
                        }
                    }
                }
            }
        }
        
        StdDraw.show(0);
    }
}
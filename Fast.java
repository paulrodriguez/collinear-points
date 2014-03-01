/***
  * Author:       Paul Rodriguez
  * Created:      2/27/2014
  * LastUpdated:  3/1/2014
  * 
  * Compilation:  javac Fast.java
  * Execution:    java Fast input.txt
  */

import java.util.Arrays;
import java.util.ArrayList;
public class Fast
{
   private static double ERROR = 0.00000000000000000000000001;
   
   private static class Segment
   {
       private final Point start;
       private final Point end;
       private double slope;
       public Segment(Point s, Point e, double slope)
       {
           this.start = s;
           this.end = e;
           this.slope = slope;
       }
       
       public boolean pointInSegment(Point p)
       {
           if (p.slopeTo(this.start) == this.slope && p.slopeTo(this.end) == this.slope)
           {
               return true;
           }
           return false;
       }
   }
   /*
   private static boolean isEndPoint(ArrayList<Point> list, Point p)
   {
       for (int i = 0; i < list.size(); i++)
       {
           if(p == list.get(i))
           {
               return true;
           }
       }
       return false;
   }*/
    public static void main(String[] args)
    {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01);
        In file = new In(args[0]);
        int N = file.readInt();
        Point[] points = new Point[N];
        ArrayList<Segment> segments = new ArrayList<Segment>();
       
        for (int i = 0; i < N; i++)
        {
            points[i] = new Point(file.readInt(), file.readInt());
            points[i].draw();
            StdDraw.show(0);
        }
        
        Point prevPoint = null;
        Arrays.sort(points);
     
        StdOut.println(Arrays.toString(points));
        
        Point[] sortedPoints = new Point[N];
        
        for (int i = 0; i < N; i++)
        {
            Point origin = points[i];
            
            Point[] sortedPoints = new Point[points.length - 1];
            int counter = 0;
            for(int j = 0; j < sortedPoints.length; j++)
            {
                if (j == i)
                    continue
                Points copy = points[j];
                sortedPoints[counter] = copy;
                counter++;
            }
            //StdOut.println("\n\ncurr point: "+points[i].toString());
            //boolean currPointAdded = false;
           // System.arraycopy(points, 0, sortedPoints, 0, N);
            
            Arrays.sort(sortedPoints, origin.SLOPE_ORDER);
          //  StdOut.println("ordered: "+Arrays.toString(sortedPoints));
            
            double prevSlope = 0.0;
            //  pointers group of adjacent points with equal slopes
            int start = i+1;
            
            int count = 1;
            for (int j = i+1; j < N; j++)
            {
                //StdOut.println("high counter: "+highPos+", low counter: "+lowPos);
                double currSlope = sortedPoints[i].slopeTo(sortedPoints[j]);
                //StdOut.println("prev slope: "+prevSlope+", curr slope: "+currSlope);
                if (prevSlope == currSlope || Math.abs(prevSlope - currSlope) <ERROR)
                {
                    count++;
                }
                else
                {
                    
                    if (count >= 4)
                    {
                        StdOut.println("count: "+count);
                       
                    
                        StdOut.print(sortedPoints[i]);
                        for (int k = start; k < j; k++)
                        {
                            StdOut.print(" -> " + sortedPoints[k]);
                        }
                        StdOut.println("");
                        sortedPoints[i].drawTo(sortedPoints[j-1]);
                        
                    }
                  
                    start  = j;
                    count = 1;
                    prevSlope = currSlope;
                }
            }
           
            if (count >= 4)
            {
                 StdOut.println("count: "+count);
             
                StdOut.print(sortedPoints[i]);
        
                
                for (int k = start; k < N; k++)
                {
                    StdOut.print(" -> " + sortedPoints[k]);
                }
                StdOut.println("");   
                sortedPoints[i].drawTo(sortedPoints[N-1]);
            }
            
        }
        
        StdDraw.show(0);
    }
}
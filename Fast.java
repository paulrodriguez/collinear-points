/***
  * Author:       Paul Rodriguez
  * Created:      2/27/2014
  * LastUpdated:  3/1/2014
  * 
  * Compilation:   javac Fast.java
  * Execution:     java Fast input.txt
  * Dependencies:  Point.java  
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
       
       public boolean isSubSegment(Point p, Point q, double s)
       {
           //StdOut.println("is sub segment called");
          // StdOut.print("inside pointInSegment for p = "+p.toString()+" checking segment ["+this.start.toString()+" | "+this.end.toString());
           if (s == this.slope && (q.equals(this.end) || p.equals(this.end)))
           {
              // StdOut.println(", this point is in a curretn segment");
               return true;
           }
         //  else if(s == this.slope && p == this.end)
          // {
            //   return true;
           //}
          
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
     
       // StdOut.println(Arrays.toString(points));
        
        Point[] sortedPoints = new Point[points.length];
       // Segment pivot = null;
        for (int i = 0; i < points.length; i++)
        {
            Point origin = points[i];
            
            sortedPoints = new Point[points.length];
            int counter = 0;
            for (int j = 0; j < sortedPoints.length; j++)
            {
               
                    Point copy = points[j];
                    sortedPoints[counter] = copy;
                    counter++;
                
            }
           // StdOut.println("\n\ncurr point: "+origin.toString());
            //boolean currPointAdded = false;
           // System.arraycopy(points, 0, sortedPoints, 0, N);
            
            Arrays.sort(sortedPoints, i, sortedPoints.length, origin.SLOPE_ORDER);
            //StdOut.println("ordered: "+Arrays.toString(sortedPoints));
            
            double prevSlope = 0.0;
            int start = i+1;
            int count = 1;
            if (sortedPoints.length-i > 1)
            {    
                prevSlope = origin.slopeTo(sortedPoints[i+1]);
                count = 2;
            }
            //  pointers group of adjacent points with equal slopes
          //  int start = 1;
            
           // int count = 2;
            for (int j = i+2; j < sortedPoints.length; j++)
            {
                //StdOut.println("high counter: "+highPos+", low counter: "+lowPos);
                double currSlope = origin.slopeTo(sortedPoints[j]);
                //StdOut.println("prev slope: "+prevSlope+", curr slope: "+currSlope);
                if (prevSlope == currSlope || Math.abs(prevSlope - currSlope) < ERROR)
                {
                    count++;
                }
                else
                {
                    
                    if (count >= 4)
                    {
                       // StdOut.println("count: "+count);
                        boolean isInSegment = false;
                        if (segments.isEmpty())
                        {
                            segments.add(new Segment(origin, sortedPoints[j-1], prevSlope));
                        }
                        else
                        {
                            for (int k = 0; k < segments.size(); k++)
                            {
                                if (segments.get(k).isSubSegment(origin, sortedPoints[j-1], prevSlope))
                                {
                                    isInSegment = true;
                                    break;
                                }
                            }
                        }
                        
                        //StdOut.print(origin);
                        if (!isInSegment)
                        {
                            segments.add(new Segment(origin, sortedPoints[j-1], prevSlope));
                            StdOut.print(origin);
                            for (int k = start; k < j; k++)
                            {
                                StdOut.print(" -> " + sortedPoints[k]);
                            }
                            StdOut.println("");
                            origin.drawTo(sortedPoints[j-1]);
                        }
                        
                    }
                  
                    start  = j;
                    count = 2;
                    prevSlope = currSlope;
                }
            }
           
            if (count >= 4)
            {
                // StdOut.println("count: "+count);
             
                boolean isInSegment = false;
                if (segments.isEmpty())
                {
                    segments.add(new Segment(origin, sortedPoints[sortedPoints.length-1], prevSlope));
                }
                else
                {
                    for (int k = 0; k < segments.size(); k++)
                    {
                        if (segments.get(k).isSubSegment(origin, sortedPoints[sortedPoints.length-1], prevSlope))
                        {
                            isInSegment = true;
                            break;
                        }
                    }
                }
                        
                if (!isInSegment)
                {
                    segments.add(new Segment(origin, sortedPoints[sortedPoints.length-1], prevSlope));
                    StdOut.print(origin);
        
                
                    for (int k = start; k < sortedPoints.length; k++)
                    {
                        StdOut.print(" -> " + sortedPoints[k]);
                    }
                    StdOut.println("");   
                    origin.drawTo(sortedPoints[sortedPoints.length-1]);
                }
            }
            
        }
        
        StdDraw.show(0);
    }
}
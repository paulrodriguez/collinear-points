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
import java.util.Hashtable;
public class Fast
{
   private static double ERROR = 0.00000000000000000000000001;
   
   private static boolean checkHashtable(Hashtable<Point, ArrayList<Double>> endPts, Point p, double s)
   {
       if (endPts.get(p) == null)
       {
           return false;
       }
       else
       {
           ArrayList<Double> slopes = endPts.get(p);
           for (int i = 0; i < slopes.size(); i++)
           {
               if (slopes.get(i).equals(s))
               {
                   return true;
               }
           }
       }
       return false;
   }
   
    public static void main(String[] args)
    {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01);
        In file = new In(args[0]);
        int N = file.readInt();
        Point[] points = new Point[N];
        //  each point can be collinear in different lines, so store it along with each slope for each line
        Hashtable<Point, ArrayList<Double>> endPoints = new Hashtable<Point, ArrayList<Double>>();
       
        for (int i = 0; i < N; i++)
        {
            points[i] = new Point(file.readInt(), file.readInt());
            points[i].draw();
            StdDraw.show(0);
        }
        
        Point prevPoint = null;
        Arrays.sort(points);
     
        Point[] sortedPoints = new Point[points.length];
        
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
            
            Arrays.sort(sortedPoints, i, sortedPoints.length, origin.SLOPE_ORDER);
            
            double prevSlope = 0.0;
            int start = i+1;
            int count = 1;
            if (sortedPoints.length-i > 1)
            {    
                prevSlope = origin.slopeTo(sortedPoints[i+1]);
                count = 2;
            }
           
            for (int j = i+2; j < sortedPoints.length; j++)
            {
                double currSlope = origin.slopeTo(sortedPoints[j]);
              
                if (prevSlope == currSlope || Math.abs(prevSlope - currSlope) < ERROR)
                {
                    count++;
                }
                else
                {         
                    if (count >= 4)
                    {      
                        boolean isInSegment = false;
                      
                        isInSegment = checkHashtable(endPoints, sortedPoints[j-1], new Double(prevSlope));
                     
                        if (!isInSegment)
                        {
                            if (endPoints.get(sortedPoints[j-1]) == null)
                            {
                                ArrayList<Double> slopes = new ArrayList<Double>();
                                slopes.add(prevSlope);
                                endPoints.put(sortedPoints[j-1], slopes);
                            }
                            else
                            {
                                ArrayList<Double> slopes = endPoints.get(sortedPoints[j-1]);
                                slopes.add(prevSlope);
                                endPoints.put(sortedPoints[j-1], slopes);
                            }

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
                boolean isInSegment = false;
              
                isInSegment = checkHashtable(endPoints, sortedPoints[sortedPoints.length-1], prevSlope);
                               
                if (!isInSegment)
                {
                    if (endPoints.get(sortedPoints[sortedPoints.length-1]) == null)
                    {
                        ArrayList<Double> slopes = new ArrayList<Double>();
                        slopes.add(prevSlope);
                        endPoints.put(sortedPoints[sortedPoints.length-1], slopes);
                    }
                    else
                    {
                        ArrayList<Double> slopes = endPoints.get(sortedPoints[sortedPoints.length-1]);
                        slopes.add(prevSlope);
                        endPoints.put(sortedPoints[sortedPoints.length-1], slopes);
                    }
                    
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
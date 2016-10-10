import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
   
    private LineSegment[] segments;
   
     // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {   
        if (points == null) throw new NullPointerException();
        if (hasDuplicates(points)) throw new IllegalArgumentException();
        
        LinkedList<LineSegment> segmentList = new LinkedList<LineSegment>();
      
        Point[] copyPoints = Arrays.copyOf(points, points.length);
        int n = copyPoints.length;
        int counter;
        
        
        for (Point zeroPoint: copyPoints) {
            
            Point[] slopeOrderPoints = copyPoints.clone();
            Arrays.sort(slopeOrderPoints, zeroPoint.slopeOrder());
            
            LinkedList<Point> segment = new LinkedList<Point>();
            segment.add(zeroPoint);
            
            for (int j = 1; j < n - 1; j++) {
                
                Point slopePoint = slopeOrderPoints[j];
                Point nextSlopePoint = slopeOrderPoints[j+1];
                
                double slope = zeroPoint.slopeTo(slopePoint);
                double nextSlope = zeroPoint.slopeTo(nextSlopePoint);
                
                if (slope == nextSlope) {
                    if (!segment.contains(slopePoint)) segment.add(slopePoint);
                    if (!segment.contains(nextSlopePoint)) segment.add(nextSlopePoint);
                }
                
                if (slope != nextSlope || j == n - 2) {
                    if (segment.size() > 3) {
                        LineSegment seg = new LineSegment(segment.getFirst(), segment.getLast());
                        if (!segmentList.contains(seg)) segmentList.add(seg);
                    }
                    segment.clear();
                    segment.add(zeroPoint);
                }
            }
        }
        
        this.segments = segmentList.toArray(new LineSegment[segmentList.size()]);
    }
    
    public int numberOfSegments() {     // the number of line segments
        return this.segments.length;
    }
   
    public LineSegment[] segments() {   // the line segments
        return this.segments;
    }
    
    private boolean hasDuplicates (Point[] points) {
        for (int i = 0; i < points.length; i++)
            for (int j = i+1; j < points.length; j++)
                if (points[i].compareTo(points[j]) == 0)
                    return true;
        return false;
    }
   
    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
        StdOut.println(collinear.numberOfSegments());
    }
}
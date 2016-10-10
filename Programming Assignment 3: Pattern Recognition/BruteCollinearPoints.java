import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    
    private LineSegment[] segments;
    
    public BruteCollinearPoints(Point[] points){    // finds all line segments containing 4 points
        if (points == null) throw new NullPointerException();
        
        if (hasDuplicates(points)) throw new IllegalArgumentException();
        
        Point[] sortedPoints = Arrays.copyOf(points, points.length);
        Arrays.sort(sortedPoints);
        int n = points.length;
        ArrayList<LineSegment> segmentList = new ArrayList<LineSegment>();
        
        for (int i = 0; i< n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                for (int k = j + 1; k < n - 1; k++) {
                    for (int l = k + 1; l < n; l++) {
                        Point p0 = sortedPoints[i];
                        Point p1 = sortedPoints[j];
                        Point p2 = sortedPoints[k];
                        Point p3 = sortedPoints[l];
                        
                        if (p0.slopeTo(p1) == p0.slopeTo(p2)  && p0.slopeTo(p1) == p0.slopeTo(p3)) {
                            LineSegment seg = new LineSegment(p0, p3);
                            segmentList.add(seg);
                        }
                    }}}}
        this.segments = segmentList.toArray(new LineSegment[segmentList.size()]);
    }
    
    
    public int numberOfSegments(){        // the number of line segments
        return segments.length;
    }
    
    public LineSegment[] segments(){                // the line segments
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
        
        StdOut.println(collinear.numberOfSegments());
    }
}
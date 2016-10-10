import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class PointSET {
    
    private SET<Point2D> set;
    
    public PointSET() {   // construct an empty set of points 
        set = new SET<Point2D>();
    }
    
    public boolean isEmpty() {   // is the set empty? 
        return set.isEmpty();
    }
    
    public int size() { // number of points in the set 
        return set.size();
    }
    
    public void insert(Point2D p) {  
    // add the point to the set (if it is not already in the set)
        if (!set.contains(p))
            set.add(p);
    }
    
    public boolean contains(Point2D p) {  // does the set contain point p? 
        return set.contains(p);
    }
    
    public void draw() {  // draw all points to standard draw 
        for (Point2D point: set)
            point.draw();
    }
    
    public Iterable<Point2D> range(RectHV rect) {
    // all points that are inside the rectangle 
        Stack<Point2D> stack = new Stack<Point2D>();
        for (Point2D point: set) {
            if (rect.contains(point))
                stack.push(point);
        }
        return stack;
    }
    
    public Point2D nearest(Point2D p) {
    // a nearest neighbor in the set to point p; null if the set is empty 
        if (set.isEmpty()) return null;
        Point2D nearestPoint = set.min();
        double nearestDistance = p.distanceTo(nearestPoint);
        for (Point2D point: set) {
            if (p.distanceTo(point) < nearestDistance) {
                nearestDistance = p.distanceTo(point);
                nearestPoint = point;
            }
        }
        return nearestPoint;
    }
    
    // unit testing of the methods (optional)
    public static void main(String[] args) {}                 
     
}
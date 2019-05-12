import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.util.List;
import java.util.ArrayList;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

public class PointSET
{   
    private final SET<Point2D> set = new SET<Point2D>();
   
    // construct an empty set of points
    public PointSET()
    {
    }
   
    // is the set empty?
    public boolean isEmpty()                       
    {
        return set.isEmpty();
    }
   
    // number of points in the set 
    public int size()
    {
        return set.size();
    }
   
    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p)              
    {
        if (p == null)
            throw new java.lang.IllegalArgumentException();
        set.add(p);
    }
   
    // does the set contain point p? 
    public boolean contains(Point2D p)            
    {
        if (p == null)
            throw new java.lang.IllegalArgumentException();
        return set.contains(p);
    }
   
    // draw all points to standard draw 
    public void draw()                         
    {
        for (Point2D p : set)
        {
            StdDraw.point(p.x(), p.y());
        }
    }
   
    // all points that are inside the rectangle (or on the boundary) 
    public Iterable<Point2D> range(RectHV rect)             
    {
        if (rect == null)
            throw new java.lang.IllegalArgumentException();
        List<Point2D> allPoints = new ArrayList<Point2D>();
        for (Point2D pointInSet : set)
        {
            if (rect.contains(pointInSet))
            {
                allPoints.add(pointInSet);
            }
        }
        return allPoints;
    }
   
    // a nearest neighbor in the set to point p; null if the set is empty 
    public Point2D nearest(Point2D p)             
    {
        if (p == null)
            throw new java.lang.IllegalArgumentException();
        
        double distance = Double.POSITIVE_INFINITY;
        Point2D nearestPoint = null; 
        for (Point2D pointInSet : set)
        {
            double distanceToCurrentPoint = p.distanceSquaredTo(pointInSet);
            if (distance > distanceToCurrentPoint) 
            {
                distance = distanceToCurrentPoint;
                nearestPoint = pointInSet;
            }
        }
        return nearestPoint;
    }
}
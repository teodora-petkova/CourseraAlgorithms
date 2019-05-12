import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.util.List;
import java.util.ArrayList;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree 
{
    private Node root;
    private int size;
    
    private static class Node
    {
        private Point2D p; // the point
        private RectHV rect; // the axis-aligned rectangle corresponding to this node
        private Node leftbottom; // the left/bottom subtree
        private Node righttop; // the right/top subtree
    }
     
    // construct an empty set of points
    public KdTree()
    {
        this.root = null;
        this.size = 0;
    }
   
    // is the set empty?
    public boolean isEmpty()                       
    {
        return this.root == null;
    }
     
    // number of points in the set 
    public int size()
    {
        return this.size;
    }
    
    private RectHV[] splitRectangle(RectHV rect, Point2D p,
                                           boolean isOrientationByXAxis)
    {
        if (isOrientationByXAxis)
        {
            RectHV r1 = new RectHV(rect.xmin(), rect.ymin(), p.x(), rect.ymax());
            RectHV r2 = new RectHV(p.x(), rect.ymin(), rect.xmax(), rect.ymax());
            return new RectHV[] {r1, r2};
        }
        else
        {
            RectHV r1 = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), p.y());
            RectHV r2 = new RectHV(rect.xmin(), p.y(), rect.xmax(), rect.ymax());
            return new RectHV[] {r1, r2};  
        }
    }
    
    private Node insert(Node node, Point2D p, boolean isOrientationByXAxis, 
                        RectHV rect)
    {
        if (node == null)
        {
            Node newNode = new Node();
            newNode.p = new Point2D(p.x(), p.y());
            newNode.leftbottom = null;
            newNode.righttop = null;
            newNode.rect = rect;
            this.size++;
            return newNode;
        }
        else
        {
            RectHV[] rects = splitRectangle(rect, node.p, isOrientationByXAxis);
        
            if (isOrientationByXAxis)
            {
                if (p.x() < node.p.x())
                {
                    node.leftbottom = insert(node.leftbottom, p,
                                             !isOrientationByXAxis, rects[0]);
                }
                else if (p.x() > node.p.x() 
                             || p.x() == node.p.x() && p.y() != node.p.y())
                {
                    node.righttop = insert(node.righttop, p,
                                           !isOrientationByXAxis, rects[1]);
                }
            }
            else 
            {
                if (p.y() < node.p.y())
                {
                    node.leftbottom = insert(node.leftbottom, p,
                                             !isOrientationByXAxis, rects[0]);
                }
                else if (p.y() > node.p.y()
                             || p.y() == node.p.y() && p.x() != node.p.x())
                {
                   node.righttop = insert(node.righttop, p,
                                           !isOrientationByXAxis, rects[1]);
                }
            }
            
            return node;
        }
    }
        
    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p)              
    {
        if (p == null)
            throw new java.lang.IllegalArgumentException();
        
        this.root = insert(this.root, p, true, new RectHV(0, 0, 1, 1));
    }
   
    private boolean contains(Node node, Point2D p, 
                             boolean isOrientationByXAxis)
    {
        if (node == null)
        {
            return false;
        }
        else if (node.p.compareTo(p) == 0)
        {
            return true;
        }
        else
        {
            if (isOrientationByXAxis)
            {
                if (p.x() < node.p.x())
                {
                    return contains(node.leftbottom, p, !isOrientationByXAxis);
                }
                else
                {
                    return contains(node.righttop, p, !isOrientationByXAxis);
                }
            }
            else 
            {
                if (p.y() < node.p.y())
                {
                    return contains(node.leftbottom, p, !isOrientationByXAxis);  
                }
                else
                {
                    return contains(node.righttop, p, !isOrientationByXAxis);    
                }
            }
        }
    }
    
    // does the set contain point p? 
    public boolean contains(Point2D p)            
    {
        if (p == null)
            throw new java.lang.IllegalArgumentException();
        
        return contains(this.root, p, true);
    }
    
    private void draw(Node node, boolean isOrientationByXAxis)
    {
        if (node == null)
        {
            return;
        }
              
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        node.p.draw();
        
        Point2D point1 = null;
        Point2D point2 = null;
        if (isOrientationByXAxis)
        {
            StdDraw.setPenColor(StdDraw.RED);
            point1 = new Point2D(node.p.x(), node.rect.ymin());
            point2 = new Point2D(node.p.x(), node.rect.ymax());
        }
        else
        {
            StdDraw.setPenColor(StdDraw.BLUE);
            point1 = new Point2D(node.rect.xmin(), node.p.y());
            point2 = new Point2D(node.rect.xmax(), node.p.y());
        }
        StdDraw.setPenRadius(0.005);
        StdDraw.line(point1.x(), point1.y(), point2.x(), point2.y());
        
        draw(node.leftbottom, !isOrientationByXAxis);
        draw(node.righttop, !isOrientationByXAxis);
    }
    
    // draw all points to standard draw 
    public void draw()                         
    {
        draw(this.root, true);
    }
   
    private List<Point2D> range(Node node, RectHV rect)             
    {
        List<Point2D> list = new ArrayList<Point2D>();
       
        if (node != null)
        {
            if (rect.contains(node.p))
            {
                list.add(node.p);
            }
        
            if (node.righttop != null && node.righttop.rect.intersects(rect))
            {
                list.addAll(range(node.righttop, rect));
            }
        
            if (node.leftbottom != null && node.leftbottom.rect.intersects(rect))
            {
                list.addAll(range(node.leftbottom, rect));
            }
        }
        return list;
    }
    
    public Iterable<Point2D> range(RectHV rect)             
    {
        if (rect == null)
            throw new java.lang.IllegalArgumentException();
        
        return range(this.root, rect);
    }

    private Point2D nearest(Node node, Point2D p, Point2D currentMin)       
    {
        if (node != null)
        {
            double minDistance = currentMin.distanceSquaredTo(p);
            if (node.p.distanceSquaredTo(p) < minDistance)
            {
                currentMin = node.p;
            }
            
            if (node.leftbottom != null && node.righttop != null
                    && node.leftbottom.rect.distanceSquaredTo(p)
                    > node.righttop.rect.distanceSquaredTo(p))
            {
                currentMin = nearest(node.righttop, p, currentMin);
                
                if (minDistance > node.leftbottom.rect.distanceSquaredTo(p))
                {
                    currentMin = nearest(node.leftbottom, p, currentMin);
                }
            }
            else 
            {
                if (node.leftbottom != null)
                {
                    currentMin = nearest(node.leftbottom, p, currentMin);
                
                    if (node.righttop != null && minDistance
                            > node.righttop.rect.distanceSquaredTo(p))
                    {
                        currentMin = nearest(node.righttop, p, currentMin);
                    }
                }
            }
        }
        return currentMin;
    }
    
    // a nearest neighbor in the set to point p; null if the set is empty 
    public Point2D nearest(Point2D p)             
    {
        if (p == null)
            throw new java.lang.IllegalArgumentException();
        Point2D currentMin = null;
        if (this.root != null)
        {
            currentMin = this.root.p;
        }
        return nearest(this.root, p, currentMin);
    }
}
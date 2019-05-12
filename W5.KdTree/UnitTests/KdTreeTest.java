import junit.framework.TestCase;
import edu.princeton.cs.algs4.Point2D;
import java.util.Arrays;
import edu.princeton.cs.algs4.RectHV;

public class KdTreeTest extends TestCase {

    public void test1() 
    {        
        KdTree tree = new KdTree();
        tree.insert(new Point2D(0.25, 0.375));
        tree.insert(new Point2D(0.875, 1.0));
        tree.insert(new Point2D(0.875, 0.625));
        tree.insert(new Point2D(0.75, 1.0));
        assertEquals("", tree.size(), 4);
    }
    
    public void test2()
    {
        KdTree tree = new KdTree();
        tree.insert(new Point2D(0.875, 0.0));
        tree.insert(new Point2D(0.75, 0.375));
        tree.insert(new Point2D(0.0, 0.625));
        tree.insert(new Point2D(0.5, 0.625));
        assertEquals("", tree.size(), 4);
        
    }
    
    public void test3()
    {
        KdTree tree = new KdTree();
        tree.insert(new Point2D(0.75, 0.875));
        tree.insert(new Point2D(0.125, 0.625));
        tree.insert(new Point2D(0.75, 0.625));
        tree.insert(new Point2D(0.75, 0.5));
        tree.insert(new Point2D(0.125, 1.0));
        tree.insert(new Point2D(1.0, 0.75));
        tree.insert(new Point2D(0.875, 0.375));
        tree.insert(new Point2D(0.125, 0.75));
        tree.insert(new Point2D(0.875, 0.875));
        tree.insert(new Point2D(0.0, 0.125));
        tree.insert(new Point2D(1.0, 0.875));
        tree.insert(new Point2D(0.0, 1.0));
        tree.insert(new Point2D(0.5, 0.25));
        tree.insert(new Point2D(0.375, 0.125));
        tree.insert(new Point2D(0.875, 0.25));
        tree.insert(new Point2D(1.0, 0.125));
        tree.insert(new Point2D(0.125, 0.5));
        tree.insert(new Point2D(0.0, 0.875));
        tree.insert(new Point2D(0.0, 0.0));
        tree.insert(new Point2D(0.625, 0.25)); 
        assertEquals("", tree.range(new RectHV(0, 0, 0, 0)), Arrays.asList(new Point2D(0.0, 0.0)));
    }
    
    public void test4()    
    {
        KdTree tree = new KdTree();
        tree.insert(new Point2D(0.0, 0.0));
        tree.insert(new Point2D(1.0, 0.25));
        tree.insert(new Point2D(0.25, 1.0));
        tree.insert(new Point2D(0.5, 0.0));
        tree.insert(new Point2D(1.0, 0.75));
        tree.insert(new Point2D(0.5, 0.5));
        tree.insert(new Point2D(0.25, 0.75));
        tree.insert(new Point2D(0.75, 0.0));
        tree.insert(new Point2D(0.5, 0.75));
        tree.insert(new Point2D(0.75, 0.25));
        
        assertEquals("", tree.contains(new Point2D(0.5, 0.75)), true);
        
    }
}
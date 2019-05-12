import junit.framework.TestCase;
public class PointTest extends TestCase {

    public void testSlopeTo() {
        Point p = new Point(211, 80);
        Point q = new Point(292, 487);
        double slope = p.slopeTo(q);
        assertEquals("", 5.0246913580246915, slope);
        
        p = new Point(269, 12864);
        q = new Point(26890, 19475);
        slope = p.slopeTo(q);
        assertEquals("", 0.24833777844558808, slope);
  
        p = new Point(0, 5);
        q = new Point(5, 1);
        slope = p.slopeTo(q);
        assertEquals("", -0.8, slope);
    }
    
    public void testCompareTo() {
        Point p = new Point(226, 345);
        Point q = new Point(226, 345);
        int result = p.compareTo(q);
        //int result = p.slopeOrder().compare(p,q);
        assertEquals("", 0, result);
        
        p = new Point(296, 20530);
        q = new Point(296, 20530);
        result = p.compareTo(q);
        assertEquals("", 0, result);
        
        p = new Point(389, 418);
        q = new Point(166, 418);
        result = p.compareTo(q);
        assertEquals("", 1, result);
        
        p = new Point(495, 396);
        q = new Point(470, 396);
        result = p.compareTo(q);
        assertEquals("", 1, result);
        result = q.compareTo(p);
        assertEquals("", -1, result);
    }
}
import junit.framework.TestCase;
public class FastCollinearPointsTest extends TestCase {

    public void testEightPoints() {
        Point[] points = new Point[8];
        points[0] = new Point(10, 0);
        points[1] = new Point(0, 10);
        points[2] = new Point(3, 7);
        points[3] = new Point(7, 3);
        points[4] = new Point(20, 21);
        points[5] = new Point(3, 4);
        points[6] = new Point(14, 15);
        points[7] = new Point(6, 7);
            
        FastCollinearPoints fast = new FastCollinearPoints(points);
        LineSegment[] segments = fast.segments();
        
        assertEquals("", 2, fast.numberOfSegments());
                
        assertEquals("", segments[0].toString(), new LineSegment(new Point(10, 0), new Point(0, 10)).toString());
        assertEquals("", segments[1].toString(), new LineSegment(new Point(3, 4), new Point(20, 21)).toString());
    }
    
    public void testOneHorizontalLine() {
        Point[] points = new Point[5];
        points[0] = new Point(0, 0);        
        points[1] = new Point(0, 1);
        points[2] = new Point(0, 2);
        points[3] = new Point(0, 10);
        points[4] = new Point(1, 1);
        
        FastCollinearPoints fast = new FastCollinearPoints(points);
        LineSegment[] segments = fast.segments();
        
        assertEquals("", 1, fast.numberOfSegments());
                
        assertEquals("", segments[0].toString(), new LineSegment(new Point(0, 0), new Point(0, 10)).toString());
    }
    
    public void testOneHorizontalLine2() {
        Point[] points = new Point[7];
        points[0] = new Point(0, 0);        
        points[1] = new Point(0, 1);
        points[2] = new Point(0, 2);
        points[3] = new Point(0, 3);
        points[4] = new Point(0, 4);
        points[5] = new Point(0, 5);
        points[6] = new Point(0, 10);
        
        FastCollinearPoints fast = new FastCollinearPoints(points);
        LineSegment[] segments = fast.segments();
        
        assertEquals("", 1, fast.numberOfSegments());
                
        assertEquals("", segments[0].toString(), new LineSegment(new Point(0, 0), new Point(0, 10)).toString());
    }

    public void testOneVerticalLine() {
        Point[] points = new Point[4];
        points[0] = new Point(0, 10);        
        points[1] = new Point(1, 10);
        points[2] = new Point(2, 10);
        points[3] = new Point(10, 10);
        
        FastCollinearPoints fast = new FastCollinearPoints(points);
        LineSegment[] segments = fast.segments();
        
        assertEquals("", 1, fast.numberOfSegments());
                
        assertEquals("", segments[0].toString(), new LineSegment(new Point(0, 10), new Point(10, 10)).toString());
    }
    
    public void testOneVerticalLine2() {
        Point[] points = new Point[7];
        points[0] = new Point(0, 10);        
        points[1] = new Point(1, 10);
        points[2] = new Point(2, 10);
        points[3] = new Point(4, 10);
        points[4] = new Point(5, 10);
        points[5] = new Point(6, 10);
        points[6] = new Point(10, 10);
        
        FastCollinearPoints fast = new FastCollinearPoints(points);
        LineSegment[] segments = fast.segments();
        
        assertEquals("", 1, fast.numberOfSegments());
                
        assertEquals("", segments[0].toString(), new LineSegment(new Point(0, 10), new Point(10, 10)).toString());
    }

    /*
    //@Test(expected = java.lang.IllegalArgumentException.class)
    public void testNullPoint() {
        Point[] points = new Point[4];
        points[0] = new Point(0, 1);        
        points[1] = new Point(1, 1);
        points[2] = null; //new Point(1, 2);
        points[3] = null;
        
        FastCollinearPoints fast = new FastCollinearPoints(points);
        LineSegment[] segments = fast.segments();
        
        assertEquals("", 1, fast.numberOfSegments());
                
        assertEquals("", segments[0].toString(), new LineSegment(new Point(0, 10), new Point(10, 10)).toString());
    }*/
}


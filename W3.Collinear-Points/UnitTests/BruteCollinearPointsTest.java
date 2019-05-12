import junit.framework.TestCase;
public class BruteCollinearPointsTest extends TestCase {

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
            
        BruteCollinearPoints brute = new BruteCollinearPoints(points);
        LineSegment[] segments = brute.segments();
        
        assertEquals("", 2, brute.numberOfSegments());
                
        assertEquals("", segments[0].toString(), new LineSegment(new Point(10, 0), new Point(0, 10)).toString());
        assertEquals("", segments[1].toString(), new LineSegment(new Point(3, 4), new Point(20, 21)).toString());
    }
    
 
}
import  java.util.List;
import  java.util.Arrays;
import  java.util.ArrayList;

public class BruteCollinearPoints {
    
    private final List<LineSegment> lineSegments = new ArrayList<LineSegment>();
    
    public BruteCollinearPoints(Point[] points) {
        
        if (points == null) {
            throw new java.lang.IllegalArgumentException();
        }
    
        for (int i1 = 0; i1 < points.length; i1++) {
            Point p = points[i1];
            checkIfNull(p);
            
            for (int i2 = i1+1; i2 < points.length; i2++) {
                Point q = points[i2];
                checkIfNull(q);                
                if (p.compareTo(q) == 0) {
                    throw new java.lang.IllegalArgumentException();
                }    
                
                for (int i3 = i2+1; i3 < points.length; i3++) {
                    Point r = points[i3];
                    checkIfNull(r);
                    if (p.compareTo(r) == 0 ||
                        q.compareTo(r) == 0) {
                        throw new java.lang.IllegalArgumentException();
                    }    
                    
                    for (int i4 = i3+1; i4 < points.length; i4++) {
                        Point s = points[i4];
                        checkIfNull(s);
                        if (p.compareTo(s) == 0 ||
                            q.compareTo(s) == 0 ||
                            r.compareTo(s) == 0) {
                            throw new java.lang.IllegalArgumentException();
                        }   
                             
                        LineSegment segment = getLineSegmentIfPointsAreCollinear(p, r, s, q);
                        if (segment != null) {
                            lineSegments.add(segment);
                        }
                    }                     
                }  
            }
        } 
    }
    
    private LineSegment getLineSegmentIfPointsAreCollinear(Point p, Point r, Point s, Point q) {
        LineSegment segment = null;
       
        double pSlopeToQ = p.slopeTo(q);
        double pSlopeToR = p.slopeTo(r);
        double pSlopeToS = p.slopeTo(s);
                        
        if (pSlopeToQ == pSlopeToR && 
            pSlopeToQ == pSlopeToS) {
                  
            Point[] pointsToCompare = new Point[4];
            pointsToCompare[0] = p;
            pointsToCompare[1] = q;
            pointsToCompare[2] = r;
            pointsToCompare[3] = s;
                            
            Arrays.sort(pointsToCompare);
                            
            Point min = pointsToCompare[0];
            Point max = pointsToCompare[3];
                            
            segment = new LineSegment(min, max);
        }       
        return segment;
    }
    
    private void checkIfNull(Point p) {
        if (p == null) {
            throw new java.lang.IllegalArgumentException();
        }
    }
    
    public int numberOfSegments() {
        return lineSegments.size();
    }
    
    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }
}
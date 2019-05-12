import  java.util.List;
import  java.util.ArrayList;
import  java.util.Arrays;

public class FastCollinearPoints {
    private final List<LineSegment> lines = new ArrayList<LineSegment>();
    
    public FastCollinearPoints(Point[] pointsInput) {
        checkIfNull(pointsInput);
        
        for (int i = 0; i < pointsInput.length; i++) {

            Point p = pointsInput[i];  
            checkIfNull(p);
            
            Point[] points = pointsInput.clone();    
            
            Arrays.sort(points, 0, points.length, p.slopeOrder());        
            
            ArrayList<ArrayList<Point>> groupedCollinearPoints = getCollinearPoints(points, p);
            
            for (ArrayList<Point> group : groupedCollinearPoints) {
                
                Point[] collinearPoints = new Point[group.size()];
                collinearPoints = group.toArray(collinearPoints);

                if (collinearPoints.length > 3) {
                    Arrays.sort(collinearPoints, 0, collinearPoints.length);
                    if (p.compareTo(collinearPoints[0]) == 0) {
                        lines.add(new LineSegment(collinearPoints[0], collinearPoints[collinearPoints.length - 1]));
                    }
                }
            }
        }
    }   
    
    private ArrayList<ArrayList<Point>> getCollinearPoints(Point[] points, Point p) {
        
        ArrayList<ArrayList<Point>> groupedPoints = new ArrayList<ArrayList<Point>>();
        
        double previousSlope = 0;
        Point previousPoint = p;
        ArrayList<Point> collinearPoints = new ArrayList<Point>();
        
        for (int i = 0; i < points.length; i++) {
             
            Point q = points[i];
            double pSlopeToQ = p.slopeTo(q); 
            
            if (previousSlope == pSlopeToQ) {
                collinearPoints.add(previousPoint);
            }
            else {
                collinearPoints.add(previousPoint);
                groupedPoints.add(collinearPoints);
                
                collinearPoints = new ArrayList<Point>();
                collinearPoints.add(p);
            }
            previousSlope = pSlopeToQ;   
            previousPoint = q;
        }
        collinearPoints.add(previousPoint);
        groupedPoints.add(collinearPoints);
                    
        return groupedPoints;
    }
    
    private void checkIfNull(Object o) {
        if (o == null) {
            throw new java.lang.IllegalArgumentException();
        }
    }
    
    public int numberOfSegments() {
        return lines.size();
    }
    
    public LineSegment[] segments() {
        
        LineSegment[] lineSegments = new LineSegment[lines.size()];
        return lines.toArray(lineSegments);
    }
}
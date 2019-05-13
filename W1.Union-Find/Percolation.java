//import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
/*
 public class Percolation {
   public Percolation(int n)                // create n-by-n grid, with all sites blocked
   public void open(int row, int col)       // open site (row, col) if it is not open already
   public boolean isOpen(int row, int col)  // is site (row, col) open?
   public boolean isFull(int row, int col)  // is site (row, col) full?
   public int numberOfOpenSites()           // number of open sites
   public boolean percolates()              // does the system percolate?
   public static void main(String[] args)   // test client (optional)
*/ 

public class Percolation 
{
    private int nSize;
    private int size;
    private int sizeForFullSiteUF;
    private boolean[] openSites;
    private int numberOfOpenSites;
    
    private WeightedQuickUnionUF ufForPercolation;
    private WeightedQuickUnionUF ufForFullSite;
    
    private int countUF = 0;
    
    public int GetCount()
    {
        return countUF;
    }
    
    public Percolation(int n)
    {
        if (n <= 0)
        {
            throw  new java.lang.IllegalArgumentException();
        }
        
        nSize = n;
        size = n*n+2*n;
        sizeForFullSiteUF = size - nSize;
        openSites = new boolean[size];
        numberOfOpenSites = 0;
        
        countUF++;
        ufForPercolation = new WeightedQuickUnionUF(size);
        countUF++;
        ufForFullSite = new WeightedQuickUnionUF(sizeForFullSiteUF);
   
        for (int i = 0; i < nSize; i++)
        {
            openSites[i] = true;
            
            // top
            countUF++;
            ufForPercolation.union(0, i);
            countUF++;
            ufForFullSite.union(0, i);
            
            // bottom
            openSites[size - nSize + i] = true;
            countUF++;
            ufForPercolation.union(size - nSize + i, size-1);
        }
        
        for (int i = nSize; i < size - nSize; i++)
        {
           openSites[i] = false;
        }
    }
    
    // open site (row, col) if it is not already open 
    public void open(int row, int col)
    {
       if (!isOpen(row, col))
       {
           int current = getItem(row, col);
           
           openSites[current] = true;
           numberOfOpenSites++;
                      
           int up = getItem(row+1, col);
           int down = getItem(row-1, col);
           int right = getItem(row, col+1);
           int left = getItem(row, col-1);
                      
           if (row-1 == 0 || isInRangeAndIsOpen(row-1, col, down))
           {
               union(current, down); 
           }
           if (row+1 == nSize+1 || isInRangeAndIsOpen(row+1, col, up))
           {
               union(current, up);
           }
           if (isInRangeAndIsOpen(row, col-1, left))
           {
                union(current, left);
           }       
           if (isInRangeAndIsOpen(row, col+1, right))
           {
               union(current, right);
           }       
       }
    }
   
    private void union(int current, int newItem)
    {
        countUF++;
        ufForPercolation.union(current, newItem);
        if (newItem < sizeForFullSiteUF) 
        {
            countUF++;
            ufForFullSite.union(current, newItem);
        }
    }
    
    // is site (row, col) open?
    public boolean isOpen(int row, int col)
    {
       checkInRange(row, col);
       return openSites[getItem(row, col)];
    }
    
    private boolean isInRangeAndIsOpen(int row, int col, int item)
    {
        return isInRange(row, col) && openSites[item];
    }
    
    // is site (row, col) full?
    public boolean isFull(int row, int col)
    {
       checkInRange(row, col);
       return connectedForFullSite(0, getItem(row, col));
    }
   
    // number of open sites
    public  int numberOfOpenSites()  
    {
       return numberOfOpenSites;
    } 
   
    // does the system percolate?
    public boolean percolates()           
    {
        return connected(0, size-1);
    }
   
    private boolean connected(int p, int q)
    {
        countUF++;
        countUF++;
        return ufForPercolation.find(p) == ufForPercolation.find(q);
    }
    
    private boolean connectedForFullSite(int p, int q)
    {
        countUF++;
        countUF++;
        return ufForFullSite.find(p) == ufForFullSite.find(q);
    }    
   
    private void checkInRange(int row, int col)
    {
        if (!isInRange(row, col))
        {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }

    private boolean isInRange(int row, int col)
    {
      return (row >= 1 && row  <= nSize && col >= 1 && col <= nSize);
    }

    private int getItem(int row, int col)
    {
       return row*nSize+col-1;
    }
   
    public static void main(String[] args)  
    {
       
    }
}
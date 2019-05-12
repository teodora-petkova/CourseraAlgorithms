
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats 
{
   private double[] stats;
   private int numberTrails;
   
   // perform trials independent experiments on an n-by-n grid
   public PercolationStats(int n, int trails) 
   {
       if (n <= 0 || trails <= 0)
       {
           throw  new java.lang.IllegalArgumentException();
       }
              
       numberTrails = trails;
       stats = new double[trails];
             
       int numberOfCellsInGrid = n*n;
       
       for (int i = 0; i < trails; i++)
       {
           Percolation p =  new Percolation(n);
           
           while (!p.percolates())
           {
               int row = StdRandom.uniform(1, n+1);
               int column = StdRandom.uniform(1, n+1);
               p.open(row, column);
           }
           
           stats[i] = (double) p.numberOfOpenSites()/(double) numberOfCellsInGrid;
       }
   }    
   
   // sample mean of percolation threshold
   public double mean() 
   {
       double sum = 0;
       for (int i = 0; i < numberTrails; i++)
       {
           sum += stats[i];
       }
       return sum / numberTrails;
   }                        
   
   // sample standard deviation of percolation threshold
   public double stddev() 
   {
       double mean = mean();
       double deviation = 0;
       for (int i = 0; i < numberTrails; i++)
       {
           deviation += (stats[i] - mean)*(stats[i] - mean);
       }
       return Math.sqrt(deviation / (numberTrails - 1));    
   }                       
   
    // low  endpoint of 95% confidence interval
   public double confidenceLo()
   {
       double mean = mean();
       return mean - 1.96*stddev()/Math.sqrt(numberTrails);
   }                  
   
   // high endpoint of 95% confidence interval
   public double confidenceHi()
   {
       double mean = mean();
       return mean + 1.96*stddev()/Math.sqrt(numberTrails);   
   }                 

   public static void main(String[] args)// test client (described below)
   {
      if (args.length == 2)
      {
          int n = Integer.parseInt(args[0]);
          int t = Integer.parseInt(args[1]);
          PercolationStats p1 = new PercolationStats(n, t);
          StdOut.println("mean = " + p1.mean());
          StdOut.println("stddev = " + p1.stddev());
          StdOut.println("95% confidence interval = " + p1.confidenceLo()+ ", " + p1.confidenceHi() + "");
      }
   }        
}


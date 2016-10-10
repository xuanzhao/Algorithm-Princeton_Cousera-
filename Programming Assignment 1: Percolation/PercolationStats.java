import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;


public class PercolationStats {

    private int gridSize;
    private int N;
    private double[] results;
    
   // perform trials independent experiments on an n-by-n grid     
   public PercolationStats(int n, int trials)    
   {
       if (n < 1 || trials < 1)
       {
           throw new IllegalArgumentException(
           "both arguments gridSize and trials must be greater than 1");
       }
       
       this.N = trials;
       gridSize = n*n;
       results = new double[N];
       
       for (int k = 0; k < N; k++)
       {
           Percolation percolation_trial = new Percolation(n);
           int openedSites = 0;
           while (!percolation_trial.percolates())
           {
               int i = StdRandom.uniform(1, n+1);
               int j = StdRandom.uniform(1, n+1);
               
               if (!percolation_trial.isOpen(i, j))
               {
                   percolation_trial.open(i, j);
                   openedSites += 1;
               }
           }
           double threshold_p = (double) openedSites / (double) gridSize;
           results[k] = threshold_p; 
       }
   }
   
   public double mean()  // sample mean of percolation threshold
   {
       double mu = StdStats.mean(results);   
       return mu;
   }
   
   public double stddev() // sample standard deviation of percolation threshold
   {
       double sigma = StdStats.stddev(results);
       return sigma;
   }
   
   public double confidenceLo() // low  endpoint of 95% confidence interval
   {
       double low = mean() - 1.96*stddev() / Math.sqrt(N);
       return low;
   }
   
   public double confidenceHi()  // high endpoint of 95% confidence interval
   {
       double high = mean() + 1.96*stddev() / Math.sqrt(N);
       return high;
   }
   
   public static void main(String[] args) // test client (described below)
   {
       int n = Integer.parseInt(args[0]);
       int N = Integer.parseInt(args[1]);
       PercolationStats stats = new PercolationStats(n, N);
       StdOut.println("Percolation plate size is " + n*n + 
                      " ," + "simulate times is " + N);
       StdOut.println("mean = " + stats.mean() );
       StdOut.println("stddev = " + stats.stddev());
       StdOut.println("95% confidence interval = " + 
                      stats.confidenceLo() + " , " + stats.confidenceHi());
   }
}

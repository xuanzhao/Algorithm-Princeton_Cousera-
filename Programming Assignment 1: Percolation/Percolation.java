import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;


public class Percolation {
   
   private int gridLength;
   private int virtualTopIndex;
   private int virtualBottomIndex;
   private boolean[] siteStates;
   private WeightedQuickUnionUF isFull;
   private WeightedQuickUnionUF percolation;
       
   public Percolation(int n)  // create n-by-n grid, with all sites blocked
   {
       if (n < 1) {
           throw new IllegalArgumentException();
       }
       
       gridLength = n;
       int gridSize = n*n + 2;
       siteStates = new boolean[gridSize];
       
       isFull = new WeightedQuickUnionUF(gridSize);
       percolation = new WeightedQuickUnionUF(gridSize);
       
       virtualTopIndex = 0;
       virtualBottomIndex = gridSize-1;
       siteStates[virtualTopIndex] = true;
       siteStates[virtualBottomIndex] = true;
       
       for (int j = 1; j <= gridLength; j++)
       {
           int i = 1;
           int siteIndex = getSiteIndex(i, j);
           isFull.union(virtualTopIndex, siteIndex);
           percolation.union(virtualTopIndex, siteIndex);
           
           i = gridLength;
           siteIndex = getSiteIndex(i, j);
           isFull.union(virtualBottomIndex, siteIndex);
           percolation.union(virtualBottomIndex, siteIndex);         
       }  
   }
   
   // open site (row i, column j) if it is not open already
   public void open(int i, int j) 
   {
       checkBound(i, j);
       int siteIndex = getSiteIndex(i, j);
       
       if (!siteStates[siteIndex])
       {
           siteStates[siteIndex] = true;
           
           if (i > 1 && isOpen(i-1, j))
           {
               int topIndex = getSiteIndex(i-1, j);
               isFull.union(siteIndex, topIndex);
               percolation.union(siteIndex, topIndex);
           }
           
           if (i < gridLength && isOpen(i+1, j))
           {
               int downIndex = getSiteIndex(i+1, j);
               isFull.union(siteIndex, downIndex);
               percolation.union(siteIndex, downIndex);
           }
           
           if (j > 1 && isOpen(i, j-1))
           {
               int leftIndex = getSiteIndex(i, j-1);
               isFull.union(siteIndex, leftIndex);
               percolation.union(siteIndex, leftIndex);
           }
           
           if (j < gridLength && isOpen(i, j+1))
           {
               int rightIndex = getSiteIndex(i, j+1);
               isFull.union(siteIndex, rightIndex);
               percolation.union(siteIndex, rightIndex);
           }
       }
   }
    
   public boolean isOpen(int i, int j)     // is site (row i, column j) open?
   {   
       checkBound(i, j);
       int siteIndex = getSiteIndex(i, j);
       return siteStates[siteIndex];
   }
   
   public boolean isFull(int i, int j)     // is site (row i, column j) full?
   {
       checkBound(i, j);
       int siteIndex = getSiteIndex(i, j);
       boolean isfull = siteStates[siteIndex] && 
               isFull.connected(virtualTopIndex, siteIndex);
       return isfull;
   }
   
   public boolean percolates()             // does the system percolate?
   {
       if (gridLength > 1)
       {
           return percolation.connected(virtualTopIndex, virtualBottomIndex);
       } else {
           return siteStates[getSiteIndex(1, 1)]; }
   }
   
   private void checkBound(int i, int j)
   {
       if (i < 1 || i > gridLength)
       {
           throw new IndexOutOfBoundsException("row index i out of range");
       }
       if (j < 1 || j > gridLength)
       {
           throw new IndexOutOfBoundsException("column index j out of range");
       }
   }
   
   private int getSiteIndex(int i, int j)
   {
       checkBound(i, j);
       int x = j;
       int y = i;
       return (y-1) * gridLength + x; 
           
   }
   
   public static void main(String[] args)  // test client (optional)
   {
        Percolation percolation = new Percolation(1);
        StdOut.println(percolation.percolates());
        percolation.open(1, 1);
        StdOut.println(percolation.percolates());
        Percolation percolation2 = new Percolation(2);
        StdOut.println(percolation2.percolates());
        percolation2.open(1, 1);
        StdOut.println(percolation2.percolates());
        percolation2.open(2, 1);
        StdOut.println(percolation2.percolates());
   }


}
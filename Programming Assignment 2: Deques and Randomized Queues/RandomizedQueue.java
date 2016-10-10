import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item> {
   private Item[] array;
   private int n;
   
   public RandomizedQueue() {  // construct an empty randomized queue
       array = (Item[]) new Object[2];
       n = 0;
   }
   public boolean isEmpty() {  // is the queue empty?
       return n == 0;
   }
   public int size() {         // return the number of items on the queue
       return n;
   }
   
   public void enqueue(Item item) {   // add the item
       if (item == null) throw new NullPointerException();
       if (n == array.length) resize(array.length * 2);
       array[n++] = item;
   }
   
   public Item dequeue() {  // remove and return a random item
       if (isEmpty()) throw new NoSuchElementException();
       
       int randomIdx = StdRandom.uniform(n);
       Item item = array[randomIdx];
       array[randomIdx] = array[n - 1];
       array[n-1] = null;
       n--;
       if (n > 0 && n == array.length / 4) resize(array.length / 2);
       return item;
   }
   
   public Item sample() {   // return (but do not remove) a random item
       if (isEmpty()) throw new NoSuchElementException();
       int randomIdx = StdRandom.uniform(n);
       Item item = array[randomIdx];
       return item;
   }
   
   private String pToString() {
       StringBuilder s = new StringBuilder();
       for (Item item: array)
           s.append(item + " ");
       return s.toString();
   }
   
   private void resize(int capacity) {
       assert capacity >= n;
       Item[] temp = (Item[]) new Object[capacity];
       for (int i = 0; i < n; i++) {
           temp[i] = array[i];
       }
       array = temp;
   }
   
   // return an independent iterator over items in random order
   public Iterator<Item> iterator() {        
       return new RandQueueIter();
   }
   
   private class RandQueueIter implements Iterator<Item> {
       private int i;
       
       public RandQueueIter() {
           this.i = n -1;
       }
       
       public boolean hasNext() {
           return this.i >= 0;
       }
       
       public void remove() {
           throw new UnsupportedOperationException();
       }
       
       public Item next() {
           if (!hasNext()) throw new NoSuchElementException();
           int randomIdx = StdRandom.uniform(i);
           Item item = array[randomIdx];
           array[randomIdx] = array[i-1];
           array[i-1] = null;
           i--;
           return item;
       }  
   }
   
   public static void main(String[] args) {  // unit testing
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        queue.enqueue(1);
        StdOut.println(queue.pToString());
        StdOut.println(queue.pToString());
        queue.enqueue(2);
        StdOut.println(queue.pToString());
        StdOut.println(queue.pToString());
        queue.enqueue(3);
        StdOut.println(queue.pToString());
        StdOut.println(queue.pToString());
        queue.enqueue(4);
        StdOut.println(queue.pToString());
        StdOut.println(queue.pToString());
        queue.enqueue(5);
        StdOut.println(queue.pToString());
        StdOut.println(queue.pToString());
        queue.enqueue(6);
        StdOut.println(queue.pToString());
        StdOut.println(queue.pToString());
        queue.enqueue(7);
        StdOut.println(queue.pToString());
        StdOut.println(queue.pToString());
        queue.enqueue(8);
        StdOut.println(queue.pToString());
        StdOut.println(queue.pToString());
        queue.enqueue(9);
        StdOut.println(queue.pToString());
        StdOut.println(queue.pToString());
        queue.enqueue(10);
        StdOut.println(queue.pToString());
        StdOut.println(queue.pToString());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.pToString());
        StdOut.println(queue.pToString());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.pToString());
        StdOut.println(queue.pToString());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.pToString());
        StdOut.println(queue.pToString());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.pToString());
        StdOut.println(queue.pToString());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.pToString());
        StdOut.println(queue.pToString());
   }
}
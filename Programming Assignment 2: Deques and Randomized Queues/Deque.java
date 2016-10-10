import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;
import junit.framework.Assert;

public class Deque<Item> implements Iterable<Item> {
   private int n;
   private Node first;
   private Node last;
   
   private class Node {
       private Item item;
       private Node prev;
       private Node next;
   }
   
   public Deque() {  // construct an empty deque
       first = last = null;
       n = 0;
       assert check();
   }
   
   public boolean isEmpty() { // is the deque empty?
       return n == 0;
   }
   
   public int size() {  // return the number of items on the deque
       return n;
   }
   
   public void addFirst(Item item) { // add the item to the front
        if (item == null) { throw new NullPointerException(); }
        Node newFirst = new Node();
        newFirst.item = item;
        newFirst.prev = null;
        newFirst.next = first;

        if (isEmpty()) {
            last = newFirst;
        }
        else {
            first.prev = newFirst;
        }

        first = newFirst;
        n++;
   }
   
   public void addLast(Item item) {  // add the item to the end
        if (item == null) { throw new NullPointerException(); }
        Node newLast = new Node();
        newLast.item = item;
        newLast.prev = last;
        newLast.next = null;

        if (isEmpty()) {
            first = newLast;
        }
        else {
            last.next = newLast;
        }

        last = newLast;
        n++;
   }
   
   public Item removeFirst() {  // remove and return the item from the front
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");
        Item item = first.item;
        if (n == 1) first = last = null;
        else{
            first = first.next;
            first.prev = null;
        }
        n--;
        return item;
   }
   
   // remove and return the item from the end
   public Item removeLast() {                 
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");
        Item item = last.item;
        if (n == 1) first = last = null;
        else{
            last = last.prev;
            last.next = null;
        }
        n--;
        return item;
   }
   
   private String pToString() {
       StringBuilder s = new StringBuilder();
       for (Item item : this)
           s.append(item + " ");
       return s.toString();
   }
   
   // return an iterator over items in order from front to end
   public Iterator<Item> iterator() {         
       return new DequeIterator();
   }
   
   private class DequeIterator implements Iterator<Item> {
       private Node current;
       
       public DequeIterator(){
           this.current = first;
       }
       
       public boolean hasNext() { return current != null; }

       public void remove()     { throw new UnsupportedOperationException(); }
       
       public Item next() {
           if (!this.hasNext()) throw new NoSuchElementException();
           Item item = current.item;
           current = current.next;
           return item;
       }
   }
   
   private boolean check() {
       
       if (n < 0) return false;
       if (n == 0) {
           if (first != null && last != null) return false;
       }
       else if (n == 1) {
           if (first == null && last == null) return false;
           if (first.next != null && last.prev != null) return false;
       }
       else {
           if (first == null || last == null) return false;
           if (first.next == null || last.prev == null) return false;
       }
       
       int numberOfNodes = 0;
       for (Node x = first; x != null && numberOfNodes <= n; x = x.next) {
           numberOfNodes++;
       }
       if (numberOfNodes != n) return false;
       
       return true;
   }
   
   public static void main(String[] args) {  // unit testing
       Deque<Integer> deq = new Deque<Integer>();
       deq.addFirst(0);
       deq.addFirst(1);
       deq.addLast(2);
       assert deq.size() == 2;
       StdOut.println("deq :" + deq.pToString());
       StdOut.println("deq :" + deq.size());
       deq.addFirst(3);
       deq.addLast(5);
       deq.addFirst(6);

       deq.removeLast();
       StdOut.println("deq :" + deq.pToString());
       StdOut.println("deq :" + deq.size());
       deq.removeLast();
       deq.removeFirst();
       
       StdOut.println("deq :" + deq.pToString());
       StdOut.println("deq :" + deq.size());
       
   }
}
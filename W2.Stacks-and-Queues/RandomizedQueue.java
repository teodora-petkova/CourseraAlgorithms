import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int n;
    
    public RandomizedQueue() {
        a = (Item[]) new Object[2];
        n = 0;
    }
    
    private RandomizedQueue(Item[] aa, int an) {
        a = (Item[]) new Object[an];
        for (int t = 0; t < an; t++) a[t] = aa[t];
        n = an;
    }
        
    public boolean isEmpty() {
        return n == 0;
    }
    
    public int size() {
        return n;
    }
    
    private Item[] resize(int capacity) {
        assert capacity >= n;
        
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        return temp;
    }      
    
    private void swap(Item[] array, int i, int j) {
        Item temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    public void enqueue(Item item) {
        if (item == null) throw new java.lang.IllegalArgumentException();
        if (n == a.length) a = resize(2*a.length);  
        a[n++] = item;
    }
    
    public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        
        int indexToRemove = StdRandom.uniform(n);
            
        swap(a, n-1, indexToRemove);
          
        Item item = a[n-1];
        a[n-1] = null; // to avoid loitering
        n--;
            
        // shrink size of array if necessary
        if (n > 0 && n == a.length/4) resize(a.length/2);
            
        return item;
    }
    
    public Item sample() {
        if (isEmpty()) throw new java.util.NoSuchElementException();   
        
        return a[StdRandom.uniform(n)];
    }
    
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator(a, n);
    }   
    
    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i;
        private RandomizedQueue<Item> queue;

        public RandomizedQueueIterator(Item[] a, int n) {
            this.i = n-1;
            this.queue = new RandomizedQueue<Item>(a, n);
        }

        public boolean hasNext() {
            return i >= 0 && i < queue.n;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            
            i--;
            
            return queue.dequeue();
        }
    }
    
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);        
        queue.enqueue(5);
        queue.enqueue(6);        
        
        for (int d1 : queue) {
            for (int d2 : queue) {
                System.out.println(d1 + "    " + d2); 
            }               
        }
    }
}
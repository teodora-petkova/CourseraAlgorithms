import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    
    private Node<Item> first;
    private Node<Item> last;
    private int size;
    
    private class Node<Item> {
        private Item data;
        private Node<Item> next; 
        private Node<Item> previous;
    }

    public Deque() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }
   
    public boolean isEmpty() {
        return this.first == null || this.last == null;
    }
   
    public int size() {
        return this.size;
    }
   
    public void addFirst(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException();
        }
       
        // save first
        Node<Item> oldFirst = this.first;
        
        // create new first
        this.first = new Node<Item>();
        this.first.data = item; 
        this.first.next = oldFirst;
        this.first.previous = null;
        
        if (this.isEmpty()) this.last = this.first;
        else oldFirst.previous = this.first;
        
        this.size = this.size + 1;
    }
   
    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException();
        }      
 
        // save last
        Node<Item> oldLast = this.last;
        
        // new last
        this.last = new Node<Item>();
        this.last.data = item; 
        this.last.next = null;
        this.last.previous = oldLast;
        
        if (this.isEmpty()) this.first = this.last;
        else oldLast.next = this.last;
        
        this.size = this.size + 1;     
    }
   
    public Item removeFirst() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        
        this.size = this.size - 1;
        
        Item oldFirstData = this.first.data;
        
        if (this.first == this.last) {
            this.first = null;
            this.last = null;
        }
        else {
            this.first = this.first.next;
            this.first.previous = null;
        }
        
        return oldFirstData;
    }
   
    public Item removeLast()                 // remove and return the item from the end
    {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
       
        this.size = this.size - 1;
        
        Item oldLastData = this.last.data;
        
        if (this.first == this.last) {
            this.first = null;
            this.last = null;
        }
        else {
            this.last = this.last.previous;
            this.last.next = null;
        }
        
        return oldLastData;       
    }
   
    public Iterator<Item> iterator() {
        return new DequeIterator(this.first);
    }
    
    private class DequeIterator implements Iterator<Item> {    
        private Node<Item> current;
        
        public DequeIterator(Node<Item> node) {
            this.current = node;
        }
            
        public boolean hasNext() {
            return this.current != null;
        }

        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            Item oldCurrentData = this.current.data;
            this.current = this.current.next;
            return oldCurrentData;
        }
 
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addFirst(5);
        deque.addFirst(6);
        
        for (int d1 : deque) {
            for (int d2 : deque) {
                System.out.println(d1 + "    " + d2);
            }
        }
    }
}
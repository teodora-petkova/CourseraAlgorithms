import junit.framework.TestCase;
public class DequeTest extends TestCase {

    public void testWhenTheDequeIsEmptyReturnsTrue() {
        Deque<String> deque = new Deque<String>();
    
        assertEquals("", true, deque.isEmpty());
    }

    public void testWhenAddFirstThreeItemsRemoveSameThreeItemsBackwards() {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");
    
        assertEquals("", false, deque.isEmpty());
    
        assertEquals("", deque.removeFirst(), "c");
        assertEquals("", deque.removeFirst(), "b");
        assertEquals("", deque.removeFirst(), "a");
        assertEquals("", true, deque.isEmpty());
    }

    public void testWhenAddLastThreeItemsRemoveSameThreeItemsBackwards() {
        Deque<String> deque = new Deque<String>();
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");
    
        assertEquals("", false, deque.isEmpty());
    
        assertEquals("", deque.removeLast(), "c");
        assertEquals("", deque.removeLast(), "b");
        assertEquals("", deque.removeLast(), "a");
        assertEquals("", true, deque.isEmpty());
    }
    
    public void testIterators()
    {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addFirst(5);
        deque.addFirst(6);
        
        int i = 6;
        for (int d1 : deque) {
            assertEquals("", i--, d1);
            int j = 6;
            for (int d2 : deque) {
                assertEquals("", j--, d2);
            }               
        }
    }
}

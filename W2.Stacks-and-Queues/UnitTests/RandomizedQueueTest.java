import junit.framework.TestCase;
public class RandomizedQueueTest extends TestCase {
    
    
    public void testWhenTheQueueIsEmptyReturnsTrue() {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        assertEquals("", true, q.isEmpty());
    }

    public void testWhenAddFirstQueueIsNotEmpty() {
        RandomizedQueue<String> queque = new RandomizedQueue<String>();
        queque.enqueue("a");
        assertEquals("", false, queque.isEmpty());
    }

    public void testWhenAddFirstThreeItemsRemoveSameThreeItemsBackwards() {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
    
        assertEquals("", false, queue.isEmpty());
    
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        assertEquals("", true, queue.isEmpty());
    }
}

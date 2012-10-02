
import data_structures.LevelQueue;
import data_structures.Node;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Broileri
 */
public class LevelQueueTest {
    
    private LevelQueue jono;
    
    public LevelQueueTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        jono = new LevelQueue();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void empty_queue_is_empty() {
        
        assertTrue(jono.isEmpty());
    }
    
    @Test
    public void enqueue_and_dequeue_work_correctly() {
                
        jono.enqueue(new Node(3));
        jono.enqueue(new Node(6));
        jono.enqueue(new Node(-67));
        jono.enqueue(new Node(100000));
        jono.enqueue(new Node(33));
        jono.enqueue(new Node(68));
        jono.enqueue(new Node(3));
        jono.enqueue(new Node(90));
        
        Node a, b, c, d, e, f, g, h;
        
        a = jono.dequeue();
        b = jono.dequeue();
        c = jono.dequeue();
        d = jono.dequeue();
        e = jono.dequeue();
        f = jono.dequeue();
        g = jono.dequeue();
        h = jono.dequeue();
        
        assertEquals(3, a.getKey());
        assertEquals(6, b.getKey());
        assertEquals(-67, c.getKey());
        assertEquals(100000, d.getKey());
        assertEquals(33, e.getKey());
        assertEquals(68, f.getKey());
        assertEquals(3, g.getKey());
        assertEquals(90, h.getKey());        
    }
    
    @Test
    public void unempty_queue_is_not_empty() {
        
        boolean truee = jono.isEmpty();
        jono.enqueue(new Node(7));
        
        assertTrue(truee);
        assertFalse(jono.isEmpty());
    }
    
    @Test
    public void does_not_crash_when_dequeueing_empty_queue() {
        
        LevelQueue q = new LevelQueue();
        Node x = q.dequeue();
        assertNull(x);
    }
}


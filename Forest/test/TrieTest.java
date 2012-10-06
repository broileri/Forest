
import org.junit.*;
import static org.junit.Assert.*;
import trees.Trie;

/**
 *
 * @author Broileri
 */
public class TrieTest {
    
    private Trie tree;
    
    public TrieTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        this.tree = new Trie();
        tree.insert(334);           //               -root+
        tree.insert(3);             //  _____________/    \________________________
        tree.insert(34);            //   1   2.   3.    6      0.  2       3.    7.
        tree.insert(0);             //   0       3 4.   1          3     3   4.     
        tree.insert(-334);          //   0       4.     2.         9   2. 4.
        tree.insert(-10000);        //   0                         0.
        tree.insert(-612);          //   0.                        9
        tree.insert(23909090);      //                             0
        tree.insert(2390);          //                             9
        tree.insert(007);           //                             0.
        tree.insert(332);
        tree.insert(-2);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void doSearchNInsertWork() {
        
        assertTrue(tree.trieSearch(334));
        assertTrue(tree.trieSearch(3));
        assertTrue(tree.trieSearch(34));
        assertTrue(tree.trieSearch(0));
        assertTrue(tree.trieSearch(-334));
        assertTrue(tree.trieSearch(-10000));
        assertTrue(tree.trieSearch(-612));
        assertTrue(tree.trieSearch(23909090));
        assertTrue(tree.trieSearch(2390));
        assertTrue(tree.trieSearch(7));
        assertTrue(tree.trieSearch(332));
        assertTrue(tree.trieSearch(-2));
        
        assertFalse(tree.trieSearch(666));
        assertFalse(tree.trieSearch(33));
        assertFalse(tree.trieSearch(-100));
        assertFalse(tree.trieSearch(13));
    }
    
    @Test
    public void doesDeleteWork() {
        
        tree.delete(9); // ei puussa
        tree.delete(-612);
        tree.delete(23909090);        
        tree.delete(334);        
        tree.delete(34);
        tree.delete(3);
        tree.delete(-2);
        System.out.println(tree.trieSearch(-2));
        
        assertFalse(tree.trieSearch(9));
        assertFalse(tree.trieSearch(23909090));
        assertFalse(tree.trieSearch(-612));
        assertFalse(tree.trieSearch(612)); // ei ole ollutkaan puussa
        assertFalse(tree.trieSearch(3));
        assertFalse(tree.trieSearch(334));
        assertFalse(tree.trieSearch(34));
        assertFalse(tree.trieSearch(239090));
        assertFalse(tree.trieSearch(-2));
        assertTrue(tree.trieSearch(2390));
    }
}

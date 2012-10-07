
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import trees.Splay;

/**
 *
 * @author Broileri
 */
public class SplayTest {

    private Splay tree;
    private PrintStream oldOut;
    private ByteArrayOutputStream newOut;

    public SplayTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        tree = new Splay();
        tree.splayInsert(18);   //              188
        tree.splayInsert(25);   //             /
        tree.splayInsert(12);   //           -78
        tree.splayInsert(-66);  //              \
        tree.splayInsert(-7);   //              162
        tree.splayInsert(162);  //              /
        tree.splayInsert(0);    //             12
        tree.splayInsert(9);    //            /  \
        tree.splayInsert(12);   //           0    25
        tree.splayInsert(-78);  //          / \   /
        tree.splayInsert(188);  //       -66   9 18
                                //          \
    }                           //           -7

    @After
    public void tearDown() {
    }

    /**
     * Apumetodi, joka uudelleenohjaa System.outin uuteen PrintStreamiin.
     */
    public void redirectSystemOut() {

        newOut = new ByteArrayOutputStream();

        // Vanha System.out talteen
        oldOut = System.out;

        // Uudelleenohjataan System.out uuteen PrintStreamiin
        System.setOut(new PrintStream(newOut));
    }

    /**
     * Apumetodi, joka asettaa System.outin takaisin vanhaan System.outiin.
     */
    public void setSystemOutBack() {

        // Vanha System.out takaisin
        System.setOut(oldOut);
    }

    @Test
    public void doInsertAndSearchWork() {

        Splay test = this.tree;

        assertTrue(test.search(18));
        assertTrue(test.search(0));
        assertTrue(test.search(-66));
        assertTrue(test.search(-78));
        assertTrue(test.search(12));

        assertFalse(test.search(100));
        assertFalse(test.search(-10));
        assertFalse(test.search(2));
    }

    @Test
    public void doesDeleteWork() {

        Splay test = this.tree;

        test.delete(188);
        test.delete(12);
        test.delete(-7);
        test.delete(25);
        test.delete(-33); // ei puussa

        assertFalse(test.search(188));
        assertFalse(test.search(12));
        assertFalse(test.search(-7));
        assertFalse(test.search(25));
        assertFalse(test.search(-33));

        assertTrue(test.search(-78));
        assertTrue(test.search(18));
        assertTrue(test.search(0));
        assertTrue(test.search(9));
        assertTrue(test.search(-66));
        assertTrue(test.search(162));
    }

    @Test
    public void doesMaxWork() {
        Splay test = this.tree;
        assertEquals(188, test.getMaxKey());
        test.search(0);
        assertEquals(188, test.getMaxKey());
    }

    @Test
    public void doesMinWork() {
        Splay test = this.tree;
        assertEquals(-78, test.getMinKey());
        test.search(162);
        assertEquals(-78, test.getMinKey());
    }

    @Test
    public void doesSplayingWorkProperly() {

        Splay test = this.tree;
        assertEquals(188, tree.getRoot().getKey());
        test.delete(0);
        assertEquals(12, tree.getRoot().getKey());
        test.search(9);
        assertEquals(9, tree.getRoot().getKey());
    }

    @Test
    public void testDelCaseNoKids() {

        // Puu on pelkkä juuri
        Splay justRoot = new Splay();
        justRoot.splayInsert(45);
        justRoot.delete(45);
        assertFalse(justRoot.search(45));

        // Puun korkeus on 1
        Splay lowTree = new Splay();
        lowTree.splayInsert(6);
        lowTree.splayInsert(9);
        lowTree.splayInsert(7);

        Splay testLeft = lowTree, testRight = lowTree;

        testLeft.delete(6);
        assertFalse(testLeft.search(6));

        testRight.delete(9);
        assertFalse(testRight.search(9));
    }

    @Test
    public void testDelCaseOneKid() {

        Splay test = new Splay();
        test.splayInsert(6);
        test.splayInsert(9);
        test.splayInsert(7);
        test.splayInsert(8);
        test.delete(7);
        assertFalse(test.search(7));

        test = new Splay();
        test.splayInsert(5);
        test.splayInsert(9);
        test.splayInsert(7);
        test.splayInsert(6);
        test.delete(7);
        assertFalse(test.search(7));
    }

    @Test
    public void test_Del_Case_Two_Kids_When_New_Root_Has_Child() {

        Splay test = new Splay();

        test.splayInsert(56);
        test.splayInsert(67);
        test.splayInsert(23);
        test.splayInsert(60);
        test.splayInsert(54);
        test.splayInsert(55);
        test.splayInsert(58);
        test.splayInsert(53);
        test.splayInsert(59);
        test.splayInsert(52);
        test.splayInsert(57);
        test.splayInsert(25);

        test.delete(25);
        assertFalse(test.search(25));
    }
    
        @Test
    public void treeLooksRightAfterDeleting() {

        Splay test = tree;

        test.delete(162);
        test.delete(-3);
        test.delete(0);
        test.delete(-7);
        test.delete(18);
        test.delete(20);
        test.delete(188);

        String lvl = "25 12 9 -78 -66 ";
        redirectSystemOut();
        test.printLevelOrder(test.getRoot());
        setSystemOutBack();
        String testResult = new String(newOut.toByteArray());
        assertEquals(lvl, testResult);
    }
    
    @Test
    public void treeLooksRightAfterInserting() {
        
        String lvl = "188 -78 162 12 0 25 -66 9 18 -7 ";
        
        redirectSystemOut();
        this.tree.printLevelOrder(this.tree.getRoot());
        setSystemOutBack();
        String testResult = new String(newOut.toByteArray());
        assertEquals(lvl, testResult);
    }  
}

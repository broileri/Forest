
import data_structures.Node;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.*;
import org.junit.*;
import trees.AVL;

/**
 *
 * @author Broileri
 */
public class AVLTest {

    private AVL avltree;
    private PrintStream oldOut;
    private ByteArrayOutputStream newOut;

    public AVLTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
                                      // avltree-puun pitäisi näyttää lisäysten jälkeen tältä:
        avltree = new AVL();          //
        avltree.AVLinsert(10);        //             _ 14_
        avltree.AVLinsert(4);         //            /     \
        avltree.AVLinsert(2);         //           /       \     
        avltree.AVLinsert(3);         //          /         \
        avltree.AVLinsert(16);        //        4            22
        avltree.AVLinsert(14);        //      /   \         /   \
        avltree.AVLinsert(18);        //     2     11      16    1000
        avltree.AVLinsert(11);        //    / \    / \    /  \    /  \
        avltree.AVLinsert(15);        //  -4   3  9  13  15  18 234  1002
        avltree.AVLinsert(1002);      //         / \         /  /        \
        avltree.AVLinsert(13);        //        8  10       17 233       1003
        avltree.AVLinsert(22);
        avltree.AVLinsert(234);
        avltree.AVLinsert(-4);
        avltree.AVLinsert(17);
        avltree.AVLinsert(1000);
        avltree.AVLinsert(9);
        avltree.AVLinsert(8);
        avltree.AVLinsert(1003);
        avltree.AVLinsert(233);
    }

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
    public void doesGetRootWork() {

        AVL tree = new AVL();
        Node a, b;
        a = tree.getRoot(); // tyhjä juuri
        tree.AVLinsert(99);
        b = tree.getRoot(); // juuri, jonka avain on 99

        assertEquals(null, a);
        assertEquals(99, b.getKey());
    }

    @Test
    public void doesInsertWork() {

        Node root, nullNode;
        int a, b, c, d, e, f;
        root = avltree.getRoot();
        a = root.getRight().getRight().getRight().getKey(); // a:n pitäisi nyt olla 1002
        b = root.getLeft().getKey(); // b:n pitäisi nyt olla 4
        c = root.getLeft().getLeft().getRight().getKey(); // c:n pitäisi nyt olla 3
        d = root.getRight().getLeft().getRight().getKey(); // d:n pitäisi nyt olla 18
        e = root.getRight().getRight().getLeft().getKey(); // e:n pitäisi nyt olla 234
        f = root.getRight().getLeft().getRight().getLeft().getKey(); // f:n pitäisi nyt olla 17       
        nullNode = root.getLeft().getLeft().getLeft().getLeft(); // nullNoden pitäisi nyt olla null

        assertEquals(1002, a);
        assertEquals(4, b);
        assertEquals(3, c);
        assertEquals(18, d);
        assertEquals(234, e);
        assertEquals(17, f);
        assertEquals(null, nullNode);
    }

    @Test
    public void doesSearchWork() {

        boolean a, b, c, d, e, f;
        a = avltree.search(4);
        b = avltree.search(100);
        c = avltree.search(18);
        d = avltree.search(13);
        e = avltree.search(-4);
        f = avltree.search(66);

        assertTrue(a);
        assertFalse(b);
        assertTrue(c);
        assertTrue(d);
        assertTrue(e);
        assertFalse(f);
    }

    @Test
    public void doesGetMaxWork() {

        int max = avltree.getMaxKey();
        assertEquals(1003, max);
    }

    @Test
    public void doesGetMinWork() {

        int min = avltree.getMinKey();
        assertEquals(-4, min);
    }

    @Test
    public void doesDeleteWork_KeyDoesNotExist() {

        AVL tree = avltree;

        // Poistettavaa avainta ei ole puussa
        tree.AVLdelete(59);
        tree.AVLdelete(1000000);

        assertFalse(tree.search(59));
        assertFalse(tree.search(1000000));
    }

    @Test
    public void doesDeleteWorkWhenDeletingRoot() {

        AVL justRoot, twoChildren, leftChild, rightChild;

        // Pelkkä juuri
        justRoot = new AVL(); // Pelkkä juuri
        justRoot.AVLinsert(100);
        justRoot.AVLdelete(100);

        // Juuri, jolla kaksi lasta
        twoChildren = new AVL(); // Juurella kaksi lasta
        twoChildren.AVLinsert(6);
        twoChildren.AVLinsert(7);
        twoChildren.AVLinsert(5);
        twoChildren.AVLdelete(6);

        // Juuri, jolla vasen lapsi
        leftChild = new AVL(); // Juurella vasen lapsi
        leftChild.AVLinsert(30);
        leftChild.AVLinsert(20);
        leftChild.AVLdelete(30);

        // Juuri, jolla oikea lapsi
        rightChild = new AVL(); // Juurella oikea lapsi
        rightChild.AVLinsert(55);
        rightChild.AVLinsert(14000);
        rightChild.AVLdelete(55);

        assertFalse(justRoot.search(100));
        assertFalse(twoChildren.search(6));
        assertTrue(twoChildren.search(7));
        assertTrue(twoChildren.search(5));
        assertFalse(leftChild.search(30));
        assertTrue(leftChild.search(20));
        assertFalse(rightChild.search(55));
        assertTrue(rightChild.search(14000));
    }

    @Test
    public void doesDeleteWork_2Children() {

        AVL tree = avltree;

        // Kaksilapsiset tapaukset
        tree.AVLdelete(16);
        tree.AVLdelete(1000);

        assertFalse(tree.search(16));
        assertFalse(tree.search(1000));
    }

    @Test
    public void doesDeleteWork_1Child() {

        AVL tree = avltree;

        // Yksilapsiset tapaukset
        tree.AVLdelete(18);
        tree.AVLdelete(234);

        assertFalse(tree.search(18));
        assertFalse(tree.search(234));
    }

    @Test
    public void doesDeleteWork_NoChildren() {

        AVL tree = avltree;

        // Lapsettomat tapaukset
        tree.AVLdelete(1003);
        tree.AVLdelete(-4);

        assertFalse(tree.search(1003));
        assertFalse(tree.search(-4));
    }

    @Test
    public void treeLooksRightAfterDeleting() {

        AVL tree = avltree;

        tree.AVLdelete(22);
        tree.AVLdelete(1000);
        tree.AVLdelete(1002);
        tree.AVLdelete(1003);
        tree.AVLdelete(4);
        tree.AVLdelete(3);
        tree.AVLdelete(2);
        tree.AVLdelete(-4);

        String lvl = "14 9 18 8 11 16 233 10 13 15 17 234 ";
        redirectSystemOut();
        avltree.printLevelOrder(avltree.getRoot());
        setSystemOutBack();
        String testResult = new String(newOut.toByteArray());
        assertEquals(lvl, testResult);
    }
    
    @Test
    public void treeLooksRightAfterInserting() {
        
        String lvl = "14 4 22 2 11 16 1000 -4"
                + " 3 9 13 15 18 234 1002 8 10 17 233 1003 ";
        
        redirectSystemOut();
        avltree.printLevelOrder(avltree.getRoot());
        setSystemOutBack();
        String testResult = new String(newOut.toByteArray());
        assertEquals(lvl, testResult);
    }   
}
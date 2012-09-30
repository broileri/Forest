
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;
import org.junit.*;
import trees.Binary_Search;

/**
 *
 * @author Broileri
 */
public class WalksTest {

    private Binary_Search bintree;
    private PrintStream oldOut;
    private ByteArrayOutputStream newOut;

    public WalksTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {

        bintree = new Binary_Search();
        bintree.insert(10);
        bintree.insert(4);         // bintree-puu lisäysten jälkeen:        
        bintree.insert(2);         //             10       
        bintree.insert(3);         //            /   \
        bintree.insert(16);        //           4     16
        bintree.insert(14);        //          /     /   \
        bintree.insert(18);        //         2     14    18
        bintree.insert(11);        //        / \    / \   /  \
        bintree.insert(15);        //       -4  3  11 15  17 1002
        bintree.insert(1002);      //                \       /
        bintree.insert(13);        //                 13    22
        bintree.insert(22);        //                         \
        bintree.insert(234);       //                          234
        bintree.insert(-4);        //                            \
        bintree.insert(17);        //                           1000 
        bintree.insert(1000);
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
    public void doesLevelOrderWork() {

        String lvl = "10 4 16 2 14 18 -4 3 11 15 17 1002 13 22 234 1000 ";
        redirectSystemOut();
        bintree.printLevelOrder(bintree.getRoot());
        setSystemOutBack();
        String testResult = new String(newOut.toByteArray());

        assertEquals(lvl, testResult);
    }

    @Test
    public void doesInOrderWork() {

        String in = "-4 2 3 4 10 11 13 14 15 16 17 18 22 234 1000 1002 ";
        redirectSystemOut();
        bintree.printInOrder(bintree.getRoot());
        setSystemOutBack();
        String testResult = new String(newOut.toByteArray());

        assertEquals(in, testResult);
    }

    @Test
    public void doesPreOrderWork() {

        String pre = "10 4 2 -4 3 16 14 11 13 15 18 17 1002 22 234 1000 ";
        redirectSystemOut();
        bintree.printPreOrder(bintree.getRoot());
        setSystemOutBack();
        String testResult = new String(newOut.toByteArray());

        assertEquals(pre, testResult);
    }

    @Test
    public void doesPostOrderWork() {

        String post = "-4 3 2 4 13 11 15 14 17 1000 234 22 1002 18 16 10 ";
        redirectSystemOut();
        bintree.printPostOrder(bintree.getRoot());
        setSystemOutBack();
        String testResult = new String(newOut.toByteArray());

        assertEquals(post, testResult);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import data_structures.Node;
import org.junit.*;
import static org.junit.Assert.*;
import trees.Binary_Search;

/**
 *
 * @author Broileri
 */
public class BinaryTest {

    public BinaryTest() {
    }
    private Binary_Search bintree;

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
        bintree.insert(4);         // bintree-puun pitäisi näyttää lisäysten jälkeen tältä:        
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

    @Test
    public void doesGetRootWork() {

        Binary_Search tree = new Binary_Search();
        Node a, b;
        a = tree.getRoot(); // tyhjä juuri
        tree.insert(14);
        b = tree.getRoot(); // juuri, jonka avain on 14

        assertEquals(null, a);
        assertEquals(14, b.getKey());
    }

    @Test
    public void doesInsertWork() {

        Node root, nullNode;
        int a, b, c, d, e, f;
        root = bintree.getRoot();
        a = root.getRight().getRight().getRight().getLeft().getRight().getKey(); // g:n pitäisi nyt olla 234
        b = root.getKey(); // b:n pitäisi nyt olla 10
        c = root.getLeft().getLeft().getRight().getKey(); // c:n pitäisi nyt olla 3
        d = root.getRight().getLeft().getLeft().getRight().getKey(); // d:n pitäisi nyt olla 13
        e = root.getRight().getRight().getRight().getKey(); // e:n pitäisi nyt olla 1002
        f = root.getRight().getLeft().getRight().getKey(); // f:n pitäisi nyt olla 15       
        nullNode = root.getLeft().getLeft().getLeft().getLeft(); // nullNoden pitäisi nyt olla null

        assertEquals(234, a);
        assertEquals(10, b);
        assertEquals(3, c);
        assertEquals(13, d);
        assertEquals(1002, e);
        assertEquals(15, f);
        assertEquals(null, nullNode);
    }

    @Test
    public void doesSearchWork() {

        boolean a, b, c, d, e, f;
        a = bintree.search(4);
        b = bintree.search(100);
        c = bintree.search(18);
        d = bintree.search(13);
        e = bintree.search(-4);
        f = bintree.search(66);

        assertTrue(a);
        assertFalse(b);
        assertTrue(c);
        assertTrue(d);
        assertTrue(e);
        assertFalse(f);
    }

    @Test
    public void doesGetMaxWork() {

        int max = bintree.getMaxKey();
        assertEquals(1002, max);
    }

    @Test
    public void doesGetMinWork() {

        int min = bintree.getMinKey();
        assertEquals(-4, min);
    }

    @Test
    public void doesDeleteWork_2Children() {

        Binary_Search tree = bintree;

        // Kaksilapsiset tapaukset
        tree.delete(14);
        tree.delete(18);

        assertFalse(tree.search(14));
        assertFalse(tree.search(18));
    }

    @Test
    public void doesDeleteWork_1Child() {

        Binary_Search tree = bintree;

        // Yksilapsiset tapaukset
        tree.delete(4);
        tree.delete(234);

        assertFalse(tree.search(4));
        assertFalse(tree.search(234));
    }

    @Test
    public void doesDeleteWork_NoChildren() {

        Binary_Search tree = bintree;

        // Lapsettomat tapaukset
        tree.delete(13);
        tree.delete(-4);
        
        assertFalse(tree.search(13));
        assertFalse(tree.search(-4));
    }

    @Test
    public void doesDeleteWork_KeyDoesNotExist() {

        Binary_Search tree = bintree;

        // Poistettavaa avainta ei ole puussa
        tree.delete(59);
        tree.delete(1000000);

        assertFalse(tree.search(59));
        assertFalse(tree.search(1000000));
    }

    @Test
    public void doesDeleteWorkWhenDeletingRoot() {

        Binary_Search justRoot, twoChildren, leftChild, rightChild;

        // Pelkkä juuri
        justRoot = new Binary_Search(); // Pelkkä juuri
        justRoot.insert(100);
        justRoot.delete(100);

        // Juuri, jolla kaksi lasta
        twoChildren = new Binary_Search(); // Juurella kaksi lasta
        twoChildren.insert(6);
        twoChildren.insert(7);
        twoChildren.insert(5);
        twoChildren.delete(6);

        // Juuri, jolla vasen lapsi
        leftChild = new Binary_Search(); // Juurella vasen lapsi
        leftChild.insert(30);
        leftChild.insert(20);
        leftChild.delete(30);

        // Juuri, jolla oikea lapsi
        rightChild = new Binary_Search(); // Juurella oikea lapsi
        rightChild.insert(55);
        rightChild.insert(14000);
        rightChild.delete(55);

        assertFalse(justRoot.search(100));
        assertFalse(twoChildren.search(6));
        assertTrue(twoChildren.search(7));
        assertTrue(twoChildren.search(5));
        assertFalse(leftChild.search(30));
        assertTrue(leftChild.search(20));
        assertFalse(rightChild.search(55));
        assertTrue(rightChild.search(14000));
    }
}

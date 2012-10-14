package run;

import java.util.Random;
import trees.AVL;
import trees.Binary_Search;
import trees.Splay;
import trees.Trie;

/**
 * Puiden aikatestausta ja esitys läpikäynneistä binäärihakupuulle, AVL-puulle
 * ja splay-puulle.
 *
 * @author Broileri
 */
public class Demo {

    public static void demo() {


        /**
         * Alkuasetukset
         */
        Random r = new Random();
        long start, end;

        Binary_Search b = new Binary_Search();
        AVL a = new AVL();
        Splay s = new Splay();
        Trie t = new Trie();

        int[] small = new int[10000];
        int[] medium = new int[100000];
        int[] large = new int[1000000];
        int[] findTheseS = new int[10000];
        int[] findTheseM = new int[100000];
        int[] findTheseL = new int[1000000];
        int[] delTheseS = new int[10000];
        int[] delTheseM = new int[100000];
        int[] delTheseL = new int[1000000];


        for (int i = 0; i < 10000; i++) {
            small[i] = r.nextInt(20000000) - 10000000;
            findTheseS[i] = r.nextInt(20000000) - 10000000;
            delTheseS[i] = r.nextInt(20000000) - 10000000;
        }
        for (int i = 0; i < 100000; i++) {
            medium[i] = r.nextInt(20000000) - 10000000;
            findTheseM[i] = r.nextInt(20000000) - 10000000;
            delTheseM[i] = r.nextInt(20000000) - 10000000;
        }
        for (int i = 0; i < 1000000; i++) {
            large[i] = r.nextInt(20000000) - 10000000;
            findTheseL[i] = r.nextInt(20000000) - 10000000;
            delTheseL[i] = r.nextInt(20000000) - 10000000;
        }


        System.out.println("INSERTS");
        /**
         * BINÄÄRIHAKUPUU, insert
         */
        start = System.currentTimeMillis();

        for (int i = 0; i < 10000; i++) {
            b.insert(small[i]);
        }
        end = System.currentTimeMillis();
        System.out.println("Binary search, insert 10 000 times: " + (end - start) + " ms.");

        b = new Binary_Search();
        start = System.currentTimeMillis();

        for (int i = 0; i < 100000; i++) {
            b.insert(medium[i]);
        }
        end = System.currentTimeMillis();
        System.out.println("Binary search, insert 100 000 times: " + (end - start) + " ms.");

        b = new Binary_Search();
        start = System.currentTimeMillis();

        for (int i = 0; i < 1000000; i++) {
            b.insert(large[i]);
        }
        end = System.currentTimeMillis();
        System.out.println("Binary search, insert 1 000 000 times: " + (end - start) + " ms.");


        /**
         * AVL-PUU, insert
         */
        start = System.currentTimeMillis();

        for (int i = 0; i < 10000; i++) {
            a.AVLinsert(small[i]);
        }
        end = System.currentTimeMillis();
        System.out.println("AVL, insert 10 000 times: " + (end - start) + " ms.");

        a = new AVL();
        start = System.currentTimeMillis();

        for (int i = 0; i < 100000; i++) {
            a.AVLinsert(medium[i]);
        }
        end = System.currentTimeMillis();
        System.out.println("AVL, insert 100 000 times: " + (end - start) + " ms.");

        a = new AVL();
        start = System.currentTimeMillis();

        for (int i = 0; i < 1000000; i++) {
            a.AVLinsert(large[i]);
        }
        end = System.currentTimeMillis();
        System.out.println("AVL, insert 1 000 000 times: " + (end - start) + " ms.");


        /**
         * SPLAY-PUU, insert
         */
        start = System.currentTimeMillis();

        for (int i = 0; i < 10000; i++) {
            s.splayInsert(small[i]);
        }
        end = System.currentTimeMillis();
        System.out.println("Splay, insert 10 000 times: " + (end - start) + " ms.");

        s = new Splay();
        start = System.currentTimeMillis();

        for (int i = 0; i < 100000; i++) {
            s.splayInsert(medium[i]);
        }
        end = System.currentTimeMillis();
        System.out.println("Splay, insert 100 000 times: " + (end - start) + " ms.");

        s = new Splay();
        start = System.currentTimeMillis();

        for (int i = 0; i < 1000000; i++) {
            s.splayInsert(large[i]);
        }
        end = System.currentTimeMillis();
        System.out.println("Splay, insert 1 000 000 times: " + (end - start) + " ms.");


        /**
         * TRIE-PUU, insert
         */
        start = System.currentTimeMillis();

        for (int i = 0; i < 10000; i++) {
            t.insert(small[i]);
        }
        end = System.currentTimeMillis();
        System.out.println("Trie, insert 10 000 times: " + (end - start) + " ms.");

        t = new Trie();
        start = System.currentTimeMillis();

        for (int i = 0; i < 100000; i++) {
            t.insert(medium[i]);
        }
        end = System.currentTimeMillis();
        System.out.println("Trie, insert 100 000 times: " + (end - start) + " ms.");

        t = new Trie();
        start = System.currentTimeMillis();

        for (int i = 0; i < 1000000; i++) {
            t.insert(large[i]);
        }
        end = System.currentTimeMillis();
        System.out.println("Trie, insert 1 000 000 times: " + (end - start) + " ms.");


        System.out.println("\n\n\nSEARCHES");

        /**
         * BINÄÄRIHAKUPUU, haut
         */
        start = System.currentTimeMillis();

        for (int i = 0; i < findTheseS.length; i++) {
            b.search(i);
        }
        end = System.currentTimeMillis();
        System.out.println("Binary search, 10 000 searches: " + (end - start) + " ms.");

        start = System.currentTimeMillis();

        for (int i = 0; i < findTheseM.length; i++) {
            b.search(i);
        }
        end = System.currentTimeMillis();
        System.out.println("Binary search, 100 000 searches: " + (end - start) + " ms.");

        start = System.currentTimeMillis();

        for (int i = 0; i < findTheseL.length; i++) {
            b.search(i);
        }
        end = System.currentTimeMillis();
        System.out.println("Binary search, 1 000 000 searches: " + (end - start) + " ms.");

        /**
         * AVL-PUU, haut
         */
        start = System.currentTimeMillis();

        for (int i = 0; i < findTheseS.length; i++) {
            a.search(i);
        }
        end = System.currentTimeMillis();
        System.out.println("AVL, 10 000 searches: " + (end - start) + " ms.");

        start = System.currentTimeMillis();

        for (int i = 0; i < findTheseM.length; i++) {
            a.search(i);
        }
        end = System.currentTimeMillis();
        System.out.println("AVL, 100 000 searches: " + (end - start) + " ms.");

        start = System.currentTimeMillis();

        for (int i = 0; i < findTheseL.length; i++) {
            a.search(i);
        }
        end = System.currentTimeMillis();
        System.out.println("AVL, 1 000 000 searches: " + (end - start) + " ms.");

        /**
         * SPLAY-PUU, haut
         */
        start = System.currentTimeMillis();

        for (int i = 0; i < findTheseS.length; i++) {
            s.search(i);
        }
        end = System.currentTimeMillis();
        System.out.println("Splay, 10 000 searches: " + (end - start) + " ms.");

        start = System.currentTimeMillis();

        for (int i = 0; i < findTheseM.length; i++) {
            s.search(i);
        }
        end = System.currentTimeMillis();
        System.out.println("Splay, 100 000 searches: " + (end - start) + " ms.");

        start = System.currentTimeMillis();

        for (int i = 0; i < findTheseL.length; i++) {
            s.search(i);
        }
        end = System.currentTimeMillis();
        System.out.println("Splay, 1 000 000 searches: " + (end - start) + " ms.");

        /**
         * TRIE-PUU, haut
         */
        start = System.currentTimeMillis();

        for (int i = 0; i < findTheseS.length; i++) {
            t.trieSearch(i);
        }
        end = System.currentTimeMillis();
        System.out.println("Trie, 10 000 searches: " + (end - start) + " ms.");

        start = System.currentTimeMillis();

        for (int i = 0; i < findTheseM.length; i++) {
            t.trieSearch(i);
        }
        end = System.currentTimeMillis();
        System.out.println("Trie, 100 000 searches: " + (end - start) + " ms.");

        start = System.currentTimeMillis();

        for (int i = 0; i < findTheseL.length; i++) {
            t.trieSearch(i);
        }
        end = System.currentTimeMillis();
        System.out.println("Trie, 1 000 000 searches: " + (end - start) + " ms.");


        System.out.println("\n\n\nMIN & MAX");
        /**
         * BINÄÄRIHAKUPUU, min ja max
         */
        start = System.currentTimeMillis();

        System.out.println("(" + b.getMinKey() + " & " + b.getMaxKey() + ")");

        end = System.currentTimeMillis();
        System.out.println("Binary search, min & max: " + (end - start) + " ms.");

        /**
         * AVL-PUU, min ja max
         */
        start = System.currentTimeMillis();

        a.getMinKey();
        a.getMaxKey();

        end = System.currentTimeMillis();
        System.out.println("AVL, min & max: " + (end - start) + " ms.");

        /**
         * SPLAY-PUU, min ja max
         */
        start = System.currentTimeMillis();

        s.getMinKey();
        s.getMaxKey();

        end = System.currentTimeMillis();
        System.out.println("Splay, min & max: " + (end - start) + " ms.");



        System.out.println("\n\n\nDELETES FROM TREES WITH ~1 000 000 NODES");
        Binary_Search b2 = b;
        AVL a2 = a;
        Splay s2 = s;
        Trie t2 = t;
        /**
         * BINÄÄRIHAKUPUU, poistot
         */
        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            b2.delete(delTheseS[i]);
        }
        end = System.currentTimeMillis();
        System.out.println("Binary search, 10 000 deletes: " + (end - start) + " ms.");
        b2 = b;

        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            b2.delete(delTheseM[i]);
        }
        end = System.currentTimeMillis();
        System.out.println("Binary search, 100 000 deletes: " + (end - start) + " ms.");
        b2 = b;

        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            b2.delete(delTheseL[i]);
        }
        end = System.currentTimeMillis();
        System.out.println("Binary search, 1 000 000 deletes: " + (end - start) + " ms.");


        /**
         * AVL-PUU, poistot
         */
        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            a2.AVLdelete(delTheseS[i]);
        }
        end = System.currentTimeMillis();
        System.out.println("AVL, 10 000 deletes: " + (end - start) + " ms.");
        a2 = a;

        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            a2.AVLdelete(delTheseM[i]);
        }
        end = System.currentTimeMillis();
        System.out.println("AVL, 100 000 deletes: " + (end - start) + " ms.");
        a2 = a;

        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            a2.AVLdelete(delTheseL[i]);
        }
        end = System.currentTimeMillis();
        System.out.println("AVL, 1 000 000 deletes: " + (end - start) + " ms.");

        /**
         * SPLAY-PUU, poistot
         */
        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            s2.delete(delTheseS[i]);
        }
        end = System.currentTimeMillis();
        System.out.println("Splay, 10 000 deletes: " + (end - start) + " ms.");
        s2 = s;

        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            s2.delete(delTheseM[i]);
        }
        end = System.currentTimeMillis();
        System.out.println("Splay, 100 000 deletes: " + (end - start) + " ms.");
        s2 = s;

        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            s2.delete(delTheseL[i]);
        }
        end = System.currentTimeMillis();
        System.out.println("Splay, 1 000 000 deletes: " + (end - start) + " ms.");

        /**
         * TRIE-PUU, poistot
         */
        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            t2.delete(delTheseS[i]);
        }
        end = System.currentTimeMillis();
        System.out.println("Trie, 10 000 deletes: " + (end - start) + " ms.");
        t2 = t;

        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            t2.delete(delTheseM[i]);
        }
        end = System.currentTimeMillis();
        System.out.println("Trie, 100 000 deletes: " + (end - start) + " ms.");
        t2 = t;

        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            t2.delete(delTheseL[i]);
        }
        end = System.currentTimeMillis();
        System.out.println("Trie, 1 000 000 deletes: " + (end - start) + " ms.");

        /**
         * Läpikäynnit
         */
        b = new Binary_Search();
        a = new AVL();
        s = new Splay();
        t = new Trie();


        System.out.println("\n\n\nTREE TRAVERSALS when inserts are ");

        for (int i = 0; i < 20; i++) {
            int k = r.nextInt(50);
            b.insert(k);
            a.AVLinsert(k);
            s.splayInsert(k);
            t.insert(k);
            System.out.print(k + " ");
        }

        System.out.println("\n\nBinary search, inorder:");
        b.printInOrder(b.getRoot());
        System.out.println("\nBinary search, preorder:");
        b.printPreOrder(b.getRoot());
        System.out.println("\nBinary search, postorder:");
        b.printPostOrder(b.getRoot());
        System.out.println("\nBinary search, level-order:");
        b.printLevelOrder(b.getRoot());

        System.out.println("\n\nAVL, inorder:");
        a.printInOrder(a.getRoot());
        System.out.println("\nAVL, preorder:");
        a.printPreOrder(a.getRoot());
        System.out.println("\nAVL, postorder:");
        a.printPostOrder(a.getRoot());
        System.out.println("\nAVL, level-order:");
        a.printLevelOrder(a.getRoot());

        System.out.println("\n\nSplay, inorder:");
        s.printInOrder(s.getRoot());
        System.out.println("\nSplay, preorder:");
        s.printPreOrder(s.getRoot());
        System.out.println("\nSplay, postorder:");
        s.printPostOrder(s.getRoot());
        System.out.println("\nSplay, level-order:");
        s.printLevelOrder(s.getRoot());
        System.out.println("\n");
    }
}
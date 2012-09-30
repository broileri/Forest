
import trees.AVL;
import trees.Binary_Search;

/**
 * Pääluokka, jossa jotain testailua näin alkuun. :P
 * 
 * @author Broileri
 */
public class Main {
    
    public static void main(String[] args) {
        
        Binary_Search b = new Binary_Search();
        b.insert(8);
        b.insert(3);
        b.insert(4);
        b.insert(2);
        b.insert(1);
        b.insert(12);
        b.insert(10);
        b.insert(11);
        b.insert(9);
        
        /*
         *                  8
         *                /   \
         *             3         12
         *           /  \        /
         *         2      4     10
         *        /            /  \
         *       1            9    11
         */
        
                      
        
        b.printInOrder(b.getRoot());      // 1 2 3 4 8 9 10 11 12 toimii  
        System.out.println("");
        b.printPostOrder(b.getRoot());    // 1 2 4 3 9 11 10 12 8 toimii
        System.out.println("");
        b.printPreOrder(b.getRoot());     // 8 3 2 1 4 12 10 9 11 toimii
        System.out.println("");
        b.printLevelOrder(b.getRoot());   // 8 3 12 2 4 10 1 9 11 toimii
        System.out.println("");
                
        System.out.println(b.getMaxKey()); // 12 toimii
        System.out.println(b.getMinKey()); // 1 toimii
        
        System.out.println(b.search(10)); // true toimii
        System.out.println(b.search(4)); // true toimii
        System.out.println(b.search(9)); // true toimii
        System.out.println(b.search(7)); // false toimii
        
        System.out.println("Poisto onnistui:");
        b.delete(15);
        b.delete(10);
        System.out.println(b.search(10)); // false toimii
       
        System.out.println("PUU VAIHTUUUUU");
        
        
        
        AVL a = new AVL();
        a.AVLinsert(3);
        a.AVLinsert(2);
        a.AVLinsert(1);
        a.AVLinsert(4);
        a.AVLinsert(5);
        a.AVLinsert(6);
        a.AVLinsert(7);
        a.AVLinsert(16);
        a.AVLinsert(15);
        a.AVLinsert(14);
        
        /*
         *            4
         *           / \
         *         2     7
         *        /\     /\
         *       1  3   6  15
         *             /   / \
         *            5   14  16
         */
        
        a.printPreOrder(a.getRoot()); // 4 2 1 3 7 6 5 15 14 16 toimii
        System.out.println("");
        a.printLevelOrder(a.getRoot()); // 4 2 7 1 3 6 15 5 14 16 toimii
        System.out.println("");
        a.printInOrder(a.getRoot()); // 1 2 3 4 5 6 7 14 15 16 toimii? :P
        System.out.println("");
        a.printPostOrder(a.getRoot()); // 1 3 2 5 6 14 16 15 7 4 toimii? :P
        System.out.println("");
        
        System.out.println(a.getMaxKey()); // 16 toimii
        System.out.println(a.getMinKey()); // 1 toimii
        
        System.out.println(a.search(2)); // true toimii
        System.out.println(a.search(3)); // true toimii
        System.out.println(a.search(8)); // false toimii
        System.out.println(a.search(5)); // true toimii
        System.out.println(a.search(14)); // true toimii
        
        // Poisto:
        a.AVLdelete(22);
        a.AVLdelete(2);
        
        // Ei enää löydy:
        System.out.println(a.search(7)); // false toimii
        
        a.printPreOrder(a.getRoot()); // 4 2 1 3 14 6 5 15 16 toimii
        System.out.println("");
        a.printLevelOrder(a.getRoot()); // 4 2 14 1 3 6 15 5 16 toimii
        System.out.println("");
        
        System.out.println("");
        System.out.println(a.search(123)); // false toimii
        
        System.out.println("PUU VAIHTUU TAAS, BEHOLD!");
        AVL lol = new AVL();
        lol.AVLinsert(1);
        lol.AVLinsert(21);
        lol.AVLinsert(14);
        lol.AVLinsert(15);
        lol.AVLinsert(12);
        lol.AVLinsert(16);
        lol.AVLinsert(19);
        lol.AVLinsert(18);
        lol.AVLinsert(41);
        lol.AVLinsert(13);
        lol.AVLinsert(10);
        lol.AVLinsert(17);
        lol.printInOrder(lol.getRoot());
        System.out.println("");
        lol.printLevelOrder(lol.getRoot());
        
    }
    
}

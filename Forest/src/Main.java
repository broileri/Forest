/*
 * Pääluokka, jossa jotain testailua näin alkuun. :P
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
        
        System.out.println(b.getMax()); // 12 toimii
        System.out.println(b.getMin()); // 1 toimii
        
        System.out.println(b.search(10)); // true toimii
        System.out.println(b.search(4)); // true toimii
        System.out.println(b.search(9)); // true toimii
        System.out.println(b.search(7)); // false toimii
        
        System.out.println("Poisto onnistui:");
        b.delete(15);
        b.delete(10);
        System.out.println(b.search(10)); // false toimii
        
    }
    
}

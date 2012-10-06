
import trees.AVL;
import trees.Binary_Search;
import trees.Splay;
import trees.Trie;

/**
 * Pääluokka, jossa jotain testailua näin alkuun. :P
 * 
 * @author Broileri
 */
public class Main {
    
    public static void main(String[] args) {

/*        
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
    
    * 
    */
    
        
        //                   8
        //                 /   \
        //              3         12
        //            /  \        /
        //          2      4     10
        //         /            /  \
        //        1            9    11
         
        
                      
    /*    
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
        
        * 
        */
        //
        //            4
        //           / \
        //         2     7
        //        /\     /\
        //       1  3   6  15
        //             /   / \
        //            5   14  16
        //
       
        /*
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
        
        
        
        Binary_Search bintree = new Binary_Search();
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
        System.out.println("");
        bintree.printInOrder(bintree.getRoot());
        System.out.println("");
        bintree.printPreOrder(bintree.getRoot());
        System.out.println("");
        bintree.printPostOrder(bintree.getRoot());
    
    
    * 
    */
        /*
        Splay s = new Splay();
        s.splayInsert(18);
        s.splayInsert(5);
        s.printLevelOrder(s.getRoot());
        System.out.println("");
        s.splayInsert(6);
        s.printLevelOrder(s.getRoot());        
        System.out.println("");
        s.splayInsert(20);
        s.printLevelOrder(s.getRoot());
        System.out.println("");        
        s.splayInsert(33);
        s.printLevelOrder(s.getRoot());
        System.out.println("");        
        s.splayInsert(1);
        s.printLevelOrder(s.getRoot());
        System.out.println("");  
        s.splayInsert(9);
        s.printLevelOrder(s.getRoot());
        System.out.println("");
        s.splayInsert(30);
        s.printLevelOrder(s.getRoot());
        System.out.println("");        
        s.splayInsert(7);
        s.printLevelOrder(s.getRoot());
        System.out.println("");       
        s.splayInsert(8);
        s.printLevelOrder(s.getRoot());
        System.out.println("");
        s.splayInsert(4);
        s.printLevelOrder(s.getRoot());
        
        // Inserttien jälkeen
        //
        //              4
        //            /   \
        //           1     8
        //                / \
        //               6   9
        //              / \   \
        //             5   7   30
        //                     / \
        //                    18  33
        //                     \
        //                      20
        //
       /* 
        System.out.println("\nHAKUJA");
        
        System.out.println(s.search(4));
        s.printLevelOrder(s.getRoot());
        System.out.println("");
        
        System.out.println(s.search(33));
        s.printLevelOrder(s.getRoot());
        System.out.println("");
        
        System.out.println(s.search(6));
        s.printLevelOrder(s.getRoot());
        System.out.println("");
        
        System.out.println(s.getMaxKey());
        s.printLevelOrder(s.getRoot());
        System.out.println("");
        
        System.out.println(s.search(-5));
        s.printLevelOrder(s.getRoot());
        System.out.println("");
        
        
        // search taitaa toimia
        // delete taitaa toimia
        // getMax ja getMin taitavat toimia
        // insert toimii kai hyvin :)))
        // to do: splay-testi, trie?, käyttis, dokkarit
        
        /*
        
        System.out.println("");
        System.out.println("");
        System.out.println("");
        s.splayDelete(4);
        s.printLevelOrder(s.getRoot());
        System.out.println("");
        
        s.splayDelete(20);
        s.printLevelOrder(s.getRoot());
        System.out.println("");
        
        s.splayDelete(6);
        s.printLevelOrder(s.getRoot());
        System.out.println("");
        
        s.splayDelete(33);
        s.printLevelOrder(s.getRoot());
        System.out.println("");
       
        
        s.splayDelete(11);
        s.printLevelOrder(s.getRoot());
        System.out.println("");
       
        
        */
        
        // oho, toimii
        Trie c = new Trie();
        c.insert(8);
        c.insert(-3);
        c.insert(-38);
        c.insert(-3834);
        c.insert(12);
        c.insert(3389);
        c.insert(9999);
        c.insert(23857369);
        c.insert(06);
        
        // nämäkin
        System.out.println(c.trieSearch(8));
        System.out.println(c.trieSearch(-38));
        System.out.println(c.trieSearch(11));
        System.out.println(c.trieSearch(12));
        System.out.println(c.trieSearch(-3834));        
        System.out.println("");
        
        // poistotkin taitavat olla jees ^^
        System.out.println("POISTOT!");
        
        c.delete(11);
        System.out.println(c.trieSearch(8));
        System.out.println(c.trieSearch(-38));
        System.out.println(c.trieSearch(11));
        System.out.println(c.trieSearch(12));
        System.out.println(c.trieSearch(-3834));
        System.out.println("");
        
        System.out.println("POISTOT!");
        
        c.delete(8);
        System.out.println(c.trieSearch(8));
        System.out.println(c.trieSearch(-38));
        System.out.println(c.trieSearch(11));
        System.out.println(c.trieSearch(12));
        System.out.println(c.trieSearch(-3834));
        System.out.println("");
        
        System.out.println("POISTOT!");
        
        c.delete(-38);
        System.out.println(c.trieSearch(8));
        System.out.println(c.trieSearch(-38));
        System.out.println(c.trieSearch(11));
        System.out.println(c.trieSearch(12));
        System.out.println(c.trieSearch(-3834));
        System.out.println("");
        
        System.out.println("POISTOT!");
        
        c.delete(12);
        System.out.println(c.trieSearch(8));
        System.out.println(c.trieSearch(-38));
        System.out.println(c.trieSearch(11));
        System.out.println(c.trieSearch(12));
        System.out.println(c.trieSearch(-3834));
        
        System.out.println("");
        
        c.insert(-2);
        c.delete(-2);
        System.out.println(c.trieSearch(-2));
        c.insert(-65);
        c.delete(-65);
        System.out.println(c.trieSearch(-65));
        c.insert(-65);
        c.insert(-655);
        c.delete(-65);
        System.out.println(c.trieSearch(-655));
      
        
        
    }

}
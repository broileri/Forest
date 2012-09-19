/*
 * Binäärihakupuu
 */

public class Binary_Search extends Walks {

    private Node root;    

    // Konstruktori, puuhun tehdään tyhjä juuri
    public Binary_Search() {
        this.root = null;
    }
    
    public Node getRoot() {
        return this.root;
    }

    public void insert(int key) {

        // Jos tyhjä puu, luodaan juurisolmu
        if (this.root == null) {
            this.root = new Node(key);
        }  
        
        else {            
            // Käsittelyssä oleva solmu
            Node current = this.root;

            while (true) {
                // Lisättävä pienempi tai yhtä suuri --> vasemmalle
                if (key <= current.getKey()) {

                    // Jos vasen lapsi ei ole tyhjä, siirrytään puussa alaspäin
                    if (current.getLeft() != null) {
                        current = current.getLeft();
                    } 
                    // Jos vasen lapsi on tyhjä, tallennetaan uusi arvo sen kohdalle 
                    else {
                        current.setLeft(new Node(key));
                        current.getLeft().setParent(current);
                        return;
                    }
                } 
                // Lisättävä suurempi --> oikealle
                else {

                    // Jos oikea lapsi ei ole tyhjä, siirrytään puussa alaspäin
                    if (current.getRight() != null) {
                        current = current.getRight();
                    } 
                    // Jos oikea lapsi on tyhjä, tallennetaan uusi arvo sen kohdalle
                    else {
                        current.setRight(new Node(key));
                        current.getRight().setParent(current);
                        return;
                    }
                }
            }
        }
    }

    public boolean search(int key) {

        // Tyhjä puu
        if (this.root == null) {
            return false;
        }

        // Käsittelyssä oleva solmu
        Node current = this.root;

        while (true) {

            // Löytyi!
            if (key == current.getKey()) {
                return true;
            }

            // Etsittävä pienempi --> vasemmalle
            if (key < current.getKey()) {

                // Jos vasen lapsi ei ole tyhjä, siirrytään puussa alaspäin
                if (current.getLeft() != null) {
                    current = current.getLeft();
                } // Jos vasen lapsi on tyhjä, etsittävää arvoa ei löytynyt 
                else {
                    current.setLeft(new Node(key));
                    return false;
                }
            } // Etsittävä suurempi --> oikealle
            else {

                // Jos oikea lapsi ei ole tyhjä, siirrytään puussa alaspäin
                if (current.getRight() != null) {
                    current = current.getRight();
                } // Jos oikea lapsi on tyhjä, etsittävää arvoa ei löytynyt 
                else {
                    current.setRight(new Node(key));
                    return false;
                }
            }
        }
    }        
    
    public void delete(int key) {
        
        Node current = this.root;
        
        while (true) {
            
            // Poistettava löytyi - poistetaan
            if (current.getKey() == key) {
                
                current.getParent().setLeft(current.getLeft());
                current.getParent().setRight(current.getRight());
                current.getLeft().setParent(current.getParent());
                current.getRight().setParent(current.getParent());                                
                return;
            }
            
            // Siirrytään vasemmalle
            else if (key < current.getKey()) {
                
                if (current.getLeft() != null) {
                    current = current.getLeft();
                }
                else {
                    return;
                }                
            }
            
            // Siirrytään oikealle
            else {
                
                if (current.getRight() != null) {
                    current = current.getRight();
                }
                else {
                    return;
                }                 
            }
        }
    }

    public int getMin() {
        
        Node current = this.root;
         
         while (current.getLeft() != null) {
             current = current.getLeft();
         }
        return current.getKey();
    }
    
    public int getMax() {
        
         Node current = this.root;
         
         while (current.getRight() != null) {
             current = current.getRight();
         }
                 
        return current.getKey();
    }
}

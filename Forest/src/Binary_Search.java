/*
 * Binäärihakupuu
 */

public class Binary_Search {

    private Node root;
    private int max;

    // Konstruktori, puuhun tehdään tyhjä juuri
    public Binary_Search() {
        this.root = null;
    }

    public void insert(int key) {

        // Jos tyhjä puu, luodaan juurisolmu ja asetetaan max alkutilaansa
        if (this.root == null) {
            this.root = new Node(key);
            max = key;
        } 
        
        else {

            // Max-arvon tarkastus
            if (key > this.max) {
                max = key;
            }

            // Käsittelyssä oleva solmu
            Node currentNode = this.root;


            while (true) {

                // Lisättävä pienempi tai yhtä suuri --> vasemmalle
                if (key <= currentNode.getKey()) {

                    // Jos vasen lapsi ei ole tyhjä, siirrytään puussa alaspäin
                    if (currentNode.getLeft() != null) {
                        currentNode = currentNode.getLeft();
                    } 
                    // Jos vasen lapsi on tyhjä, tallennetaan uusi arvo sen kohdalle 
                    else {
                        currentNode.setLeft(new Node(key));
                        currentNode.getLeft().setParent(currentNode);
                        return;
                    }
                } 
                // Lisättävä suurempi --> oikealle
                else {

                    // Jos oikea lapsi ei ole tyhjä, siirrytään puussa alaspäin
                    if (currentNode.getRight() != null) {
                        currentNode = currentNode.getRight();
                    } 
                    // Jos oikea lapsi on tyhjä, tallennetaan uusi arvo sen kohdalle
                    else {
                        currentNode.setRight(new Node(key));
                        currentNode.getRight().setParent(currentNode);
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
        Node currentNode = this.root;

        while (true) {

            // Löytyi!
            if (key == currentNode.getKey()) {
                return true;
            }

            // Etsittävä pienempi --> vasemmalle
            if (key < currentNode.getKey()) {

                // Jos vasen lapsi ei ole tyhjä, siirrytään puussa alaspäin
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } // Jos vasen lapsi on tyhjä, etsittävää arvoa ei löytynyt 
                else {
                    currentNode.setLeft(new Node(key));
                    return false;
                }
            } // Etsittävä suurempi --> oikealle
            else {

                // Jos oikea lapsi ei ole tyhjä, siirrytään puussa alaspäin
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } // Jos oikea lapsi on tyhjä, etsittävää arvoa ei löytynyt 
                else {
                    currentNode.setRight(new Node(key));
                    return false;
                }
            }
        }
    }
        
    
    public void delete(int key) {
        
        // Käsittelyssä oleva solmu
        Node currentNode = this.root;
        
        while (true) {
            
            // Poistettava löytyi - poistetaan
            if (currentNode.getKey() == key) {
                
                currentNode.getParent().setLeft(currentNode.getLeft());
                currentNode.getParent().setRight(currentNode.getRight());
                currentNode.getLeft().setParent(currentNode.getParent());
                currentNode.getRight().setParent(currentNode.getParent());
                
                if (currentNode.getKey() == max) {
                    max = currentNode.getParent().getKey();
                }
                return;
            }
            
            // Siirrytään vasemmalle
            else if (key < currentNode.getKey()) {
                
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                }
                else {
                    return;
                }                
            }
            
            // Siirrytään oikealle
            else {
                
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                }
                else {
                    return;
                }                 
            }
        }
    }

    public int getMin() {
        return root.getKey();
    }
    
    public int getMax() {
        return this.max;
    }
}

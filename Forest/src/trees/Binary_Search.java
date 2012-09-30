package trees;

import data_structures.Node;
import data_structures.Walks;

/**
 * Binäärihakupuu
 * 
 * @author Broileri
 */
public class Binary_Search extends Walks {

    private Node root;

    /**
     * Konstruktori asettaa puun juureksi null-arvon.
     */
    public Binary_Search() {
        this.root = null;
    }

    /**
     * Palauttaa puun juurisolmun.
     * 
     * @see data_structures.Node
     * 
     * @return Puun juurisolmu.
     */
    public Node getRoot() {
        return this.root;
    }

    /**
     * insert lisää puuhun solmun, jolla on parametrina annettu avain.
     * 
     * @see data_structures.Node
     * 
     * @param key Puuhun lisättävä avain.
     */
    public void insert(int key) {

        // Jos tyhjä puu, luodaan juurisolmu
        if (this.root == null) {
            this.root = new Node(key);
        } else {
            // Käsittelyssä oleva solmu
            Node current = this.root;

            while (true) {
                // Lisättävä pienempi tai yhtä suuri --> vasemmalle
                if (key <= current.getKey()) {

                    // Jos vasen lapsi ei ole tyhjä, siirrytään puussa alaspäin
                    if (current.getLeft() != null) {
                        current = current.getLeft();
                    } // Jos vasen lapsi on tyhjä, tallennetaan uusi arvo sen kohdalle 
                    else {
                        current.setLeft(new Node(key));
                        current.getLeft().setParent(current);
                        return;
                    }
                } // Lisättävä suurempi --> oikealle
                else {

                    // Jos oikea lapsi ei ole tyhjä, siirrytään puussa alaspäin
                    if (current.getRight() != null) {
                        current = current.getRight();
                    } // Jos oikea lapsi on tyhjä, tallennetaan uusi arvo sen kohdalle
                    else {
                        current.setRight(new Node(key));
                        current.getRight().setParent(current);
                        return;
                    }
                }
            }
        }
    }

  /**
     * Tutkii, löytyykö parametrina annetusta puusta parametrina annettua avainta.
     * Jos löytyy, metodi palauttaa solmun, jolla on parametrina annettu avain.
     * 
     * @see data_structures.Node
     * 
     * @param x Puu, josta avainta etsitään.
     * @param key Avain, jota etsitään.
     * 
     * @return Solmu, jolla on etsittävä avain tai null.
     */
    private Node nodeSearch(Node x, int key) {

        if (x == null || x.getKey() == key) {
            return x;
        }
        if (key < x.getKey()) {
            return nodeSearch(x.getLeft(), key);
        } else {
            return nodeSearch(x.getRight(), key);
        }
    }

    /**
     * delete poistaa puusta solmun, jolla on parametrina annettu avain. Jos
     * puussa ei ole poistettavaa solmua, metodi ei tee mitään.
     * 
     * @param key Poistettavan solmun avain.
     */
    public void delete(int key) {
        
        // Onko poistettavaa puussa
        Node deleted = nodeSearch(this.getRoot(), key),
                parent, child, next;        
        if (deleted != null) {                       
            // Jos poistettavalla ei ole lapsia...
            if (deleted.getLeft() == null && deleted.getRight() == null) {
                parent = deleted.getParent();
                // Jos kyseessä on juuri
                if (parent == null) {
                    this.root = null;
                    return;
                }
                if (deleted == parent.getLeft()) {
                    parent.setLeft(null);
                } else {
                    parent.setRight(null);
                }
            } // Jos poistettavalla on vain yksi lapsi
            else if (deleted.getLeft() == null || deleted.getRight() == null) {

                if (deleted.getLeft() != null) {
                    child = deleted.getLeft();
                } else {
                    child = deleted.getRight();
                }
                parent = deleted.getParent();
                child.setParent(parent);

                // Poistettava on juuri...
                if (parent == null) {
                    this.root = child;
                    return;
                }
                if (deleted == parent.getLeft()) {
                    parent.setLeft(child);
                } else {
                    parent.setRight(child);
                }
            } // Jos poistettavalla on kaksi lasta...
            else {
                next = getMin(deleted.getRight());
                deleted.setKey(next.getKey());
                child = next.getRight();
                parent = next.getParent();
                if (parent.getLeft() == next) {
                    parent.setLeft(child);
                } else {
                    parent.setRight(child);
                }
                if (child != null) {
                    child.setParent(parent);
                }
            }
        }
    }    


    /**
     * Tutkii NodeSearch-metodin avulla, löytyykö puusta parametrina
     * annettu arvo.
     * 
     * @see trees.AVL#nodeSearch(data_structures.Node, int) 
     * 
     * @param key Avain, jota etsitään.
     * 
     * @return Tieto siitä, onko avain puussa vai ei.
     */
    public boolean search(int key) {

        Node found = nodeSearch(this.getRoot(), key);
        if (found == null) {
            return false;
        }
        return true;
    }

    /**
     * Etsii annetusta (ali)puusta solmun, jolla on pienin avain.
     *
     * @see data_structures.Node
     *
     * @param x Puu, jonka pienintä solmua etsitään.
     *
     * @return Pieniavaimisin x:n alipuun solmu.
     */
    private Node getMin(Node x) {

        while (x.getLeft() != null) {
            x = x.getLeft();
        }
        return x;
    }

    /**
     * Etsii annetusta (ali)puusta solmun, jolla on suurin avain.
     *
     * @see data_structures.Node
     *
     * @param x Puu, jonka suurinta solmua etsitään.
     *
     * @return Suuriavaimisin x:n alipuun solmu.
     */
    private Node getMax(Node x) {

        while (x.getRight() != null) {
            x = x.getRight();
        }
        return x;
    }

    /**
     * Etsii puusta suurimman avaimen getMax-metodin avulla.
     *
     * @see trees.AVL#getMax(data_structures.Node)
     *
     * @return Puun suurin avain.
     */
    public int getMaxKey() {
        return getMax(this.root).getKey();
    }

    /**
     * Etsii puusta pienimmän avaimen getMin-metodin avulla.
     *
     * @see trees.AVL#getMin(data_structures.Node)
     *
     * @return Puun pienin avain.
     */
    public int getMinKey() {
        return getMin(this.root).getKey();
    }

}

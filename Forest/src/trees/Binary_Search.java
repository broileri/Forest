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
     * @return Puun juurisolmu.
     */
    public Node getRoot() {
        return this.root;
    }

    /**
     * Lisää puuhun solmun, jolla on parametrina annettu avain.
     *
     * @see data_structures.Node
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

                if (key == current.getKey()) {
                    return; // Lisättävä on jo puussa - ei tehdä uutta lisäystä, palataan!
                }
                if (key < current.getKey()) { // Lisättävä pienempi tai yhtä suuri --> vasemmalle                    
                    if (current.getLeft() != null) {
                        current = current.getLeft(); // Vasen lapsi ei ole tyhjä, siirrytään puussa alaspäin
                    } else {
                        current.setLeft(new Node(key)); // Vasen lapsi on tyhjä, tallennetaan uusi arvo sen kohdalle 
                        current.getLeft().setParent(current);
                        return;
                    }
                } else { // Lisättävä suurempi --> oikealle
                    if (current.getRight() != null) {
                        current = current.getRight(); // Oikea lapsi ei ole tyhjä, siirrytään puussa alaspäin
                    } else {
                        current.setRight(new Node(key)); // Oikea lapsi on tyhjä, tallennetaan uusi arvo sen kohdalle
                        current.getRight().setParent(current);
                        return;
                    }
                }
            }
        }
    }

    /**
     * Tutkii, löytyykö parametrina annetusta puusta parametrina annettua
     * avainta. Jos löytyy, metodi palauttaa solmun, jolla on parametrina
     * annettu avain.
     *
     * @see data_structures.Node
     * @param x Puu, josta avainta etsitään.
     * @param key Avain, jota etsitään.
     * @return Solmu, jolla on etsittävä avain tai null.
     */    
    private Node nodeSearch(Node x, int key) {
        
        while (x != null) {
            if (key == x.getKey()) {
                return x;
            }
            if (key < x.getKey()) {
                x = x.getLeft();
            }
            else {
                x = x.getRight();
            }
        }
        return null;        
    }


    /**
     * Poistaa puusta solmun, jolla on paremetrina annettu avain. Jos puussa ei
     * ole poistettavaa solmua, metodi ei tee mitään.
     *
     * @see data_structures.Node
     * @see trees.Binary_Search#nodeSearch(data_structures.Node, int)
     * @see trees.Binary_Search#caseNoChildren(data_structures.Node)
     * @see trees.Binary_Search#caseOneChild(data_structures.Node)
     * @see trees.Binary_Search#caseTwoChildren(data_structures.Node)
     * @param key Poistettavan solmun avainarvo.
     */
    public void delete(int key) {

        Node found = nodeSearch(this.getRoot(), key);

        // Jos avain on puussa, poistetaan node
        if (found != null) {
            if (found.getLeft() == null && found.getRight() == null) {
                caseNoChildren(found); // Poistettavalla ei lapsia                    
            } else if (found.getLeft() == null || found.getRight() == null) {
                caseOneChild(found);  // Poistettavalla yksi lapsi
            } else {
                caseTwoChildren(found); // Poistettavalla kaksi lasta
            }
        }
    }

    /**
     * Apumetodi deletelle. Hoitaa sellaisen solmun poistamisen, jolla ei ole
     * lainkaan lapsisolmuja.
     *
     * @see data_structures.Node
     * @see trees.Binary_Search#delete(int)
     * @param deleteThis Node, joka poistetaan.
     */
    private void caseNoChildren(Node deleteThis) {

        Node parent = deleteThis.getParent();
        // Jos kyseessä on juuri, puusta tulee tyhjä
        if (parent == null) {
            this.root = null;
            return;
        }
        if (deleteThis == parent.getLeft()) {
            parent.setLeft(null);
        } else {
            parent.setRight(null);
        }
    }

    /**
     * Apumetodi deletelle. Hoitaa sellaisen solmun poistamisen, jolla on yksi
     * lapsisolmu.
     *
     * @see data_structures.Node
     * @see trees.Binary_Search#delete(int)
     * @param deleteThis Node, joka poistetaan.
     */
    private void caseOneChild(Node deleteThis) {

        Node child, parent;
        if (deleteThis.getLeft() != null) {
            child = deleteThis.getLeft();
        } else {
            child = deleteThis.getRight();
        }
        parent = deleteThis.getParent();
        child.setParent(parent);

        // Poistettava on juuri
        if (parent == null) {
            this.root = child;
            return;
        }
        if (deleteThis == parent.getLeft()) {
            parent.setLeft(child);
        } else {
            parent.setRight(child);
        }
    }

    /**
     * Apumetodi deletelle. Hoitaa sellaisen solmun poistamisen, jolla on kaksi
     * lapsisolmua.
     *
     * @see data_structures.Node
     * @see trees.Binary_Search#delete(int)
     * @param deleteThis Node, joka poistetaan.
     */
    private void caseTwoChildren(Node deleteThis) {

        Node next, child, parent;

        next = getMin(deleteThis.getRight());
        deleteThis.setKey(next.getKey());
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

    /**
     * Tutkii NodeSearch-metodin avulla, löytyykö puusta parametrina annettu
     * arvo.
     *
     * @see trees.Binary_Search#nodeSearch(data_structures.Node, int)
     * @param key Avain, jota etsitään.
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
     * @param x Puu, jonka pienintä solmua etsitään.
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
     * @param x Puu, jonka suurinta solmua etsitään.
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
     * @see trees.Binary_Search#getMax(data_structures.Node)
     * @return Puun suurin avain.
     */
    public int getMaxKey() {
        return getMax(this.root).getKey();
    }

    /**
     * Etsii puusta pienimmän avaimen getMin-metodin avulla.
     *
     * @see trees.Binary_Search#getMin(data_structures.Node)
     * @return Puun pienin avain.
     */
    public int getMinKey() {
        return getMin(this.root).getKey();
    }
}
package trees;

import data_structures.Node;
import data_structures.Walks;

/**
 * Splay-puu
 *
 * @author Broileri
 */
public class Splay extends Walks {

    private Node root;

    /**
     * Konstruktori asettaa puun juureksi null-arvon.
     */
    public Splay() {
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

    public void splayInsert(int key) {

        Node current = insert(key);
        splay(current);
    }

    /**
     * SplayInsertin käyttämä apumetodi, joka hoitaa varsinaisen
     * solmunlisäysoperaation.
     *
     * @see trees.Splay#SplayInsert(int key)
     * @see data_structures.Node
     * @param key Puuhun lisättävän alkion avain.
     * @return Viite puuhun juuri lisättyyn solmuun.
     */
    private Node insert(int key) {

        Node newNode = new Node(key);

        // Jos tyhjä puu, luodaan juurisolmu
        if (this.root == null) {
            this.root = newNode;
            return newNode;
        } else {
            Node current = this.root;
            while (true) {
                // Lisättävä pienempi --> vasemmalle
                if (key < current.getKey()) {

                    // Jos vasen lapsi ei ole tyhjä, siirrytään puussa alaspäin
                    if (current.getLeft() != null) {
                        current = current.getLeft();
                    } // Jos vasen lapsi on tyhjä, tallennetaan uusi arvo sen kohdalle 
                    else {
                        current.setLeft(newNode);
                        current.getLeft().setParent(current);
                        return newNode;
                    }
                } // Lisättävä suurempi --> oikealle
                else if (key > current.getKey()) {

                    // Jos oikea lapsi ei ole tyhjä, siirrytään puussa alaspäin
                    if (current.getRight() != null) {
                        current = current.getRight();
                    } // Jos oikea lapsi on tyhjä, tallennetaan uusi arvo sen kohdalle
                    else {
                        current.setRight(newNode);
                        current.getRight().setParent(current);
                        return newNode;
                    }
                } // Lisättävä on jo puussa
                else {
                    return current;
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
     *
     * @param x Puu, josta avainta etsitään.
     * @param key Avain, jota etsitään.
     *
     * @return Solmu, jolla on etsittävä avain tai null.
     */
    private Node nodeSearch(Node x, int key) {

        if (x != null) {
            // Etsittävää arvoa ei ole puussa, palautetaan arvo, joka olisi ollut keyn parent
            if (x.getKey() != key && x.getLeft() == null && x.getRight() == null) {
                return x;
            }
            // Löytyi! Palautetaan solmu, jolla key.
            if (x.getKey() == key) {
                return x;
            }
            if (key < x.getKey()) {
                return nodeSearch(x.getLeft(), key);
            } else {
                return nodeSearch(x.getRight(), key);
            }
        }
        return null;
    }

    public void splayDelete(int key) {

        if (this.root != null) {
            Node deleted = nodeSearch(this.root, key);
            splay(deleted);
            // key on puussa
            if (deleted.getKey() == key) {                
                deleteRoot();
            }
        }
    }

    private void deleteRoot() {

        Node deleted = this.getRoot(), newRoot;

        // Jos juurella ei ole lapsia...
        if (deleted.getLeft() == null && deleted.getRight() == null) {
            this.root = null;
        } // Jos poistettavalla on vain yksi lapsi
        else if (deleted.getLeft() == null || deleted.getRight() == null) {

            if (deleted.getLeft() != null) {
                this.root = deleted.getLeft();
                this.root.setParent(null);
            } else {
                newRoot = getMin(this.root.getLeft());
                splay(newRoot);
            }
        } // Jos poistettavalla on kaksi lasta...
        else {
            // poistettava on nyt juuri
            Node left = deleted.getLeft();
            Node right = deleted.getRight();
            newRoot = getMin(right);
            this.root = null;
            left.setParent(null);
            right.setParent(null);

            if (right == null) {
                this.root = left;
            }
            if (right == newRoot) {
                this.root = right;
                right.setLeft(left);
            } else {
                splay(newRoot);
                this.root.setLeft(left);
            }
        }
    }

    /**
     * Tutkii NodeSearch-metodin avulla, löytyykö puusta parametrina annettu
     * arvo.
     *
     * @see trees.AVL#nodeSearch(data_structures.Node, int)
     *
     * @param key Avain, jota etsitään.
     *
     * @return Tieto siitä, onko avain puussa vai ei.
     */
    public boolean search(int key) {

        Node found = nodeSearch(this.getRoot(), key);
        if (found != null) {
            splay(found);
        }
        if (found == null || found.getKey() != key) {
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
        Node x = getMax(this.root);
        splay(x);
        return x.getKey();
    }

    /**
     * Etsii puusta pienimmän avaimen getMin-metodin avulla.
     *
     * @see trees.AVL#getMin(data_structures.Node)
     *
     * @return Puun pienin avain.
     */
    public int getMinKey() {
        Node x = getMin(this.root);
        splay(x);
        return x.getKey();
    }

    /**
     * splayn yhteydessä käytettävä metodi, joka kiertää puusolmuja oikealle.
     *
     * @see trees.Splay#splay(data_structures.Node)
     * @see data_structures.Node
     * @param x Solmu, jota kierretään.
     * @return Kierretyn alipuun uusi juuri.
     */
    private Node rotateRight(Node x) {

        Node y = x.getLeft();
        y.setParent(x.getParent());
        x.setParent(y);
        x.setLeft(y.getRight());
        y.setRight(x);
        if (x.getLeft() != null) {
            x.getLeft().setParent(x);
        }
        return y; // y
    }

    /**
     * splayn yhteydessä käytettävä metodi, joka kiertää puusolmuja vasemmalle.
     *
     * @see trees.Splay#splay(data_structures.Node)
     * @see data_structures.Node
     * @param x Solmu, jota kierretään.
     * @return Kierretyn alipuun uusi juuri.
     */
    private Node rotateLeft(Node x) {

        Node y = x.getRight();
        y.setParent(x.getParent());
        x.setParent(y);
        x.setRight(y.getLeft());
        y.setLeft(x);
        if (x.getRight() != null) {
            x.getRight().setParent(x);
        }
        return y;
    }

    // TÄMÄ JA INSERT TOIMIVAT! \o/ (luultavasti)
    public void splay(Node x) {

        Node p, gp, current;

        while (x.getParent() != null) {
            p = x.getParent();
            gp = p.getParent();

            // Zig & zag
            if (p == this.getRoot()) {
                if (x == p.getLeft()) {
                    current = rotateRight(p);
                    fixParent(current);
                } else {
                    current = rotateLeft(p);
                    fixParent(current);
                }
            } // Zig-zig & zag-zag
            else if (x == p.getLeft() && p == gp.getLeft()) {
                current = rotateRight(gp);
                fixParent(current);
                current = rotateRight(p);
                fixParent(current);
            } else if (x == p.getRight() && p == gp.getRight()) {
                current = rotateLeft(gp);
                fixParent(current);
                current = rotateLeft(p);
                fixParent(current);
            } // Zig-zag & zag-zig
            else if (x == p.getRight() && p == gp.getLeft()) {
                current = rotateLeft(p);
                fixParent(current);
                current = rotateRight(gp);
                fixParent(current);
            } else {
                current = rotateRight(p);
                fixParent(current);
                current = rotateLeft(gp);
                fixParent(current);
            }
        }
    }

    private void fixParent(Node x) {

        Node parent = x.getParent();

        if (parent == null) {
            this.root = x;
        } else if (x.getKey() > parent.getKey()) {
            parent.setRight(x);
        } else {
            parent.setLeft(x);
        }
    }
}

package trees;

import data_structures.Node;
import data_structures.Walks;

/**
 * AVL-puu.
 *
 * @see data_structures.Walks
 *
 * @author Broileri
 */
public class AVL extends Walks {

    private Node root;

    /**
     * Konstruktori asettaa puun juureksi null-arvon.
     */
    public AVL() {
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
     * Asettaa puulle juurisolmun.
     *
     * @see data_structures.Node
     *
     */
    private void setRoot(Node root) {
        this.root = root;
    }

    /**
     * AVLinsert lisää insertin avulla puuhun solmun, jolla on parametrina
     * annettu avain. Sen jälkeen metodi tasapainottaa puun apumetodien avulla.
     *
     * @see data_structures.Node
     * @see trees.AVL#insert(int)
     * @see trees.AVL#balanceLeftsGrandchild(data_structures.Node)
     * @see trees.AVL#balanceRightsGrandchild(data_structures.Node)
     *
     * @param key Puuhun lisättävän solmun avain.
     */
    public void AVLinsert(int key) {

        // Viedään uusi arvo puuhun
        Node newNode = insert(key), p = newNode.getParent();

        while (p != null) {

            // Jos vasen lapsi aiheuttaa epätasapainon...
            if (countHeight(p.getLeft()) == countHeight(p.getRight()) + 2) {
                balanceLeftsGrandchild(p);
                return;
            }
            // Jos oikea lapsi aiheuttaa epätasapainon...
            if (countHeight(p.getRight()) == countHeight(p.getLeft()) + 2) {
                balanceRightsGrandchild(p);
                return;
            }
            // Asetetaan p:lle uusi pituus ja jatketaan ylös juurta päin         
            p.setHeight(higher(countHeight(p.getLeft()), countHeight(p.getRight())) + 1);
            p = p.getParent();
        }
    }

    /**
     * AVLinsertin yhteydessä käytettävä metodi, joka päivittää p:n vanhemman
     * lapsen ja korkeuden sen jälkeen, kun p:ssä ollut epätasapaino on
     * korjattu.
     *
     * @see trees.AVL#AVLinsert(int key)
     * @see data_structures.Node
     * @param parent p:n vanhempi.
     * @param subtree Alipuu, joka on syntynyt puun kierroista ja joka nyt
     * asetetaan parentin lapseksi.
     * @param p Solmu, jonka epätasapaino korjattiin äskettäin.     
     * 
     */
    private void fixParent(Node parent, Node subtree, Node p) {

        if (parent == null) {
            this.setRoot(subtree);
        } else if (parent.getLeft() == p) {
            parent.setLeft(subtree);
        } else {
            parent.setRight(subtree);
        }
        // Asetetaan korkeudet oikein
        if (parent != null) {
            parent.setHeight(higher(countHeight(parent.getLeft()), countHeight(parent.getRight())) + 1);
        }
    }

    /**
     * Laskee parametrinä annetun solmun korkeuden puussa.
     *
     * @see data_structures.Node
     * @param x Tutkittava solmu.
     * @return Solmun x korkeus.
     */
    private int countHeight(Node x) {

        if (x == null) {
            return -1;
        }
        int leftHeight = countHeight(x.getLeft());
        int rightHeight = countHeight(x.getRight());
        return (higher(leftHeight, rightHeight) + 1);
    }

    /**
     * AVLinsertin käyttämä apumetodi, joka hoitaa varsinaisen
     * solmunlisäysoperaation.
     *
     * @see trees.AVL#AVLinsert(int key)
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
                // Lisättävä pienempi tai yhtä suuri --> vasemmalle
                if (key <= current.getKey()) {

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
                else {

                    // Jos oikea lapsi ei ole tyhjä, siirrytään puussa alaspäin
                    if (current.getRight() != null) {
                        current = current.getRight();
                    } // Jos oikea lapsi on tyhjä, tallennetaan uusi arvo sen kohdalle
                    else {
                        current.setRight(newNode);
                        current.getRight().setParent(current);
                        return newNode;
                    }
                }
            }
        }
    }

    /**
     * Metodi poistaa puusta deleten avulla solmun, jolla on parametrina annettu
     * avain. Tämän jälkeen AVLdelete tasapainottaa puun juureen asti
     * apumetodien avulla.
     *
     * @see data_structures.Node
     * @see trees.AVL#delete(int)
     * @see trees.AVL#balanceLeftsGrandchild(data_structures.Node)
     * @see trees.AVL#balanceRightsGrandchild(data_structures.Node)
     * @param key Poistettavan solmun avain.
     */
    public void AVLdelete(int key) {

        Node deleted = delete(key);

        // Jos poistettava oli puussa...
        if (deleted != null) {
            Node p = deleted.getParent();

            while (p != null) {
                // Jos vasen lapsi aiheuttaa epätasapainon...
                if (countHeight(p.getLeft()) == countHeight(p.getRight()) + 2) {
                    balanceLeftsGrandchild(p);

                } // Jos oikea lapsi aiheuttaa epätasapainon?
                else if (countHeight(p.getRight()) == countHeight(p.getLeft()) + 2) {
                    balanceRightsGrandchild(p);

                } // Tasapaino kunnossa, päivitetään solmun pituus ja jatketaan juurta päin
                else {
                    p.setHeight(higher(countHeight(p.getLeft()), countHeight(p.getRight())) + 1);
                    p = p.getParent();
                }
            }
        }
    }

    /**
     * AVLinsertin ja AVLdeleten käyttämä apumetodi, joka tasapainottaa puuta
     * silloin, kun tasapainon on aiheuttanut käsiteltävän solmun vasemman
     * lapsen lapsi.
     *
     * @see trees.AVL#fixParent(data_structures.Node, data_structures.Node, data_structures.Node) 
     * 
     * @param p Käsiteltävä solmu.
     */
    private void balanceLeftsGrandchild(Node p) {

        Node subtree,
                parent = p.getParent();

        // Aiheutuuko epätasapaino vasemman lapsen vasemmasta vai oikeasta alipuusta?
        if (countHeight(p.getLeft().getLeft()) > countHeight(p.getLeft().getRight())) {
            subtree = rotateRight(p);
        } else {
            subtree = rotateLeftRight(p);
        }
        fixParent(parent, subtree, p);
    }

    /**
     * AVLinsertin ja AVLdeleten käyttämä apumetodi, joka tasapainottaa puuta
     * silloin, kun tasapainon on aiheuttanut käsiteltävän solmun oikean lapsen
     * lapsi.
     * 
     * @see trees.AVL#fixParent(data_structures.Node, data_structures.Node, data_structures.Node) 
     * 
     * @param p Käsiteltävä solmu.
     */
    private void balanceRightsGrandchild(Node p) {

        Node subtree,
                parent = p.getParent();

        // Aiheutuuko epätasapaino oikean lapsen oikeasta vai vasemmastaalipuusta?
        if (countHeight(p.getRight().getRight()) > countHeight(p.getRight().getLeft())) {
            subtree = rotateLeft(p);
        } else {
            subtree = rotateRightLeft(p);
        }
        fixParent(parent, subtree, p);
    }

    /**
     * AVLdeleten käyttämä apumetodi, joka hoitaa varsinaisen poistamisen.
     * Poistaa alkion, jolla on parametrina annettu avain.
     *
     * @param key Puusta poistettava avain.
     * @return Viite puusta poistettuun solmuun tai null, jos poistettavaa
     * avainta ei löytynyt puusta.
     */
    private Node delete(int key) {

        Node deleted = nodeSearch(this.getRoot(), key),
                parent, child;

        // Onko poistettavaa puussa
        if (deleted != null) {

            // Jos poistettavalla ei ole lapsia...
            if (deleted.getLeft() == null && deleted.getRight() == null) {
                parent = deleted.getParent();
                // Jos kyseessä on juuri
                if (parent == null) {
                    this.root = null;
                    return deleted;
                }
                if (deleted == parent.getLeft()) {
                    parent.setLeft(null);
                } else {
                    parent.setRight(null);
                }
                return deleted;
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
                    return deleted;
                }
                if (deleted == parent.getLeft()) {
                    parent.setLeft(child);
                } else {
                    parent.setRight(child);
                }
                return deleted;
            } // Jos poistettavalla on kaksi lasta...
            else {
                Node next = getMin(deleted.getRight());
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
                return next;
            }
        }
        return null;
    }

    /**
     * AVLinsertin ja AVLdeleten yhteydessä käytettävä metodi, joka kiertää
     * puusolmuja oikealle tasapainon säilyttämiseksi ja asettaa paikkaansa
     * vaihtaneiden solmujen korkeudet oikeanlaisiksi.
     *
     * @see trees.AVL#AVLdelete(int)
     * @see trees.AVL#AVLinsert(int)
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
        // Asetetaan korkeudet oikein
        x.setHeight(higher(countHeight(x.getLeft()), countHeight(x.getRight())) + 1);
        y.setHeight(higher(countHeight(y.getLeft()), countHeight(y.getRight())) + 1);
        return y;
    }

    /**
     * AVLinsertin ja AVLdeleten yhteydessä käytettävä metodi, joka kiertää
     * puusolmuja vasemmalle tasapainon säilyttämiseksi ja asettaa paikkaansa
     * vaihtaneiden solmujen korkeudet oikeanlaisiksi.
     *
     * @see trees.AVL#AVLdelete(int)
     * @see trees.AVL#AVLinsert(int)
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
        // Asetetaan korkeudet oikein
        x.setHeight(higher(countHeight(x.getLeft()), countHeight(x.getRight())) + 1);
        y.setHeight(higher(countHeight(y.getLeft()), countHeight(y.getRight())) + 1);
        return y;
    }

    /**
     * AVLinsertin ja AVLdeleten yhteydessä käytettävä metodi, joka kiertää
     * puusolmuja oikealle ja vasemmalle tasapainon säilyttämiseksi.
     *
     * @see trees.AVL#AVLdelete(int)
     * @see trees.AVL#AVLinsert(int)
     * @see trees.AVL#rotateLeft(data_structures.Node)
     * @see trees.AVL#rotateRight(data_structures.Node)
     * @see data_structures.Node
     * @param x Solmu, jota kierretään.
     * @return Viite rotateLeftin palauttamaan kierretyn alipuun uuteen juureen.
     */
    private Node rotateRightLeft(Node x) {

        Node y = x.getRight();
        x.setRight(rotateRight(y));
        return rotateLeft(x);
    }

    /**
     * AVLinsertin ja AVLdeleten yhteydessä käytettävä metodi, joka kiertää
     * puusolmuja vasemmalle ja oikealle tasapainon säilyttämiseksi.
     *
     * @see trees.AVL#AVLdelete(int)
     * @see trees.AVL#AVLinsert(int)
     * @see trees.AVL#rotateLeft(data_structures.Node)
     * @see trees.AVL#rotateRight(data_structures.Node)
     * @see data_structures.Node
     * @param x Solmu, jota kierretään.
     * @return Viite rotateRightin palauttamaan kierretyn alipuun uuteen
     * juureen.
     */
    private Node rotateLeftRight(Node x) {

        Node y = x.getLeft();
        x.setLeft(rotateLeft(y));
        return rotateRight(x);
    }

    /**
     * Tutkii, löytyykö parametrina annetusta puusta parametrina annettua
     * avainta.
     *
     * @see data_structures.Node
     * @param x Puu, josta avainta etsitään.
     * @param key Avain, jota etsitään.
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
     * Tutkii NodeSearch-metodin avulla, löytyykö puusta parametrina annettu
     * arvo.
     *
     * @see trees.AVL#nodeSearch(data_structures.Node, int)
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
     * @see trees.AVL#getMax(data_structures.Node)
     * @return Puun suurin avain.
     */
    public int getMaxKey() {
        return getMax(this.root).getKey();
    }

    /**
     * Etsii puusta pienimmän avaimen getMin-metodin avulla.
     *
     * @see trees.AVL#getMin(data_structures.Node)
     * @return Puun pienin avain.
     */
    public int getMinKey() {
        return getMin(this.root).getKey();
    }

    /**
     * Laskee, kumpi annetuista parametreistä on suurempi ja palauttaa sen.
     *
     * @param a
     * @param b
     * @return Suurempi parametri.
     */
    private int higher(int a, int b) {

        if (a >= b) {
            return a;
        }
        return b;
    }
}

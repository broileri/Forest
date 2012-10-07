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
     * @return Puun juurisolmu.
     */
    public Node getRoot() {
        return this.root;
    }

    /**
     * Lisää puuhun insertin avulla solmun, jolla on parametrina annettu avain
     * ja sen jälkeen splayaa uuden solmun juureksi. Jos puussa on jo solmu,
     * jolla on parametrina annettu avain, lisäystä ei tehdä, mutta solmu
     * splayataan kuitenkin juureksi.
     *
     * @see data_structures.Node
     * @see trees.Splay#insert(int)
     * @param key Puuhun lisättävän solmun avain.
     */
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
            // Ei löytynyt, mennään vasemmalle tai palautetaan muka-vanhempi
            if (key < x.getKey()) {
                if (x.getLeft() != null) {
                    return nodeSearch(x.getLeft(), key);
                } else {
                    return x;
                }
                // Ei löytynyt, mennään oikealle tai palautetaan muka-vanhempi
            } else {
                if (x.getRight() != null) {
                    return nodeSearch(x.getRight(), key);
                } else {
                    return x;
                }
            }
        }
        return null; // Tähän kohtaan ei tässä toteutuksessa päästä koskaan
    }

    /**
     * Tutkii NodeSearch-metodin avulla, löytyykö puusta solmu, jolla on
     * parametrina annettu key. Jos löytyy, ko. solmu splayataan puun juureksi.
     *
     * @see trees.Splay#nodeSearch(data_structures.Node, int)
     * @see data_structures.Node
     * @param key Avain, jota etsitään.
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
     * Etsii puusta suurimman avaimen getMax-metodin avulla, minkä jälkeen
     * splayaa sen solmun puun juureksi, jolla suurin avain on.
     *
     * @see trees.Splay#getMax(data_structures.Node)
     * @see trees.Splay#getMax(data_structures.Node)
     * @return Puun suurin avain.
     */
    public int getMaxKey() {
        Node x = getMax(this.root);
        splay(x);
        return x.getKey();
    }

    /**
     * Etsii puusta pienimmän avaimen getMin-metodin avulla, minkä jälkeen
     * splayaa sen solmun puun juureksi, jolla pienin avain on.
     *
     * @see trees.Splay#getMin(data_structures.Node)
     * @see trees.Splay#getMin(data_structures.Node)
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
        return y;
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

    /**
     * Metodi vaihtaa parametrina annetun solmun puun juureksi left- ja
     * right-kiertojen avulla.
     *
     * @see trees.Splay#rotateLeft(data_structures.Node)
     * @see trees.Splay#rotateRight(data_structures.Node)
     * @see trees.Splay#fixParent(data_structures.Node)
     * @param x
     */
    private void splay(Node x) {

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

    /**
     * splayn käyttämä apumetodi, joka korjaa äskettäin käytetyn rotaten
     * palauttaman solmun vanhemman oikeanlaiseksi.
     *
     * @see data_structures.Node
     * @see trees.Splay#splay(data_structures.Node)
     * @see trees.Splay#rotateLeft(data_structures.Node)
     * @see trees.Splay#rotateRight(data_structures.Node)
     * @param x rotaten palauttama solmu.
     */
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

    /**
     * Poistaa puusta solmun, jolla on paremetrina annettu avain, ja splayaa sen
     * vanhemman juureen. Tai jos puussa ei ole poistettavaa solmua, metodi
     * splayaa juureen sen solmun, joka olisi ollut poistettavan solmun
     * vanhempi.
     *
     * @see data_structures.Node
     * @see trees.Splay#caseNoChildren(data_structures.Node)
     * @see trees.Splay#caseOneChild(data_structures.Node)
     * @see trees.Splay#caseTwoChildren(data_structures.Node)
     * @see trees.Splay#nodeSearch(data_structures.Node, int)
     * @see trees.Splay#splay(data_structures.Node)
     * @param key Poistettavan solmun avainarvo.
     */
    public void delete(int key) {

        Node found = nodeSearch(this.getRoot(), key);

        // Jos avain on puussa, poistetaan node ja splayataan sen vanhempi
        if (found.getKey() == key) {

            if (found.getLeft() == null && found.getRight() == null) {
                if (caseNoChildren(found)) { // Poistettavalla ei lapsia
                    return;
                }
            } else if (found.getLeft() == null || found.getRight() == null) {
                if (caseOneChild(found)) { // Poistettavalla yksi lapsi
                    return;
                }
            } else {
                caseTwoChildren(found); // Poistettavalla kaksi lasta
            }
            if (found.getParent() != null) {
                splay(found.getParent());
            }
        } else {
            splay(found); // Jos avainta ei ole puussa, splayataan se node, joka olisi ollut poistettavan vanhempi
        }
    }

    /**
     * Apumetodi deletelle. Hoitaa sellaisen solmun poistamisen, jolla ei ole
     * lainkaan lapsisolmuja.
     *
     * @see data_structures.Node
     * @see trees.Splay#delete(int)
     * @param deleteThis Node, joka poistetaan.
     * @return Tieto siitä, keskeytetäänkö deleten suorittaminen.
     */
    private boolean caseNoChildren(Node deleteThis) {

        Node parent = deleteThis.getParent();
        // Jos kyseessä on juuri, puusta tulee tyhjä
        if (parent == null) {
            this.root = null;
            return true;
        }
        if (deleteThis == parent.getLeft()) {
            parent.setLeft(null);
        } else {
            parent.setRight(null);
        }
        return false;
    }

    /**
     * Apumetodi deletelle. Hoitaa sellaisen solmun poistamisen, jolla on yksi
     * lapsisolmu.
     *
     * @see data_structures.Node
     * @see trees.Splay#delete(int)
     * @param deleteThis Node, joka poistetaan.
     * @return Tieto siitä, keskeytetäänkö deleten suorittaminen.
     */
    private boolean caseOneChild(Node deleteThis) {

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
            return true;
        }
        if (deleteThis == parent.getLeft()) {
            parent.setLeft(child);
        } else {
            parent.setRight(child);
        }

        return false;
    }

    /**
     * Apumetodi deletelle. Hoitaa sellaisen solmun poistamisen, jolla on kaksi
     * lapsisolmua.
     *
     * @see data_structures.Node
     * @see trees.Splay#delete(int)
     * @param deleteThis Node, joka poistetaan.
     * @return Tieto siitä, keskeytetäänkö deleten suorittaminen.
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
}
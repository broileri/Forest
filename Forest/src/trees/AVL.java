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
     * @return Puun juurisolmu.
     */
    public Node getRoot() {
        return this.root;
    }

    /**
     * Asettaa puulle juurisolmun.
     *
     * @see data_structures.Node     
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
        Node newNode = insert(key);
        if (newNode == null) {
            return;
        }
        Node p = newNode.getParent();

        while (p != null) {

            int lChildHeight = -1, rChildHeight = -1;
            
            if (p.getLeft() != null) {
                lChildHeight = p.getLeft().getHeight();
            }
            if (p.getRight() != null) {
                rChildHeight = p.getRight().getHeight();
            }
            
            // Jos vasen lapsi aiheuttaa epätasapainon...
            if (lChildHeight == rChildHeight + 2) {
                balanceLeftsGrandchild(p);
                return;
            }
            // Jos oikea lapsi aiheuttaa epätasapainon...
            if (rChildHeight == lChildHeight + 2) {
                balanceRightsGrandchild(p);
                return;
            }
            // Korjataan p:n korkeus ja jatketaan ylös juurta päin            
            lChildHeight = -1;
            rChildHeight = -1;
            if (p.getLeft() != null) {
                lChildHeight = p.getLeft().getHeight();
            }
            if (p.getRight() != null) {
                rChildHeight = p.getRight().getHeight();
            }
            p.setHeight(higher(lChildHeight, rChildHeight) + 1);
            p = p.getParent();
        }
    }
    
        /**
     * AVLinsertin käyttämä apumetodi, joka hoitaa varsinaisen
     * solmunlisäysoperaation.
     *
     * @see trees.AVL#AVLinsert(int key)
     * @see data_structures.Node
     * @param key Puuhun lisättävän alkion avain.
     * @return Viite puuhun juuri lisättyyn solmuun, tai null, jos key on jo puussa.
     */
    public Node insert(int key) {
     
        Node insertThis = new Node(key);
        
        // Koska uusi solmu tulee puun lehdeksi, se saa korkeudeksi 0
        insertThis.setHeight(0);
        
        // Tyhjä puu, uusi solmu laitetaan juureksi
        if (this.getRoot() == null) {
            this.setRoot(insertThis);            
            return insertThis;
        }           
        // Haetaan uuden lisättävälle solmulle vanhempi
        Node current = this.getRoot(), insertsParent = null;
        while (current != null) {
            insertsParent = current;
            if (current.getKey() == key) {
                return null; // Key on jo puussa - palautetaan tyhjä!
            }
            if (key < current.getKey()) {
                current = current.getLeft();
            }
            else {
                current = current.getRight();
            }  
        }
        insertThis.setParent(insertsParent);
        
        // Asetataan lisättävä solmu vanhemman oikeaksi tai vasemmaksi lapseksi ja päivitetään vanhemman korkeus
        if (key < insertsParent.getKey()) {
            insertsParent.setLeft(insertThis);
            if (insertsParent.getRight() == null) {
                insertsParent.setHeight(1);                
            }
        }
        else {
            insertsParent.setRight(insertThis);
            if (insertsParent.getLeft() == null) {
                insertsParent.setHeight(1);                 
            }
        }        
        return insertThis;        
    }   

    /**
     * AVLinsertin ja AVLdeleten yhteydessä käytettävä metodi, joka päivittää
     * p:n vanhemman lapsen sen jälkeen, kun p:ssä ollutepätasapaino on korjattu.
     *
     * @see trees.AVL#AVLinsert(int key)
     * @see trees.AVL#balanceLeftsGrandchild(data_structures.Node)
     * @see trees.AVL#balanceRightsGrandchild(data_structures.Node)
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

                } // Tasapaino kunnossa, jatketaan juurta päin
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
     * @see trees.AVL#fixParent(data_structures.Node, data_structures.Node,
     * data_structures.Node)
     * @see trees.AVL#AVLdelete(int)
     * @see trees.AVL#AVLinsert(int)
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
     * @see trees.AVL#fixParent(data_structures.Node, data_structures.Node,
     * data_structures.Node)
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
     * @see trees.AVL#nodeSearch(data_structures.Node, int) 
     * @see trees.AVL#caseNoChildren(data_structures.Node) 
     * @see trees.AVL#caseOneChild(data_structures.Node) 
     * @see trees.AVL#caseTwoChildren(data_structures.Node) 
     * @param key Puusta poistettava avain.
     * @return Viite puusta poistettuun solmuun tai null, jos poistettavaa
     * avainta ei löytynyt puusta.
     */
    private Node delete(int key) {

        Node deleted = nodeSearch(this.getRoot(), key);

        // Onko poistettavaa puussa
        if (deleted != null) {
            if (deleted.getLeft() == null && deleted.getRight() == null) {
                return caseNoChildren(deleted); // Poistettavalla ei lapsia                
            } else if (deleted.getLeft() == null || deleted.getRight() == null) {
                return caseOneChild(deleted); // Poistettavalla on yksi lapsi
            } else {
                return caseTwoChildren(deleted); // Poistettavalla on kaksi lasta
            }
        }        
        return null; // Jos poistettavaa ei ollut puussa, palautetaan null
    }

    /**
     * deleten apumetodi, joka poistaa lapsettoman solmun.
     * 
     * @see trees.AVL#delete(int) 
     * @see data_structures.Node
     * @param deleteThis Poistettava solmu.
     * @return 
     */
    private Node caseNoChildren(Node deleteThis) {

        Node parent = deleteThis.getParent();

        if (parent == null) {
            this.root = null; // Kyseessä on juuri
            return deleteThis;
        }
        if (deleteThis == parent.getLeft()) {
            parent.setLeft(null);
        } else {
            parent.setRight(null);
        }
        return deleteThis;
    }

    /**
     * deleten apumetodi, joka poistaa yksilapsisen solmun.
     * 
     * @see trees.AVL#delete(int) 
     * @see data_structures.Node
     * @param deleteThis Poistettava solmu.
     * @return 
     */
    private Node caseOneChild(Node deleteThis) {

        Node child, parent;
        if (deleteThis.getLeft() != null) {
            child = deleteThis.getLeft();
        } else {
            child = deleteThis.getRight();
        }
        parent = deleteThis.getParent();
        child.setParent(parent);


        if (parent == null) {
            this.root = child; // Poistettava on juuri
            return deleteThis;
        }
        if (deleteThis == parent.getLeft()) {
            parent.setLeft(child);
        } else {
            parent.setRight(child);
        }
        return deleteThis;
    }

    /**
     * deleten apumetodi, joka poistaa kaksilapsisen solmun.
     * 
     * @see trees.AVL#delete(int) 
     * @see data_structures.Node
     * @see trees.AVL#getMin(data_structures.Node) 
     * @param deleteThis Poistettava solmu.
     * @return 
     */
    private Node caseTwoChildren(Node deleteThis) {

        Node next = getMin(deleteThis.getRight()),
                child, parent;
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
        return next;
    }

    /**
     * AVLinsertin ja AVLdeleten yhteydessä käytettävä metodi, joka kiertää
     * puusolmuja oikealle tasapainon säilyttämiseksi.
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
     * puusolmuja vasemmalle tasapainon säilyttämiseksi.
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
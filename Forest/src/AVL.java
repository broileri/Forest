/*
 * AVL-puu
 */

public class AVL extends Walks {

    private Node root;

    // Konstruktori, puuhun tehdään tyhjä juuri
    public AVL() {
        this.root = null;
    }

    public Node getRoot() {
        return this.root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void AVLinsert(int key) {

        // Viedään uusi arvo puuhun
        Node newNode = insert(key), p = newNode.getParent(), subtree, parent;

        while (p != null) {

            // Aiheuttaako vasen lapsi epätasapainon?
            if (countHeight(p.getLeft()) == countHeight(p.getRight()) + 2) {

                parent = p.getParent();

                // Aiheutuuko epätasapaino vasemman lapsen vasemmasta vai oikeasta alipuusta?
                if (countHeight(p.getLeft().getLeft()) > countHeight(p.getLeft().getRight())) {
                    subtree = rotateRight(p);
                } else {
                    subtree = rotateLeftRight(p);
                }

                // Tutkitaan ja asetataan vanhempi oikein
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
                // Puu on taas tasapainossa; palataan.
                return;
            }

            // Aiheuttaako oikea lapsi epätasapainon?
            if (countHeight(p.getRight()) == countHeight(p.getLeft()) + 2) {

                parent = p.getParent();
                // Aiheutuuko epätasapaino oikean lapsen oikeasta vai vasemmastaalipuusta?
                if (countHeight(p.getRight().getRight()) > countHeight(p.getRight().getLeft())) {
                    subtree = rotateLeft(p);
                } else {
                    subtree = rotateRightLeft(p);
                }

                // Tutkitaan ja asetataan vanhempi oikein
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
                // Puu on taas tasapainossa; palataan.
                return;
            }

            // Asetetaan p:lle uusi pituus            
            p.setHeight(higher(countHeight(p.getLeft()), countHeight(p.getRight())) + 1);

            // Jatketaan ylös, juurta päin
            p = p.getParent();

        }
    }

    private int countHeight(Node x) {

        if (x == null) {
            return -1;
        }
        int leftHeight = countHeight(x.getLeft());
        int rightHeight = countHeight(x.getRight());
        return (higher(leftHeight, rightHeight) + 1);
    }

    private Node insert(int key) {

        Node newNode = new Node(key);

        // Jos tyhjä puu, luodaan juurisolmu
        if (this.root == null) {
            this.root = newNode;
            return newNode;
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

   
    public void AVLdelete(int key) {
        
        Node deleted = delete(key);

        // Jos poistettava oli puussa...
        if (deleted != null) {

            Node p = deleted.getParent(), parent, subtree;

            while (p != null) {

                // Aiheuttaako vasen lapsi epätasapainon?
                if (countHeight(p.getLeft()) == countHeight(p.getRight()) + 2) {

                    parent = p.getParent();

                    // Aiheutuuko epätasapaino vasemman lapsen vasemmasta vai oikeasta alipuusta?
                    if (countHeight(p.getLeft().getLeft()) > countHeight(p.getLeft().getRight())) {
                        subtree = rotateRight(p);
                    } else {
                        subtree = rotateLeftRight(p);
                    }

                    // Tutkitaan ja asetataan vanhempi oikein
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
                    // Puu on taas tasapainossa; palataan.
                    return;
                } // Aiheuttaako oikea lapsi epätasapainon?
                else if (countHeight(p.getRight()) == countHeight(p.getLeft()) + 2) {

                    parent = p.getParent();
                    // Aiheutuuko epätasapaino oikean lapsen oikeasta vai vasemmastaalipuusta?
                    if (countHeight(p.getRight().getRight()) > countHeight(p.getRight().getLeft())) {
                        subtree = rotateLeft(p);
                    } else {
                        subtree = rotateRightLeft(p);
                    }

                    // Tutkitaan ja asetataan vanhempi oikein
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
                    // Puu on taas tasapainossa; palataan.
                    return;

                    // Tasapaino kunnossa
                } else {
                    // Asetetaan p:lle uusi pituus
                    p.setHeight(higher(countHeight(p.getLeft()), countHeight(p.getRight())) + 1);

                    // Jatketaan ylös, juurta päin
                    p = p.getParent();

                }
            }
        }
    }

    public Node delete(int key) {

        Node deleted = nodeSearch(this.getRoot(), key), parent, child;

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

    public Node rotateRight(Node x) {


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

    public Node rotateLeft(Node x) {


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

    public Node rotateRightLeft(Node x) {

        Node y = x.getRight();
        x.setRight(rotateRight(y));
        return rotateLeft(x);
    }

    public Node rotateLeftRight(Node x) {

        Node y = x.getLeft();
        x.setLeft(rotateLeft(y));
        return rotateRight(x);
    }

    public Node nodeSearch(Node x, int key) {

        if (x == null || x.getKey() == key) {
            return x;
        }
        if (key < x.getKey()) {
            return nodeSearch(x.getLeft(), key);
        } else {
            return nodeSearch(x.getRight(), key);
        }
    }

    public boolean search(int key) {

        Node found = nodeSearch(this.getRoot(), key);
        if (found == null) {
            return false;
        }
        return true;
    }

    public Node getMin(Node x) {

        while (x.getLeft() != null) {
            x = x.getLeft();
        }
        return x;
    }

    public Node getMax(Node x) {

        while (x.getRight() != null) {
            x = x.getRight();
        }
        return x;
    }

    public int getMaxKey() {
        return getMax(this.root).getKey();
    }

    public int getMinKey() {
        return getMin(this.root).getKey();
    }

    public int higher(int a, int b) {

        if (a >= b) {
            return a;
        }
        return b;
    }
}

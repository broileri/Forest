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

    public Node nodeSearch(int key) {

        // Tyhjä puu
        if (this.root == null) {
            return null;
        }

        // Käsittelyssä oleva solmu
        Node current = this.root;

        while (true) {

            // Löytyi!
            if (key == current.getKey()) {
                return current;
            }

            // Etsittävä pienempi --> vasemmalle
            if (key < current.getKey()) {

                // Jos vasen lapsi ei ole tyhjä, siirrytään puussa alaspäin
                if (current.getLeft() != null) {
                    current = current.getLeft();
                } // Jos vasen lapsi on tyhjä, etsittävää arvoa ei löytynyt 
                else {
                    current.setLeft(new Node(key));
                    return null;
                }
            } // Etsittävä suurempi --> oikealle
            else {

                // Jos oikea lapsi ei ole tyhjä, siirrytään puussa alaspäin
                if (current.getRight() != null) {
                    current = current.getRight();
                } // Jos oikea lapsi on tyhjä, etsittävää arvoa ei löytynyt 
                else {
                    current.setRight(new Node(key));
                    return null;
                }
            }
        }
    }

    public boolean search(int key) {

        Node found = nodeSearch(key);
        if (found == null) {
            return false;
        }
        return true;
    }

    public void delete(int key) {

        Node deleted = nodeSearch(key);

        // Onko poistettavaa puussa
        if (deleted != null) {
            Node parent, child;
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
            }
        }
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
}

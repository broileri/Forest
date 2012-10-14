package data_structures;

/**
 * Erilaisten käyntien tulostukseen käytettävä luokka.
 * 
 * @author Broileri
 */

public class Walks {    


    /**
     * Puun alkioiden tulostaminen sisäjärjestyksessä.
     * 
     * @see data_structures.Node
     * @param tree Puu, jonka alkiot halutaan tulostaa.
     */
    public void printInOrder(Node tree) {

        if (tree != null) {
            printInOrder(tree.getLeft());
            System.out.print(tree.getKey() + " ");
            printInOrder(tree.getRight());
        }
    }

    /**
     * Puun alkioiden tulostaminen esijärjestyksessä.
     * 
     * @see data_structures.Node
     * @param tree Puu, jonka alkiot halutaan tulostaa.
     */
    public void printPreOrder(Node tree) {

        if (tree != null) {
            System.out.print(tree.getKey() + " ");
            printPreOrder(tree.getLeft());
            printPreOrder(tree.getRight());
        }
    }

    /**
     * Puun alkioiden tulostaminen jälkijärjestyksessä.
     * 
     * @see data_structures.Node
     * @param tree Puu, jonka alkiot halutaan tulostaa.
     */
    public void printPostOrder(Node tree) {

        if (tree != null) {
            printPostOrder(tree.getLeft());
            printPostOrder(tree.getRight());
            System.out.print(tree.getKey() + " ");
        }
    }

    /**
     * Puun alkioiden tulostaminen leveyssuuntaisessa järjestyksessä. Käyttää
     * apunaan LevelQueue-luokkaa
     * 
     * @see data_structures.Node
     * @see data_structures.LevelQueue
     * @param tree Puu, jonka alkiot halutaan tulostaa.
     */
    public void printLevelOrder(Node tree) {

        LevelQueue q = new LevelQueue();

        if (tree != null) {
            q.enqueue(tree);
        }

        while (!q.isEmpty()) {

            Node current = q.dequeue();
            System.out.print(current.getKey() + " ");
            if (current.getLeft() != null) {
                q.enqueue(current.getLeft());
            }
            if (current.getRight() != null) {
                q.enqueue(current.getRight());
            }
        }
    }
}
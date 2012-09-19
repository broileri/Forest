/*
 * Käynnit
 */

public class Walks {    


    public void printInOrder(Node tree) {

        if (tree != null) {
            printInOrder(tree.getLeft());
            System.out.print(tree.getKey() + " ");
            printInOrder(tree.getRight());
        }
    }

    public void printPreOrder(Node tree) {

        if (tree != null) {
            System.out.print(tree.getKey() + " ");
            printPreOrder(tree.getLeft());
            printPreOrder(tree.getRight());
        }
    }

    public void printPostOrder(Node tree) {

        if (tree != null) {
            printPostOrder(tree.getLeft());
            printPostOrder(tree.getRight());
            System.out.print(tree.getKey() + " ");
        }
    }

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


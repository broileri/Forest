/*
 * Solmuluokka
 */
public class Node {

    private Node parent, left, right;
    private int key, height;
  

    public Node(int key) {
        this.key = key;        
        this.parent = null;        
        this.left = null;
        this.right = null;
        this.height = -1;
    }
    
    public Node getParent() {
        return this.parent;
    }
   
    public Node getLeft() {
        return this.left;
    }
    
    public Node getRight() {
        return this.right;
    }    

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }
    
    public void setParent(Node parent) {
        this.parent = parent;
    } 

    public int getKey() {
        return this.key;
    }
    
    public void setKey(int key) {
        this.key = key;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }

}

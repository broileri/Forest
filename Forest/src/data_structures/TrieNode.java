package data_structures;

/**
 * Solmuluokka trie-puuta varten.
 *
 * @author Broileri
 */
public class TrieNode {

    private TrieNode[] nodes;
    private TrieNode parent;
    private boolean endsHere;
    private int key;

    /**
     * Jokaisella solmulla on solmulista ja tieto siitä, päättyykö
     * jokin puun arvoista tähän solmuun.
     *
     * @param key Solmulle annettava avainarvo.
     */
    public TrieNode(int key) { 
        this.nodes = new TrieNode[10];
        this.endsHere = false;
        this.parent = null;
        this.key = key;
    }
    
    public void setParent(TrieNode p) {
        this.parent = p;
    }
    
    public TrieNode getParent() {
        return this.parent;
    }

    public void setEnd(boolean b) {
        this.endsHere = b;
    }

    public boolean getEnd() {
        return this.endsHere;
    }

    public TrieNode[] getList() {
        return this.nodes;
    }
}

package data_structures;

/**
 * Solmuluokka trie-puuta varten.
 *
 * @author Broileri
 */
public class TrieNode {

    private TrieNode[] list, plus, minus;
    private TrieNode parent;
    private boolean endsHere;
    private int key;

    /**
     * Juurisolmulla on kaksisolmulistaa; yksi negatiivisille arvoille ja toinen
     * positiivisille arvoille.
     */
    public TrieNode() {
        this.plus = new TrieNode[10];
        this.minus = new TrieNode[10];
    }
    
    public TrieNode[] getPlus() {
        return this.plus;
    }

    public TrieNode[] getMinus() {
        return this.minus;
    }

    /**
     * Jokaisella solmulla juurisolmua lukuun ottamatta on solmulista,
     * avainarvo, viite vanhempaan ja tieto siitä, päättyykö jokin puun arvoista
     * tähän solmuun.
     *
     * @param key Solmulle annettava avainarvo.
     */
    public TrieNode(int key) {
        this.list = new TrieNode[10];
        this.endsHere = false;
        this.parent = null;
        this.key = key;
    }

    public int getKey() {
        return this.key;
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
    
    public void setList(TrieNode[] l) {
        this.list = l;
    }

    public TrieNode[] getList() {
        return this.list;
    }
}

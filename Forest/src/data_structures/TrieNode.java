package data_structures;

/**
 * Solmuluokka trie-puuta varten.
 *
 * @author Broileri
 */
public class TrieNode {

    private TrieNode[] nodes;
    private boolean endsHere;

    /**
     * Jokaisella solmulla on solmulista ja tieto siitä, päättyykö
     * jokin puun arvoista tähän solmuun.
     *
     * @param key Solmulle annettava avainarvo.
     */
    public TrieNode() { 
        this.nodes = new TrieNode[10];
        this.endsHere = false;
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

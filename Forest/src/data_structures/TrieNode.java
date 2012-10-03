package data_structures;

/**
 * Solmuluokka puita varten.
 * 
 * @author Broileri
 */
public class TrieNode {

    private  TrieNode parent, left, right;
    private int key;
    private  TrieNode[] nodes, rootList;    
    private boolean endsHere;

    /**
     * Jokaisella solmulla on korkeus, avainarvo sekä
     * viiteet solmun vanhempaan ja molempiin lapsiin.
     * 
     * @param key Solmulle annettava avainarvo.
     */
    public TrieNode(int key) {
        this.key = key;        
        this.parent = null;        
        this.left = null;
        this.right = null;
        this.nodes = new  TrieNode[10];        
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

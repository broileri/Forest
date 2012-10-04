
package trees;

import data_structures.TrieNode;

/**
 *
 * @author Broileri
 */
public class Trie {
    
    private TrieNode[] plus, minus;
    
    /**
     * Konstruktori "luo puun juuren". Juuri saa kaksi
     * 10 merkin mittaista TrieNode-taulukkoa - toisen positiivisia
     * ja toisen negatiivisia avaimia varten.
     */
    public Trie() {
        plus = new TrieNode[10];
        minus = new TrieNode[10];
    }
    
    /**
     * Metodi lisää puuhun parametrina annetun arvon. Jos arvo on 
     * jo puussa, puuhun ei tule muutoksia.
     * 
     * @see data_structures.TrieNode
     * @param key Lisättävän solmun avain.
     */
    public void insert(int key) {
        
        char[] number = Integer.toString(key).toCharArray();
        TrieNode[] list;
        int index;
        
        // Lisätäänkö luku minus- vai plus-puuhun
        if (number[0] == '-') {
            list = minus;
            index = 1;
        }
        else {
            list = plus;
            index = 0;
        }        
        // Lisätään ensimmäinen luku
        for (int i = index; i < number.length; i++) {            
            
            int place = Integer.parseInt(Character.toString(number[i]));
            
            // Jos listassa ei nodea numeron kohdalla, tehdään node
             if (list[place] == null) {
                 list[place] = new TrieNode();
             }
             // Jos luku loppuu tähän nodeen, merkitään node lopetusnodeksi
             if (i == number.length - 1) {
                 list[place].setEnd(true);
                 return;
             }
             list = list[place].getList();
        }       
    }
    
    /**
     * Metodi palauttaa true tai false sen mukaan, löytyykö
     * puusta haettavaa arvoa. Käyttää haussa apunaan search-metodia.
     * 
     * @see data_structures.TrieNode
     * @see trees.Trie#search(int) 
     * @param key Haettava arvo.
     * @return Tieto siitä, onko puussa haettavaa arvoa.
     */
    public boolean trieSearch(int key) {
        
        TrieNode[] found = search(key);
        if (found == null) {
            return false;
        }
        return true;
    }  
    
    /**
     * Apumetodi, joka hoitaa varsinaisen etsimisen.
     * Metodia käyttävät trieSearch ja delete.
     * 
     * @see trees.Trie#trieSearch(int) 
     * @see trees.Trie#delete(int) 
     * @param key Etsittävä arvo.
     * @return Viite siihen TrieNodeen, jonka TrieNode-listaan on tallennettu
     * parametrin viimeinen merkki.
     */
    private TrieNode[] search(int key) {
        
        char[] number = Integer.toString(key).toCharArray();
        TrieNode[] list;
        int index;
        
        // Key on negatiivinen
        if (key < 0) {
            list = this.minus;
            index = 1;
        }
        // Key on positiivinen
        else {
            list = this.plus;
            index = 0;
        }        
        // Etsitään...
        for (int i = index; i < number.length; i++) {
            
            int place = Integer.parseInt(Character.toString(number[i]));
            
            // Jos etsittävän luvun jonkin osan kohdalla on tyhjä node, lukua ei ole puussa
            if (list[place] == null) {
                return null;
            }
            // Jos ollaan päästy haettavan luvun viimeiseen osaan ja solmussa on tosi endsHere-arvo, key on puussa!
            if (i == number.length - 1) {
                if (list[place].getEnd()) {
                    //return list[place];
                    return list;
                }                
            }
            list = list[place].getList();
        }        
        return null;
    }
    
    /**
     * Metodi, joka poistaa puusta annetun arvon. Jos poistettavaa arvoa ei
     * ole puussa, puuhun ei tehdä muutoksia.
     * 
     * @see data_structures.TrieNode
     * @see trees.Trie#search(int) 
     * @param key 
     */
    public void delete(int key) {
        
        TrieNode[] found = search(key);
        
        // Key ei puussa, palataan
        if (found == null) {
            return;
        }
        
        // Napataan keyn viimeinen numero
        String value = Integer.toString(key);
        value = value.substring(value.length() - 1);
        int index = Integer.parseInt(value);      
        
        // Napataan keyn viimeinen node ja sillä oleva nodelista
        TrieNode lastNode = found[index];
        TrieNode[] finalList = lastNode.getList();
        
        // Käydään viimeisen keyn viimeisen noden lista läpi - jos se on tyhjä, koko noden voi poistaa
        for (int i = 0; i < finalList.length; i++) {
            
            // Poistettavan viimeisellä solmulla "alisolmuja", poistetaan vain päättymismerkki
            if (finalList[i] != null) {
                lastNode.setEnd(false);
                return;
            }
        }
        found[index] = null;       
    }   
    
    public void printDamnIt() {
        printValues(this.plus);
    }
    
    public void printValues(TrieNode[] list) {
        
        
        for (int i = 0; i < 10; i++) {
            
            if (list[i] != null) {
                System.out.print(i);
                printValues(list[i].getList());
                System.out.println("");
            }
        }
    }
    
    /*
     * printTrie(Node node, int offset) {
     print(node, offset)
     // here you can play with the order of the children
     for(Node child : node.getChildren()) {
          printTrie(child, offset + 2)
     } 
}

Start your recursion with:

printTrie(root, 0)

     */
}

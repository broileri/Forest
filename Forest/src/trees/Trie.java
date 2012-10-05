package trees;

import data_structures.TrieNode;

/**
 *
 * @author Broileri
 */
public class Trie {
   
    private TrieNode root;

    /**
     * Konstruktori "luo puun juuren". Juuri saa kaksi 10 merkin mittaista
     * TrieNode-taulukkoa - toisen positiivisia ja toisen negatiivisia avaimia
     * varten.
     */
    public Trie() {
        this.root = new TrieNode();
    }

    /**
     * Metodi lisää puuhun parametrina annetun arvon. Jos arvo on jo puussa,
     * puuhun ei tule muutoksia.
     *
     * @see data_structures.TrieNode
     * @param key Lisättävän solmun avain.
     */
    public void insert(int key) {

        char[] number = Integer.toString(key).toCharArray();
        TrieNode[] list;
        int index;
        TrieNode parent = this.root;

        // Lisätäänkö luku minus- vai plus-puuhun
        if (number[0] == '-') {
            list = this.root.getMinus();
            index = 1;
        } else {
            list = this.root.getPlus();
            index = 0;
        }
        // Lisätään ensimmäinen luku
        for (int i = index; i < number.length; i++) {

            int place = Integer.parseInt(Character.toString(number[i]));

            // Jos listassa ei nodea numeron kohdalla, tehdään node
            if (list[place] == null) {
                list[place] = new TrieNode(place);
            }
            list[place].setParent(parent);
            // Jos luku loppuu tähän nodeen, merkitään node lopetusnodeksi
            if (i == number.length - 1) {
                list[place].setEnd(true);
                return;
            }
            parent = list[place];
            list = list[place].getList();
        }
    }

    /**
     * Metodi palauttaa true tai false sen mukaan, löytyykö puusta haettavaa
     * arvoa. Käyttää haussa apunaan search-metodia.
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
     * Apumetodi, joka hoitaa varsinaisen etsimisen. Metodia käyttävät
     * trieSearch ja delete.
     *
     * @see trees.Trie#trieSearch(int)
     * @see trees.Trie#delete(int)
     * @param key Etsittävä arvo.
     * @return Viite siihen TrieNodelistaan, johon on tallennettu parametrin
     * viimeinen merkki.
     */
    private TrieNode[] search(int key) {

        char[] number = Integer.toString(key).toCharArray();
        TrieNode[] list;
        int index;

        // Key on negatiivinen
        if (key < 0) {
            list = this.root.getMinus();
            index = 1;
        } // Key on positiivinen
        else {
            list = this.root.getPlus();
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
     * Metodi, joka poistaa puusta annetun arvon. Jos poistettavaa arvoa ei ole
     * puussa, puuhun ei tehdä muutoksia.
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

        // Napataan keyn viimeinen numero (esim. 2634:sta se olisi 4)
        String value = Integer.toString(key);
        value = value.substring(value.length() - 1);
        int index = Integer.parseInt(value);

        // Napataan keyn viimeisellä solmulla oleva solmulista (siis Noden, jonka key on 4, solmulista)
        TrieNode lastNode = found[index];
        TrieNode[] finalList = lastNode.getList();
        boolean deleteThis = true;

        // Käydään keyn viimeisen solmun lista läpi (siis käydään sen solmun, jonka key on 4, solmulista läpi)
        for (int i = 0; i < finalList.length; i++) {

            // Solmulla "alisolmuja", poistetaan vain päättymismerkki (siis 4:n solmulista ei ole tyhjä)
            if (finalList[i] != null) {
                lastNode.setEnd(false);
                deleteThis = false;
                break;
            }
        }

        // Poistetaan koko solmu, jos se oli tyhjä, ja lähdetään puussa ylöspäin (siis poistetaan solmu, jonka key on 4)
        if (deleteThis) {
            TrieNode p = found[index].getParent(), parent;
            // Ollaan juuressa
            if (p.getParent() == null) {
                deleteAtRoot(index);
                return;
            }
            p.getList()[index] = null;
            index = p.getKey();

            // Poistetaan solmun vanhemmat, jos ne eivät ole muiden puussa olevien lukujen osia
            while (true) {
                // Joku luku päättyy käsiteltävään Nodeen, joten sitä ei voida poistaa - palataan
                if (p.getEnd()) {
                    return;
                }
                for (int i = 0; i < 10; i++) {

                    if (p.getList()[i] != null) {
                        return; // Solmu ei ole tyhjä, palataan
                    }
                }
                parent = p.getParent();
                if (parent.getMinus() == null) {
                    parent.getList()[index] = null;
                    p = parent;
                } // Ollaan juuressa
                else {
                    deleteAtRoot(index);
                    return;
                }
            }
        }
    }

    private void deleteAtRoot(int key) {

        if (key < 0) {
            this.root.getMinus()[key] = null;
        } else {
            this.root.getPlus()[key] = null;
        }
    }  
}

package data_structures;

/**
 * Leveyssuuntaisessa puun käynnissä käytettävä jono.
 * 
 * @see data_structures.Walks#printLevelOrder(data_structures.Node)  * 
 * @author Broileri
 */
public class LevelQueue {
    
    private QueueNode head, tail;
    
    /**
     * Konstruktori asettaa jonon head- ja tail-muuttujien arvoksi null.
     */
    public LevelQueue() {
        
        this.head = null;
        this.tail = null;
    }
    
    /**
     * QueueNode-luokan alkioita voidaan laittaa LevelQueuehen.
     * QueueNodet sisältävät viitteen puusolmuun ja seuraavaan
     * QueueNode-solmuun.
     * 
     * @see data_structures.LevelQueue
     */
    private class QueueNode {
        
        private QueueNode next;
        private Node node;
        
        /**
         * Konstruktori asettaa solmun next-arvoksi null ja Node-arvoksi annetun
         * parametrin.
         * 
         * @param x Jonosolmuun tallennettava puusolmu.
         */
        private QueueNode(Node x) {
            
            this.next = null;
            this.node = x;
        }
    }   
    
    /**
     * Metodi lisää annetun puusolmun LevelQueue-jonon loppuun.
     * 
     * @param x Jonoon lisättävä puusolmu.
     */
    public void enqueue(Node x) {
        
        QueueNode y = new QueueNode(x);
        
        // Jos jono on tyhjä, uusin jäsen asetetaan jonon ensimmäiseksi
        if (this.head == null) {
            this.head = y;
            this.tail = y;
            return;
        }        
        // Muuten uusin jonosolmu asetetaan jonon perälle
        this.tail.next = y;
        this.tail = y;        
    }
    
    /**
     * Poistaa ja palauttaa LevelQueue-jonon ensimmäisen jäsenen
     * 
     * @return LevelQueue-jonossa ensimmäisenä oleva puusolmu.
     */
    public Node dequeue() {
        
        Node first = this.head.node;        
        this.head = this.head.next;
        
        // Jos jono on tyhjä, myös tailille asetetaan null-arvo.
        if (this.head == null) {
            this.tail = null;
        }        
        return first;        
    }
    
    /**
     * Tarkastaa, onko LevelQueue-jono tyhjä.
     * 
     * @return Tieto siitä, onko jono tyhjä.
     */    
    public boolean isEmpty() {
        
        if (this.head == null) {
        return true;
        }
        return false;
    }
}

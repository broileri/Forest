/*
 * Leveyssuuntaisessa puun käynnissä käytettävä jono 
 */


public class LevelQueue {
    
    private QueueNode head, tail;
    
    public LevelQueue() {
        
        this.head = null;
        this.tail = null;
    }
    
    /* 
     * Jonosolmuluokka
     */
    static class QueueNode {
        
        private QueueNode next;
        private Node node;
        
        public QueueNode(Node x) {
            
            this.next = null;
            this.node = x;
        }
    }   
    
    /*
     * Asettaa jonon perään uuden jäsenen
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
        tail.next = y;
        tail = y;        
    }
    
    /*
     * Poistaa ja palauttaa jonon ensimmäisen jäsenen
     */
    public Node dequeue() {
        
        Node first = this.head.node;
        
        this.head = this.head.next;
        
        if (this.head == null) {
            this.tail = null;
        }        
        return first;        
    }
    
    /*
     * Tarkastaa, onko jono tyhjä
     */    
    public boolean isEmpty() {
        
        if (this.head == null) {
        return true;
        }
        return false;
    }
}

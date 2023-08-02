package Homework.Homework2;

public class LinkedList {
    private Node head;
    
    private static class Node {
        private int data;
        private Node next;
        
        public Node(int data) {
            this.data = data;
            next = null;
        }
    }
    
    public void reverse() {
        Node prev = null;
        Node current = head;
        Node next = null;
        
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        
        head = prev;
    }
    
    
}

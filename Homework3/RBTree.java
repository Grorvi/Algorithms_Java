package Homework.Homework3;

public class RBTree {
    private Node root;
    
    private static class Node {
        private int data;
        private Node parent;
        private Node left;
        private Node right;
        private boolean isRed;
        
        public Node(int data) {
            this.data = data;
            parent = null;
            left = null;
            right = null;
            isRed = true;
        }
    }
    
    public void insert(int data) {
        Node newNode = new Node(data);
        
        if (root == null) {
            root = newNode;
            root.isRed = false;
        } else {
            Node current = root;
            Node parent;
            
            while (true) {
                parent = current;
                
                if (data < current.data) {
                    current = current.left;
                    if (current == null) {
                        parent.left = newNode;
                        newNode.parent = parent;
                        break;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = newNode;
                        newNode.parent = parent;
                        break;
                    }
                }
            }
            
            balanceAfterInsert(newNode);
        }
    }
    
    private void balanceAfterInsert(Node node) {
        while (node.parent != null && node.parent.isRed) {
            if (node.parent == node.parent.parent.left) {
                Node uncle = node.parent.parent.right;
                if (uncle != null && uncle.isRed) {
                    node.parent.isRed = false;
                    uncle.isRed = false;
                    node.parent.parent.isRed = true;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.right) {
                        node = node.parent;
                        rotateLeft(node);
                    }
                    node.parent.isRed = false;
                    node.parent.parent.isRed = true;
                    rotateRight(node.parent.parent);
                }
            } else {
                Node uncle = node.parent.parent.left;
                if (uncle != null && uncle.isRed) {
                    node.parent.isRed = false;
                    uncle.isRed = false;
                    node.parent.parent.isRed = true;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) {
                        node = node.parent;
                        rotateRight(node);
                    }
                    node.parent.isRed = false;
                    node.parent.parent.isRed = true;
                    rotateLeft(node.parent.parent);
                }
            }
        }
        
        root.isRed = false;
    }
    
    // остальные методы дерева...
    
    // Методы для балансировки
    private void rotateLeft(Node node) {
        Node pivot = node.right;
        node.right = pivot.left;
        
        if (pivot.left != null) {
            pivot.left.parent = node;
        }
        
        pivot.parent = node.parent;
        
        if (node.parent == null) {
            root = pivot;
        } else if (node == node.parent.left) {
            node.parent.left = pivot;
        } else {
            node.parent.right = pivot;
        }
        
        pivot.left = node;
        node.parent = pivot;
    }
    
    private void rotateRight(Node node) {
        Node pivot = node.left;
        node.left = pivot.right;
        
        if (pivot.right != null) {
            pivot.right.parent = node;
        }
        
        pivot.parent = node.parent;
        
        if (node.parent == null) {
            root = pivot;
        } else if (node == node.parent.right) {
            node.parent.right = pivot;
        } else {
            node.parent.left = pivot;
        }
        
        pivot.right = node;
        node.parent = pivot;
    }
    
}

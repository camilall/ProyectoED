package Data;
import java.math.*;

        
public class capacidadesComedoresAVL {
// Create node

    class Node {

        String nombreComedor;
        int item, height;
        Node left, right;

        Node(int d, String nombreComedor) {
            this.nombreComedor = nombreComedor;
            item = d;
            height = 1;
        }
    }
    Node root;

    capacidadesComedoresAVL() { //Constructor
        root = null;
    }

    int height(Node N) {
        if (N == null) {
            return 0;
        }
        return N.height;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;
        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
        return y;
    }

    // Get balance factor of a node
    int getBalanceFactor(Node N) {
        if (N == null) {
            return 0;
        }
        return height(N.left) - height(N.right);
    }

    // Insert a node
    Node insertNode(String nombre, Node node, int item) {

        // Find the position and insert the node
        if (node == null) {
            return (new Node(item, nombre));
        }
        if (item < node.item) {
            node.left = insertNode(nombre, node.left, item);
        } else if (item > node.item) {
            node.right = insertNode(nombre, node.right, item);
        } else {
            return node;
        }

        // Update the balance factor of each node
        // And, balance the tree
        node.height = 1 + max(height(node.left), height(node.right));
        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1) {
            if (item < node.left.item) {
                return rightRotate(node);
            } else if (item > node.left.item) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }
        if (balanceFactor < -1) {
            if (item > node.right.item) {
                return leftRotate(node);
            } else if (item < node.right.item) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }
        return node;
    }

    Node nodeWithMimumValue(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // Delete a node
    Node deleteNode(Node root, int item) {

        // Find the node to be deleted and remove it
        if (root == null) {
            return root;
        }
        if (item < root.item) {
            root.left = deleteNode(root.left, item);
        } else if (item > root.item) {
            root.right = deleteNode(root.right, item);
        } else {
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left) {
                    temp = root.right;
                } else {
                    temp = root.left;
                }
                if (temp == null) {
                    temp = root;
                    root = null;
                } else {
                    root = temp;
                }
            } else {
                Node temp = nodeWithMimumValue(root.right);
                root.item = temp.item;
                root.right = deleteNode(root.right, temp.item);
            }
        }
        if (root == null) {
            return root;
        }

        // Update the balance factor of each node and balance the tree
        root.height = max(height(root.left), height(root.right)) + 1;
        int balanceFactor = getBalanceFactor(root);
        if (balanceFactor > 1) {
            if (getBalanceFactor(root.left) >= 0) {
                return rightRotate(root);
            } else {
                root.left = leftRotate(root.left);
                return rightRotate(root);
            }
        }
        if (balanceFactor < -1) {
            if (getBalanceFactor(root.right) <= 0) {
                return leftRotate(root);
            } else {
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }
        }
        return root;
    }

    void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.item + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    // Print the tree
    private void printTree(Node currPtr, String indent, boolean last) {
        if (currPtr != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }
            System.out.println(currPtr.item + " " + currPtr.nombreComedor);
            printTree(currPtr.left, indent, false);
            printTree(currPtr.right, indent, true);
        }
    }

    private void printTree2(Node currPtr, String indent, boolean last) {
        if (currPtr != null) {
        if (currPtr.left != null) {
            printTree2(currPtr.left, indent, false);
        }
        System.out.println(currPtr.item + " " + currPtr.nombreComedor);
        if (currPtr.right != null) {
            printTree2(currPtr.right, indent, false);
        }
        }
    }

    public static void main(String[] args) {
        capacidadesComedoresAVL tree = new capacidadesComedoresAVL();
        generadorNodos(tree,100);
        tree.printTree2(tree.root, "", true);
        boradorNodos(tree,100);
        
        
    }
    
    public static void generadorNodos(capacidadesComedoresAVL  a, int tamanio){
        for (int i =0; i < tamanio; i++){
          a.root = a.root = a.insertNode("Prueba"+i, a.root, i);
        }
    }
    
    public static void boradorNodos(capacidadesComedoresAVL  a, int tamanio){
        for (int i =0; i < tamanio; i++){
          a.root = a.root = a.deleteNode( a.root, i);
        }
    }
}

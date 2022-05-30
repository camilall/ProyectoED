package Data;

class capacidadesComedores { 

    class Node { 
        String nombreComedor;
        int capacidad; 
        Node left;
        Node right; 
   
        public Node(String nombre, int cap){ 
            nombreComedor = nombre;
            capacidad = cap; 
            left = right = null; 
        } 
    } 
    Node root; 
    
    capacidadesComedores(){ //Constructor
        root = null; 
    } 
    //Métodos BST   
    //Inserción
    void insertComedor(String nombre, int capacidad)  { 
        root = insertRecursive(root,nombre, capacidad); 
    } 
    Node insertRecursive(Node root, String nombre, int capacidad) { 
        if (root == null) { 
            root = new Node(nombre, capacidad); 
            return root; 
        } 
        if (capacidad < root.capacidad)   
            root.left = insertRecursive(root.left, nombre, capacidad); 
        else if (capacidad > root.capacidad)    
            root.right = insertRecursive(root.right, nombre, capacidad); 
        return root; 
    } 
    /*****************************************/
    //Eliminación ****************************/
    void deleteComedor(String nombreComedor) { 
        root = deleteRecursive(root, nombreComedor); 
    } 
    Node deleteRecursive(Node root, String nombre)  { 
        if (root == null){
            return root; 
            }
        if (nombre != root.nombreComedor)
            root.left = deleteRecursive(root.left, nombre); 
        else if (nombre != root.nombreComedor) 
            root.right = deleteRecursive(root.right, nombre);
        else  { 
            if (root.left == null) 
                return root.right; 
            else if (root.right == null) 
                return root.left; 
        } 
        return root;         
    }
    /**************************************/
    
    //Mínimo valor ***********************/
     int minimum(Node root)  { 
        //initially minval = root
        int valorMinimo = root.capacidad; 
        //find minval
        while (root.left != null)  { 
            valorMinimo = root.left.capacidad; 
            root = root.left; 
        } 
        return valorMinimo; 
    }  
    /*************************************/
    
    //Impresión**************************/    
    void printInorder() { 
        inorderRecursive(root); 
    } 
    void inorderRecursive(Node root) { 
        if (root != null) { 
            inorderRecursive(root.left); 
            System.out.print(root.nombreComedor + ": " + root.capacidad + " "); 
            inorderRecursive(root.right); 
        } 
    } 
    /*****************************/
    
    //Búsqueda***************************/
    boolean search(String nombreComedor)  { 
        root = searchRecursive(root, nombreComedor); 
        if (root!= null){
            System.out.println(root.nombreComedor + ": " + root.capacidad);
            return true;
            }
        else{
            return false;
            }
    }
    Node searchRecursive(Node root, String nombre)  { 
        if (root==null || root.nombreComedor==nombre) 
            return root; 
        if (root.nombreComedor != nombre) 
            return searchRecursive(root.left, nombre); 
        return searchRecursive(root.right, nombre); 
    } 
    /************************************/

    public static void main(String[] args)  { 
        /*
                          Humanas:45 
                        /             \ 
           Agrarias:10                  Central90 
          /            \                   /       \
        FEM:7     Hemeroteca:12       Ancizar:50
        */
        capacidadesComedores bst = new capacidadesComedores(); 
        
        bst.insertComedor("Humanas", 45); 
        bst.insertComedor("Agrarias", 10); 
        bst.insertComedor("Fem", 7); 
        bst.insertComedor("Hemeroteca",12); 
        bst.insertComedor("Central",90); 
        bst.insertComedor("Ancízar",50); 
        bst.printInorder(); 
        System.out.println();
        
       
     } 
}

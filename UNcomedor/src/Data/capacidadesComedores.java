package Data;

import java.util.Scanner;
import java.io.*;

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
    void deleteComedor(int key) {
        root = deleteRecursive(root, key);
    }
    Node deleteRecursive(Node root, int key)  {
        if (root == null){
            return root;
        }
        if (key < root.capacidad)
            root.left = deleteRecursive(root.left, key);
        else if (key > root.capacidad)
            root.right = deleteRecursive(root.right, key);

        else  {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.capacidad = minimum(root.right);
            root.right = deleteRecursive(root.right, root.capacidad);
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
    boolean search(int key)  {
        root = searchRecursive(root, key);
        if (root!= null) {
            System.out.println(root.nombreComedor + ": " + root.capacidad);
            return true;
        }
        else {
            return false;
        }
    }
    Node searchRecursive(Node root, int key)  {
        if (root==null || root.capacidad==key)
            return root;
        if (root.capacidad > key)
            return searchRecursive(root.left, key);
        return searchRecursive(root.right, key);
    }
    /************************************/


}

class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
            capacidadesComedores bst = new capacidadesComedores();
            boolean proceso = true;
            while (proceso) {

                System.out.println("");
                menu();
                //menu
                int seleccion = selector();
                if (seleccion != 0) {
                    switch (seleccion) {
                        case 1:
                            //Inserción por archivo .csv
                            Scanner sc = new Scanner(new File("C:\\Users\\Familia Rojas\\Desktop\\Diezmildatos.csv"));
                            int counter = 0;
                            System.out.println("Ingresando comedores ...");
                            long inicioInsert = System.currentTimeMillis();
                            while (sc.hasNext()) {
                                counter++;
                                String[] cadena = sc.next().split(",");
                                int capacidad = Integer.parseInt(cadena[0]);
                                String nombreComedor = cadena[1];
                                //Inserción en la lista
                                bst.insertComedor(nombreComedor, capacidad);
                            }
                            sc.close();
                            System.out.println("Los datos han sido ingresados");
                            //System.out.println(counter);
                            long finInsert = System.currentTimeMillis();
                            long tiempoInsert = (finInsert - inicioInsert);
                            System.out.println("Tiempo de ejecución: " + tiempoInsert + " milisegundos");
                            break;

                        //Inserción por consola
                    /*
                        scanner.nextLine();
                        boolean finish = false;
                        System.out.println("Ingrese los comedores");
                        while (finish != true) {
                            String cadena = scanner.nextLine();
                            if (cadena.charAt(0) != '-') {// entrar un - para terminar el ingreso de usuarios
                                String[] dataUser = cadena.split(",");
                                int capacidad = Integer.parseInt(dataUser[0]);
                                String nombreComedor = dataUser[1];
                                //Inserción en la lista
                                Comedores.insert(new Comedor(capacidad, nombreComedor));
                                System.out.println("");
                            } else {
                                System.out.println("Los datos han sido ingresados");
                                break;
                            }
                        }
                        break;*/


                        case 2:

                            scanner.nextLine();
                            System.out.println("Ingrese la capacidad del comedor que quiere buscar");
                            int capacidadBuscada = scanner.nextInt();
                            long inicioSearch = System.currentTimeMillis();
                            boolean existencia = bst.search (capacidadBuscada);
                            System.out.println(existencia);
                            long finSearch = System.currentTimeMillis();
                            long tiempoSearch = (finSearch - inicioSearch);
                            System.out.println("Tiempo de ejecución: " + tiempoSearch + " milisegundos");
                            break;

                        case 3:
                            scanner.nextLine();
                            System.out.println("Ingrese la capacidad del comedor que desea eliminar");
                            int capacidadEliminada = scanner.nextInt();
                            long inicioDelete = System.currentTimeMillis();
                            bst.deleteComedor(capacidadEliminada);
                            long finDelete = System.currentTimeMillis();
                            long tiempoDelete = (finDelete-inicioDelete);
                            System.out.println("Tiempo de ejecución: " + tiempoDelete + " milisegundos");
                            break;


                        case 4:
                            bst.printInorder();
                            break;
                        case 5:
                            System.out.println("Terminado");
                            proceso = false;
                            System.exit(0);
                            break;


                        default:
                            System.out.println("Ingrese una opción válida");
                            break;


                    }
                }
            }
            
        }
        public static void menu () {
            System.out.println("\nAdminstración BSTComedores :D \nIngrese el número de la opción que desea \n1. Ingresar comedores\n2. Buscar capacidad\n3. Eliminar comedor\n4. Mostrar comedores\n5. Salir");
        }

        public static int selector () {
            int select = scanner.nextInt();
            return select;
        }
    }
 
 

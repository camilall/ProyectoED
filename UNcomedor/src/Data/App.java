package Data;

import java.util.Scanner;
public class App {
    
    static ListaUsuarios listaUsuarios = new ListaUsuarios();
    static Pila pilaUsuarios = new Pila();
    static Cola colaUsuarios = new Cola();
    static ListaEnlazada listasincola = new ListaEnlazada();
    static Array arrayUsuarios = new Array();
    static Usuario[] array = new Usuario[10000000];
    static PriorityQueueClass pq = new PriorityQueueClass(array);
    static Scanner scanner= new Scanner(System.in);
    static Cola colacomedor1 = new Cola();
    static Cola colacomedor2 = new Cola();
    static Cola colacomedor3 = new Cola();
    static Cola colacomedor4 = new Cola();
    static Menu menuLunes = new Menu("Muu","cuchuco","mora","pollo","ensalada", "yuca");
    static Comedor central = new Comedor(100, "Comedor central", menuLunes, colacomedor1);
    static Comedor economia = new Comedor(100, "Comedor economia", menuLunes, colacomedor2);
    static Comedor yu_takeuchi = new Comedor(100, "Comedor Yu Takeuchi", menuLunes, colacomedor3);
    static Comedor agrarias = new Comedor(100, "Comedor Ciencias Agrarias", menuLunes, colacomedor4);
    
    public static void main(String[] args) throws Exception {
        int opcion=0;
        do{
            System.out.println("Escoja una opcion: \n 1. Entrar como administrador\n 2. Crear usuarios\n 3. Buscar usuario\n 4. Pedir turno\n 5. Pruebas\n 6. Pruebas cp \n 7. Salir");

            opcion = scanner.nextInt();
            switch(opcion){
            
            
                case 1:
                    System.out.println("Escoja un comedor:\n1. Comedor central \n2. Economia\n3. Yu takeuchi \n4. Ciencias Agrarias");//para ver la cola que hay en x comedor
                    int numComedor = scanner.nextInt();
                    central.colaComedor.imprimirCola();
                    break;
                case 2:
                    System.out.println("Para ingresar los datos del nuevo usuario, utilice el siguiente formato:");
                    System.out.println("Nombre,id, apoyo, usuario");
                    System.out.println("Ingrese los datos del usuario utlizando las comas:");
                    CrearUsuarios();
                    break;
                case 3:
                    System.out.println(central);

                    break;  
                case 4:
                    break;
                case 5:
                    System.out.println("Con que estructura desea crear los usuarios:\n1. Listas enlazadas\n2. Pilas con referencias\n3. Colas con referencias\n4. Arreglos dinamicos\n5. Lista enlazada sin cola");
                    opcion2 = scanner.nextInt();
                    CrearUsuarios(opcion2);
                    break;
                case 6:
                    crearUsuariosCP();
                    System.out.println("Que desea hacer con la cola de prioridades\n1. eliminar\n2. Organizar la cola de prioridades")  ;
                    opcion2 = scanner.nextInt();
                    if(opcion2==1){
                        opcion2 = scanner.nextInt();
                        long Ti = System.currentTimeMillis(); 
                        pq.remove(opcion2);
                        
                        long Tf = System.currentTimeMillis(); 
                        System.out.println("Tiempo de eliminacion: " + (Tf-Ti));
                    }else{
                        long Ti = System.currentTimeMillis(); 
                        pq.sort();
                        
                        long Tf = System.currentTimeMillis(); 
                        System.out.println("Tiempo de eliminacion: " + (Tf-Ti));
                    }


            }        
        }while(opcion != 6);
        long fin = System.currentTimeMillis();
        double tiempo =(double) (fin-inicio);
        System.out.println(tiempo);     
    }
    public static void CrearUsuarios(){
        boolean finish = false;
        scanner.nextLine();
        while(!finish){
            
            String cadena= scanner.nextLine();
            if(cadena.charAt(0)!='-'){// entrar un - para terminar el ingreso de usuarios
                
                String[] dataUser= cadena.split(",");
                String nombre =dataUser[0];
                String id = dataUser[1];
                String apoyo = dataUser[2];
                String usuario = dataUser[3];
                String comedor = dataUser[4];
                int opcion = Integer.valueOf(comedor);
                switch(opcion){
                    case 1:
                        Comedor comedor_us1 = central;
                        Usuario user1 = new Usuario(nombre, id, apoyo, usuario, comedor_us1);
                        crearTurno(user1);
                        break;
                    case 2:
                        Comedor comedor_us2 = economia;
                        Usuario user2 = new Usuario(nombre, id, apoyo, usuario, comedor_us2);
                        crearTurno(user2);
                        break;
                    case 3:
                        Comedor comedor_us3 = yu_takeuchi;
                        Usuario user3 = new Usuario(nombre, id, apoyo, usuario, comedor_us3);
                        crearTurno(user3);
                        break;
                    case 4:
                        Comedor comedor_us4 = agrarias;
                        Usuario user4 = new Usuario(nombre, id, apoyo, usuario, comedor_us4);
                        crearTurno(user4);
                        break;

                }
                //Usuario user = new Usuario(nombre, id, apoyo, usuario, comedor2);
                //crearTurno(user);
                
            }else{
                System.out.println("END");
                finish = true;
            }
        }
        central.colaComedor.imprimirCola();
        yu_takeuchi.colaComedor.imprimirCola();
        economia.colaComedor.imprimirCola();
        agrarias.colaComedor.imprimirCola();

    }
    public static void crearTurno(Usuario user) {
        Comedor comedor = user.getComedor();
        int numTurno=comedor.getTurno();
        numTurno++;
        Turno turno = new Turno(numTurno,comedor,user,menuLunes);
        comedor.setTurno(numTurno);
        //System.out.println(turno.toString());
        comedor.getcolaComedor().enque(turno);
        //System.out.println(user.getNombre()+" se encolo y tiene el turno "+numTurno);

      
    }
    public static void crearUsuariosCP(){
        System.out.println("Para ingresar los datos del nuevo usuario, utilice el siguiente formato: Nombre,id, apoyo, usuario");
        System.out.println("Ingrese los datos del usuario utlizando las comas:");
       int x=0;
        File archivo;
        FileReader fr;
        BufferedReader br;
        try {
            archivo = new File("/Users/nataliaquiroga/Desktop/Proyecto estructuras/ProyectoED/UNcomedor/data/Prueba1M.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String line;
            long Tinicio = System.currentTimeMillis();
            while ((line=br.readLine()) != null){
                x++;
                String[] dataUser= line.split(",");
                String nombre =dataUser[1];
                String id = dataUser[0].replace(" ","");;
                String apoyo = dataUser[4];
                String usuario = dataUser[3];
                String comedor = dataUser[5];
                int opcion = Integer.valueOf(comedor);
                Usuario user;
                if(opcion==1){
                    Comedor comedor_us1 = central;
                    user= new Usuario(nombre, id, apoyo, usuario, comedor_us1);
                }else if(opcion==2){
                    Comedor comedor_us2 = economia;
                    user = new Usuario(nombre, id, apoyo, usuario, comedor_us2);
                }else if(opcion==3){
                    Comedor comedor_us3 = yu_takeuchi;
                    user = new Usuario(nombre, id, apoyo, usuario, comedor_us3);
                }else{
                    Comedor comedor_us4 = agrarias;
                    user = new Usuario(nombre, id, apoyo, usuario, comedor_us4);
                }
                pq.insert(user);
            } 
            long Tfinal = System.currentTimeMillis(); 
            System.out.print("Priority Queue = ");
            
            System.out.println("Tiempo de ejecucuion: "+ (Tfinal-Tinicio));
            //pq.print();
            Tfinal = System.currentTimeMillis();
            System.out.println("Tiempo de ejecucuion: "+ (Tfinal-Tinicio));
        } catch (Exception e) {
            System.out.println("Error: "+x+e.getMessage());
        }
    
        
        }
    
}

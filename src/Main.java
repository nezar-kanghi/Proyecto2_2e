import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal del Sistema de Gestión de Conciertos.
 * Contiene el menú principal y todos los submenús.
 */
public class Main {

    static ArtistaDAO artistaDAO = new ArtistaDAO();
    static ConciertoDAO conciertoDAO = new ConciertoDAO();
    static EntradaDAO entradaDAO = new EntradaDAO();

    static Scanner scanner = new Scanner(System.in);
    static SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

    // MENÚ PRINCIPAL

    public static void main(String[] args) {

        int opcion;

        System.out.println("  - SISTEMA DE GESTION DE CONCIERTOS  -");

        do {
            System.out.println("====== MENU PRINCIPAL ======");
            System.out.println("1. Gestionar Artistas");
            System.out.println("2. Gestionar Conciertos");
            System.out.println("3. Gestionar Entradas");
            System.out.println("0. Salir");
            System.out.print("Elige una opcion: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        menuArtistas(); //llamamos a cada metodo dependiendo del menu que quieran abrir
                        break;
                    case 2:
                        menuConciertos();
                        break;
                    case 3:
                        menuEntradas();
                        break;
                    case 0:
                        System.out.println("Saliendo de la aplicacion. Hasta luego!");
                        break;
                    default:
                        System.out.println("Opcion no valida. Elige entre 0 y 3.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: debes introducir un numero.");
                scanner.nextLine();
                opcion = -1;
            }

        } while (opcion != 0);

        scanner.close();
    }

    //SUBMENÚ ARTISTAS

    static void menuArtistas() {
        int opcion;

        do {
            System.out.println("--- MENU ARTISTAS ---");
            System.out.println("1. Añadir artista");
            System.out.println("2. Eliminar artista");
            System.out.println("3. Listar artistas");
            System.out.println("0. Volver");
            System.out.print("Elige una opcion: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        añadirArtista();
                        break;
                    case 2:
                        eliminarArtista();
                        break;
                    case 3:
                        listarArtistas();
                        break;
                    case 0:
                        System.out.println("Volviendo al menu principal...");
                        break;
                    default:
                        System.out.println("Opcion no valida.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: debes introducir un numero.");
                scanner.nextLine();
                opcion = -1;
            }

        } while (opcion != 0);
    }

    static void añadirArtista() {
        System.out.println("-- Añadir Artista --"); //USAMOS .TRIM EN TODOS PARA QUITAR LOS ESPACIOS EN BLANCO

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Genero musical: ");
        String genero = scanner.nextLine().trim();

        System.out.print("Pais de origen: ");
        String pais = scanner.nextLine().trim();

        artistaDAO.insertar(new Artista(nombre, genero, pais));
    }

    static void eliminarArtista() {
        System.out.println("-- Eliminar Artista --");
        listarArtistas();

        try {
            System.out.print("Id del artista a eliminar: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            artistaDAO.eliminar(id); //pasamos el ID al metodo artistaDAO.eliminar

        } catch (InputMismatchException e) {
            System.out.println("Error: el id debe ser un numero.");
            scanner.nextLine();
        }
    }

    static void listarArtistas() {
        System.out.println("-- Lista de Artistas --");
        List<Artista> lista = artistaDAO.listarTodos();

        if (lista.isEmpty()) {
            System.out.println("No hay artistas registrados.");
        } else {
            for (Artista a : lista) { //con un bucle for each mostramos los artistas de la lista
                System.out.println(a);
            }
        }
    }

    // SUBMENÚ CONCIERTOS

    static void menuConciertos() {
        int opcion;

        do {
            System.out.println("--- MENU CONCIERTOS ---");
            System.out.println("1. Añadir concierto");
            System.out.println("2. Eliminar concierto");
            System.out.println("3. Listar conciertos");
            System.out.println("0. Volver");
            System.out.print("Elige una opcion: ");

                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        añadirConcierto();
                        break;
                    case 2:
                        eliminarConcierto();
                        break;
                    case 3:
                        listarConciertos();
                        break;
                    case 0:
                        System.out.println("Volviendo al menu principal...");
                        break;
                    default:
                        System.out.println("Opcion no valida.");
                }


        } while (opcion != 0);
    }

    //Metodo para añadir concierto
    static void añadirConcierto() {
        System.out.println("-- Añadir Concierto --");

        List<Artista> artistas = artistaDAO.listarTodos();

        System.out.println("Artistas disponibles:"); //le mostramos primero los artistas que hay con el for each
        for (Artista a : artistas) {
            System.out.println("  " + a);
        }

        try {
            System.out.print("Id del artista: ");
            int idArtista = scanner.nextInt();
            scanner.nextLine();

            Artista artista = artistaDAO.buscarPorId(idArtista); //llamamos al metodo buscarPorId y le pasamos la idArtista

            System.out.print("Lugar: ");
            String lugar = scanner.nextLine().trim();
            if (lugar.isEmpty()) {
                System.out.println("Error: el lugar no puede estar vacio.");
                return;
            }

            System.out.print("Fecha (dd/MM/yyyy): ");
            String fechaStr = scanner.nextLine().trim();
            Date fecha;

            try {
                fecha = formatoFecha.parse(fechaStr); //Parseamos la fecha
            } catch (ParseException e) {
                System.out.println("Error: formato de fecha incorrecto. Usa dd/MM/yyyy.");
                return;
            }

            System.out.print("Precio de la entrada: ");
            double precio = scanner.nextDouble();
            scanner.nextLine();

            if (precio < 0) {
                System.out.println("Error: el precio no puede ser negativo.");
            }

            conciertoDAO.insertar(new Concierto(artista, fecha, lugar, precio));

        } catch (InputMismatchException e) {
            System.out.println("Error: valor no valido.");
            scanner.nextLine();
        }
    }

    //Metodo para eliminar conciertos
    static void eliminarConcierto() {
        System.out.println("-- Eliminar Concierto --");
        listarConciertos();

        try {
            System.out.print("Id del concierto a eliminar: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            conciertoDAO.eliminar(id); //llamamos al metodo eliminar y le pasamos la ID

        } catch (InputMismatchException e) {
            System.out.println("Error: el id debe ser un numero.");
            scanner.nextLine();
        }
    }

    static void listarConciertos() {
        System.out.println("-- Lista de Conciertos --");
        List<Concierto> lista = conciertoDAO.listarTodos();

        if (lista.isEmpty()) {
            System.out.println("No hay conciertos registrados.");
        } else {
            for (Concierto c : lista) { //mostramos los conciertos mediante for each
                System.out.println(c);
            }
        }
    }

    // SUBMENÚ ENTRADAS

    static void menuEntradas() { //metodo para mostrar el submenu de entradas
        int opcion;

        do {
            System.out.println("--- MENU ENTRADAS ---");
            System.out.println("1. Registrar venta de entradas");
            System.out.println("2. Listar ventas de entradas");
            System.out.println("0. Volver");
            System.out.print("Elige una opcion: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        registrarVenta();
                        break;
                    case 2:
                        listarEntradas();
                        break;
                    case 0:
                        System.out.println("Volviendo al menu principal...");
                    default:
                        System.out.println("Opcion no valida.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: debes introducir un numero.");
                scanner.nextLine();
                opcion = -1;
            }

        } while (opcion != 0);
    }

    static void registrarVenta() { //metodo para registrar las ventas
        System.out.println("-- Registrar Venta de Entrada --");

        List<Concierto> conciertos = conciertoDAO.listarTodos();
        if (conciertos.isEmpty()) {
            System.out.println("No hay conciertos registrados. Añade un concierto primero.");
            return;
        }

        System.out.println("Conciertos disponibles:"); //mostramos los conciertos disponibles mediante for each
        for (Concierto c : conciertos) {
            System.out.println("  " + c);
        }

        try {
            System.out.print("Id del concierto: ");
            int idConcierto = scanner.nextInt();
            scanner.nextLine();

            Concierto concierto = conciertoDAO.buscarPorId(idConcierto); //pasamos el id de concierto al metodo buscarPorId
            if (concierto == null) {
                System.out.println("Error: no existe ningun concierto con ese id.");
                return;
            }

            System.out.print("Nombre del comprador: ");
            String comprador = scanner.nextLine().trim();

            System.out.print("Cantidad de entradas: ");
            int cantidad = scanner.nextInt();
            scanner.nextLine();

            if (cantidad <= 0) {
                System.out.println("Error: la cantidad debe ser mayor que cero.");
                return;
            }

            entradaDAO.insertar(new Entrada(concierto, comprador, cantidad, new Date()));

        } catch (InputMismatchException e) {
            System.out.println("Error: valor no valido.");
            scanner.nextLine();
        }
    }

    //MOSTRAMOS LA LISTA DE ENTRADAS
    static void listarEntradas() {
        System.out.println("-- Lista de Ventas de Entradas --");
        List<Entrada> lista = entradaDAO.listarTodas();

        if (lista.isEmpty()) {
            System.out.println("No hay ventas registradas.");
        } else {
            for (Entrada e : lista) {
                System.out.println(e);
            }
        }
    }
}
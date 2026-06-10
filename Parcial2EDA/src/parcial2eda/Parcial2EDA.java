package parcial2eda;

import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parcial2EDA {

    static Scanner teclado = new Scanner(System.in);
    static Jugador jugador;
    static Mundo mundo;

    public static void main(String[] args) {
        mundo = Mundo.getMundo();
        mundo.imprimirMatriz();
        mostrarMatrizResultante(mundo.getMatrizCostos(), "--- Matriz de Costos Mínimos (Floyd-Warshall) ---");
        mostrarMatrizResultante(mundo.getMatrizCaminos(), "--- Mapa con caminos optimos disponibles ---");

        int opciónInicio;

        System.out.println("-----------------------------");
        System.out.println("---------------Bienvenido a Reinos Conectados---------------");
        System.out.println("Para volver a un menú anterior ingrese 0.");
        System.out.println("-------------------------------");
        System.out.println("Seleccione una opción:");
        System.out.println("--------------------------");
        System.out.println("1. Comenzamos a jugar?");
        System.out.println("2. Salir del juego");

        while (!teclado.hasNextInt()) {
            System.out.println("Elige un número válido (1 o 2)");
            teclado.next();
        }

        opciónInicio = teclado.nextInt();
        teclado.nextLine();

        switch (opciónInicio) {
            case 1:
                jugador = crearPersonaje();
                System.out.println("Bienvenido a Reinos Conectados, " + jugador.getNombreJugador() + "!");
                break;
            case 2:
                System.out.println("Saliendo del juego..");
                System.exit(0);
        }
        ;

        int option = -1;
        do {
            menuOpciones();
            option = teclado.nextInt();
            if (option == 0)
                break;
        } while (true);

    }

    static void menuOpciones() {
        int option = 0;
        while (true) {
            System.out.println("Estás en " + jugador.getPosicion().getNombreLugar());
            System.out.println("Cuál es tu siguiente movimiento? ");
            System.out.println("1. Viajar a otra zona del reino");
            System.out.println("2. Calcular caminos óptimos");
            System.out.println("3. Detectar caminos inaccesibles");
            System.out.println("4. Ver costos de los caminos");
            System.out.println("5. Ver estado del personaje");
            System.out.print(": ");
            option = teclado.nextInt();
            teclado.nextLine(); // toca limpiar el buffer
            switch (option) {
                case 0: return;
                case 1:
                    opcionesCaminosDisponibles();
                    menuViajar();
                    break;
                case 2:
                    opcionCaminosOptimos();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println(jugador);
                    break;
            }
        }

    }
    static void esperarEnter() {
        System.out.println("Presiona enter para continuar...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void menuViajar() {
        int option = -1;
        do {
            System.out.print("A dónde vamos? : ");
            option = teclado.nextInt();
            if (option == 0)
                return;
        } while (!jugador.setPosicion(option));
    }

    static void opcionesCaminosDisponibles() { // metodo para imprimir los mapas disponibles y la distancia

        ArrayList<Mapa> opcionesMapas = mundo.getCaminosDisponiblesMapa(jugador.getPosicion());
        ArrayList<Mapa> mapasExistentes = mundo.getVertices();

        System.out.println("Miras a tu alrededor y ves los siguientes caminos");

        for (Mapa m : opcionesMapas) {
            int numeroMapa = mapasExistentes.indexOf(m);
            int indiceOrigen = mapasExistentes.indexOf(jugador.getPosicion());
            int distanciaMapaDestino = mundo.getMatrizAdy()[indiceOrigen][numeroMapa];

            System.out.println(
                    "[" + numeroMapa + "]" + m.getNombreLugar() + ", distancia hasta llegar: " + distanciaMapaDestino);
        }
    }

    static void opcionCaminosOptimos() {
        mostrarMapasExistentes();
        System.out.println("A que lugar te gustaria calcularle el camino optimo?");
        int destinoMapa = teclado.nextInt();
        teclado.nextLine();

        if (destinoMapa < 0 || destinoMapa >= mundo.getVertices().size()) {
            System.out.println("ESE MAPA NO EXISTE");
            System.out.println("USA ENTER PARA VOLVER ATRAS");
            teclado.nextLine();
            return;
        }

        int posicionJugadorInterna = mundo.getVertices().indexOf(jugador.getPosicion());
        int costo = mundo.getMatrizCostos()[posicionJugadorInterna][destinoMapa];

        if(costo == 1000){
            System.out.println("ESE LUGAR NO TIENE NINGUN CAMINO ACCESIBLE");
            return;
        }

        System.out.println("Tu camino optimo es el siguiente: ");
        List<String> rutaOptima = mundo.encontrarCaminoOptimo(jugador.getPosicion(), destinoMapa);
        for (String lugar : rutaOptima) {
            System.out.print(lugar + " - ");
            
        }
        System.out.println("");
        System.out.println("------------");
        esperarEnter();
    }

    static void mostrarMapasExistentes(){

        ArrayList<Mapa> mapasExistentes = mundo.getVertices();

        for(int i = 0; i < mapasExistentes.size(); i++){
            System.out.println("[" + i  + "]" + mapasExistentes.get(i).getNombreLugar());
        }


    }

    static Jugador crearPersonaje() {
        System.out.print("Ingrese el nombre del personaje: ");
        var nombreJugador = teclado.nextLine();
        return new Jugador(nombreJugador);
    }

    static void mostrarMatrizResultante(Integer[][] matrizResultado, String header) {
        System.out.println(header);
        for (int i = 0; i < matrizResultado.length; i++) {
            for (int j = 0; j < matrizResultado[i].length; j++) {
                System.out.print((matrizResultado[i][j] == 1000 ? "X" : matrizResultado[i][j]) + "\t");
            }
            System.out.println(); //
        }
    }

}

//

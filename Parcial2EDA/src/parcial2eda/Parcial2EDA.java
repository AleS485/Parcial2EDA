package parcial2eda;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

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
                System.out.println("Bienvenido a Reinos Conectados, : " + jugador.getNombreJugador() + "!");
                System.out.println("Inicias tu aventura en " + jugador.getPosicion().getNombreLugar());
                
                
                
                System.out.print("Hacia dónde te gustaría ir?: ");
                int destino = teclado.nextInt();
                jugador.setPosicion(destino);

                int opciónJuego;
                do{
                     System.out.println("Estás en: " + jugador.getPosicion().getNombreLugar().toLowerCase());
                     System.out.println("--------------------------------");
                     System.out.println("Seleccione una opción: ");
                     System.out.println("--------------------------------");
                     System.out.println("1. Ver caminos y distancias desde donde estás");
                     System.out.println("2. Viajar a otra zona del reino");
                     System.out.println("3. Calcular caminos óptimos");
                     System.out.println("4. Detectar caminos inaccesibles");
                     System.out.println("5. Ver costos de los caminos");
                     System.out.println("6. Ver estado del personaje");
                     System.out.println("7. Volver atrás");

                 while(!teclado.hasNextInt()){
                    System.out.println("Ingrese un número válido(1 al 7)");
                    teclado.next();
                }
                opciónJuego = teclado.nextInt();
                teclado.nextLine();

                
                // mostrarMenu
                break;
            case 2:
                System.out.println("Saliendo del juego..");
                System.exit(0);
                break;

        }

    }

    static void opcionesCaminosDisponibles(){ // metodo para imprimir los mapas disponibles y la distancia

        ArrayList<Mapa> opcionesMapas = mundo.getCaminosDisponiblesMapa(jugador.getPosicion());
        ArrayList<Mapa> mapasExistentes = mundo.getVertices();

        System.out.println("Miras a tu alrededor y ves los siguientes caminos");
        
        for(Mapa m: opcionesMapas){
            int numeroMapa = mapasExistentes.indexOf(m);
            int indiceOrigen = mapasExistentes.indexOf(jugador.getPosicion());
            int distanciaMapaDestino = mundo.getMatrizAdy()[indiceOrigen][numeroMapa];

            System.out.println("[" + numeroMapa + "]" + m.getNombreLugar() + ", distancia hasta llegar: " + distanciaMapaDestino);
        }


    }


    static void mostrarMenu(){

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

package parcial2eda;

import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parcial2EDA {

    static Scanner teclado = new Scanner(System.in);
    static Jugador jugador;
    static Mundo mundo;

    public static void main(String[] args) {

        limpiarConsola();
        mundo = Mundo.getMundo();

        System.out.println("--------------- Bienvenido a Reinos Conectados ---------------");

        jugador = crearPersonaje();
        jugador.setNivel(10);
        menuMapa();
    }

    static void esperarEnter() {
        System.out.println("Presiona enter para continuar...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        limpiarConsola();
    }

    static void limpiarConsola() {
        System.out.print("\033[H\033[2J");
    }

    static void menuMapa() {

        String option = "";
        while (option != "0") {
            limpiarConsola();
            System.out.println("Estás en " + jugador.getPosicion().getNombre());
            System.out.println("[I] Listar objetos en el mapa.");
            System.out.println("[E] Listar enemigos.");
            System.out.println("[V] Viajar");
            System.out.println("[J] Menu del jugador.");
            option = teclado.nextLine().toUpperCase().trim();
            switch (option) {
                case "I":
                    limpiarConsola();
                    wea();
                    break;
                case "E":
                    var npc = jugador.getPosicion().getEntidades();
                    if (npc.size() == 0) {
                        limpiarConsola();
                        System.out.println("Ni un alma a la vista.");
                        esperarEnter();
                        break;
                    }
                    for (int i = 0; i < npc.size(); i++) {
                        System.out.println("[" + i + "] " + npc.get(i).getNombrePnj());
                    }
                    System.out.println("Elegi un enemigo para empezar a combatir:");
                    int accionElegirEnemigo = teclado.nextInt();
                    teclado.nextLine();

                    if(accionElegirEnemigo > npc.size() || accionElegirEnemigo < 0){
                        System.out.println("TE ESTA PEGANDO LA ESQUIZO AMIGO, ESE BICHO NO EXISTE");
                    } else{
                        iniciarCombate(jugador, npc.get(accionElegirEnemigo));
                    }
                    esperarEnter();
                    break;
                case "J":
                    menuJugador();
                    break;
                case "V":
                    menuViajar();
                    break;
            }
        }

    }

    static void wea() {
        var items = jugador.getPosicion().getItems();
        if (items.size() == 0) {
            limpiarConsola();
            System.out.println("Aqui no hay nada que ver...");
            esperarEnter();
            return;
        }
        for (int i = 0; i < items.size(); i++) {
            System.out.println("[" + i + "] " + items.get(i));
        }
        System.out.print(": ");
        int item = teclado.nextInt();
        jugador.recoger(items.get(item));
        items.remove(item);
        esperarEnter();
    }

    static void menuJugador() {
        limpiarConsola();
        System.out.println("A. Calcular caminos óptimos");
        System.out.println("B. Detectar caminos inaccesibles");
        System.out.println("C. Ver costos de los caminos");
        System.out.println("D. Imprimir matriz");
        System.out.println("E. Estado del personaje");
        

        String option = teclado.nextLine().toUpperCase().trim();

        switch (option) {
            case "A":
                opcionCaminosOptimos();
                break;

            case "B":
                limpiarConsola();
                mundo.detectorZonasInaccesibles(jugador.getPosicion());
                break;

            case "C":
                limpiarConsola();
                mostrarMatrizResultante(mundo.getMatrizCostos(), "Matriz de costos mínimos: ");
                System.out.println();
                break;

            case "D":
                limpiarConsola();
                System.out.println("Matriz de adyacencia original: ");
                mundo.imprimirMatriz();
                break;

            case "E":
                limpiarConsola();
                System.out.println(jugador.toString());
                break;

            default:
                System.out.println("\n Opción incorrecta. Volviendo..");
                break;

        }
        System.out.println("\n presione ENTER para continuar....");
        teclado.nextLine();
    }

    static void menuViajar() {
        limpiarConsola();
        String option = "";
        while (option != "0") {
            System.out.println("[M] para listar los mapa del mundo");
            opcionesCaminosDisponibles();
            System.out.print(": ");
            option = teclado.nextLine().toUpperCase().trim();

            switch (option) {
                case "M":
                    limpiarConsola();
                    mostrarMapasExistentes();
                    break;
                default:
                    try {
                        int nmapa = Integer.valueOf(option);
                        jugador.setPosicion(nmapa);
                        menuMapa();
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("No es una opción válida.");
                    }
            }
        }

        esperarEnter();
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
                    "[" + numeroMapa + "] " + m.getNombre() + ". Distancia hasta llegar: " + distanciaMapaDestino);
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

        if (costo == 1000) {
            System.out.println("ESE LUGAR NO TIENE NINGUN CAMINO ACCESIBLE");
            esperarEnter();
            return;
        }

        System.out.println("Tu camino optimo es el siguiente:");
        List<String> rutaOptima = mundo.encontrarCaminoOptimo(jugador.getPosicion(), destinoMapa);
        System.out.println(rutaOptima);
        esperarEnter();
    }

    static void mostrarMapasExistentes() {
        ArrayList<Mapa> mapasExistentes = mundo.getVertices();
        for (int i = 0; i < mapasExistentes.size(); i++) {
            System.out.println("[" + i + "] " + mapasExistentes.get(i).getNombre());
        }
    }

    static Jugador crearPersonaje() {
        System.out.print("Ingrese el nombre del personaje: ");
        var nombreJugador = teclado.nextLine();
        return new Jugador(nombreJugador);
    }

    static void mostrarMatrizResultante(Integer[][] matrizResultado, String header) {
        System.out.println(header);
        var limit = mundo.getVertices().size();
        for (int i = 0; i < limit; i++) {
            for (int j = 0; j < limit; j++) {
                System.out.print((matrizResultado[i][j] == 1000 ? "X" : matrizResultado[i][j]) + "\t");
            }
            System.out.println(); //
        }
    }

    static void iniciarCombate(Jugador jugador, Pnj enemigo){

        System.out.println("EMPIEZA EL COMBATE ENTRE: " + jugador.getNombreJugador() + " Y " + enemigo.getNombrePnj());

        while(jugador.getHp() > 0 && enemigo.getVida() > 0){
            limpiarConsola();
            System.out.println("Vida actual: " + jugador.getHp());
            System.out.println("Vida de " + enemigo.nombrePnj + ": " + enemigo.getVida());
            System.out.println("Que queres hacer?");
            System.out.println("[1] ATACAR");
            System.out.println("[2] VOLVER ATRAS(COMO TODO CAGON)");
            System.out.println("--------------------------------");
            int accionCombate = teclado.nextInt();
            teclado.nextLine();

            if(accionCombate == 2){
                double randomCascotazo =  Math.random();
                if(randomCascotazo > 0.5){
                    jugador.setHp(jugador.getHp() - 3);
                    System.out.println("Te comiste un cascotazo intentando escapar y recibiste: " + 3 + " de daño.");
                    
                    if(jugador.getHp() <= 0){
                        System.out.println("COMO VAS A MORIR DE UN CASCOTAZO? XD");
                        System.exit(0);
                    }

                }
                System.out.println("Lograste escapar del combate");
                esperarEnter();
                return;
            }

            if(accionCombate == 1){
                System.out.println("TU TURNO:");
                jugador.atacar(enemigo);

                if(enemigo.getVida() <= 0){
                    jugador.matarEnemigo(enemigo.getNombrePnj());
                    System.out.println("MATASTE A: " + enemigo.getNombrePnj());
            
                    String nombreBichoMatado = enemigo.getNombrePnj(); 
                    jugador.getMonstruosMatados().put(nombreBichoMatado, jugador.getNMonstruosMatados(nombreBichoMatado) + 1); 
                    


                    jugador.getPosicion().getEntidades().remove(enemigo);

                    esperarEnter();
                    return;
                }

                System.out.println("----------");
                System.out.println("TURNO DEL ENEMIGO");
                enemigo.atacar(jugador);

                if(jugador.getHp() <= 0){
                    System.out.println("PERDISTE, COMO TE VA A MATAR ESE BICHO? XD");
                }


            
                esperarEnter();
            } else{
                System.out.println("NO EXISTE ESA OPCION");
                esperarEnter();
            }

        }

    }

}




package parcial2eda;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Mundo {

    Integer[][] matrizAdy;
    Integer[][] matrizCostos;
    Integer[][] matrizCaminos;

    ArrayList<Mapa> vertices;
    private static final Integer CANTIDADMAXIMA = 10;
    private static Mundo mundo;

    private Mundo() {
        matrizAdy = new Integer[CANTIDADMAXIMA][CANTIDADMAXIMA];
        this.matrizCostos = new Integer[CANTIDADMAXIMA][CANTIDADMAXIMA];
        this.matrizCaminos = new Integer[CANTIDADMAXIMA][CANTIDADMAXIMA];
        vertices = new ArrayList<>(CANTIDADMAXIMA);
        inicializarGrafo();
    }

    public static Mundo getMundo() {
        if (mundo == null) {
            mundo = new Mundo();
        }
        return mundo;
    }

    private void inicializarGrafo() {
        for (int i = 0; i < CANTIDADMAXIMA; i++) {
            for (int j = 0; j < CANTIDADMAXIMA; j++) {
                matrizAdy[i][j] = 1000;
            }
        }
        Mapa puebloInicio = new Mapa();
        puebloInicio.setNombre("Pueblo Primavera");
        Mapa cueva = new Mapa();
        cueva.setNombre("cueva");
        Mapa ciudad = new Mapa();
        ciudad.setNombre("ciudad");
        Mapa bosque = new Mapa();
        bosque.setNombre("bosque");
        bosque.getItems().add("Hongos");
        Mapa inframundo = new Mapa();
        inframundo.setNombre("inframundo");
        Mapa mazmorra = new Mapa();
        mazmorra.setNombre("mazmorra");
        Mapa castillo = new Mapa();
        castillo.setNombre("castillo"); 

        // final

        vertices.add(puebloInicio);
        vertices.add(cueva);
        vertices.add(ciudad);
        vertices.add(bosque);
        vertices.add(mazmorra);
        vertices.add(castillo);
        vertices.add(inframundo);

        for (int i = 0; i < matrizAdy.length; i++) {
            matrizAdy[i][i] = 0;
        }

        Pnj goblin = new Pnj("Goblin", true, 10);
        Pnj esqueleto = new Pnj("Esqueleto", true, 15);
        Pnj slime = new Pnj("Slime", true, 5);
        Pnj lobo = new Pnj("Lobo", true, 20);
        Pnj vampiro = new Pnj("Vampiro", true, 25);
        Pnj chupacabras = new Pnj("Chupacabras", true, 25);
        Pnj lobizon = new Pnj("Lobizon", true, 25);
        Pnj luzmala = new Pnj("LuzMala", true, 20);
        Pnj ReyFinal = new Pnj("Rey Sin Nombre", true, 185);

        cueva.agregarEntidad(goblin);
        cueva.agregarEntidad(slime);

        bosque.agregarEntidad(lobo);
        bosque.agregarEntidad(lobizon);
        bosque.agregarEntidad(chupacabras);

        mazmorra.agregarEntidad(luzmala);
        mazmorra.agregarEntidad(esqueleto);
        mazmorra.agregarEntidad(vampiro);

        castillo.agregarEntidad(ReyFinal);


        conectar(puebloInicio, bosque, 15);
        conectar(bosque, cueva, 2);
        conectar(cueva, ciudad, 10);
        conectar(ciudad, mazmorra, 20);
        //conectar(mazmorra, castillo, 35); para probar mision de la mazmorra y camino bloqueado
        conectar(mazmorra, bosque, 35);
        FloydWarshall();
    }

    public Integer[][] getMatrizCaminos() {
        return this.matrizCaminos;
    }

    public Integer[][] getMatrizCostos() {
        return this.matrizCostos;
    }

    public void conectar(Mapa mapa1, Mapa mapa2, int distancia) {
        matrizAdy[vertices.indexOf(mapa1)][vertices.indexOf(mapa2)] = distancia;
        matrizAdy[vertices.indexOf(mapa2)][vertices.indexOf(mapa1)] = distancia;
        FloydWarshall();
    }

    public Mapa getMapa(int indice) {

        if (indice < 0 || indice > vertices.size()) {
            System.out.println("Te perdiste en el vacio");
            System.out.println("Perdiste el juego, al menos dejaste un hermoso cuerpo.");
            Mapa vacio = new Mapa();
            vacio.setNombre("Vacio");
            return vacio;
        }

        return vertices.get(indice);

    }

    public ArrayList<Mapa> getCaminosDisponiblesMapa(Mapa mapa) {

        ArrayList<Mapa> mapasDisponibles = new ArrayList<>();
        int indiceMapa = vertices.indexOf(mapa);

        if (indiceMapa == -1) {
            return mapasDisponibles;
        }

        for (int i = 0; i < CANTIDADMAXIMA; i++) {
            Boolean noSeAgrega = matrizAdy[indiceMapa][i] == 1000 || indiceMapa == i;
            if (noSeAgrega)
                continue;
            mapasDisponibles.add(vertices.get(i));
        }

        return mapasDisponibles;
    }

    // hasta longitud de mi arreglo de mapas, caminos optimos
    public void detectorZonasInaccesibles(Mapa posicionActual) {

        int indiceOrigen = vertices.indexOf(posicionActual);

        if (indiceOrigen == -1) {
            System.out.println("ESTE MAPA NO EXISTE EN EL MUNDO");
            return;
        }

        System.out.println("SE ESTAN ANALIZANDO CAMINOS INNACESIBLES DESDE: " + posicionActual.getNombre());

        boolean flagInaccesible = false;

        for (int i = 0; i < vertices.size(); i++) {

            if (indiceOrigen != i && matrizCaminos[indiceOrigen][i] == -1) {

                System.out.println(vertices.get(i).getNombre() + ", es un camino innacesible");
                flagInaccesible = true;
            }

        }

        if (!flagInaccesible) {
            System.out.println("No se encontraron caminos innacesibles desde: " + posicionActual.getNombre());
        }

    }

    public void imprimirMatriz() {
        for (int i = 0; i < CANTIDADMAXIMA; i++) {
            for (int j = 0; j < CANTIDADMAXIMA; j++) {
                System.out.print(this.matrizAdy[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public Integer[][] getMatrizAdy() {
        return matrizAdy;
    }

    public ArrayList<Mapa> getVertices() {
        return vertices;
    }

    private void FloydWarshall() {
        for (int i = 0; i < CANTIDADMAXIMA; i++) {
            for (int j = 0; j < CANTIDADMAXIMA; j++) {
                matrizCostos[i][j] = matrizAdy[i][j];
                if (i != j && matrizAdy[i][j] != 1000) {
                    matrizCaminos[i][j] = i;
                } else {
                    matrizCaminos[i][j] = -1;
                }
            }
        }

        for (int k = 0; k < CANTIDADMAXIMA; k++) {
            for (int i = 0; i < CANTIDADMAXIMA; i++) {
                for (int j = 0; j < CANTIDADMAXIMA; j++) {
                    if (matrizCostos[i][k] != 1000 && matrizCostos[k][j] != 1000) {
                        if (matrizCostos[i][j] > matrizCostos[i][k] + matrizCostos[k][j]) {
                            matrizCostos[i][j] = matrizCostos[i][k] + matrizCostos[k][j];
                            matrizCaminos[i][j] = matrizCaminos[k][j];
                        }
                    }
                }
            }
        }
    }

    public List<String> encontrarCaminoOptimo(Mapa origen, Integer destino) {
        var i_origen = vertices.indexOf(origen);
        var listaMapas = new LinkedList<String>();
        for (int i = destino.intValue(); i != i_origen; i = matrizCaminos[i_origen][i]) {
            listaMapas.add(vertices.get(i).getNombre());
        }
        return listaMapas.reversed();
    }



}
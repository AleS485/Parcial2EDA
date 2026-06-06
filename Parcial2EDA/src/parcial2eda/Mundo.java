
package parcial2eda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Mundo {

    Integer matrizAdy[][];
    ArrayList<Mapa> vertices;
    private static final Integer CANTIDADMAXIMA = 10;
    private static final Integer SINCONEXION = null;
    private static Mundo mundo;

    private Mundo() {
        matrizAdy = new Integer[CANTIDADMAXIMA][CANTIDADMAXIMA];
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
        puebloInicio.setNombreLugar("Pueblo Inicio");
        Mapa cueva = new Mapa();
        cueva.setNombreLugar("cueva");
        Mapa ciudad = new Mapa();
        ciudad.setNombreLugar("ciudad");
        Mapa bosque = new Mapa();
        bosque.setNombreLugar("bosque");
        Mapa mazmorra = new Mapa();
        mazmorra.setNombreLugar("mazmorra");
        Mapa castillo = new Mapa();
        castillo.setNombreLugar("castillo");
        // final

        vertices.add(puebloInicio);
        vertices.add(cueva);
        vertices.add(ciudad);
        vertices.add(bosque);
        vertices.add(mazmorra);
        vertices.add(castillo);

        for (int i = 0; i < matrizAdy.length; i++) {
            matrizAdy[i][i] = 0;
        }

        conectar(puebloInicio, bosque, 15);
        conectar(bosque, cueva, 2);
        conectar(cueva, ciudad, 10);
        conectar(ciudad, mazmorra, 20);
        conectar(mazmorra, castillo, 35);

        System.out.println("Mundo Inicializado correctamente");

    }

    public void conectar(Mapa mapa1, Mapa mapa2, int distancia) {

        this.matrizAdy = matrizAdy;

        matrizAdy[vertices.indexOf(mapa1)][vertices.indexOf(mapa2)] = distancia;
        matrizAdy[vertices.indexOf(mapa2)][vertices.indexOf(mapa1)] = distancia;

    }

    public Mapa getMapa(int indice) {

        if (indice < 0 || indice > vertices.size()) {
            System.out.println("Te perdiste en el vacio");
            System.out.println("Perdiste el juego, al menos dejaste un hermoso cuerpo.");
            Mapa vacio = new Mapa();
            vacio.setNombreLugar("Vacio");
            return vacio;
        }

        return vertices.get(indice);

    }

    public ArrayList<Mapa> getCaminosDisponiblesMapa(Mapa mapa) {

        ArrayList<Mapa> mapasDisponibles = new ArrayList<>();
        int indiceMapa = vertices.indexOf(mapa);

        if (indiceMapa == -1) {
            // System.out.println("No se encontro ese mapa");
            return mapasDisponibles;
        }

        for (int i = 0; i < matrizAdy.length; i++) {
            Boolean noSeAgrega = matrizAdy[indiceMapa][i] == 1000 || indiceMapa == i;
            if (noSeAgrega)
                continue;
            mapasDisponibles.add(vertices.get(i));

            // System.out.println("[" + i + "]" + vertices.get(i) + ", distancia hasta
            // llegar: " + matrizAdy[indiceMapa][i]);

            this.matrizAdy = matrizAdy;
        }

        return mapasDisponibles;
    }

    public void imprimirCaminos(Mapa mapa) {
        var indiceMapa = vertices.indexOf(mapa);
        var mapitas = getCaminosDisponiblesMapa(mapa);
        System.out.println("Estas en:" + mapa.nombreLugar);
        System.out.println("Y podes ir a: ");
        for (var m : mapitas) {
            var i = vertices.indexOf(m);
            System.out.println(
                    "[" + i + "]" + m.getNombreLugar() + ", distancia hasta llegar: " + matrizAdy[indiceMapa][i]);
        }
    }

    public void vecindadIzquierda(Mapa mapa) {

        int indiceMapa = vertices.indexOf(mapa);

        if (indiceMapa == -1) {
            System.out.println("No se encontro ese mapa");
            return;
        }

        for (int i = 0; i < matrizAdy.length; i++) {
            if (matrizAdy[i][indiceMapa] == null || indiceMapa == i) {
            } else {
                System.out.println("Podes volver a: " + vertices.get(i));
            }
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

    public void mostrarArrayList(ArrayList<Mapa> mapas) {
        System.out.println("El mundo tiene los mapas: ");
        for (Mapa mapa : mapas) {
            System.out.println(mapa);
        }
        System.out.println("---------");

    }

    public Integer[][] getMatrizAdy() {
        return matrizAdy;
    }

    public void setMatrizAdy(Integer[][] matrizAdy) {
        this.matrizAdy = matrizAdy;
    }

    public ArrayList<Mapa> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Mapa> vertices) {
        this.vertices = vertices;
    }

    public HashMap<String, Integer[][]> FloydWarshall() {
        Integer n = matrizAdy.length;
        Integer[][] costos = new Integer[n][n];
        Integer[][] caminos = new Integer[n][n];
        // Inicializamos las matrices.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                costos[i][j] = matrizAdy[i][j];
                if (i != j && matrizAdy[i][j] != 1000) {
                    caminos[i][j] = i;
                } else {
                    caminos[i][j] = -1;
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {

                    if (costos[i][k] != 1000 && costos[k][j] != 1000) {

                        if (costos[i][j] > costos[i][k] + costos[k][j]) {
                            costos[i][j] = costos[i][k] + costos[k][j];
                            caminos[i][j] = caminos[k][j];
                        }
                    }
                }
            }
        }

        HashMap r = new HashMap<String, Integer[][]>();
        r.put("costos", costos);
        r.put("caminos", caminos);
        return r;
    }

}

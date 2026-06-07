package parcial2eda;

public class Parcial2EDA {

    public static void main(String[] args) {

        Mundo mundo = Mundo.getMundo();
        Jugador jugador1 = new Jugador("Pepe Argento", mundo.getVertices().get(0));

        mundo.imprimirMatriz();
        jugador1.verCaminos();

        var matrices = mundo.FloydWarshall();

        mostrarMatrizResultante(matrices.get("costos"), "--- Matriz de Costos Mínimos (Floyd-Warshall) ---");
        mostrarMatrizResultante(matrices.get("caminos"), "--- Mapa con caminos optimos disponibles ---");
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
    
//
}

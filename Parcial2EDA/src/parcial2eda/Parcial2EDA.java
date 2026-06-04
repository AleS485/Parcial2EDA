
package parcial2eda;


public class Parcial2EDA {

    
    public static void main(String[] args) {
        
        Mundo mundoCreado = Mundo.getMundo();
        Jugador jugador1 = new Jugador("pepe argento", mundoCreado.getVertices().get(0));
        
        
        
        mundoCreado.imprimirMatriz();

        
        
        mundoCreado.verMapasDisponibles(jugador1.getPosicion());
        jugador1.setPosicion(mundoCreado.getMapa(5));
        mundoCreado.verMapasDisponibles(jugador1.getPosicion());
        
        
    }
    
}

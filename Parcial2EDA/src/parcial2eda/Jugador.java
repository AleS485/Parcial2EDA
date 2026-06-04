
package parcial2eda;

import java.util.ArrayList;
import java.util.HashMap;



public class Jugador {
    
    public String nombreJugador;
    public int hp;
    public int nivel;
    public HashMap<Item, Integer> mochila;
    public ArrayList<Mision> misiones;
    public Mapa posicion;

    public Jugador(String nombreJugador, Mapa posicionInicial) {
        this.nombreJugador = nombreJugador;
        this.hp = 50;
        this.nivel = 1;
        this.posicion = posicionInicial;
    }
    
    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public HashMap<Item, Integer> getMochila() {
        return mochila;
    }

    public void setMochila(HashMap<Item, Integer> mochila) {
        this.mochila = mochila;
    }

    public ArrayList<Mision> getMisiones() {
        return misiones;
    }

    public void setMisiones(ArrayList<Mision> misiones) {
        this.misiones = misiones;
    }

    public Mapa getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        
        
        Mundo mundo = Mundo.getMundo();
        ArrayList<Mapa> mapas = mundo.verMapasDisponibles(this.posicion);
        
        if(mundo.getVertices().indexOf(this.posicion))
        
        //jugador1.setPosicion(Mundo.getMundo().);
    }

    @Override
    public String toString() {
        return "Jugador{" + "nombreJugador=" + nombreJugador + ", hp=" + hp + ", nivel=" + nivel + ", mochila=" + mochila + ", misiones=" + misiones + ", posicion=" + posicion + '}';
    }
    
    
    
    
    
    
    
    
    
    
    
}

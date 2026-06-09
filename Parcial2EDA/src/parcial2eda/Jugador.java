package parcial2eda;

import java.util.ArrayList;
import java.util.HashMap;

public class Jugador {
    private Mundo mundo = Mundo.getMundo();
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
        this.mochila = new HashMap<>();
        this.misiones = new ArrayList<>();
    }

    public void aceptarMision(Mision m) {
        if (misiones.contains(m)) return;
        misiones.add(m);
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


    public void setPosicion(int i_destino) {
        Mapa destino = mundo.getMapa(i_destino);
        if (destino == null)
            return;
        ArrayList<Mapa> mapasDisponibles = mundo.getCaminosDisponiblesMapa(this.posicion);
        if (!mapasDisponibles.contains(destino)) {
            System.out.println("Movimiento no permitido!");
        }
        this.posicion = destino;
    }

    public boolean checkMision(Mision m) {
        return m.completarMision(this);
    }


    public void verCaminos() {
        /* ArrayList<Mapa> mapasDisponibles = mundo.getCaminosDisponiblesMapa(this.posicion);

        System.out.println("Estas en:" + this.posicion.getNombreLugar());
        System.out.println("Y podes ir a: ");

        for (Mapa m : mapasDisponibles) {
            System.out.println(m);
        } */

        mundo.imprimirCaminos(this.posicion);
    }


    @Override
    public String toString() {
        return "Jugador{" + "nombreJugador=" + nombreJugador + ", hp=" + hp + ", nivel=" + nivel + ", mochila="
                + mochila + ", misiones=" + misiones + ", posicion=" + posicion + '}';
    }
    // prueba 2



}
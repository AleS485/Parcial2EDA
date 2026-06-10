
package parcial2eda;

import java.util.ArrayList;


public class Mapa {
    
    public String nombre;
    public ArrayList<Pnj> entidades;
    public ArrayList<Mision> misiones;
    public ArrayList<String> items;

    public Mapa() {
        this.entidades = new ArrayList<>();
        this.misiones = new ArrayList<>();
        this.items = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombreLugar) {
        this.nombre = nombreLugar;
    }

    public ArrayList<Pnj> getEntidades() {
        return entidades;
    }

    public void setEntidades(ArrayList<Pnj> entidades) {
        this.entidades = entidades;
    }

    public ArrayList<Mision> getMisiones() {
        return misiones;
    }

    public void setMisiones(ArrayList<Mision> misiones) {
        this.misiones = misiones;
    }

    public ArrayList<String> getItems() {
        return items;
    }


    @Override
    public String toString() {
        return nombre;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
}





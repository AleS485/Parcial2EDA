
package parcial2eda;

import java.util.ArrayList;


public class Mapa {
    
    public String nombreLugar;
    public ArrayList<Pnj> entidades;
    public ArrayList<Mision> misiones;
    public ArrayList<Item> items;
    
    
    public Mapa(String nombreLugar, ArrayList<Pnj> entidades, ArrayList<Mision> misiones, ArrayList<Item> items) {
        this.nombreLugar = nombreLugar;
        this.entidades = entidades;
        this.misiones = misiones;
        this.items = items;
    }

    public Mapa() {
        
    }

    public String getNombreLugar() {
        return nombreLugar;
    }

    public void setNombreLugar(String nombreLugar) {
        this.nombreLugar = nombreLugar;
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

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return nombreLugar;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
}





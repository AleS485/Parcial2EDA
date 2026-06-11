
package parcial2eda;

import java.util.ArrayList;


public class Pnj {
    
    private String nombrePnj;
    private int vida;
    private boolean hostil;
    private int dañoPnj;

    public Pnj(String nombrePnj, boolean hostil, int vida, int dañoPnj) {
        this.nombrePnj = nombrePnj;
        this.hostil = hostil;
        this.vida = vida;
        this.dañoPnj = dañoPnj;
    }

    public String getNombrePnj() {
        return nombrePnj;
    }

    public void setNombrePnj(String nombrePnj) {
        this.nombrePnj = nombrePnj;
    }

    public boolean isHostil() {
        return hostil;
    }

    public void setHostil(boolean hostil) {
        this.hostil = hostil;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    

    public void atacar(Jugador jugador){
        if(!this.hostil) return;

        int hpJugadorObjetivo = jugador.getHp();
        jugador.setHp(hpJugadorObjetivo - this.dañoPnj);
        System.out.println(this.nombrePnj + " ataco a: " + jugador.getNombreJugador() + ", le saco "+ this.dañoPnj + " de HP");
        
        
    
    }


    public int getDañoPnj() {
        return dañoPnj;
    }

    public void setDañoPnj(int dañoPnj) {
        this.dañoPnj = dañoPnj;
    }





    

    
    
    
    
    
    
    
    
    
    
    
}

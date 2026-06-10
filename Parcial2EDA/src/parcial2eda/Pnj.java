
package parcial2eda;

import java.util.ArrayList;


public class Pnj {
    
    public String nombrePnj;
    public int vida;
    public boolean hostil;

    public Pnj(String nombrePnj, boolean hostil, int vida) {
        this.nombrePnj = nombrePnj;
        this.hostil = hostil;
        this.vida = vida;
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

        int dañoPnj = 0;
        switch(this.nombrePnj){
            case "Goblin":
                dañoPnj = 5;
                break;
            case "Esqueleto":
                dañoPnj = 10;
                break;
            case "Slime":
                dañoPnj = 10;
                break;
            case "Lobo":
                dañoPnj = 10;
                break;
            case "Vampiro":
                dañoPnj = 10;
                break;
            case "Chupacabras":
                dañoPnj = 10;
                break;
            case "Lobizon":
                dañoPnj = 20;
                break;
            case "LuzMala":
                dañoPnj = 20;
                break;
            case "Rey Sin Nombre":
                dañoPnj = 25;
                break;
        } 
        int hpJugadorObjetivo = jugador.getHp();
        jugador.setHp(hpJugadorObjetivo - dañoPnj);
        System.out.println(this.nombrePnj + " ataco a: " + jugador.getNombreJugador() + ", le saco "+ dañoPnj + " de HP");
        
        
    
    }

    @Override
    public String toString() {
        return "Pnj [nombrePnj=" + nombrePnj + ", vida=" + vida + ", hostil=" + hostil + ", getNombrePnj()="
                + getNombrePnj() + ", isHostil()=" + isHostil() + ", getVida()=" + getVida() + ", getClass()="
                + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
    }





    

    
    
    
    
    
    
    
    
    
    
    
}

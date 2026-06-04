
package parcial2eda;

import java.util.ArrayList;


public class Pnj {
    
    public String nombrePnj;
    public int vida;
    public boolean hostil;
    public ArrayList<Mision> mision;

    public Pnj(String nombrePnj, boolean hostil, ArrayList<Mision> mision, int vida) {
        this.nombrePnj = nombrePnj;
        this.hostil = hostil;
        this.vida = vida;
        this.mision = mision;
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

    public ArrayList<Mision> getMision() {
        return mision;
    }

    public void setMision(ArrayList<Mision> mision) {
        this.mision = mision;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    @Override
    public String toString() {
        return "Pnj{" + "nombrePnj=" + nombrePnj + ", vida=" + vida + ", hostil=" + hostil + ", mision=" + mision + '}';
    }

    
    
    
    
    
    
    
    
    
    
    
}

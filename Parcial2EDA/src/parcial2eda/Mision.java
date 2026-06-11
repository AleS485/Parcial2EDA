
package parcial2eda;


public class Mision { 
    public interface ICMision {
        public boolean exec(Mision m, Jugador j);
    }
    public String objetivoMision;
    public int idMision;
    public boolean estadoMision;
    private ICMision icom_mision;

    public Mision(String nombre, String objetivoMision, ICMision icom_mision) {
        this.objetivoMision = objetivoMision;
        this.estadoMision = false;
        this.icom_mision = icom_mision;
    }

    public String getObjetivoMision() {
        return objetivoMision;
    }

    public void setObjetivoMision(String objetivoMision) {
        this.objetivoMision = objetivoMision;
    }

    public boolean isEstadoMision() {
        return estadoMision;
    }

    public boolean setEstadoMision(boolean estadoMision) {
        this.estadoMision = estadoMision;
        return estadoMision;
    }

    
    public boolean verificarMision(Jugador jugador){
        boolean r = icom_mision.exec(this, jugador);
        if (r) {
            jugador.subirDeNivel();
        }
        return r;
    }
    
    
    @Override
    public String toString() {
        return "Mision{" + "objetivoMision=" + objetivoMision + ", estadoMision=" + estadoMision + '}';
    }

    
    
    
    
    
    
    
    
    
    
}
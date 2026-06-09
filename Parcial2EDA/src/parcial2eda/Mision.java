
package parcial2eda;


public class Mision {
    public interface Cb {
        public void exec();
    }
    public String objetivoMision;
    public int idMision;
    public boolean estadoMision;
    private Cb cb;

    public Mision(String nombre, String objetivoMision, Cb cb) {
        this.objetivoMision = objetivoMision;
        this.estadoMision = false;
        this.cb = cb;
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

    public void setEstadoMision(boolean estadoMision) {
        this.estadoMision = estadoMision;
    }

    // despues
    public boolean completarMision(Jugador jugador){
        // si el jugador cumple los requisitos...
        cb.exec();
        return true;
    }
    
    
    public void completarMision(){
        cb.exec();
        /* switch(idMision){
            case 1: 
                // recuperar algo en la cueva
                System.out.println("Recuperaste el caliz en la cueva");
                System.out.println("Ahora podes acceder a la cueva");
                // conexion y texto
                break;
            case 2:
                System.out.println("hola");
                break;
                
                    
        } */
        
        
    }
    
    
    @Override
    public String toString() {
        return "Mision{" + "objetivoMision=" + objetivoMision + ", estadoMision=" + estadoMision + '}';
    }

    
    
    
    
    
    
    
    
    
    
}

package parcial2eda;


public class Mision {
    
    public String objetivoMision;
    public int idMision;
    public boolean estadoMision;

    public Mision(String objetivoMision, boolean estadoMision) {
        this.objetivoMision = objetivoMision;
        this.estadoMision = estadoMision;
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
    public void completarMision(Jugador jugador){
        
        
        
        
    }
    
    
    public void completarMision(){
        
        switch(idMision){
            case 1: 
                // recuperar algo en la cueva
                System.out.println("Recuperaste el caliz en la cueva");
                System.out.println("Ahora podes acceder a la cueva");
                // conexion y texto
                break;
            case 2:
                System.out.println("hola");
                break;
                
                    
        }
        
        
    }
    
    
    @Override
    public String toString() {
        return "Mision{" + "objetivoMision=" + objetivoMision + ", estadoMision=" + estadoMision + '}';
    }

    
    
    
    
    
    
    
    
    
    
}
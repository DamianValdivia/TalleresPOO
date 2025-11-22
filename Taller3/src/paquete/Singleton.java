package paquete;

public class Singleton {
	
    private static Singleton unico;
    
  
    private Singleton() {
        
    }
    
    public static Singleton obtenerInstancia() {
        if (unico == null) {
            unico = new Singleton();
        }
        return unico;
    }
    
  
}



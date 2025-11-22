package paquete;

public class OrdenComplejidad implements Strategy{

	@Override
	public int calcular(Tarea tarea) {
		String complejidad = tarea.getComplejidad().toUpperCase();
        
        
        
        if (complejidad.equals("ALTA")) {
            return 3; 
            
        } else if (complejidad.equals("MEDIA")) {
            return 2;
            
        } else if (complejidad.equals("BAJA")) {
            return 1; 
            

        }
		return 0;
    }
}
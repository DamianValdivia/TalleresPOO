package paquete;

public class Factory {
	
	public static Tarea crearTarea(String tipo, String idProyecto, String id, String descripcion, String estado, 
            String user, String complejidad, String fecha) {

		String mayus = tipo.toUpperCase();

	if (mayus.equals("BUG")) {
	return new Bug(idProyecto, id, descripcion, estado, user, complejidad, fecha);
	
	} else if (mayus.equals("FEATURE") || mayus.equals("REQUISITO")) {
	return new Feature(idProyecto, id, descripcion, estado, user, complejidad, fecha);
	
	} else if (mayus.equals("DOCUMENTACION")) {
	return new Documentacion(idProyecto, id, descripcion, estado, user, complejidad, fecha);
	
	} else {
	return null;
	}
}
	
	
}
package paquete;

public class Documentacion extends Tarea{

	public Documentacion(String idProyecto, String id, String descripcion, String estado, String user,
			String complejidad, String fecha) {
		super(idProyecto, id, descripcion, estado, user, complejidad, fecha);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void aceptar(Visitor visitor) {
		// TODO Auto-generated method stub
		visitor.visitar(this);
		
		
	}

	@Override
	public String tipoImpacto() {
		// TODO Auto-generated method stub
		return "BAJO";
	}
	
	

}

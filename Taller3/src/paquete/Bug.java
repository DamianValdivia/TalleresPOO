package paquete;

public class Bug extends Tarea{

	public Bug(String idProyecto, String id, String descripcion, String estado, String user, String complejidad,
			String fecha) {
		super(idProyecto, id, descripcion, estado, user, complejidad, fecha);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void aceptar(Visitor visitor) {
		visitor.visitar(this);
		
	}

	@Override
	public String tipoImpacto() {
		// TODO Auto-generated method stub
		return "ALTO";
	}
	
	

}

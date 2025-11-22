package paquete;


public abstract class Tarea {
	private String idProyecto;
	private String id;
	private String descripcion;
	private String estado;
	private String user;
	private String complejidad;
	private String fecha;
	public Tarea(String idProyecto, String id, String descripcion, String estado, String user, String complejidad,
			String fecha) {
		super();
		this.idProyecto = idProyecto;
		this.id = id;
		this.descripcion = descripcion;
		this.estado = estado;
		this.user = user;
		this.complejidad = complejidad;
		this.fecha = fecha;
	}
	public String getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(String idProyecto) {
		this.idProyecto = idProyecto;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getComplejidad() {
		return complejidad;
	}
	public void setComplejidad(String complejidad) {
		this.complejidad = complejidad;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public abstract void aceptar(Visitor visitor);
	public abstract String tipoImpacto();

}

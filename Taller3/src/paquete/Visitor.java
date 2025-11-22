package paquete;

public interface Visitor {
	void visitar(Bug bug);
	void visitar(Feature requisito);
	void visitar(Documentacion documentacion);

}

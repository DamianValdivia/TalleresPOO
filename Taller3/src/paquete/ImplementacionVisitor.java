package paquete;

public class ImplementacionVisitor implements Visitor {

	@Override
	public void visitar(Bug bug) {
		System.out.println("VISITOR: Bug " + bug.getId() + " - Afectando la criticidad del Proyecto.");
		
	}

	@Override
	public void visitar(Feature requisito) {
		System.out.println("VISITOR: Requisito " + requisito.getId() + " - Impactando en la estimación de tiempo.");
		
	}

	@Override
	public void visitar(Documentacion documentacion) {
		System.out.println("VISITOR: Documentación " + documentacion.getId() + " - Mejorando la calidad del proyecto.");
		
	}

}

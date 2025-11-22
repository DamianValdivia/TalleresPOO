package paquete;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class OrdenFecha implements Strategy{
	
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
   
    private static final LocalDate HOY = LocalDate.now();

	@Override
	public int calcular(Tarea tarea) {
		LocalDate fechaTarea = LocalDate.parse(tarea.getFecha(), FORMATTER);
		long antiguedad = ChronoUnit.DAYS.between(fechaTarea, HOY);
		return (int) antiguedad;
	}

}

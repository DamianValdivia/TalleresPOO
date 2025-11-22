package paquete;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Sistema {
	
    private static Sistema unico;
    
    private List<Usuario> usuarios;
    private List<Proyecto> proyectos;
    
    private Sistema() {
        this.usuarios = new ArrayList<>();
        this.proyectos = new ArrayList<>();
        cargarArchivos();
    }
    
    public static Sistema obtenerInstancia() {
        if (unico == null) {
            unico = new Sistema();
        }
        return unico;
    }
    
    
   
    public void cargarArchivos() {
        cargarUsuarios("usuarios.txt");
        cargarProyectos("proyectos.txt");
        cargarTareas("tareas.txt");
    }

    private void cargarUsuarios(String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] partes = linea.split("\\|"); 
                if (partes.length >= 3) {
                    usuarios.add(new Usuario(partes[0], partes[1], partes[2]));
                
                }
            }}
         catch (IOException e) {
           
        }
    }
    
    private void cargarProyectos(String archivo) {
        try (BufferedReader lec = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lec.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] partes = linea.split("\\|"); 
                if (partes.length >= 3) {
                	proyectos.add(new Proyecto(partes[0], partes[1], partes[2]));
                }
            }
        } catch (IOException e) {
            
        }
    }

    private void cargarTareas(String archivo) {
        try (BufferedReader lec = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lec.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                
                String[] partes = linea.split("\\|");
                
                if (partes.length >= 8) {
                    String idProyecto = partes[0];
                    String tipo = partes[2];
                    
                    
                    Tarea nuevaTarea = Factory.crearTarea(
                        tipo, partes[0], partes[1], partes[3], partes[4], partes[5], partes[6], partes[7]
                    );
                    
                    if (nuevaTarea != null) {
                        Proyecto p = buscarProyectoPorId(idProyecto);
                        if (p != null) {
                            p.agregarTarea(nuevaTarea);
                        }
                   
                }}
            }
        }catch (IOException e) {
            
            
        }
    }
    
    
    
    public Usuario obtenerUsuario(String nombreUsuario, String contrasena) {
        for (Usuario u : usuarios) {
            if (u.getUser().equals(nombreUsuario) && u.getContraseña().equals(contrasena)) {
                return u;
            }
            
        }
        return null;
    }

    public Proyecto buscarProyectoPorId(String id) {
        for (Proyecto p : proyectos) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public Tarea buscarTareaPorId(String id) {
        for (Proyecto p : proyectos) {
            for (Tarea t : p.obtenerTareas()) {
                if (t.getId().equals(id)) {
                    return t;
                }
                
            }
        }
        
        return null;
    }

    

    public void agregarProyecto(String id, String nombre, String responsable) {
        if (buscarProyectoPorId(id) == null) {
            Proyecto nuevoProyecto = new Proyecto(id, nombre, responsable);
            proyectos.add(nuevoProyecto);
            System.out.println("Proyecto " + id + " " + nombre + " agregado exitosamente");
        }
        else {
            System.out.println("Error El ID de proyecto " + id + " ya existe");
        }
    }

    public void eliminarProyecto(String id) {
        Proyecto proyectoAEliminar = buscarProyectoPorId(id);
        if (proyectoAEliminar != null) {
            proyectos.remove(proyectoAEliminar);
            System.out.println("Proyecto " + id + " ELIMINADO");
        } 
        else {
            System.out.println("Error Proyecto " + id + " no encontrado");
        }
    }

    
    
    public void agregarTarea(String idProyecto, String tipo, String id, String descripcion, String estado, 
                             String user, String complejidad, String fecha) 
    {
        Proyecto p = buscarProyectoPorId(idProyecto);
        if (p == null) {
            System.out.println("Error Proyecto " + idProyecto + " no encontrado");
            return;
        }
        
        if (buscarTareaPorId(id) != null) {
            System.out.println("Error la tarea  " + id + " ya existe");
            return;
        }

        
        Tarea nuevaTarea = Factory.crearTarea(tipo, idProyecto, id, descripcion, estado, user, complejidad, fecha);
        
        if (nuevaTarea != null) {
            p.agregarTarea(nuevaTarea);
            System.out.println("Tarea " + id + " Tipo: " + tipo + " agregada a " + idProyecto);
        } 
    }

    public void eliminarTarea(String idTarea) {
        Tarea tareaAEliminar = buscarTareaPorId(idTarea);
        if (tareaAEliminar != null) {
            Proyecto p = buscarProyectoPorId(tareaAEliminar.getIdProyecto());
            if (p != null) {
                p.obtenerTareas().remove(tareaAEliminar);
                System.out.println("Tarea " + idTarea + " eliminada del proyecto " + p.getId());
                return;
            }
        }
        System.out.println("Error: Tarea " + idTarea + " no encontrada");
    }
    
    
    public void verListaCompleta() {
        System.out.println("LISTA PROYECTOS Y TAREAS");
        for (Proyecto p : proyectos) {
            System.out.println("PROYECTO: " + p.getId() + " - " + p.getNombre() + " Responsable: " + p.getResponsable());
            for (Tarea t : p.obtenerTareas()) {
                System.out.println("TAREA: " + t.getId() + " " + t.getEstado() + " - " + t.getDescripcion() + "Impacto: " + t.tipoImpacto());
            }
        }
    }
    
    public void generarReporte() {
        final String archivo = "reporte.txt";
        
    
        
        try {
            PrintWriter escritor = new PrintWriter(new FileWriter(archivo));
            
            escritor.println("");
            escritor.println("REPORTE DE PROYECTOS Y TAREAS");
            escritor.println("");

            for (Proyecto p : proyectos) {
                escritor.println(" PROYECTO: "+p.getId() + " - " + p.getNombre() + " ");
                escritor.println("Responsable: " + p.getResponsable());
                
                for (Tarea t : p.obtenerTareas()) {
                    escritor.println("Tarea ID: "+t.getId() + "Tipo Impacto: "+t.tipoImpacto());
                    escritor.println("Descripcion: "+t.getDescripcion());
                    escritor.println("Estado: "+t.getEstado() + " Complejidad: "+t.getComplejidad());
                }
            }
            
            escritor.println("==========================================");
            
            escritor.close();
            System.out.println("Reporte generado exitosamente " + archivo);

        } catch (IOException e) {
            
        }
    }

   
    
    public void verProyectosDisponibles() {
        System.out.println("PROYECTOS DISPONIBLES");
        for (Proyecto p : proyectos) {
            System.out.println("ID: " + p.getId() + " - Nombre: " + p.getNombre());
        }
    }

    public void verTareasAsignadas(String user) {
        System.out.println("TAREAS ASIGNADAS A: " + user);
        for (Proyecto p : proyectos) {
            for (Tarea t : p.obtenerTareas()) {
                if (t.getUser().equalsIgnoreCase(user)) {
                    System.out.println(" " + p.getId() + " Tarea " + t.getId() + ": " + t.getDescripcion() + " (Estado: " + t.getEstado() + ")");
                }
            }
            
            
        }
    }
    
    public void actualizarEstadoTarea(String idTarea, String nuevoEstado) {
        Tarea t = buscarTareaPorId(idTarea);
        String estadoUpper = nuevoEstado.toUpperCase();
        
        if (t == null) {
            System.out.println("Error Tarea " + idTarea + " no encontrada");
            return;
        }
        
        if (estadoUpper.equals("EN PROGRESO") || estadoUpper.equals("COMPLETADA") || estadoUpper.equals("PENDIENTE")) {
            t.setEstado(nuevoEstado);
            System.out.println("Estado de la Tarea " + idTarea + " actualizado a: " + nuevoEstado);
        } else {
            System.out.println("Error: Estado '" + nuevoEstado + "' no válido. Use 'Pendiente', 'En Progreso' o 'Completada'");
        }
    }


    
    public void asignarPrioridades(Strategy estrategia) {
    	
        System.out.println("Asignando prioridades: " + estrategia.getClass().getSimpleName());
        
        for (Proyecto p : proyectos) {
            List<Tarea> tareas = p.obtenerTareas();
            
           
            tareas.sort((t1, t2) -> estrategia.calcular(t2) - estrategia.calcular(t1)); 
            
            System.out.println("Prioridades en Proyecto " + p.getId() + " actualizadas");
        }
    }
    
    public void aplicarVisitorATarea(Tarea tarea) {
        
        Visitor visitor = new ImplementacionVisitor(); 
       
        tarea.aceptar(visitor); 
    }
    
    public void guardarArchivos() {

    }
}

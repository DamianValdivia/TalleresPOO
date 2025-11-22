package paquete;
//Damian Alexander Valdivia Rojas - 21038937-7 - ICCI
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        
        Sistema treyo = Sistema.obtenerInstancia(); 
        Scanner scanner = new Scanner(System.in);
        Usuario usuarioLogueado = null; 
        
        System.out.println("");
        System.out.println("BIENVENIDO A TREYO");
        System.out.println("");
        
     
        
        while (usuarioLogueado == null) {
            System.out.print("Ingrese usuario: ");
            String user = scanner.nextLine();
            System.out.print("Ingrese contraseña: ");
            String contra = scanner.nextLine();
            
            usuarioLogueado = treyo.obtenerUsuario(user, contra); 
            
            if (usuarioLogueado != null) {
                System.out.println("Login exitoso. Rol: " + usuarioLogueado.getRol());
            } else {
                System.out.println("Error de logueo");
            }
        }
        
        String rol = usuarioLogueado.getRol();
        
        if (rol.equalsIgnoreCase("Administrador")) {
            menuAdministrador(treyo, scanner);
        } else if (rol.equalsIgnoreCase("Colaborador")) {
            menuColaborador(treyo, scanner, usuarioLogueado);
        }
        
        treyo.guardarArchivos();
      
    }
    
    private static void menuAdministrador(Sistema treyo, Scanner scanner) {
        String opcion = "";
        while (!opcion.equals("0")) {
        	
            System.out.println("MENÚ ADMINISTRADOR");
            System.out.println("");
            System.out.println("1. Ver lista completa de proyectos y tareas");
            System.out.println("2. Agregar o eliminar un proyecto");
            System.out.println("3. Agregar o eliminar una tarea en un proyecto");
            System.out.println("4. Asignar prioridades");
            System.out.println("5. Generar reporte de proyectos");
            System.out.println("0. Cerrar sesión y Salir");
            System.out.println("");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextLine();
            
            if (opcion.equals("1")) {
                treyo.verListaCompleta();
            } else if (opcion.equals("2")) {
                System.out.println("1) Agregar Proyecto");
                System.out.println("2) Eliminar Proyecto");
                String op2 = scanner.nextLine().toUpperCase();
                if (op2.equals("1")) {
                    System.out.print("ID: "); String id = scanner.nextLine();
                    System.out.print("Nombre: "); String nombre = scanner.nextLine();
                    System.out.print("Responsable: "); String resp = scanner.nextLine();
                    treyo.agregarProyecto(id, nombre, resp);
                } else if (op2.equals("2")) {
                    System.out.print("ID del proyecto a eliminar: "); String id = scanner.nextLine();
                    treyo.eliminarProyecto(id);
                }
            } else if (opcion.equals("3")) {
                System.out.println("A) Agregar Tarea | E) Eliminar Tarea");
                String op3 = scanner.nextLine().toUpperCase();
                if (op3.equals("A")) {
                	
                    System.out.print("ID Proyecto: "); String idProy = scanner.nextLine();
                    System.out.print("Tipo: "); String tipo = scanner.nextLine();
                    System.out.print("ID Tarea: "); String id = scanner.nextLine();
                    System.out.print("Descripcion: "); String desc = scanner.nextLine();
                    System.out.print("Responsable: "); String user = scanner.nextLine();
                    System.out.print("Complejidad (Alta/Media/Baja): "); String comp = scanner.nextLine();
                    System.out.print("Fecha: "); String fecha = scanner.nextLine();
                    treyo.agregarTarea(idProy, tipo, id, desc, "Pendiente", user, comp, fecha); 
                    
                    
                } else if (op3.equals("E")) {
                    System.out.print("ID de la tarea a eliminar: "); String idT = scanner.nextLine();
                    treyo.eliminarTarea(idT);
                }
            } else if (opcion.equals("4")) {
                System.out.println("Estrategias: 1) Complejidad, 2) Fecha");
                System.out.print("Elija la estrategia: ");
                String op4 = scanner.nextLine();
                Strategy estrategia = null;
                if (op4.equals("1")) {
                    estrategia = new OrdenComplejidad();
                } else if (op4.equals("2")) {
                    estrategia = new OrdenFecha();
                }
                if (estrategia != null) {
                    treyo.asignarPrioridades(estrategia);
                } else {
                    System.out.println("Estrategia inválida.");
                }
            } else if (opcion.equals("5")) {
                treyo.generarReporte();
                
            } else if (opcion.equals("0")) {
                System.out.println("Cerrando sesión de Administrador");
            } else {
                System.out.println("Opcion invalida");
            }
        }
    }
    
    private static void menuColaborador(Sistema treyo, Scanner scanner, Usuario user) {
        String opcion = "";
        while (!opcion.equals("0")) {
        	
            System.out.println("-MENÚ COLABORADOR -");
            System.out.println("");
            System.out.println("1. Ver proyectos disponibles");
            System.out.println("2. Ver tareas asignadas");
            System.out.println("3. Actualizar estado de una tarea");
            System.out.println("4. Aplicar accion");
            System.out.println("0. Cerrar sesion y salir");
            System.out.println("");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextLine();
            
            if (opcion.equals("1")) {
                treyo.verProyectosDisponibles();
                
            }
            else if (opcion.equals("2")) {
                treyo.verTareasAsignadas(user.getUser());
            } 
            else if (opcion.equals("3")) {
            	
                System.out.print("Ingrese ID de la tarea: "); 
                String idTarea = scanner.nextLine();
                System.out.print("Ingrese nuevo estado (Pendiente/En Progreso/Completada): ");
                String nuevoEstado = scanner.nextLine();
                treyo.actualizarEstadoTarea(idTarea, nuevoEstado);
            }
            
            else if (opcion.equals("4")) {
            	
                System.out.print("Ingrese ID de la tarea: ");
                String idTareaVis = scanner.nextLine();
                Tarea tarea = treyo.buscarTareaPorId(idTareaVis);
                if (tarea != null) {
                    treyo.aplicarVisitorATarea(tarea);
                    
                } else
                {
                    System.out.println("Tarea no encontrada.");
                }
            } 
            else if 
            (opcion.equals("0")) {
            System.out.println("Cerrando sesión de Colaborador");
            } 
            else {
                System.out.println("Opción invalida, reintenta neuvamente");
            }
        }
        
    }}

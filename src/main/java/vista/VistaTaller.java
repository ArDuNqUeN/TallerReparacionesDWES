package vista;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import DAO.DBConnection;
import controlador.ControladorTaller;
import entities.Reparacion;

public class VistaTaller {

    public static void main(String[] args) {

        // INICIAR DATASOURCE
        DBConnection.getInstance();

        // PRUEBA
        try {
            DBConnection.getConnection();
            System.out.println("Conexión a la base de datos OK");
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos:");
            e.printStackTrace();
            return; 
        }

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            menuNavegacionGeneral();
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    iniciarSesion(sc);
                    break;
                case 2:
                    verReparacionesFinalizadas();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while(opcion != 0);

        sc.close();
    }

    private static void iniciarSesion(Scanner sc) {
        System.out.print("DNI: ");
        String dni = sc.nextLine();
        System.out.print("Contraseña: ");
        String password = sc.nextLine();

        boolean exito = ControladorTaller.getInstancia().login(dni, password);
        if (exito) {
            System.out.println("Login correcto!");
            mostrarMenuUsuario(sc);
        } else {
            System.out.println("DNI o contraseña incorrectos.");
        }
    }

    private static void mostrarMenuUsuario(Scanner sc) {
        boolean sesionActiva = true;

        while (sesionActiva) {
            String rol = ControladorTaller.getInstancia().getUsuarioActual().getRol();

            if ("ADMIN".equalsIgnoreCase(rol)) {
                menuAdmin();
            } else if ("MECANICO".equalsIgnoreCase(rol)) {
                menuMecanico();
            } else {
                System.out.println("Rol no reconocido, volviendo al menú general.");
                return;
            }

            int opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            if ("ADMIN".equalsIgnoreCase(rol)) {
                switch (opcion) {
                    case 1: listarClientes(); break;
                    case 2: listarVehiculos(); break;
                    case 3: gestionarUsuarios(sc); break;
                    case 4: registrarNuevaReparacion(sc); break;
                    case 5:  modificarEstadoReparacion(sc);;
                    case 6: verReparacionesFinalizadas(); break;
                    case 0:
                        ControladorTaller.getInstancia().cerrarSesion();
                        System.out.println("Sesión cerrada. Volviendo al menú de invitado...");
                        sesionActiva = false;
                        break;
                    default: System.out.println("Opción no válida.");
                }
            } else if ("MECANICO".equalsIgnoreCase(rol)) {
                switch (opcion) {
                    case 1: registrarNuevaReparacion(sc); break;
                    case 2: modificarEstadoReparacion(sc); break;
                    case 3: consultarReparacionesMecanico(sc); break;
                    case 0:
                        ControladorTaller.getInstancia().cerrarSesion();
                        System.out.println("Sesión cerrada. Volviendo al menú de invitado...");
                        sesionActiva = false;
                        break;
                    default: System.out.println("Opción no válida.");
                }
            }
        }
    }

    private static void gestionarUsuarios(Scanner sc) {
        boolean gestionActiva = true;
        ControladorTaller ctl = ControladorTaller.getInstancia();

        while (gestionActiva) {
            System.out.println("\n=== GESTIÓN DE USUARIOS ===");
            System.out.println("1. Listar usuarios");
            System.out.println("2. Crear usuario");
            System.out.println("3. Eliminar usuario");
            System.out.println("0. Volver al menú ADMIN");

            int opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1: 
                    ctl.listarUsuarios().forEach(System.out::println);
                    break;
                case 2: 
                    System.out.print("DNI: ");
                    String dni = sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Contraseña: ");
                    String pass = sc.nextLine();
                    System.out.print("Rol (ADMIN/MECANICO): ");
                    String rol = sc.nextLine().toUpperCase();
                    ctl.crearUsuario(new entities.Usuario(nombre, pass, rol, dni));
                    System.out.println("Usuario creado.");
                    break;
                case 3: 
                    System.out.print("DNI del usuario a eliminar: ");
                    String dniDel = sc.nextLine();
                    ctl.borrarUsuario(dniDel);
                    System.out.println("Usuario eliminado si existía.");
                    break;
                case 0:
                    gestionActiva = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void registrarNuevaReparacion(Scanner sc) {
        ControladorTaller ctl = ControladorTaller.getInstancia();

        System.out.print("Matrícula del vehículo: ");
        String matricula = sc.nextLine();
        System.out.print("Descripción: ");
        String descripcion = sc.nextLine();
        System.out.print("Coste estimado: ");
        double coste = sc.nextDouble();
        sc.nextLine();
        System.out.print("Estado inicial (PENDIENTE/FINALIZADA): ");
        String estadoStr = sc.nextLine().toUpperCase();

        java.sql.Date fechaEntrada = new java.sql.Date(System.currentTimeMillis());
        entities.Estado estado = entities.Estado.valueOf(estadoStr);

        entities.Reparacion r = new entities.Reparacion(matricula, descripcion, fechaEntrada, coste, estado);
        ctl.registrarReparacion(r);

        System.out.println("Reparación registrada correctamente.");
    }

    private static void modificarEstadoReparacion(Scanner sc) {
        ControladorTaller ctl = ControladorTaller.getInstancia();

        System.out.print("Matrícula del vehículo: ");
        String matricula = sc.nextLine();

        System.out.print("Nuevo estado (PENDIENTE/FINALIZADA): ");
        String estadoStr = sc.nextLine().toUpperCase();
        entities.Estado nuevoEstado = entities.Estado.valueOf(estadoStr);

        boolean exito = ctl.cambiarEstadoReparacionPorMatricula(matricula, nuevoEstado);
        if (exito) {
            System.out.println("Estado actualizado correctamente.");
        } else {
            System.out.println("No se encontró reparación para esa matrícula.");
        }
    }

    private static void listarClientes() {
        ControladorTaller.getInstancia().listarClientes()
                .forEach(System.out::println);
    }

    private static void listarVehiculos() {
        ControladorTaller.getInstancia().listarVehiculos()
                .forEach(System.out::println);
    }

    private static void verReparacionesFinalizadas() {
        List<Reparacion> finalizadas = ControladorTaller.getInstancia().listarReparacionesFinalizadas();
        if (finalizadas.isEmpty()) {
            System.out.println("No hay reparaciones finalizadas.");
        } else {
            System.out.println("=== Reparaciones Finalizadas ===");
            for (Reparacion r : finalizadas) {
                System.out.println(r);
            }
        }
    }
    
    private static void consultarReparacionesMecanico(Scanner sc) {
        ControladorTaller ctl = ControladorTaller.getInstancia();

        System.out.println("=== LISTADO DE TODAS LAS REPARACIONES ===");
        ctl.listarReparaciones().forEach(System.out::println);
    }



    private static void menuNavegacionGeneral() {
        System.out.println("\n\\MENÚ DE LA APLICACIÓN/");
        System.out.println("Escoge lo que quieres hacer: ");
        System.out.println("0. Salir");
        System.out.println("1. Iniciar sesión");
        System.out.println("2. Ver reparaciones finalizadas");
    }

    private static void menuAdmin() {
        System.out.println("\n=== MENÚ ADMINISTRADOR ===");
        System.out.println("1. Gestionar clientes");
        System.out.println("2. Gestionar vehículos");
        System.out.println("3. Gestionar usuarios");
        System.out.println("4. Registrar nueva reparación");
        System.out.println("5. Modificar estado de reparación");
        System.out.println("6. Consultar reparaciones");
        System.out.println("0. Cerrar sesión");
    }

    private static void menuMecanico() {
        System.out.println("\n=== MENÚ MECÁNICO ===");
        System.out.println("1. Registrar nueva reparación");
        System.out.println("2. Modificar estado de reparación");
        System.out.println("3. Consultar reparaciones");
        System.out.println("0. Cerrar sesión");
    }
}




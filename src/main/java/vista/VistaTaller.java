package vista;

import java.util.Scanner;

import controlador.ControladorTaller;

public class VistaTaller {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            menuNavegacionGeneral();
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch(opcion) {
                case 1:
                    iniciarSesion(sc);
                    break;
                case 2:
                    //verReparacionesFinalizadas();
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
        System.out.print("Usuario: ");
        String nombre = sc.nextLine();
        System.out.print("Contraseña: ");
        String password = sc.nextLine();

        boolean exito = ControladorTaller.getInstancia().login(nombre, password);
        if (exito) {
            System.out.println("Login correcto!");
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }
    
    private static void menuNavegacionGeneral() {
        System.out.println("\\MENÚ DE LA APLICACIÓN//");
        System.out.println("Escoge lo que quieres hacer: ");
        System.out.println("0. Salir");
        System.out.println("1. Iniciar sesión");
        System.out.println("2. Ver reparaciones finalizadas");
    }

}


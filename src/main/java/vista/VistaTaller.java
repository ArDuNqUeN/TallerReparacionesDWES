package vista;

import java.util.Scanner;

public class VistaTaller {
public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	int opcion;
	 do {
         menuNavegacionGeneral();
         opcion = sc.nextInt();
         sc.nextLine(); // LIMPIAR BUFFER

         switch (opcion) {
             case 1:
                 iniciarSesion(sc);
                 break;
             case 2:
                verReparacionesFinalizadas();
                 break;
             default:
                 System.out.println("Opción no válida. Inténtalo de nuevo.");
         }

     } while (opcion!=0);
    	 System.out.println("Saliendo del sistema...");
    	 sc.close();

}

	public void iniciarSesion(Scanner sc) {
	
}
	public void verReparacionesFinalizadas() {
		
	}
	public static void menuNavegacionGeneral () {
		System.out.println("\\MENÚ DE LA APLICACIÓN//");
		System.out.println("Escoga que quiere hacer: \n");
		System.out.println("0. Salir de la aplicación");
		System.out.println("1. Iniciar sesión");
		System.out.println("2. Ver reparaciones finalizadas");
	}
}


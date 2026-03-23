package es.damdi.francisco;

import es.damdi.francisco.operaciones.GestorOperaciones;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Main {
    public static void main(String[] args) {
        GestorOperaciones gestor = new GestorOperaciones();
        int opcion;

        do {
            System.out.println("\n**************************************");
            System.out.println("     GESTIÓN CENTRO PROFESORES");
            System.out.println("**************************************");
            System.out.println("1.- Alta de Especialidad");
            System.out.println("0.- Salir");
            System.out.print("Selecciona una opción: ");

            opcion = Entrada.entero();

            switch (opcion) {
                case 1:
                    System.out.print("Introduce el código (ej. IN): ");
                    String id = Entrada.cadena();
                    System.out.print("Introduce el nombre: ");
                    String nombre = Entrada.cadena();
                    gestor.altaEspecialidad(id, nombre);
                    break;
                case 0:
                    System.out.println("Cerrando aplicación...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }
}

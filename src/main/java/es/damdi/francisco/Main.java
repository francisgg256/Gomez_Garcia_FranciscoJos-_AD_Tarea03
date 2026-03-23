package es.damdi.francisco;

import es.damdi.francisco.operaciones.GestorOperaciones;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Main {
    public static void main(String[] args) {
        GestorOperaciones gestor = new GestorOperaciones();
        int opcion;

        do {
            System.out.println("\n*************************************************");
            System.out.println("           GESTIÓN CENTRO DE PROFESORES");
            System.out.println("*************************************************");
            System.out.println("--- PROFESORES ---");
            System.out.println(" 1.- Dar de alta un profesor");
            System.out.println(" 2.- Dar de baja un profesor");
            System.out.println(" 3.- Información de un profesor");
            System.out.println(" 4.- Listado de profesores");
            System.out.println("--- CENTROS ---");
            System.out.println(" 5.- Dar de alta un centro");
            System.out.println(" 6.- Dar de baja un centro");
            System.out.println(" 7.- Modificar el director del centro");
            System.out.println(" 8.- Listado de centros");
            System.out.println("--- ESPECIALIDADES ---");
            System.out.println(" 9.- Dar de alta una especialidad");
            System.out.println("10.- Dar de baja una especialidad");
            System.out.println("11.- Modificar el nombre de una especialidad");
            System.out.println("12.- Listado de especialidades");
            System.out.println("--- ASIGNATURAS ---");
            System.out.println("13.- Dar de alta una asignatura");
            System.out.println("14.- Dar de baja una asignatura");
            System.out.println("15.- Listado de asignaturas y sus profesores");
            System.out.println(" 0.- Salir");
            System.out.println("*************************************************");
            System.out.print("Selecciona una opción: ");

            opcion = Entrada.entero();

            switch (opcion) {
                // Casos profesores
                case 1:
                    System.out.print("Introduce el ID del nuevo profesor (ej. 4000): ");
                    int idProf = Entrada.entero();
                    System.out.print("Introduce el nombre y el apellido del nuevo profesor: ");
                    String nomProf = Entrada.cadena();
                    System.out.print("Introduce el sexo del nuevo profesor(H/M): ");
                    String sexo = Entrada.cadena();
                    System.out.print("Introduce el ID de la especialidad del nuevo profesor(ej. IN): ");
                    String idEsp = Entrada.cadena();
                    System.out.print("Introduce el código del centro del nuevo profesor(ej. 10): ");
                    int idCent = Entrada.entero();
                    gestor.altaProfesor(idProf, nomProf, sexo, idEsp, idCent);
                    break;

                case 2:
                    System.out.print("Introduce el ID del profesor que se quiere dar de baja: ");
                    int idBajaProf = Entrada.entero();
                    gestor.bajaProfesor(idBajaProf);
                    break;

                case 3:
                    System.out.print("Introduce el ID del profesor del que se quiere ver la información: ");
                    int idInfoProf = Entrada.entero();
                    gestor.infoProfesor(idInfoProf);
                    break;

                case 4:
                    System.out.println("Cargando la lista de todos los profesores.");
                    gestor.listarProfesores();
                    break;

                // Casos centros
                case 5:
                    System.out.print("Introduce el código del centro que se quiere añadir(ej. 10): ");
                    int idCentro = Entrada.entero();
                    System.out.print("Introduce el nombre del centro que se quiere añadir: ");
                    String nomCentro = Entrada.cadena();
                    System.out.print("Introduce la dirección del centro que se quiere añadir: ");
                    String direccion = Entrada.cadena();
                    System.out.print("Introduce la localidad del centro que se quiere añadir: ");
                    String localidad = Entrada.cadena();
                    System.out.print("Introduce la provincia del centro que se quiere añadir: ");
                    String provincia = Entrada.cadena();
                    gestor.altaCentro(idCentro, nomCentro, direccion, localidad, provincia);
                    break;

                case 6:
                    System.out.print("Introduce el código del centro que se quiere dar de baja: ");
                    int idBajaCentro = Entrada.entero();
                    gestor.bajaCentro(idBajaCentro);
                    break;

                case 7:
                    System.out.print("Introduce el código del centro que se quiere modificar: ");
                    int idCentroMod = Entrada.entero();
                    System.out.print("Introduce el ID del nuevo director del centro(ej. 1000): ");
                    int idNuevoDirector = Entrada.entero();
                    gestor.modificarDirector(idCentroMod, idNuevoDirector);
                    break;

                case 8:
                    System.out.println("Cargando la lista de todos los centros.");
                    gestor.listarCentros();
                    break;

                // Casos especialidades
                case 9:
                    System.out.print("Introduce el código de la nueva especialidad (ej. IN): ");
                    String idAltaEsp = Entrada.cadena();
                    System.out.print("Introduce el nombre de la nueva especialidad: ");
                    String nombreAltaEsp = Entrada.cadena();
                    gestor.altaEspecialidad(idAltaEsp, nombreAltaEsp);
                    break;

                case 10:
                    System.out.print("Introduce el código de la especialidad que se quiere dar de baja: ");
                    String idBajaEsp = Entrada.cadena();
                    gestor.bajaEspecialidad(idBajaEsp);
                    break;

                case 11:
                    System.out.print("Introduce el código de la especialidad que se quiere modificar: ");
                    String idModEsp = Entrada.cadena();
                    System.out.print("Introduce el NUEVO nombre: ");
                    String nuevoNombre = Entrada.cadena();
                    gestor.modificarEspecialidad(idModEsp, nuevoNombre);
                    break;

                case 12:
                    System.out.println("Cargando la lista de todas las especialidades.");
                    gestor.listarEspecialidades();
                    break;

                // Casos asignaturas
                case 13:
                    System.out.print("Introduce código de la nueva asignatura (ej. A01): ");
                    String codAsig = Entrada.cadena();
                    System.out.print("Introduce nombre de la nueva asignatura: ");
                    String nomAsig = Entrada.cadena();
                    gestor.altaAsignatura(codAsig, nomAsig);
                    break;

                case 14:
                    System.out.print("Introduce código de la asignatura que se quiere dar de baja: ");
                    String idBajaAsig = Entrada.cadena();
                    gestor.bajaAsignatura(idBajaAsig);
                    break;

                case 15:
                    System.out.println("Cargando la lista de todas las asignaturas y de todos los profesores.");
                    gestor.listarAsignaturasYProfesores();
                    break;

                // Salir
                case 0:
                    System.out.println("Cerrando aplicación.");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, elige un número del menú (0-15).");
            }
        } while (opcion != 0);
    }
}

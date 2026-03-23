package es.damdi.francisco;

import es.damdi.francisco.operaciones.GestorOperaciones;

public class Main {
    public static void main(String[] args) {

        System.out.println("Iniciando programa...");
        GestorOperaciones gestor = new GestorOperaciones();
        gestor.altaEspecialidad("PR", "Programación");

        System.out.println("Fin del programa.");
    }
}

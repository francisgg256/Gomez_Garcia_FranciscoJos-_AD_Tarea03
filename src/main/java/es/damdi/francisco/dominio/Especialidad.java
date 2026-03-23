package es.damdi.francisco.dominio;

import java.io.Serializable;

public class Especialidad implements Serializable {

    private String idEspecialidad;
    private String nombre;

    public Especialidad() {
    }

    public Especialidad(String idEspecialidad, String nombre) {
        this.idEspecialidad = idEspecialidad;
        this.nombre = nombre;
    }

    public String getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(String idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Especialidad{" +
                "id='" + idEspecialidad + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

package es.damdi.francisco.dominio;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Profesor implements Serializable {

    private int codProf;
    private String nombreApe;
    private Date fechaNac;
    private String sexo;

    private Especialidad especialidad;

    private Centro centro;

    private Profesor jefe;

    private Set<Asignatura> asignaturas = new HashSet<>();

    public Profesor() {
    }

    public int getCodProf() {
        return codProf;
    }

    public void setCodProf(int codProf) {
        this.codProf = codProf;
    }

    public String getNombreApe() {
        return nombreApe;
    }

    public void setNombreApe(String nombreApe) {
        this.nombreApe = nombreApe;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public Centro getCentro() {
        return centro;
    }

    public void setCentro(Centro centro) {
        this.centro = centro;
    }

    public Profesor getJefe() {
        return jefe;
    }

    public void setJefe(Profesor jefe) {
        this.jefe = jefe;
    }

    public Set<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(Set<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "codProf=" + codProf +
                ", nombreApe='" + nombreApe + '\'' +
                ", sexo='" + sexo + '\'' +
                '}';
    }
}

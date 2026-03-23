package es.damdi.francisco.dominio;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Centro implements Serializable {

    private int codCentro;
    private String nomCentro;
    private String direccion;
    private String localidad;
    private String provincia;

    private Profesor director;

    private Set<Profesor> profesores = new HashSet<>();

    public Centro() {
    }

    public int getCodCentro() {
        return codCentro;
    }

    public void setCodCentro(int codCentro) {
        this.codCentro = codCentro;
    }

    public String getNomCentro() {
        return nomCentro;
    }

    public void setNomCentro(String nomCentro) {
        this.nomCentro = nomCentro;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public Profesor getDirector() {
        return director;
    }

    public void setDirector(Profesor director) {
        this.director = director;
    }

    public Set<Profesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(Set<Profesor> profesores) {
        this.profesores = profesores;
    }

    @Override
    public String toString() {
        return "Centro{" +
                "codCentro=" + codCentro +
                ", nomCentro='" + nomCentro + '\'' +
                ", direccion='" + direccion + '\'' +
                ", localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                '}';
    }
}

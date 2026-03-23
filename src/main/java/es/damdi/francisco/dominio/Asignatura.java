package es.damdi.francisco.dominio;

import java.io.Serializable;

public class Asignatura implements Serializable {

    private String codAsig;
    private String nombreAsi;

    public Asignatura() {
    }

    public Asignatura(String codAsig, String nombreAsi) {
        this.codAsig = codAsig;
        this.nombreAsi = nombreAsi;
    }

    public String getCodAsig() {
        return codAsig;
    }

    public void setCodAsig(String codAsig) {
        this.codAsig = codAsig;
    }

    public String getNombreAsi() {
        return nombreAsi;
    }

    public void setNombreAsi(String nombreAsi) {
        this.nombreAsi = nombreAsi;
    }

    @Override
    public String toString() {
        return "Asignatura{" +
                "codAsig='" + codAsig + '\'' +
                ", nombreAsi='" + nombreAsi + '\'' +
                '}';
    }
}
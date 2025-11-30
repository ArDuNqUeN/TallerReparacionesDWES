package entities;

import java.sql.Date;

public class Reparacion {

    private String matriculaV; 
    private String descripcion;
    private Date fechaEntrada;
    private double costeEstimado;
    private Estado estado;

    
    public Reparacion(String matriculaV, String descripcion, Date fechaEntrada, double costeEstimado, Estado estado) {
        this.matriculaV = matriculaV;
        this.descripcion = descripcion;
        this.fechaEntrada = fechaEntrada;
        this.costeEstimado = costeEstimado;
        this.estado = estado;
    }

 
  
    public String getMatriculaV() {
        return matriculaV;
    }

    public void setMatriculaV(String matriculaV) {
        this.matriculaV = matriculaV;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public double getCosteEstimado() {
        return costeEstimado;
    }

    public void setCosteEstimado(double costeEstimado) {
        this.costeEstimado = costeEstimado;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Reparacion: matricula= " + matriculaV 
                + ", descripcion= " + descripcion
                + ", fechaEntrada= " + fechaEntrada
                + ", costeEstimado= " + costeEstimado
                + ", estado= " + estado + ".";
    }
}



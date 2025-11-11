package entities;

import java.sql.Date;

public class Reparacion {

	private int id_reparacion;
	private String descripcion;
	private Date fechaEntrada;
	private double costeEstimado;
	private String estado;
	
	
	public Reparacion(int id_reparacion, String descripcion, Date fechaEntrega, double costeEstimado, String estado) {
		super();
		this.id_reparacion = id_reparacion;
		this.descripcion = descripcion;
		this.fechaEntrada = fechaEntrega;
		this.costeEstimado = costeEstimado;
		this.estado = estado;
	}


	public int getId_reparacion() {
		return id_reparacion;
	}


	public void setId_reparacion(int id_reparacion) {
		this.id_reparacion = id_reparacion;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Date getFechaEntrega() {
		return fechaEntrada;
	}


	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrada = fechaEntrega;
	}


	public double getCosteEstimado() {
		return costeEstimado;
	}


	public void setCosteEstimado(double costeEstimado) {
		this.costeEstimado = costeEstimado;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
}

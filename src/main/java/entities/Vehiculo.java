package entities;

public class Vehiculo {

	private int id_vehiculo;
	private String matricula;
	private String marca;
	private String modelo;
	private int idCliente;
	
	public Vehiculo(int id_vehiculo, String matricula, String marca, String modelo, int idCliente) {
		super();
		this.id_vehiculo = id_vehiculo;
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.idCliente= idCliente;
	}
	public int getId_vehiculo() {
		return id_vehiculo;
	}
	public void setId_vehiculo(int id_vehiculo) {
		this.id_vehiculo = id_vehiculo;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
		public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	@Override
	public String toString() {
		return "Vehiculo [id_vehiculo=" + id_vehiculo + ", matricula=" + matricula + ", marca=" + marca + ", modelo="
				+ modelo + "]";
	}

	
	
}

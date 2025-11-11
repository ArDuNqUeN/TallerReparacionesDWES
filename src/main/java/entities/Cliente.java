package entities;

public class Cliente {
	private int id_cliente;
	private String nombre;
	private String email;
	private String dni;
 
 public Cliente(int id_cliente, String nombre, String email, String dni) {
	super();
	this.id_cliente = id_cliente;
	this.nombre = nombre;
	this.email = email;
	this.dni = dni;
 }

 public int getId_cliente() {
	return id_cliente;
 }

 public void setId_cliente(int id_cliente) {
	this.id_cliente = id_cliente;
 }

 public String getNombre() {
	return nombre;
 }

 public void setNombre(String nombre) {
	this.nombre = nombre;
 }

 public String getEmail() {
	return email;
 }

 public void setEmail(String email) {
	this.email = email;
 }

 public String getDni() {
	return dni;
 }

 public void setDni(String dni) {
	this.dni = dni;
 }

 @Override
 public String toString() {
	return "Cliente [id_cliente=" + id_cliente + ", nombre=" + nombre + ", email=" + email + ", dni=" + dni + "]";
 }
 
 
 
}

package entities;

public class Usuario {

	
	private int id_usuario;
	private String nombreUsuario;
	private String password;
	private String rol;
	private String dniUsuario;
	
	public Usuario(int id_usuario, String nombreUsuario, String password, String rol, String dniUsuario) {
		super();
		this.id_usuario = id_usuario;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.rol = rol;
		this.dniUsuario = dniUsuario;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getDniUsuario() {
		return dniUsuario;
	}
	public void setDniUsuario(String dniUsuario) {
		this.dniUsuario = dniUsuario;
	}
	@Override
	public String toString() {
		return "Usuario [id_usuario=" + id_usuario + ", nombreUsuario=" + nombreUsuario + ", password=" + password
				+ ", rol=" + rol + ", dniUsuario=" + dniUsuario + "]";
	}
	
	
}

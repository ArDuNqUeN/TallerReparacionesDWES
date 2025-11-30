package entities;

public class Usuario {

    private String nombreUsuario;
    private String password;
    private String rol;
    private String dniUsuario; 

    public Usuario(String nombreUsuario, String password, String rol, String dniUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.rol = rol;
        this.dniUsuario = dniUsuario;
    }

    // Getters y setters
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
        return "Usuario: nombreUsuario= " + nombreUsuario
                + ", password= " + password
                + ", rol= " + rol
                + ", dniUsuario= " + dniUsuario + ".";
    }
}

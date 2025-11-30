package entities;

public class Cliente {
    private String nombre;
    private String email;
    private String dni;  

    public Cliente(String nombre, String email, String dni) {
        this.nombre = nombre;
        this.email = email;
        this.dni = dni;
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
        return "Cliente: nombre= " + nombre + ", email= " + email + ", dni= " + dni + ".";
    }
}


package entities;

public class Vehiculo {

    private String matricula;
    private String marca;
    private String modelo;
    private String dniCliente; 

    public Vehiculo(String matricula, String marca, String modelo, String dniCliente) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.dniCliente = dniCliente;
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
    public String getDniCliente() {
        return dniCliente;
    }
    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    @Override
    public String toString() {
        return "Vehiculo: matricula= " + matricula + ", marca= " + marca + ", modelo= " + modelo + ", dniCliente= " + dniCliente + ".";
    }
}

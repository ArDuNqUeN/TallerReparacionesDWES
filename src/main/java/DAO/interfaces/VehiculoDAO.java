package DAO.interfaces;

import java.util.List;

import entities.Vehiculo;

public interface VehiculoDAO {
    int insertarVehiculo(Vehiculo vehiculo);
    Vehiculo obtenerVehiculoPorId(int id);
    List<Vehiculo> obtenerTodos();
    boolean actualizarVehiculo(Vehiculo vehiculo);
    boolean eliminarVehiculo(int id);
}

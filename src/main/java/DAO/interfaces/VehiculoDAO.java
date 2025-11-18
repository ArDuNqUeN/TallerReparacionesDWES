package DAO.interfaces;

import java.sql.SQLException;
import java.util.List;

import entities.Vehiculo;

public interface VehiculoDAO {
    void insertarVehiculo(Vehiculo vehiculo) throws SQLException;
    Vehiculo obtenerVehiculoPorId(int id);
    List<Vehiculo> obtenerTodos();
    boolean actualizarVehiculo(Vehiculo vehiculo);
    boolean eliminarVehiculo(int id);
}

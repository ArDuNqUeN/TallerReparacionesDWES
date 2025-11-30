package DAO.interfaces;

import java.sql.SQLException;
import java.util.List;
import entities.Vehiculo;

public interface VehiculoDAO {
    boolean insertarVehiculo(Vehiculo vehiculo) throws SQLException;
    Vehiculo obtenerVehiculoPorMatricula(String matricula);
    List<Vehiculo> obtenerTodos();
    boolean actualizarVehiculo(Vehiculo vehiculo);
    boolean eliminarVehiculo(String matricula);
}

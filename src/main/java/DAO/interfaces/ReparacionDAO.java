package DAO.interfaces;

import java.util.List;
import entities.Estado;
import entities.Reparacion;

public interface ReparacionDAO {

    boolean insertarReparacion(Reparacion reparacion);

    List<Reparacion> obtenerTodas();

    // Obtener reparaciones por matrícula del vehículo
    List<Reparacion> obtenerReparacionPorMatricula(String matricula);

    // Obtener reparaciones por DNI del cliente
    List<Reparacion> obtenerReparacionPorDni(String dniCliente);

    boolean actualizarReparacion(Reparacion reparacion, String matricula);

    boolean eliminarReparacion(String matricula);

    boolean cambiarEstado(int filaId, Estado nuevoEstado);
}

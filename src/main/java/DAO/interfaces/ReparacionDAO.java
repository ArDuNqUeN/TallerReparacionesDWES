package DAO.interfaces;

import java.util.List;

import entities.Reparacion;

public interface ReparacionDAO {
    boolean insertarReparacion(Reparacion reparacion);
    Reparacion obtenerReparacionPorId(int id);
    List<Reparacion> obtenerTodas();
    boolean actualizarReparacion(Reparacion reparacion);
    boolean eliminarReparacion(int id);
    boolean cambiarEstado(int id, Reparacion.Estado estado);
}

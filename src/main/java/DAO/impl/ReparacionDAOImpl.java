package DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.DBConnection;
import DAO.interfaces.ReparacionDAO;
import entities.Estado;
import entities.Reparacion;

public class ReparacionDAOImpl implements ReparacionDAO {

    @Override
    public boolean insertarReparacion(Reparacion reparacion) {
        String sql = "INSERT INTO reparaciones (matricula, descripcion, fechaEntrada, costeEstimado, estado) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, reparacion.getMatriculaV());
            ps.setString(2, reparacion.getDescripcion());
            ps.setDate(3, reparacion.getFechaEntrada());
            ps.setDouble(4, reparacion.getCosteEstimado());
            ps.setString(5, reparacion.getEstado().name());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar reparación: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Reparacion> obtenerTodas() {
        List<Reparacion> reparaciones = new ArrayList<>();
        String sql = "SELECT * FROM reparaciones";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Reparacion r = new Reparacion(
                        rs.getString("matricula"),
                        rs.getString("descripcion"),
                        rs.getDate("fechaEntrada"),
                        rs.getDouble("costeEstimado"),
                        Estado.valueOf(rs.getString("estado"))
                );
                reparaciones.add(r);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener reparaciones: " + e.getMessage());
        }
        return reparaciones;
    }

    @Override
    public List<Reparacion> obtenerReparacionPorMatricula(String matricula) {
        List<Reparacion> reparaciones = new ArrayList<>();
        String sql = "SELECT * FROM reparaciones WHERE matricula = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, matricula);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reparacion r = new Reparacion(
                        rs.getString("matricula"),
                        rs.getString("descripcion"),
                        rs.getDate("fechaEntrada"),
                        rs.getDouble("costeEstimado"),
                        Estado.valueOf(rs.getString("estado"))
                );
                reparaciones.add(r);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener reparaciones por matrícula: " + e.getMessage());
        }
        return reparaciones;
    }

    @Override
    public List<Reparacion> obtenerReparacionPorDni(String dniCliente) {
        List<Reparacion> reparaciones = new ArrayList<>();
        String sql = "SELECT r.* FROM reparaciones r JOIN vehiculos v ON r.matricula = v.matricula WHERE v.id_cliente = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dniCliente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reparacion r = new Reparacion(
                        rs.getString("matricula"),
                        rs.getString("descripcion"),
                        rs.getDate("fechaEntrada"),
                        rs.getDouble("costeEstimado"),
                        Estado.valueOf(rs.getString("estado"))
                );
                reparaciones.add(r);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener reparaciones por DNI: " + e.getMessage());
        }
        return reparaciones;
    }

    @Override
    public boolean actualizarReparacion(Reparacion reparacion, String matricula) {
        String sql = "UPDATE reparaciones SET descripcion=?, fechaEntrada=?, costeEstimado=?, estado=? WHERE matricula=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, reparacion.getDescripcion());
            ps.setDate(2, reparacion.getFechaEntrada());
            ps.setDouble(3, reparacion.getCosteEstimado());
            ps.setString(4, reparacion.getEstado().name());
            ps.setString(5, matricula);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar reparación: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminarReparacion(String matricula) {
        String sql = "DELETE FROM reparaciones WHERE matricula=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, matricula);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar reparación: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean cambiarEstado(int filaId, Estado nuevoEstado) {
        // Como no usamos id numérico, este método puede delegar a actualizarReparacion
        Reparacion r = obtenerTodas().stream()
                .filter(rep -> rep.getMatriculaV().equals(String.valueOf(filaId))) // si quieres, se podría adaptar
                .findFirst()
                .orElse(null);
        if (r != null) {
            r.setEstado(nuevoEstado);
            return actualizarReparacion(r, r.getMatriculaV());
        }
        return false;
    }
}


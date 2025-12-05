package DAO.impl;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAO.DBConnection;
import DAO.interfaces.ReparacionDAO;
import entities.Reparacion;

public class ReparacionDAOImpl implements ReparacionDAO {

    @Override
    public boolean insertarReparacion(Reparacion reparacion) {
        String sql = "INSERT INTO reparacion (id_vehiculo, descripcion, fecha_entrada, coste_estimado, estado) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reparacion.getId_reparacion());
            stmt.setString(2, reparacion.getDescripcion());
            stmt.setDate(3, reparacion.getFechaEntrega());
            stmt.setDouble(4, reparacion.getCosteEstimado());
            stmt.setString(5, reparacion.getEstado());

            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error insertando reparación: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Reparacion obtenerReparacionPorId(int id) {
        String sql = "SELECT * FROM reparacion WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Reparacion(
                		 rs.getInt("id_raparacion"),
                         rs.getString("descripcion"),
                         rs.getDate("fecha_entrada"),
                         rs.getDouble("coste_estimado"),
                         rs.getString("estado")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error buscando reparación: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Reparacion> obtenerTodas() {
        List<Reparacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM reparacion";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Reparacion(
                        rs.getInt("id_raparacion"),
                        rs.getString("descripcion"),
                        rs.getDate("fecha_entrada"),
                        rs.getDouble("coste_estimado"),
                        rs.getString("estado")
                        
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error listando reparaciones: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public boolean actualizarReparacion(Reparacion reparacion) {
        String sql = "UPDATE reparacion SET id_vehiculo=?, descripcion=?, fecha_entrada=?, coste_estimado=?, estado=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

        	stmt.setInt(1, reparacion.getId_reparacion());
            stmt.setString(2, reparacion.getDescripcion());
            stmt.setDate(3, reparacion.getFechaEntrega());
            stmt.setDouble(4, reparacion.getCosteEstimado());
            stmt.setString(5, reparacion.getEstado());

            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error actualizando reparación: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminarReparacion(int id) {
        String sql = "DELETE FROM reparacion WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error eliminando reparación: " + e.getMessage());
            return false;
        }
    }

 

	@Override
	public boolean cambiarEstado(int id, Reparacion estado) {
		// TODO Auto-generated method stub
		return false;
	}
}



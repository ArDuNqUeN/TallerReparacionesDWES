package DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAO.DBConnection;
import DAO.interfaces.VehiculoDAO;
import entities.Vehiculo;

public class VehiculoDAOImpl implements VehiculoDAO {

    @Override
    public boolean insertarVehiculo(Vehiculo vehiculo) throws SQLException {
        String sql = "INSERT INTO vehiculo (matricula, marca, modelo, dni_cliente) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehiculo.getMatricula());
            stmt.setString(2, vehiculo.getMarca());
            stmt.setString(3, vehiculo.getModelo());
            stmt.setString(4, vehiculo.getDniCliente());

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public Vehiculo obtenerVehiculoPorMatricula(String matricula) {
        String sql = "SELECT * FROM vehiculo WHERE matricula=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, matricula);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Vehiculo(
                        rs.getString("matricula"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getString("dni_cliente")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error buscando vehículo: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Vehiculo> obtenerTodos() {
        List<Vehiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM vehiculo";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Vehiculo(
                        rs.getString("matricula"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getString("dni_cliente")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error listando vehículos: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean actualizarVehiculo(Vehiculo vehiculo) {
        String sql = "UPDATE vehiculo SET marca=?, modelo=?, dni_cliente=? WHERE matricula=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehiculo.getMarca());
            stmt.setString(2, vehiculo.getModelo());
            stmt.setString(3, vehiculo.getDniCliente());
            stmt.setString(4, vehiculo.getMatricula());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error actualizando vehículo: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminarVehiculo(String matricula) {
        String sql = "DELETE FROM vehiculo WHERE matricula=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, matricula);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error eliminando vehículo: " + e.getMessage());
            return false;
        }
    }
}


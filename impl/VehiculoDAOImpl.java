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
    public boolean insertarVehiculo(Vehiculo vehiculo) {
        String sql = "INSERT INTO vehiculo (matricula, marca, modelo, id_cliente) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehiculo.getMatricula());
            stmt.setString(2, vehiculo.getMarca());
            stmt.setString(3, vehiculo.getModelo());
            stmt.setInt(4, vehiculo.getIdCliente());

            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error insertando vehículo: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Vehiculo obtenerVehiculoPorId(int id) {
        String sql = "SELECT * FROM vehiculo WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Vehiculo(
                        rs.getInt("id"),
                        rs.getString("matricula"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("id_cliente")
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
                        rs.getInt("id"),
                        rs.getString("matricula"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("id_cliente")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error listando vehículos: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public boolean actualizarVehiculo(Vehiculo vehiculo) {
        String sql = "UPDATE vehiculo SET matricula=?, marca=?, modelo=?, id_cliente=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehiculo.getMatricula());
            stmt.setString(2, vehiculo.getMarca());
            stmt.setString(3, vehiculo.getModelo());
            stmt.setInt(4, vehiculo.getIdCliente());
            stmt.setInt(5, vehiculo.getId());

            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error actualizando vehículo: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminarVehiculo(int id) {
        String sql = "DELETE FROM vehiculo WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error eliminando vehículo: " + e.getMessage());
            return false;
        }
    }
}


package DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.DBConnection;
import DAO.interfaces.ClienteDAO;
import entities.Cliente;

public class ClienteDAOImpl implements ClienteDAO {

    // INSERTAR CLIENTE
    @Override
    public boolean insertarCliente(Cliente cliente) {
        String sql = "INSERT INTO cliente (nombre, email, dni) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getDni());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error insertando cliente: " + e.getMessage());
            return false;
        }
    }

    // OBTENER CLIENTE POR DNI
    @Override
    public Cliente obtenerClientePorDni(String dni) {
        String sql = "SELECT * FROM cliente WHERE dni=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Cliente(
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("dni")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error buscando cliente: " + e.getMessage());
        }

        return null;
    }

    // LISTAR TODOS LOS CLIENTES
    @Override
    public List<Cliente> obtenerTodos() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                clientes.add(new Cliente(
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("dni")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error listando clientes: " + e.getMessage());
        }

        return clientes;
    }

    // ACTUALIZAR CLIENTE
    @Override
    public boolean actualizarCliente(Cliente cliente) {
        String sql = "UPDATE cliente SET nombre=?, email=? WHERE dni=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getDni());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error actualizando cliente: " + e.getMessage());
            return false;
        }
    }

    // ELIMINAR CLIENTE
    @Override
    public boolean eliminarCliente(String dni) {
        String sql = "DELETE FROM cliente WHERE dni=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dni);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error eliminando cliente: " + e.getMessage());
            return false;
        }
    }
}


package DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAO.DBConnection;
import DAO.interfaces.ClienteDAO;
import entities.Cliente;

public class ClienteDAOImpl implements ClienteDAO{
	
	
	 // INSERTAR CLIENTE
    public boolean insertarCliente(Cliente cliente) {
        String sql = "INSERT INTO cliente (nombre, telefono, email) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cliente.getId_cliente());
            stmt.setString(2, cliente.getNombre());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getDni());

            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error insertando cliente: " + e.getMessage());
            return false;
        }
    }

    // BUSCAR CLIENTE POR ID
    public Cliente obtenerClientePorId(int id) {
        String sql = "SELECT * FROM cliente WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("email")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error buscando cliente: " + e.getMessage());
        }

        return null;
    }

    // LISTAR TODOS LOS CLIENTES
    public List<Cliente> obtenerTodos() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                clientes.add(new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("email")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error listando clientes: " + e.getMessage());
        }

        return clientes;
    }

    // ACTUALIZAR CLIENTE
    public boolean actualizarCliente(Cliente cliente) {
        String sql = "UPDATE cliente SET nombre=?, telefono=?, email=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

        	 	stmt.setInt(1, cliente.getId_cliente());
	            stmt.setString(2, cliente.getNombre());
	            stmt.setString(3, cliente.getEmail());
	            stmt.setString(4, cliente.getDni());

            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error actualizando cliente: " + e.getMessage());
            return false;
        }
    }

    // ELIMINAR CLIENTE
    public boolean eliminarCliente(int id) {
        String sql = "DELETE FROM cliente WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error eliminando cliente: " + e.getMessage());
            return false;
        }
    }
}

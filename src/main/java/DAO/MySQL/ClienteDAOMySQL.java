package DAO.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAO.DBConnection;
import DAO.interfaces.ClienteDAO;
import DAO.interfaces.UsuarioDAO;
import dwes.maven.mysql.PasswordUtils;
import entities.Cliente;
import entities.Usuario;

public class ClienteDAOMySQL implements ClienteDAO, AutoCloseable{
	 private Connection conn;

	    // Constructor correcto
	    public ClienteDAOMySQL() throws SQLException {
	        conn = DBConnection.getInstance().getConnection();
	    }

	    @Override
	    public boolean insertarCliente(Cliente c) {
	        String sql = "INSERT INTO Cliente (nombre, email, dni) VALUES (?, ?, ?)";
	        try (PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	            pst.setString(1, c.getNombre());
	            pst.setString(2, c.getEmail());
	            pst.setString(3, c.getDni());

	            int filas = pst.executeUpdate();
	            if (filas > 0) {
	                try (ResultSet rs = pst.getGeneratedKeys()) {
	                    if (rs.next()) {
	                        c.setId_cliente(rs.getInt(1));
	                    }
	                }
	                return true;
	            }
	        } catch (SQLException e) {
	            System.err.println("Error al insertar cliente: " + e.getMessage());
	        }
	        return false;
	    }

	    @Override
	    public Cliente obtenerClientePorId(int id) {
	        String sql = "SELECT * FROM Cliente WHERE id_cliente = ?";
	        try (PreparedStatement pst = conn.prepareStatement(sql)) {
	            pst.setInt(1, id);
	            try (ResultSet rs = pst.executeQuery()) {
	                if (rs.next()) {
	                    return new Cliente(
	                        rs.getInt("id_cliente"),
	                        rs.getString("nombre"),
	                        rs.getString("email"),
	                        rs.getString("dni")
	                    );
	                }
	            }
	        } catch (SQLException e) {
	            System.err.println("Error al obtener cliente: " + e.getMessage());
	        }
	        return null;
	    }

	    @Override
	    public List<Cliente> obtenerTodos() {
	        List<Cliente> lista = new ArrayList<>();
	        String sql = "SELECT * FROM Cliente";
	        try (PreparedStatement pst = conn.prepareStatement(sql);
	             ResultSet rs = pst.executeQuery()) {
	            while (rs.next()) {
	                Cliente c = new Cliente(
	                    rs.getInt("id_cliente"),
	                    rs.getString("nombre"),
	                    rs.getString("email"),
	                    rs.getString("dni")
	                );
	                lista.add(c);
	            }
	        } catch (SQLException e) {
	            System.err.println("Error al obtener todos los clientes: " + e.getMessage());
	        }
	        return lista;
	    }

	    @Override
	    public boolean actualizarCliente(Cliente c) {
	        String sql = "UPDATE Cliente SET nombre=?, email=?, dni=? WHERE id_cliente=?";
	        try (PreparedStatement pst = conn.prepareStatement(sql)) {
	            pst.setString(1, c.getNombre());
	            pst.setString(2, c.getEmail());
	            pst.setString(3, c.getDni());
	            pst.setInt(4, c.getId_cliente());
	            return pst.executeUpdate() > 0;
	        } catch (SQLException e) {
	            System.err.println("Error al actualizar cliente: " + e.getMessage());
	        }
	        return false;
	    }

	    @Override
	    public boolean eliminarCliente(int id) {
	        String sql = "DELETE FROM Cliente WHERE id_cliente=?";
	        try (PreparedStatement pst = conn.prepareStatement(sql)) {
	            pst.setInt(1, id);
	            return pst.executeUpdate() > 0;
	        } catch (SQLException e) {
	            System.err.println("Error al eliminar cliente: " + e.getMessage());
	        }
	        return false;
	    }

	    @Override
	    public void close() throws SQLException {
	        if (conn != null && !conn.isClosed()) {
	            conn.close();
	        }
	    }}
	
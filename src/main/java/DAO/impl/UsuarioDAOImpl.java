package DAO.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAO.DBConnection;
import DAO.interfaces.UsuarioDAO;
import entities.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO, AutoCloseable{
	public void UsuarioDAOMySQL() throws SQLException{
		conn = DBConnection.getInstance().getConnection();
	}
    @Override
    public boolean insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (nombre, contrasena, rol, dniUsuario) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

        	 stmt.setInt(1, usuario.getId_usuario());
        	 stmt.setString(2, usuario.getNombreUsuario());
        	 stmt.setString(3, usuario.getPassword());
        	 stmt.setString(4, usuario.getRol());
        	 stmt.setString(5, usuario.getDniUsuario());

            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error insertando usuario: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Usuario obtenerUsuarioPorId(int id) {
        String sql = "SELECT * FROM usuario WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("contrasena"),
                        rs.getString("rol"),
                        rs.getString("dniUsuario")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error buscando usuario: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Usuario> obtenerTodos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("contrasena"),
                        rs.getString("rol"),
                        rs.getString("dniUsuario")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error listando usuarios: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public boolean actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET nombre=?, contrasena=?, rol=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

        	stmt.setInt(1, usuario.getId_usuario());
       	 stmt.setString(2, usuario.getNombreUsuario());
       	 stmt.setString(3, usuario.getPassword());
       	 stmt.setString(4, usuario.getRol());
       	 stmt.setString(5, usuario.getDniUsuario());

            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error actualizando usuario: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminarUsuario(int id) {
        String sql = "DELETE FROM usuario WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error eliminando usuario: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Usuario login(String nombre, String contrasena) {
        String sql = "SELECT * FROM usuario WHERE nombre=? AND contrasena=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setString(2, contrasena);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuario(
                		  rs.getInt("id"),
                          rs.getString("nombre"),
                          rs.getString("contrasena"),
                          rs.getString("rol"),
                          rs.getString("dniUsuario")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error en login: " + e.getMessage());
        }

        return null;
    }
}

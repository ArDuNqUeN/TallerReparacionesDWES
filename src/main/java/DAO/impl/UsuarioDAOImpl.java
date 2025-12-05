package DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DAO.DBConnection;
import DAO.interfaces.UsuarioDAO;
import entities.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {

    // INSERTAR USUARIO
    @Override
    public boolean insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (nombre, contrasena, rol, dniUsuario) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNombreUsuario());
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3, usuario.getRol());
            stmt.setString(4, usuario.getDniUsuario());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error insertando usuario: " + e.getMessage());
            return false;
        }
    }

    // OBTENER USUARIO POR DNI
    @Override
    public Usuario obtenerUsuarioPorDni(String dni) {
        String sql = "SELECT * FROM usuario WHERE dniUsuario=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getString("nombre"),
                        rs.getString("contrasena"),
                        rs.getString("rol"),
                        rs.getString("dniUsuario")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error buscando usuario: " + e.getMessage());
        }

        return null;
    }

    // LISTAR TODOS LOS USUARIOS
    @Override
    public List<Usuario> obtenerTodos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new Usuario(
                        rs.getString("nombre"),
                        rs.getString("contrasena"),
                        rs.getString("rol"),
                        rs.getString("dniUsuario")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error listando usuarios: " + e.getMessage());
        }

        return lista;
    }

    // ACTUALIZAR USUARIO
    @Override
    public boolean actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET nombre=?, contrasena=?, rol=? WHERE dniUsuario=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNombreUsuario());
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3, usuario.getRol());
            stmt.setString(4, usuario.getDniUsuario());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error actualizando usuario: " + e.getMessage());
            return false;
        }
    }

    // ELIMINAR USUARIO
    @Override
    public boolean eliminarUsuario(String dni) {
        String sql = "DELETE FROM usuario WHERE dniUsuario=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dni);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error eliminando usuario: " + e.getMessage());
            return false;
        }
    }

    // LOGIN
    @Override
    public Usuario login(String dniUsuario, String contrasena) {
        String sql = "SELECT * FROM usuario WHERE dniUsuario=? AND contrasena=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dniUsuario);   // antes estaba nombre
            stmt.setString(2, contrasena);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getString("nombre"),
                        rs.getString("contrasena"),
                        rs.getString("rol"),
                        rs.getString("dniUsuario")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error en login: " + e.getMessage());
        }

        return null;
    }

}


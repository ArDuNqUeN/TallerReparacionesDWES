package DAO.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAO.DBConnection;
import DAO.interfaces.UsuarioDAO;
import dwes.maven.mysql.PasswordUtils;
import entities.Usuario;

public class UsuarioDAOMySQL implements UsuarioDAO, AutoCloseable{

	private Connection conn;
	
	  // Constructor correcto
    public UsuarioDAOMySQL() throws SQLException {
        conn = DBConnection.getInstance().getConnection();
    }

    @Override
    public int insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuario (nombreUsuario, password, rol, dniUsuario) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, usuario.getNombreUsuario());
            pst.setString(2, PasswordUtils.hashPassword(usuario.getPassword())); // guardar hash
            pst.setString(3, usuario.getRol());
            pst.setString(4, usuario.getDniUsuario());

            int filas = pst.executeUpdate();
            if (filas > 0) {
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        usuario.setId_usuario(rs.getInt(1));
                        return usuario.getId_usuario();
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
        }
        return -1;
    }


	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Usuario> obtenerTodos() {
		List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM Usuario";
        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                lista.add(new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("nombreUsuario"),
                    rs.getString("password"),
                    rs.getString("rol"),
                    rs.getString("dniUsuario")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los usuarios: " + e.getMessage());
        }
        return lista;
	}

	@Override
	public boolean actualizarUsuario(Usuario usuario) {
		String sql = "UPDATE Usuario SET nombreUsuario=?, password=?, rol=?, dniUsuario=? WHERE id_usuario=?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, usuario.getNombreUsuario());
            pst.setString(2, PasswordUtils.hashPassword(usuario.getPassword()));
            pst.setString(3, usuario.getRol());
            pst.setString(4, usuario.getDniUsuario());
            pst.setInt(5, usuario.getId_usuario());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
        }
		return false;
	}

	@Override
	public boolean eliminarUsuario(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Usuario login(String nombre, String contrasena) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario obtenerUsuarioPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}}

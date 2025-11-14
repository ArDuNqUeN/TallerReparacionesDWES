package DAO.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import DAO.DBConnection;
import DAO.interfaces.UsuarioDAO;
import dwes.maven.mysql.PasswordUtils;
import entities.Usuario;

public class ClienteDAOMySQL implements UsuarioDAO, AutoCloseable{
	private Connection conn; 
	
	public void ClienteDAOMySQL ()throws SQLException{
	conn = DBConnection.getInstance().getConnection();
	}
	
	@Override
	public int insertarUsuario(Usuario usuario) {
		
		return 0;
			}

	@Override
	public Usuario obtenerUsuarioPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> obtenerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean actualizarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminarUsuario(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Usuario login(String dniUsuario, String password) {
		Usuario usuario=null;
			ResultSet res=null;
			
			String sql= "SELECT id_usuario, nombre, rol FROM Usuario WHERE dni= ? AND password = ?";
			PreparedStatement pst;
			
			try {
				pst= conn.prepareStatement(sql);
				pst.setString(1, dniUsuario);
				pst.setString(2, PasswordUtils.hashPassword(password));
				
				if (res.next()) {
					usuario = new Usuario();
					usuario.setId_usuario(res.getInt("id"));
					usuario.setDniUsuario(dni);
					
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
			
		return usuario;
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}
	    
}

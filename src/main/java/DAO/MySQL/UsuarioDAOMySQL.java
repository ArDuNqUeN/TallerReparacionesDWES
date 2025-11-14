package DAO.MySQL;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import DAO.DBConnection;
import DAO.interfaces.UsuarioDAO;
import entities.Usuario;

public class UsuarioDAOMySQL implements UsuarioDAO, AutoCloseable{

	private Connection conn;
	
	public void UsuarioDAOMySQL()throws SQLException{
		conn= DBConnection.getInstance().getConnection();
	}
	
	
	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int insertarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
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
	public Usuario login(String nombre, String contrasena) {
		// TODO Auto-generated method stub
		return null;
	}
  


}

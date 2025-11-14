package DAO.MySQL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import DAO.DBConnection;
import DAO.interfaces.ReparacionDAO;
import entities.Reparacion;

public class ReparacionDAOMySQL implements ReparacionDAO, AutoCloseable{

	private Connection conn;
	
	public void ReparacionDAOMySQL()throws SQLException{
		conn=DBConnection.getInstance().getConnection();
	}
	
	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean insertarReparacion(Reparacion reparacion) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Reparacion obtenerReparacionPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reparacion> obtenerTodas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean actualizarReparacion(Reparacion reparacion) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminarReparacion(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cambiarEstado(int id, Reparacion estado) {
		// TODO Auto-generated method stub
		return false;
	}

        


}

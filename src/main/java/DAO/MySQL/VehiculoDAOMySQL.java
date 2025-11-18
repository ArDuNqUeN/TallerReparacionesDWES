package DAO.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import DAO.DBConnection;
import DAO.interfaces.VehiculoDAO;
import entities.Vehiculo;

public class VehiculoDAOMySQL implements VehiculoDAO, AutoCloseable{
private Connection conn;

	public  VehiculoDAOMySQL() throws SQLException{
	conn = DBConnection.getInstance().getConnection();
}
	
	
	@Override
	public void insertarVehiculo(Vehiculo vehiculo) throws SQLException {
		int rc=0;
		String sql = "INSERT INTO Vehiculo (matricula, marca, modelo, id_cliente) VALUES (?, ?, ?, ?)";
		
			PreparedStatement pst=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			 pst.setString(1, vehiculo.getMatricula());
	         pst.setString(2, vehiculo.getMarca());
	         pst.setString(3, vehiculo.getModelo());
	         pst.setInt(4, vehiculo.getIdCliente());
			 return;
		
		
	}

	@Override
	public Vehiculo obtenerVehiculoPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vehiculo> obtenerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean actualizarVehiculo(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminarVehiculo(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}

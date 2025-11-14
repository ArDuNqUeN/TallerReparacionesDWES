package DAO.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import DAO.DBConnection;
import DAO.interfaces.VehiculoDAO;
import entities.Vehiculo;

public class VehiculoDOAMySQL implements VehiculoDAO, AutoCloseable{
private Connection conn;

public void VehiculoDAOMySQL() throws SQLException{
	conn = DBConnection.getInstance().getConnection();
}
	
	
	@Override
	public int insertarVehiculo(Vehiculo vehiculo) {
		int rc=0;
		String sql = "INSERT INTO Vehiculo (matricula, marca, modelo, id_cliente) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement pst=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			 pst.setString(1, vehiculo.getMatricula());
	         pst.setString(2, vehiculo.getMarca());
	         pst.setString(3, vehiculo.getModelo());
	         pst.setInt(4, vehiculo.getIdCliente());
		}
		
return false;
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

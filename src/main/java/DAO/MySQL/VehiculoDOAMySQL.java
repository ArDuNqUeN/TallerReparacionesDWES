package DAO.MySQL;

import java.util.List;
import entities.Vehiculo;

public interface VehiculoDOAMySQL {
	List<Vehiculo> findAll();                
    Vehiculo findById(int id);               
    int insert(Vehiculo vehiculo);             
    boolean update(Vehiculo vehiculo); 
}

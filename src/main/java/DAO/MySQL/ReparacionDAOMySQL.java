package DAO.MySQL;
import java.util.List;
import entities.Reparacion;

public interface ReparacionDAOMySQL {

	List<Reparacion> findAll();                
    Reparacion findById(int id);               
    int insert(Reparacion reparacion);             
    boolean update(Reparacion reparacion);         


}

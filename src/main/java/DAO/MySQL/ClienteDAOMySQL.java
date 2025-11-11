package DAO.MySQL;

import java.util.List;
import entities.Cliente;

public interface ClienteDAOMySQL {
	  List<Cliente> findAll();                 
	    Cliente findById(int id);               
	    int insert(Cliente cliente);             
	    boolean update(Cliente cliente);         
	    boolean delete(int id);   
}

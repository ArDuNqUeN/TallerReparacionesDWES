package DAO.interfaces;

import java.util.List;
import entities.Cliente;

public interface ClienteDAO {
    boolean insertarCliente(Cliente c);
    Cliente obtenerClientePorDni(String dni);
    List<Cliente> obtenerTodos();
    boolean actualizarCliente(Cliente c);
    boolean eliminarCliente(String dni);
}
   
	


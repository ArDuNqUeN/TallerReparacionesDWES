package DAO.interfaces;

import java.util.List;

import entities.Cliente;

public interface ClienteDAO {
    boolean insertarCliente(Cliente c);
    Cliente obtenerClientePorId(int id);
    List<Cliente> obtenerTodos();
    boolean actualizarCliente(Cliente c);
    boolean eliminarCliente(int id);
}

              
	


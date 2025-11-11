package DAO.MySQL;

import entities.Cliente;
import java.util.ArrayList;

public class CLienteDAOMySQL {
 int insert(Cliente c);
 int update(Cliente c);
 int delete(String dni);
 ArrayList<Cliente c> findall();
 Cliente findByDni(String)
}

package DAO.MySQL;

import java.util.ArrayList;

public class UsuarioDAOMySQL {
 boolean login(String dni, String password);
 int insert (Usuario u);
 ArrayList<Usuario> findall();
 Usuario findByNombre(String nombre);
}

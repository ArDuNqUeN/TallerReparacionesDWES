package DAO.interfaces;

import java.util.List;
import entities.Usuario;

public interface UsuarioDAO {
    boolean insertarUsuario(Usuario usuario);
    Usuario obtenerUsuarioPorDni(String dni);
    List<Usuario> obtenerTodos();
    boolean actualizarUsuario(Usuario usuario);
    boolean eliminarUsuario(String dni);
    Usuario login(String nombre, String contrasena);
}


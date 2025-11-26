package DAO.interfaces;

import java.util.List;

import entities.Usuario;

public interface UsuarioDAO {
    int insertarUsuario(Usuario usuario);
    Usuario obtenerUsuarioPorId(int id);
    List<Usuario> obtenerTodos();
    boolean actualizarUsuario(Usuario usuario);
    boolean eliminarUsuario(int id);
    Usuario login(String nombre, String contrasena);

}


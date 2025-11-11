package DAO.MySQL;

import java.util.List;

import entities.Usuario;

public interface UsuarioDAOMySQL {
    List<Usuario> findAll();                 // Obtener todos los usaurios
    Usuario findById(int id);                // Buscar usuarios por ID
    int insert(Usuario usuario);             // Insertar usuarios (devuelve ID generado)
    boolean update(Usuario usuario);         // Actualizar usuarios existente
    boolean delete(int id);                  // Borrar usuarios por ID

}

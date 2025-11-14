package DAO;

import DAO.interfaces.UsuarioDAO;

public interface DAOFactory {
	
	public UsuarioDAO getsUsuarioDAO();
//	public static PersonaDAOImpl getPersonaDAO() {
//		return new PersonaDAOImpl();
//	}
}

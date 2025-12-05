package DAO.interfaces;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import entities.Usuario;

class UsuarioDAOTest {

	@Test
	void test() {
		try(UsuarioDAOMySQL usuarioDAO = new UsuarioDAOMySQL()){
			Usuario usuario = new Usuario(1, Ardun, null, null, null);
			int rc = usuarioDAO.insert()
		}
	}
	@Test
	void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	void testInsert() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

}

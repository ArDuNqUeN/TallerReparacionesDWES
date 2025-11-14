package controlador;

import java.awt.List;
import DAO.impl.ClienteDAOImpl;
import DAO.impl.ReparacionDAOImpl;
import DAO.impl.UsuarioDAOImpl;
import DAO.impl.VehiculoDAOImpl;
import DAO.interfaces.ClienteDAO;
import DAO.interfaces.ReparacionDAO;
import DAO.interfaces.UsuarioDAO;
import DAO.interfaces.VehiculoDAO;
import entities.Cliente;
import entities.Reparacion;
import entities.Usuario;

public class ControladorTaller {

	private static ControladorTaller instancia;
	private Usuario usuarioActual; 
	private ClienteDAO clienteDAO;
	private VehiculoDAO vehiculoDAO;
	private ReparacionDAO reparacionDAO;
	private UsuarioDAO usuarioDAO;
	
	public static ControladorTaller getInstancia() {
	    if (instancia == null) {
	        instancia = new ControladorTaller();
	    }
	    return instancia;
	}
	 private ControladorTaller() {
	        clienteDAO = new ClienteDAOImpl();
	        vehiculoDAO = new VehiculoDAOImpl();
	        reparacionDAO = new ReparacionDAOImpl();
	        usuarioDAO = new UsuarioDAOImpl();
	    }

	 

	    // LOGIN
	    public boolean login(String nombre, String contrasena) {
	        Usuario u = usuarioDAO.login(nombre, contrasena);
	        
			return true;

	    }

	    public void cerrarSesion() {
	        usuarioActual = null;
	    }

	    public Usuario getUsuarioActual() {
	        return usuarioActual;
	    }

//	    // CLIENTES
//	    public List<Cliente> listarClientes() {
//	        return clienteDAO.obtenerTodos();
//	    }
//
//	    public boolean insertarCliente(Cliente c) {
//	        return clienteDAO.insertarCliente(c);
//	    }
//
//	    // REPARACIONES
//	    public boolean registrarReparacion(Reparacion r) {
//	        return reparacionDAO.insertarReparacion(r);
//	    }
//
//	    public boolean cambiarEstadoReparacion(int id, Reparacion.Estado estado) {
//	        return reparacionDAO.cambiarEstado(id, estado);
//	    }
//
//	    public List<Reparacion> listarReparacionesFinalizadas() {
//	        List<Reparacion> todas = reparacionDAO.obtenerTodas();
//	        return todas.stream()
//	                .filter(r -> r.getEstado() == Reparacion.Estado.FINALIZADA)
//	                .toList();
//	    }

	    // Puedes añadir métodos similares para Vehículo, Estadísticas, etc.
}

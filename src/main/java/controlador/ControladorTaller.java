package controlador;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

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
import entities.Vehiculo;
import entities.Estado;

public class ControladorTaller {

    private static ControladorTaller instancia;
    private Usuario usuarioActual;

    private ClienteDAO clienteDAO;
    private VehiculoDAO vehiculoDAO;
    private ReparacionDAO reparacionDAO;
    private UsuarioDAO usuarioDAO;

    // ------------------- SINGLETON -------------------
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

    // ------------------- LOGIN / LOGOUT -------------------
    public boolean login(String dni, String contrasena) {
        Usuario u = usuarioDAO.login(dni, contrasena);
        if (u != null) {
            usuarioActual = u;
            return true;
        }
        return false;
    }


    public void cerrarSesion() {
        usuarioActual = null;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public boolean esAdmin() {
        return usuarioActual != null && "ADMIN".equalsIgnoreCase(usuarioActual.getRol());
    }

    public boolean esMecanico() {
        return usuarioActual != null && "MECANICO".equalsIgnoreCase(usuarioActual.getRol());
    }

    // =====================================================
    //                     CLIENTES
    // =====================================================
    public List<Cliente> listarClientes() {
        return clienteDAO.obtenerTodos();
    }

    public boolean crearCliente(Cliente c) {
        return clienteDAO.insertarCliente(c);
    }

    public Cliente obtenerClientePorDni(String dni) {
        return clienteDAO.obtenerClientePorDni(dni);
    }

    public boolean modificarCliente(Cliente c) {
        return clienteDAO.actualizarCliente(c);
    }

    public boolean borrarCliente(String dni) {
        return clienteDAO.eliminarCliente(dni);
    }

    // =====================================================
    //                     VEHÍCULOS
    // =====================================================
    public List<Vehiculo> listarVehiculos() {
        return vehiculoDAO.obtenerTodos();
    }

    public boolean crearVehiculo(Vehiculo v) throws SQLException {
        return vehiculoDAO.insertarVehiculo(v);
    }

    public Vehiculo obtenerVehiculoPorMatricula(String matricula) {
        return vehiculoDAO.obtenerVehiculoPorMatricula(matricula);
    }

    public boolean modificarVehiculo(Vehiculo v) {
        return vehiculoDAO.actualizarVehiculo(v);
    }

    public boolean borrarVehiculo(String matricula) {
        return vehiculoDAO.eliminarVehiculo(matricula);
    }

    // =====================================================
    //                     USUARIOS
    // =====================================================
    public List<Usuario> listarUsuarios() {
        return usuarioDAO.obtenerTodos();
    }

    public boolean crearUsuario(Usuario u) {
        return usuarioDAO.insertarUsuario(u);
    }

    public boolean borrarUsuario(String dni) {
        return usuarioDAO.eliminarUsuario(dni);
    }

    // =====================================================
    //                   REPARACIONES
    // =====================================================
    public List<Reparacion> listarReparaciones() {
        return reparacionDAO.obtenerTodas();
    }

    public boolean registrarReparacion(Reparacion r) {
        return reparacionDAO.insertarReparacion(r);
    }


    // Cambiar estado por matrícula (por si hay varias)
    public boolean cambiarEstadoReparacionPorMatricula(String matricula, Estado nuevoEstado) {
        List<Reparacion> reparaciones = reparacionDAO.obtenerReparacionPorMatricula(matricula);
        if (reparaciones.isEmpty()) return false;

        boolean exito = false;
        for (Reparacion r : reparaciones) {
            r.setEstado(nuevoEstado);
            exito |= reparacionDAO.actualizarReparacion(r, matricula);
        }
        return exito;
    }

    public List<Reparacion> listarReparacionesFinalizadas() {
        return reparacionDAO.obtenerTodas().stream()
                .filter(r -> r.getEstado() == Estado.FINALIZADA)
                .collect(Collectors.toList());
    }

    public List<Reparacion> listarReparacionesPorEstado(Estado estado) {
        return reparacionDAO.obtenerTodas().stream()
                .filter(r -> r.getEstado() == estado)
                .collect(Collectors.toList());
    }

    public List<Reparacion> obtenerReparacionesPorDniCliente(String dni) {
        return reparacionDAO.obtenerReparacionPorDni(dni);
    }

    public List<Reparacion> obtenerReparacionesPorMatricula(String matricula) {
        return reparacionDAO.obtenerReparacionPorMatricula(matricula);
    }

    // =====================================================
    //                     ESTADÍSTICAS
    // =====================================================
    public double calcularCosteMedio() {
        List<Reparacion> todas = reparacionDAO.obtenerTodas();
        return todas.isEmpty() ? 0 :
                todas.stream().mapToDouble(Reparacion::getCosteEstimado).average().orElse(0);
    }

    public long totalReparacionesPorEstado(Estado estado) {
        return reparacionDAO.obtenerTodas().stream()
                .filter(r -> r.getEstado() == estado)
                .count();
    }

    public double sumarCostesPorEstado(Estado estado) {
        return reparacionDAO.obtenerTodas().stream()
                .filter(r -> r.getEstado() == estado)
                .mapToDouble(Reparacion::getCosteEstimado)
                .sum();
    }
}



package cafeteraapp;

import cafeteraapp.dao.PedidoDAO;
import cafeteraapp.dao.ProductorDAO;
import cafeteraapp.dao.UsuarioDAO;
import cafeteraapp.model.Pedido;
import cafeteraapp.model.Productor;
import cafeteraapp.model.Usuario;

import java.sql.Timestamp;
import java.util.List;

/**
 * Clase principal para realizar pruebas básicas de los DAOs y operaciones CRUD.
 */
public class CafeteraAppMain {

    public static void main(String[] args) {
        System.out.println("==== Pruebas CRUD para la base de datos db_cafetera ====");

        // Instanciamos los DAO
        PedidoDAO pedidoDAO = new PedidoDAO();
        ProductorDAO productorDAO = new ProductorDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        // Probamos operaciones con Pedido
        probarPedidos(pedidoDAO);

        // Probamos operaciones con Productor
        probarProductores(productorDAO);

        // Probamos operaciones con Usuario
        probarUsuarios(usuarioDAO);

        System.out.println("==== Fin de las pruebas ====");
    }

    private static void probarPedidos(PedidoDAO dao) {
        System.out.println("\n-- PROBANDO CRUD PARA PEDIDOS --");

        // Crear un nuevo Pedido
        Pedido nuevoPedido = new Pedido();
        nuevoPedido.setIdProductor(1);
        nuevoPedido.setClienteNombre("Luis Pérez");
        nuevoPedido.setProductoCafe("Arábica");
        nuevoPedido.setCantidadKg(20.5);
        nuevoPedido.setPrecioUnitario(15.75);
        nuevoPedido.setTotalPrecio(nuevoPedido.getCantidadKg() * nuevoPedido.getPrecioUnitario());
        nuevoPedido.setFechaPedido(new Timestamp(System.currentTimeMillis()));
        nuevoPedido.setEstadoPedido("Pendiente");

        boolean insertado = dao.insert(nuevoPedido);
        System.out.println("Pedido insertado: " + insertado);

        // Listar todos los pedidos
        List<Pedido> pedidos = dao.findAll();
        System.out.println("Listado de pedidos:");
        for (Pedido p : pedidos) {
            System.out.println("ID: " + p.getIdPedido() + ", Cliente: " + p.getClienteNombre() + ", Total: " + p.getTotalPrecio());
        }

        // Actualizar un pedido (si hay alguno)
        if (!pedidos.isEmpty()) {
            Pedido p = pedidos.get(0);
            p.setEstadoPedido("Confirmado");
            boolean actualizado = dao.update(p);
            System.out.println("Pedido actualizado ID " + p.getIdPedido() + ": " + actualizado);
        }

        // Eliminar un pedido (opcional, eliminar último insertado si ID conocido)
        // Para hacerlo seguro, puedes buscar un pedido con estado "Pendiente" y eliminarlo
    }

    private static void probarProductores(ProductorDAO dao) {
        System.out.println("\n-- PROBANDO CRUD PARA PRODUCTORES --");

        Productor nuevoProductor = new Productor();
        nuevoProductor.setNombre("Café Antioquia");
        nuevoProductor.setUbicacion("Envigado");
        nuevoProductor.setTipoCafe("Arábica");
        nuevoProductor.setCertificaciones("Orgánico");
        nuevoProductor.setFechaRegistro(new Timestamp(System.currentTimeMillis()));

        boolean insertado = dao.insert(nuevoProductor);
        System.out.println("Productor insertado: " + insertado);

        List<Productor> productores = dao.findAll();
        System.out.println("Listado de productores:");
        for (Productor prod : productores) {
            System.out.println("ID: " + prod.getIdProductor() + ", Nombre: " + prod.getNombre());
        }

        if (!productores.isEmpty()) {
            Productor p = productores.get(0);
            p.setCertificaciones("Orgánico, Fair Trade");
            boolean actualizado = dao.update(p);
            System.out.println("Productor actualizado ID " + p.getIdProductor() + ": " + actualizado);
        }
    }

    private static void probarUsuarios(UsuarioDAO dao) {
        System.out.println("\n-- PROBANDO CRUD PARA USUARIOS --");

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername("admin");
        nuevoUsuario.setPasswordHash("hash_seguro");  // Reemplazar con hash real si aplica
        nuevoUsuario.setRol("Administrador");
        nuevoUsuario.setFechaCreacion(new Timestamp(System.currentTimeMillis()));

        boolean insertado = dao.insert(nuevoUsuario);
        System.out.println("Usuario insertado: " + insertado);

        List<Usuario> usuarios = dao.findAll();
        System.out.println("Listado de usuarios:");
        for (Usuario u : usuarios) {
            System.out.println("ID: " + u.getIdUsuario() + ", Usuario: " + u.getUsername());
        }

        if (!usuarios.isEmpty()) {
            Usuario u = usuarios.get(0);
            u.setRol("Usuario");
            boolean actualizado = dao.update(u);
            System.out.println("Usuario actualizado ID " + u.getIdUsuario() + ": " + actualizado);
        }
    }

}
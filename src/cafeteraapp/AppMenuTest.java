package cafeteraapp;

import cafeteraapp.dao.PedidoDAO;
import cafeteraapp.dao.ProductorDAO;
import cafeteraapp.dao.UsuarioDAO;
import cafeteraapp.model.Pedido;
import cafeteraapp.model.Productor;
import cafeteraapp.model.Usuario;

import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class AppMenuTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PedidoDAO pedidoDAO = new PedidoDAO();
        ProductorDAO productorDAO = new ProductorDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        int opcion;

        do {
            System.out.println("\n=== MENÚ DE PRUEBAS CAFETERA APP ===");
            System.out.println("1. Listar productores");
            System.out.println("2. Crear productor");
            System.out.println("3. Actualizar productor");
            System.out.println("4. Eliminar productor");
            System.out.println("5. Listar pedidos");
            System.out.println("6. Crear pedido");
            System.out.println("7. Actualizar pedido");
            System.out.println("8. Eliminar pedido");
            System.out.println("9. Listar usuarios");
            System.out.println("10. Crear usuario");
            System.out.println("11. Actualizar usuario");
            System.out.println("12. Eliminar usuario");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leerInt(scanner);

            switch (opcion) {
                case 1:
                    listarProductores(productorDAO);
                    break;
                case 2:
                    crearProductor(scanner, productorDAO);
                    break;
                case 3:
                    actualizarProductor(scanner, productorDAO);
                    break;
                case 4:
                    eliminarProductor(scanner, productorDAO);
                    break;
                case 5:
                    listarPedidos(pedidoDAO);
                    break;
                case 6:
                    crearPedido(scanner, pedidoDAO, productorDAO);
                    break;
                case 7:
                    actualizarPedido(scanner, pedidoDAO);
                    break;
                case 8:
                    eliminarPedido(scanner, pedidoDAO);
                    break;
                case 9:
                    listarUsuarios(usuarioDAO);
                    break;
                case 10:
                    crearUsuario(scanner, usuarioDAO);
                    break;
                case 11:
                    actualizarUsuario(scanner, usuarioDAO);
                    break;
                case 12:
                    eliminarUsuario(scanner, usuarioDAO);
                    break;
                case 0:
                    System.out.println("Saliendo... ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción inválida, intente nuevamente.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    // Utilidades de lectura segura de enteros
    private static int leerInt(Scanner scanner) {
        int num;
        while (true) {
            try {
                num = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.print("Valor inválido. Ingrese un número: ");
            }
        }
        return num;
    }

    // --- PRODUCTOR ---
    private static void listarProductores(ProductorDAO dao) {
        List<Productor> productores = dao.findAll();
        System.out.println("-- Lista de Productores --");
        for (Productor p : productores) {
            System.out.printf("ID: %d | Nombre: %s | Ubic: %s | Tipo: %s | Certif: %s\n",
                              p.getIdProductor(), p.getNombre(), p.getUbicacion(),
                              p.getTipoCafe(), p.getCertificaciones());
        }
    }

    private static void crearProductor(Scanner scanner, ProductorDAO dao) {
        System.out.print("Nombre productor: ");
        String nombre = scanner.nextLine();
        System.out.print("Ubicación: ");
        String ubicacion = scanner.nextLine();
        System.out.print("Tipo café: ");
        String tipoCafe = scanner.nextLine();
        System.out.print("Certificaciones: ");
        String certif = scanner.nextLine();

        Productor nuevo = new Productor(0, nombre, ubicacion, tipoCafe, certif, new Timestamp(System.currentTimeMillis()));
        boolean ok = dao.insert(nuevo);
        System.out.println(ok ? "Productor creado exitosamente." : "Error al crear productor.");
    }

    private static void actualizarProductor(Scanner scanner, ProductorDAO dao) {
        listarProductores(dao);
        System.out.print("ID productor a actualizar: ");
        int id = leerInt(scanner);
        Productor p = dao.findById(id);
        if (p == null) {
            System.out.println("No existe ese productor.");
            return;
        }
        System.out.print("Nuevo nombre [" + p.getNombre() + "]: ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) p.setNombre(nombre);

        System.out.print("Nueva ubicación [" + p.getUbicacion() + "]: ");
        String ubic = scanner.nextLine();
        if (!ubic.isEmpty()) p.setUbicacion(ubic);

        System.out.print("Nuevo tipo café [" + p.getTipoCafe() + "]: ");
        String tipo = scanner.nextLine();
        if (!tipo.isEmpty()) p.setTipoCafe(tipo);

        System.out.print("Nuevas certificaciones [" + p.getCertificaciones() + "]: ");
        String certif = scanner.nextLine();
        if (!certif.isEmpty()) p.setCertificaciones(certif);

        p.setFechaRegistro(new Timestamp(System.currentTimeMillis()));
        boolean ok = dao.update(p);
        System.out.println(ok ? "Productor actualizado." : "Error actualizando productor.");
    }

    private static void eliminarProductor(Scanner scanner, ProductorDAO dao) {
        listarProductores(dao);
        System.out.print("ID productor a eliminar: ");
        int id = leerInt(scanner);
        boolean ok = dao.delete(id);
        System.out.println(ok ? "Productor eliminado." : "No se pudo eliminar (puede estar usado en pedidos).");
    }

    // --- PEDIDO ---
    private static void listarPedidos(PedidoDAO dao) {
        List<Pedido> pedidos = dao.findAll();
        System.out.println("-- Lista de Pedidos --");
        for (Pedido p : pedidos) {
            System.out.printf("ID: %d | Productor: %d | Cliente: %s | Café: %s | Kg: %.2f | Total: %.2f | Estado: %s\n",
                    p.getIdPedido(), p.getIdProductor(), p.getClienteNombre(),
                    p.getProductoCafe(), p.getCantidadKg(), p.getTotalPrecio(), p.getEstadoPedido());
        }
    }

    private static void crearPedido(Scanner scanner, PedidoDAO pedidoDAO, ProductorDAO productorDAO) {
        listarProductores(productorDAO);
        System.out.print("ID productor para el pedido: ");
        int idProductor = leerInt(scanner);

        System.out.print("Nombre cliente: ");
        String cliente = scanner.nextLine();
        System.out.print("Producto café: ");
        String producto = scanner.nextLine();
        System.out.print("Cantidad (kg): ");
        double cantidad = Double.parseDouble(scanner.nextLine());
        System.out.print("Precio unitario: ");
        double precioU = Double.parseDouble(scanner.nextLine());
        double total = cantidad * precioU;
        String estado = "Pendiente";

        Pedido nuevo = new Pedido(0, idProductor, cliente, producto, cantidad, precioU, total,
                new Timestamp(System.currentTimeMillis()), estado);
        boolean ok = pedidoDAO.insert(nuevo);
        System.out.println(ok ? "Pedido creado correctamente." : "Error al crear pedido (verifique Productor y datos).");
    }

    private static void actualizarPedido(Scanner scanner, PedidoDAO dao) {
        listarPedidos(dao);
        System.out.print("ID pedido a actualizar: ");
        int id = leerInt(scanner);
        Pedido p = dao.findById(id);
        if (p == null) {
            System.out.println("No existe ese pedido.");
            return;
        }
        System.out.print("Nuevo estado [" + p.getEstadoPedido() + "]: ");
        String estado = scanner.nextLine();
        if (!estado.isEmpty()) p.setEstadoPedido(estado);

        boolean ok = dao.update(p);
        System.out.println(ok ? "Pedido actualizado." : "Error actualizando pedido.");
    }

    private static void eliminarPedido(Scanner scanner, PedidoDAO dao) {
        listarPedidos(dao);
        System.out.print("ID pedido a eliminar: ");
        int id = leerInt(scanner);
        boolean ok = dao.delete(id);
        System.out.println(ok ? "Pedido eliminado." : "No se pudo eliminar.");
    }

    // --- USUARIO ---
    private static void listarUsuarios(UsuarioDAO dao) {
        List<Usuario> usuarios = dao.findAll();
        System.out.println("-- Lista de Usuarios --");
        for (Usuario u : usuarios) {
            System.out.printf("ID: %d | Usuario: %s | Rol: %s\n", u.getIdUsuario(), u.getUsername(), u.getRol());
        }
    }

    private static void crearUsuario(Scanner scanner, UsuarioDAO dao) {
        System.out.print("Nuevo username: ");
        String username = scanner.nextLine();
        System.out.print("Password (texto plano o hash): ");
        String password = scanner.nextLine();
        System.out.print("Rol (ejemplo: Administrador): ");
        String rol = scanner.nextLine();

        Usuario nuevo = new Usuario(0, username, password, rol, new Timestamp(System.currentTimeMillis()));
        boolean ok = dao.insert(nuevo);
        System.out.println(ok ? "Usuario creado correctamente." : "Error al crear usuario (puede estar duplicado).");
    }

    private static void actualizarUsuario(Scanner scanner, UsuarioDAO dao) {
        listarUsuarios(dao);
        System.out.print("ID usuario a actualizar: ");
        int id = leerInt(scanner);
        Usuario usuario = dao.findById(id);
        if (usuario == null) {
            System.out.println("No existe ese usuario.");
            return;
        }
        System.out.print("Nuevo rol [" + usuario.getRol() + "]: ");
        String rol = scanner.nextLine();
        if (!rol.isEmpty()) usuario.setRol(rol);

        System.out.print("Nueva password (dejar vacío para no cambiar): ");
        String password = scanner.nextLine();
        if (!password.isEmpty()) usuario.setPasswordHash(password);

        boolean ok = dao.update(usuario);
        System.out.println(ok ? "Usuario actualizado." : "Error actualizando usuario.");
    }

    private static void eliminarUsuario(Scanner scanner, UsuarioDAO dao) {
        listarUsuarios(dao);
        System.out.print("ID usuario a eliminar: ");
        int id = leerInt(scanner);
        boolean ok = dao.delete(id);
        System.out.println(ok ? "Usuario eliminado." : "No se pudo eliminar.");
    }

}
package CafeteraApp.dao;

import CafeteraApp.model.Pedido;
import CafeteraApp.util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la entidad Pedido que implementa operaciones CRUD usando JDBC.
 */
public class PedidoDAO {

    /**
     * Inserta un nuevo pedido en la base de datos.
     */
    public boolean insert(Pedido pedido) {
        String sql = "INSERT INTO pedidos (id_productor, cliente_nombre, producto_cafe, cantidad_kg, precio_unitario, total_precio, fecha_pedido, estado_pedido) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, pedido.getIdProductor());
            pst.setString(2, pedido.getClienteNombre());
            pst.setString(3, pedido.getProductoCafe());
            pst.setDouble(4, pedido.getCantidadKg());
            pst.setDouble(5, pedido.getPrecioUnitario());
            pst.setDouble(6, pedido.getTotalPrecio());
            pst.setTimestamp(7, pedido.getFechaPedido());
            pst.setString(8, pedido.getEstadoPedido());

            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Error insertando Pedido: " + e.getMessage());
            return false;
        }
    }

    /**
     * Busca un Pedido por su ID.
     */
    public Pedido findById(int idPedido) {
        String sql = "SELECT * FROM pedidos WHERE id_pedido = ?";
        Pedido pedido = null;

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, idPedido);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    pedido = new Pedido();
                    pedido.setIdPedido(rs.getInt("id_pedido"));
                    pedido.setIdProductor(rs.getInt("id_productor"));
                    pedido.setClienteNombre(rs.getString("cliente_nombre"));
                    pedido.setProductoCafe(rs.getString("producto_cafe"));
                    pedido.setCantidadKg(rs.getDouble("cantidad_kg"));
                    pedido.setPrecioUnitario(rs.getDouble("precio_unitario"));
                    pedido.setTotalPrecio(rs.getDouble("total_precio"));
                    pedido.setFechaPedido(rs.getTimestamp("fecha_pedido"));
                    pedido.setEstadoPedido(rs.getString("estado_pedido"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error buscando Pedido: " + e.getMessage());
        }
        return pedido;
    }

    /**
     * Devuelve la lista de todos los pedidos.
     */
    public List<Pedido> findAll() {
        String sql = "SELECT * FROM pedidos";
        List<Pedido> pedidos = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(rs.getInt("id_pedido"));
                pedido.setIdProductor(rs.getInt("id_productor"));
                pedido.setClienteNombre(rs.getString("cliente_nombre"));
                pedido.setProductoCafe(rs.getString("producto_cafe"));
                pedido.setCantidadKg(rs.getDouble("cantidad_kg"));
                pedido.setPrecioUnitario(rs.getDouble("precio_unitario"));
                pedido.setTotalPrecio(rs.getDouble("total_precio"));
                pedido.setFechaPedido(rs.getTimestamp("fecha_pedido"));
                pedido.setEstadoPedido(rs.getString("estado_pedido"));
                pedidos.add(pedido);
            }

        } catch (SQLException e) {
            System.err.println("Error listando Pedidos: " + e.getMessage());
        }
        return pedidos;
    }

    /**
     * Actualiza un pedido existente.
     */
    public boolean update(Pedido pedido) {
        String sql = "UPDATE pedidos SET id_productor=?, cliente_nombre=?, producto_cafe=?, cantidad_kg=?, precio_unitario=?, total_precio=?, fecha_pedido=?, estado_pedido=? WHERE id_pedido=?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, pedido.getIdProductor());
            pst.setString(2, pedido.getClienteNombre());
            pst.setString(3, pedido.getProductoCafe());
            pst.setDouble(4, pedido.getCantidadKg());
            pst.setDouble(5, pedido.getPrecioUnitario());
            pst.setDouble(6, pedido.getTotalPrecio());
            pst.setTimestamp(7, pedido.getFechaPedido());
            pst.setString(8, pedido.getEstadoPedido());
            pst.setInt(9, pedido.getIdPedido());

            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Error actualizando Pedido: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un pedido por su ID.
     */
    public boolean delete(int idPedido) {
        String sql = "DELETE FROM pedidos WHERE id_pedido = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, idPedido);
            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Error eliminando Pedido: " + e.getMessage());
            return false;
        }
    }
}
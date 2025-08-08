package CafeteraApp.model;

import java.sql.Timestamp;

/**
 * Modelo que representa un pedido en la base de datos.
 */
public class Pedido {
    private int idPedido;
    private int idProductor;
    private String clienteNombre;
    private String productoCafe;
    private double cantidadKg;
    private double precioUnitario;
    private double totalPrecio;
    private Timestamp fechaPedido;
    private String estadoPedido;

    public Pedido() {
    }

    public Pedido(int idPedido, int idProductor, String clienteNombre, String productoCafe,
                  double cantidadKg, double precioUnitario, double totalPrecio,
                  Timestamp fechaPedido, String estadoPedido) {
        this.idPedido = idPedido;
        this.idProductor = idProductor;
        this.clienteNombre = clienteNombre;
        this.productoCafe = productoCafe;
        this.cantidadKg = cantidadKg;
        this.precioUnitario = precioUnitario;
        this.totalPrecio = totalPrecio;
        this.fechaPedido = fechaPedido;
        this.estadoPedido = estadoPedido;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdProductor() {
        return idProductor;
    }

    public void setIdProductor(int idProductor) {
        this.idProductor = idProductor;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public String getProductoCafe() {
        return productoCafe;
    }

    public void setProductoCafe(String productoCafe) {
        this.productoCafe = productoCafe;
    }

    public double getCantidadKg() {
        return cantidadKg;
    }

    public void setCantidadKg(double cantidadKg) {
        this.cantidadKg = cantidadKg;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getTotalPrecio() {
        return totalPrecio;
    }

    public void setTotalPrecio(double totalPrecio) {
        this.totalPrecio = totalPrecio;
    }

    public Timestamp getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Timestamp fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }
}
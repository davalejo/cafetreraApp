package CafeteraApp.model;

import java.sql.Timestamp;

/**
 * Modelo que representa un productor en la base de datos.
 */
public class Productor {
    private int idProductor;
    private String nombre;
    private String ubicacion;
    private String tipoCafe;
    private String certificaciones;
    private Timestamp fechaRegistro;

    public Productor() {
    }

    public Productor(int idProductor, String nombre, String ubicacion,
                     String tipoCafe, String certificaciones, Timestamp fechaRegistro) {
        this.idProductor = idProductor;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.tipoCafe = tipoCafe;
        this.certificaciones = certificaciones;
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdProductor() {
        return idProductor;
    }

    public void setIdProductor(int idProductor) {
        this.idProductor = idProductor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTipoCafe() {
        return tipoCafe;
    }

    public void setTipoCafe(String tipoCafe) {
        this.tipoCafe = tipoCafe;
    }

    public String getCertificaciones() {
        return certificaciones;
    }

    public void setCertificaciones(String certificaciones) {
        this.certificaciones = certificaciones;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
package CafeteraApp.model;

import java.sql.Timestamp;

/**
 * Modelo que representa un usuario en la base de datos.
 */
public class Usuario {
    private int idUsuario;
    private String username;
    private String passwordHash;
    private String rol;
    private Timestamp fechaCreacion;

    public Usuario() {
    }

    public Usuario(int idUsuario, String username, String passwordHash, String rol, Timestamp fechaCreacion) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.passwordHash = passwordHash;
        this.rol = rol;
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
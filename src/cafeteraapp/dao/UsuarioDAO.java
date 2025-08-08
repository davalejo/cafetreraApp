package CafeteraApp.dao;

import CafeteraApp.model.Usuario;
import CafeteraApp.util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la entidad Usuario que implementa operaciones CRUD usando JDBC.
 */
public class UsuarioDAO {

    public boolean insert(Usuario usuario) {
        String sql = "INSERT INTO usuarios (username, password_hash, rol, fecha_creacion) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, usuario.getUsername());
            pst.setString(2, usuario.getPasswordHash());
            pst.setString(3, usuario.getRol());
            pst.setTimestamp(4, usuario.getFechaCreacion());

            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Error insertando Usuario: " + e.getMessage());
            return false;
        }
    }

    public Usuario findById(int idUsuario) {
        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
        Usuario usuario = null;

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, idUsuario);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setUsername(rs.getString("username"));
                    usuario.setPasswordHash(rs.getString("password_hash"));
                    usuario.setRol(rs.getString("rol"));
                    usuario.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error buscando Usuario: " + e.getMessage());
        }
        return usuario;
    }

    public List<Usuario> findAll() {
        String sql = "SELECT * FROM usuarios";
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPasswordHash(rs.getString("password_hash"));
                usuario.setRol(rs.getString("rol"));
                usuario.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            System.err.println("Error listando Usuarios: " + e.getMessage());
        }
        return usuarios;
    }

    public boolean update(Usuario usuario) {
        String sql = "UPDATE usuarios SET username=?, password_hash=?, rol=?, fecha_creacion=? WHERE id_usuario=?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, usuario.getUsername());
            pst.setString(2, usuario.getPasswordHash());
            pst.setString(3, usuario.getRol());
            pst.setTimestamp(4, usuario.getFechaCreacion());
            pst.setInt(5, usuario.getIdUsuario());

            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Error actualizando Usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int idUsuario) {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, idUsuario);
            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Error eliminando Usuario: " + e.getMessage());
            return false;
        }
    }
}
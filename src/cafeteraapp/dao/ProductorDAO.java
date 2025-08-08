package CafeteraApp.dao;

import CafeteraApp.model.Productor;
import CafeteraApp.util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la entidad Productor que implementa operaciones CRUD usando JDBC.
 */
public class ProductorDAO {

    public boolean insert(Productor productor) {
        String sql = "INSERT INTO productores (nombre, ubicacion, tipo_cafe, certificaciones, fecha_registro) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, productor.getNombre());
            pst.setString(2, productor.getUbicacion());
            pst.setString(3, productor.getTipoCafe());
            pst.setString(4, productor.getCertificaciones());
            pst.setTimestamp(5, productor.getFechaRegistro());

            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Error insertando Productor: " + e.getMessage());
            return false;
        }
    }

    public Productor findById(int idProductor) {
        String sql = "SELECT * FROM productores WHERE id_productor = ?";
        Productor productor = null;

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, idProductor);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    productor = new Productor();
                    productor.setIdProductor(rs.getInt("id_productor"));
                    productor.setNombre(rs.getString("nombre"));
                    productor.setUbicacion(rs.getString("ubicacion"));
                    productor.setTipoCafe(rs.getString("tipo_cafe"));
                    productor.setCertificaciones(rs.getString("certificaciones"));
                    productor.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error buscando Productor: " + e.getMessage());
        }
        return productor;
    }

    public List<Productor> findAll() {
        String sql = "SELECT * FROM productores";
        List<Productor> productores = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Productor productor = new Productor();
                productor.setIdProductor(rs.getInt("id_productor"));
                productor.setNombre(rs.getString("nombre"));
                productor.setUbicacion(rs.getString("ubicacion"));
                productor.setTipoCafe(rs.getString("tipo_cafe"));
                productor.setCertificaciones(rs.getString("certificaciones"));
                productor.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                productores.add(productor);
            }

        } catch (SQLException e) {
            System.err.println("Error listando Productores: " + e.getMessage());
        }
        return productores;
    }

    public boolean update(Productor productor) {
        String sql = "UPDATE productores SET nombre=?, ubicacion=?, tipo_cafe=?, certificaciones=?, fecha_registro=? WHERE id_productor=?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, productor.getNombre());
            pst.setString(2, productor.getUbicacion());
            pst.setString(3, productor.getTipoCafe());
            pst.setString(4, productor.getCertificaciones());
            pst.setTimestamp(5, productor.getFechaRegistro());
            pst.setInt(6, productor.getIdProductor());

            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Error actualizando Productor: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int idProductor) {
        String sql = "DELETE FROM productores WHERE id_productor = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, idProductor);
            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Error eliminando Productor: " + e.getMessage());
            return false;
        }
    }
}
package dao;

import modelo.Repartidor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepartidorDAO {

    public List<Repartidor> listarTodos() {

        List<Repartidor> repartidores = new ArrayList<>();

        String sql = "SELECT id_repartidor, nombre FROM repartidor";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {

                int id = resultado.getInt("id_repartidor");
                String nombre = resultado.getString("nombre");

                Repartidor repartidor = new Repartidor(id, nombre);

                repartidores.add(repartidor);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar repartidores: " + e.getMessage());
        }

        return repartidores;
    }

    public boolean guardar(Repartidor repartidor) {

        String sql = "INSERT INTO repartidor (id_repartidor, nombre) VALUES (?, ?)";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, repartidor.getIdRepartidor());
            sentencia.setString(2, repartidor.getNombre());

            int filasAfectadas = sentencia.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Repartidor guardado correctamente.");
                return true;
            } else {
                System.out.println("No se pudo guardar el repartidor.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al guardar repartidor: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Repartidor repartidor) {

        String sql = "UPDATE repartidor SET nombre = ? WHERE id_repartidor = ?";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, repartidor.getNombre());
            sentencia.setInt(2, repartidor.getIdRepartidor());

            int filasAfectadas = sentencia.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Repartidor actualizado correctamente.");
                return true;
            } else {
                System.out.println("No se encontró el repartidor para actualizar.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar repartidor: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idRepartidor) {

        String sql = "DELETE FROM repartidor WHERE id_repartidor = ?";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, idRepartidor);

            int filasAfectadas = sentencia.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Repartidor eliminado correctamente.");
                return true;
            } else {
                System.out.println("No se encontró el repartidor para eliminar.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar repartidor: " + e.getMessage());
            return false;
        }
    }
}

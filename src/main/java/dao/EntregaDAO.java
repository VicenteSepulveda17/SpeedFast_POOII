package dao;

import modelo.Entrega;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntregaDAO {

    public boolean guardar(Entrega entrega) {

        String sql = "INSERT INTO entrega (id_pedido, id_repartidor, fecha, hora) VALUES (?, ?, ?, ?)";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(
                     sql,
                     Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, entrega.getIdPedido());
            ps.setInt(2, entrega.getIdRepartidor());
            ps.setDate(3, java.sql.Date.valueOf(entrega.getFecha()));
            ps.setTime(4, java.sql.Time.valueOf(entrega.getHora()));

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {

                try (ResultSet resultado = ps.getGeneratedKeys()) {
                    if (resultado.next()) {
                        entrega.setId(resultado.getInt(1));
                    }
                }

                System.out.println("Entrega guardada correctamente.");
                return true;

            } else {

                System.out.println("No se pudo guardar la entrega.");
                return false;
            }

        } catch (SQLException e) {

            System.out.println("Error al guardar entrega: " + e.getMessage());
            return false;
        }
    }


    public List<Entrega> listarTodos() {

        List<Entrega> entregas = new ArrayList<>();

        String sql = "SELECT * FROM entrega";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet resultado = ps.executeQuery()) {

            while (resultado.next()) {

                Entrega entrega = new Entrega(
                        resultado.getInt("id"),
                        resultado.getInt("id_pedido"),
                        resultado.getInt("id_repartidor"),
                        resultado.getDate("fecha").toLocalDate(),
                        resultado.getTime("hora").toLocalTime()
                );

                entregas.add(entrega);
            }
        } catch (SQLException e){
            System.out.println("Error al listar entregas: " + e.getMessage());
        }

        return entregas;

    }

    public boolean actualizar(Entrega entrega) {

        String sql = "UPDATE entrega SET id_pedido = ?, id_repartidor = ?, fecha = ?, hora = ? WHERE id = ?";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, entrega.getIdPedido());
            ps.setInt(2, entrega.getIdRepartidor());
            ps.setDate(3, java.sql.Date.valueOf(entrega.getFecha()));
            ps.setTime(4, java.sql.Time.valueOf(entrega.getHora()));
            ps.setInt(5, entrega.getId());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Entrega actualizada correctamente.");
                return true;
            } else {
                System.out.println("No se encontró la entrega para actualizar.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar entrega: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idEntrega) {

        String sql = "DELETE FROM entrega WHERE id = ?";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idEntrega);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Entrega eliminada correctamente.");
                return true;
            } else {
                System.out.println("No se encontró la entrega para eliminar.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar entrega: " + e.getMessage());
            return false;
        }
    }
}

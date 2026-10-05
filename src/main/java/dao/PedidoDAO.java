package dao;

import modelo.EstadoPedido;
import modelo.Pedido;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public boolean guardar (Pedido pedido){
        String sql = "INSERT INTO pedido (id_pedido, direccion, tipo, estado) VALUES (?, ?, ?, ?)";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, pedido.getId_pedido());
            sentencia.setString(2, pedido.getDireccion());
            sentencia.setString(3, pedido.getTipo());
            sentencia.setString(4, pedido.getEstado().name());

            int filasAfectadas = sentencia.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Pedido guardado correctamente.");
                return true;
            } else {
                System.out.println("No se pudo guardar el pedido.");
                return false;
            }

        }catch (SQLException e) {
            System.out.println("Error al guardar pedido." + e.getMessage());
            return false;
        }


    }

    public List<Pedido> listarTodos() {

        List<Pedido> pedidos = new ArrayList<>();

        String sql = "SELECT id_pedido, direccion, tipo, estado FROM pedido";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {

                int id = resultado.getInt("id_pedido");
                String direccion = resultado.getString("direccion");
                String tipo = resultado.getString("tipo");
                String estado = resultado.getString("estado");

                Pedido pedido = new Pedido(
                        id,
                        direccion,
                        tipo,
                        EstadoPedido.valueOf(estado)
                );

                pedidos.add(pedido);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar pedidos: " + e.getMessage());
        }

        return pedidos;
    }

    public boolean actualizar(Pedido pedido) {
        String sql = "UPDATE pedido SET direccion = ?, tipo = ?, estado = ? WHERE id_pedido = ?";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, pedido.getDireccion());
            sentencia.setString(2, pedido.getTipo());
            sentencia.setString(3, pedido.getEstado().name());
            sentencia.setInt(4, pedido.getId_pedido());

            int filasAfectadas = sentencia.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Pedido actualizado correctamente.");
                return true;
            } else {
                System.out.println("No se encontró el pedido para actualizar.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar pedido: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idPedido) {
        String sql = "DELETE FROM pedido WHERE id_pedido = ?";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, idPedido);

            int filasAfectadas = sentencia.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Pedido eliminado correctamente.");
                return true;
            } else {
                System.out.println("No se encontró el pedido para eliminar.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar pedido: " + e.getMessage());
            return false;
        }
    }
}

package dao;

import modelo.Pedido;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public void guardar (Pedido pedido){
        String sql = "INSERT INTO pedido (id_pedido, direccion, tipo, estado) VALUES (?, ?, ?, ?)";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, pedido.getId_pedido());
            sentencia.setString(2, pedido.getDireccion());
            sentencia.setString(3, pedido.getTipo());
            sentencia.setString(4, "PENDIENTE");

            sentencia.executeUpdate();

            System.out.println("Pedido guardado correctamente.");

        }catch (SQLException e) {
            System.out.println("Error al guardar pedido." + e.getMessage());
        }


    }

    public List<Pedido> listarTodos() {

        List<Pedido> pedidos = new ArrayList<>();

        String sql = "SELECT id_pedido, direccion, tipo FROM pedido";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {

                int id = resultado.getInt("id_pedido");
                String direccion = resultado.getString("direccion");
                String tipo = resultado.getString("tipo");

                Pedido pedido = new Pedido(id, direccion, tipo);

                pedidos.add(pedido);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar pedidos: " + e.getMessage());
        }

        return pedidos;
    }
}
